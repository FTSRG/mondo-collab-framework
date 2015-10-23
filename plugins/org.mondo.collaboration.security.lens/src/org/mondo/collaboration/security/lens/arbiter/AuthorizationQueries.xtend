/*******************************************************************************
 * Copyright (c) 2004-2015 Gabor Bergmann and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gabor Bergmann - initial API and implementation
 *******************************************************************************/

package org.mondo.collaboration.security.lens.arbiter

import com.google.common.collect.ImmutableSet
import com.google.common.collect.Iterables
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.emf.ecore.EReference
import org.eclipse.incquery.runtime.api.IQuerySpecification
import org.eclipse.incquery.runtime.matchers.psystem.IExpressionEvaluator
import org.eclipse.incquery.runtime.matchers.psystem.IValueProvider
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExpressionEvaluation
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.ConstantValue
import org.eclipse.xtend.lib.annotations.Accessors
import org.mondo.collaboration.security.lens.arbiter.Asset.AttributeAsset
import org.mondo.collaboration.security.lens.arbiter.Asset.ObjectAsset
import org.mondo.collaboration.security.lens.arbiter.Asset.ReferenceAsset
import org.mondo.collaboration.security.lens.arbiter.SecurityArbiter.OperationKind
import org.mondo.collaboration.security.lens.context.keys.EObjectAttributeKey
import org.mondo.collaboration.security.lens.context.keys.EObjectReferenceKey
import org.mondo.collaboration.security.lens.context.keys.SecurityJudgementKey
import org.mondo.collaboration.security.lens.relational.QueryTemplate
import org.mondo.collaboration.security.lens.util.RuleGeneratorExtensions
import org.mondo.collaboration.security.macl.xtext.rule.mACLRule.User
import org.mondo.collaboration.security.lens.util.EnumWrapper
import java.util.Collections

/**
 * Queries for security checks.
 * @author Bergmann Gabor
 *
 */
class AuthorizationQueries extends AbstractAuthorizationQueries {
	new(User user) {
		super(user)
	}
	
	extension RuleGeneratorExtensions extendUtil = RuleGeneratorExtensions::INSTANCE
	
	@Accessors(PUBLIC_GETTER) val String fullyQualifiedName = 
		'''«AuthorizationQueries.canonicalName».{user='«user.name»'}'''
		
	@Accessors(PUBLIC_GETTER) 
	val Map<Class<? extends Asset>, Map<OperationKind, IQuerySpecification>> explicitDenialQuery = #{
		prepareExplicitlyDeniedHelperPatterns(ObjectAsset, varEObject),
		prepareExplicitlyDeniedHelperPatterns(ReferenceAsset, varSrc, varEReference, varTrg),
		prepareExplicitlyDeniedHelperPatterns(AttributeAsset, varEObject, varEAttribute)
	}
	@Accessors(PUBLIC_GETTER) 
	val explicitObfuscateQuery = 
		prepareExplicitRuleDecisionHelperPattern(OperationKind::READ, AttributeAsset, AccessControlVerdict::OBFUSCATED, varEObject, varEAttribute)
		
		
	private def Pair<Class<? extends Asset>, Map<OperationKind, IQuerySpecification>> prepareExplicitlyDeniedHelperPatterns(Class<? extends Asset> assetClass, String... assetVariables) {
		val x = EnumWrapper.myValues
		assetClass -> Collections::unmodifiableMap(newHashMap(x.map[ op |
			op -> op.prepareExplicitRuleDecisionHelperPattern(assetClass, AccessControlVerdict::DENIED, assetVariables)
		]))
	}
	
	private def IQuerySpecification prepareExplicitRuleDecisionHelperPattern(OperationKind op, Class<? extends Asset> assetClass, AccessControlVerdict judgement, String... assetVariables) {
		composeQuery('''«fullyQualifiedName».«op».explicitly.«judgement».«assetClass.simpleName»''', 
			#[QueryTemplate::fromConstrainer(assetVariables)[ body |
				typeConstraint(new SecurityJudgementKey(op, assetClass), Iterables::concat(assetVariables, #[varUser, varJudgement])).apply(body)
				new ConstantValue(body, body.getOrCreateVariableByName(varUser), user)			
				new ConstantValue(body, body.getOrCreateVariableByName(varJudgement), judgement)
			]]
		)
	}
	
	
	private def goldContainmentReference(List<String> variableNames) {
		filteredGoldReference(variableNames, "containment") [isContainment]
	}
	private def filteredGoldReference(List<String> variableNames, String checkDescription, (EReference) => Boolean referenceFilter) {
		QueryTemplate::fromConstrainer(variableNames) [ body |
			typeConstraint(EObjectReferenceKey.GOLD, variableNames).apply(body)
			
			val referenceVariableName = variableNames.get(1)
			new ExpressionEvaluation(body, new IExpressionEvaluator(){
				override evaluateExpression(IValueProvider provider) throws Exception {
					val feature = provider.getValue(referenceVariableName)
					if (feature instanceof EReference) {
						return referenceFilter.apply(feature)
					} else return false;
				}
				override getInputParameterNames() {
					#[referenceVariableName]
				}
				override getShortDescription() {
					'''Check that «referenceVariableName» is a «checkDescription» reference'''
				}
			}, null)
		]
	}
	 	
	private val IQuerySpecification effectivelyHiddenObject = 
		composeDisjunctiveQuery('''«fullyQualifiedName».«OperationKind::READ».effectivelyDenied.«ObjectAsset.simpleName»''',
			#[
				explicitDenialQuery.get(ObjectAsset).get(OperationKind::READ)
					.positiveCall(#{varEObject -> varEObject})
			], #[
				explicitDenialQuery.get(ReferenceAsset).get(OperationKind::READ)
					.positiveCall(#{varSrc -> varContainer, varEReference -> varEReference, varTrg -> varEObject}),
				goldContainmentReference(#[varContainer, varEReference, varEObject])
			], #[
				positiveRecursiveCall(#{varEObject -> varContainer}),
				goldContainmentReference(#[varContainer, varEReference, varEObject])
			]
		)	

	private val IQuerySpecification effectivelyFrozenObject = composeDisjunctiveQuery('''«fullyQualifiedName».«OperationKind::WRITE».effectivelyDenied.«ObjectAsset.simpleName»''',
			#[
				explicitDenialQuery.get(ObjectAsset).get(OperationKind::WRITE).positiveCall(#{varEObject -> varEObject})
			], #[
				effectivelyHiddenObject.positiveCallKeepNames
			]
		)
		
	private val IQuerySpecification effectivelyHiddenAttribute = 
		composeDisjunctiveQuery('''«fullyQualifiedName».«OperationKind::READ».effectivelyDenied.«AttributeAsset.simpleName»''',
			#[
				explicitDenialQuery.get(AttributeAsset).get(OperationKind::READ)
					.positiveCall(#{varEObject -> varEObject, varEAttribute -> varEAttribute})
			], #[
				effectivelyHiddenObject.positiveCall(#{varEObject -> varEObject}),
				typeConstraint(EObjectAttributeKey.GOLD, #[varEObject, varEAttribute, varValue])
			]
		)	
	private val IQuerySpecification effectivelyFrozenAttribute = 
		composeDisjunctiveQuery('''«fullyQualifiedName».«OperationKind::WRITE».effectivelyDenied.«AttributeAsset.simpleName»''',
			#[
				explicitDenialQuery.get(AttributeAsset).get(OperationKind::WRITE)
					.positiveCall(#{varEObject -> varEObject, varEAttribute -> varEAttribute})
			], #[
				effectivelyHiddenAttribute.positiveCall(#{varEObject -> varEObject, varEAttribute -> varEAttribute})
			]
		)	
	@Accessors(PUBLIC_GETTER) 
	private val IQuerySpecification effectivelyObfuscatedAttribute = 
		composeQuery('''«fullyQualifiedName».«OperationKind::READ».effectivelyObfuscated.«AttributeAsset.simpleName»''',
			#[
				effectivelyHiddenAttribute
					.negativeCall(#{varEObject -> varEObject, varEAttribute -> varEAttribute}),
				explicitObfuscateQuery.positiveCall(#{varEObject -> varEObject, varEAttribute -> varEAttribute})
			]
		)	
		
	private val IQuerySpecification effectivelyHiddenReference = 
		composeDisjunctiveQuery('''«fullyQualifiedName».«OperationKind::READ».effectivelyDenied.«ReferenceAsset.simpleName»''',
			#[
				explicitDenialQuery.get(ReferenceAsset).get(OperationKind::READ)
					.positiveCall(#{varSrc -> varSrc, varEReference -> varEReference, varTrg -> varTrg})
			], #[
				effectivelyHiddenObject.positiveCall(#{varEObject -> varSrc}),
				typeConstraint(EObjectReferenceKey.GOLD, #[varSrc, varEReference, varTrg])
			], #[
				effectivelyHiddenObject.positiveCall(#{varEObject -> varTrg}),
				typeConstraint(EObjectReferenceKey.GOLD, #[varSrc, varEReference, varTrg])
			]
		)	
		
	private val IQuerySpecification effectivelyFrozenReference = 
		composeDisjunctiveQuery('''«fullyQualifiedName».«OperationKind::WRITE».effectivelyDenied.«ReferenceAsset.simpleName»''',
			#[
				explicitDenialQuery.get(ReferenceAsset).get(OperationKind::WRITE)
					.positiveCall(#{varSrc -> varSrc, varEReference -> varEReference, varTrg -> varTrg})
			], #[
				effectivelyHiddenReference.positiveCall(#{varSrc -> varSrc, varEReference -> varEReference, varTrg -> varTrg})
			], #[
				effectivelyFrozenObject.positiveCall(#{varEObject -> varSrc}),
				typeConstraint(EObjectReferenceKey.GOLD, #[varSrc, varEReference, varTrg])
			], #[
				effectivelyFrozenObject.positiveCall(#{varEObject -> varTrg}),
				filteredGoldReference(#[varSrc, varEReference, varTrg], "containment or reverse navigable") [isContainment || (EOpposite != null)]
			]
		)	
		
		
	@Accessors(PUBLIC_GETTER) 
	val Map<Class<? extends Asset>, Map<OperationKind, IQuerySpecification>> effectivelyDeniedQuery = #{
		ObjectAsset -> #{
			OperationKind::READ -> effectivelyHiddenObject,
			OperationKind::WRITE -> effectivelyFrozenObject
		},
		AttributeAsset -> #{
			OperationKind::READ -> effectivelyHiddenAttribute,
			OperationKind::WRITE -> effectivelyFrozenAttribute
		},
		ReferenceAsset -> #{
			OperationKind::READ -> effectivelyHiddenReference,
			OperationKind::WRITE -> effectivelyFrozenReference
		}
	}
		
		
	@Accessors(PUBLIC_GETTER)
	val Set<IQuerySpecification> allQueries = ImmutableSet::copyOf(Iterables::concat(
		Iterables::concat(explicitDenialQuery.values.map[values]),
		Iterables::concat(effectivelyDeniedQuery.values.map[values]),
		ImmutableSet::of(explicitObfuscateQuery, effectivelyObfuscatedAttribute)
	))
	 
		
			

	public static val varEObject = "eObject"
	public static val varSrc = "src"
	public static val varTrg = "trg"
	public static val varContainer = "container"
	public static val varValue = "value"
	
	public static val varJudgement = "judgement"
	public static val varUser = "user"
	
	public static val varEReference = "eReference"
	public static val varEAttribute = "eAttribute"
	
}

class AbstractAuthorizationQueries {
	@Accessors(PUBLIC_GETTER) User user
	
	package new(User user) {
		this.user = user
	}
}