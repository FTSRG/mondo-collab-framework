/*
 * generated by Xtext
 */
package org.mondo.collaboration.security.macl.xtext.rule.scoping

import org.mondo.collaboration.security.macl.xtext.rule.mACLRule.Binding
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.scoping.Scopes
import org.mondo.collaboration.security.macl.xtext.rule.mACLRule.Rule
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.common.util.URI
import org.eclipse.viatra.query.patternlanguage.patternLanguage.Pattern

/**
 * This class contains custom scoping description.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 *
 */
class MACLRuleScopeProvider extends org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider {

	def scope_Binding_param(Binding binding, EReference ref) {
		val rule = binding.eContainer as Rule
		
		return Scopes::scopeFor(rule.pattern.parameters)
	}	
	
	def scope_Rule_pattern(Rule rule, EReference ref) {
		return Scopes::scopeFor(patterns(rule.eResource))
	}

	def patterns(Resource ruleResource) {
		val ruleURI = ruleResource.URI.toString
		val queryURI = ruleURI.replace("rules.maclr", "queries.eiq")
		
		val queryResource = ruleResource.resourceSet.getResource(URI.createURI(queryURI),true)
		val patterns = queryResource.allContents.filter(Pattern).toList
		return patterns
	}

}
