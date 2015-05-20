/*
* generated by Xtext
*/
grammar InternalPolicy;

options {
	superClass=AbstractInternalContentAssistParser;
	
}

@lexer::header {
package hu.bme.mit.ftsrg.mondo.accesscontrol.policydsl.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package hu.bme.mit.ftsrg.mondo.accesscontrol.policydsl.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import hu.bme.mit.ftsrg.mondo.accesscontrol.policydsl.services.PolicyGrammarAccess;

}

@parser::members {
 
 	private PolicyGrammarAccess grammarAccess;
 	
    public void setGrammarAccess(PolicyGrammarAccess grammarAccess) {
    	this.grammarAccess = grammarAccess;
    }
    
    @Override
    protected Grammar getGrammar() {
    	return grammarAccess.getGrammar();
    }
    
    @Override
    protected String getValueForTokenName(String tokenName) {
    	return tokenName;
    }

}




// Entry rule entryRuleModel
entryRuleModel 
:
{ before(grammarAccess.getModelRule()); }
	 ruleModel
{ after(grammarAccess.getModelRule()); } 
	 EOF 
;

// Rule Model
ruleModel
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getModelAccess().getElementsAssignment()); }
(rule__Model__ElementsAssignment)*
{ after(grammarAccess.getModelAccess().getElementsAssignment()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRulePolicy
entryRulePolicy 
:
{ before(grammarAccess.getPolicyRule()); }
	 rulePolicy
{ after(grammarAccess.getPolicyRule()); } 
	 EOF 
;

// Rule Policy
rulePolicy
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getPolicyAccess().getGroup()); }
(rule__Policy__Group__0)
{ after(grammarAccess.getPolicyAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleAssociation
entryRuleAssociation 
:
{ before(grammarAccess.getAssociationRule()); }
	 ruleAssociation
{ after(grammarAccess.getAssociationRule()); } 
	 EOF 
;

// Rule Association
ruleAssociation
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getAssociationAccess().getGroup()); }
(rule__Association__Group__0)
{ after(grammarAccess.getAssociationAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleElements
entryRuleElements 
:
{ before(grammarAccess.getElementsRule()); }
	 ruleElements
{ after(grammarAccess.getElementsRule()); } 
	 EOF 
;

// Rule Elements
ruleElements
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getElementsAccess().getAlternatives()); }
(rule__Elements__Alternatives)
{ after(grammarAccess.getElementsAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}




// Rule PermissionType
rulePermissionType
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPermissionTypeAccess().getAlternatives()); }
(rule__PermissionType__Alternatives)
{ after(grammarAccess.getPermissionTypeAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Rule TargetType
ruleTargetType
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getTargetTypeAccess().getAlternatives()); }
(rule__TargetType__Alternatives)
{ after(grammarAccess.getTargetTypeAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}



rule__Elements__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getElementsAccess().getPolicyParserRuleCall_0()); }
	rulePolicy
{ after(grammarAccess.getElementsAccess().getPolicyParserRuleCall_0()); }
)

    |(
{ before(grammarAccess.getElementsAccess().getAssociationParserRuleCall_1()); }
	ruleAssociation
{ after(grammarAccess.getElementsAccess().getAssociationParserRuleCall_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__PermissionType__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPermissionTypeAccess().getUNSETEnumLiteralDeclaration_0()); }
(	'UNSET' 
)
{ after(grammarAccess.getPermissionTypeAccess().getUNSETEnumLiteralDeclaration_0()); }
)

    |(
{ before(grammarAccess.getPermissionTypeAccess().getDENYEnumLiteralDeclaration_1()); }
(	'DENY' 
)
{ after(grammarAccess.getPermissionTypeAccess().getDENYEnumLiteralDeclaration_1()); }
)

    |(
{ before(grammarAccess.getPermissionTypeAccess().getALLOWEnumLiteralDeclaration_2()); }
(	'ALLOW' 
)
{ after(grammarAccess.getPermissionTypeAccess().getALLOWEnumLiteralDeclaration_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__TargetType__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getTargetTypeAccess().getGROUPEnumLiteralDeclaration_0()); }
(	'GROUP' 
)
{ after(grammarAccess.getTargetTypeAccess().getGROUPEnumLiteralDeclaration_0()); }
)

    |(
{ before(grammarAccess.getTargetTypeAccess().getUSEREnumLiteralDeclaration_1()); }
(	'USER' 
)
{ after(grammarAccess.getTargetTypeAccess().getUSEREnumLiteralDeclaration_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}



rule__Policy__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__Group__0__Impl
	rule__Policy__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getPolicyKeyword_0()); }

	'Policy' 

{ after(grammarAccess.getPolicyAccess().getPolicyKeyword_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Policy__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__Group__1__Impl
	rule__Policy__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getNameAssignment_1()); }
(rule__Policy__NameAssignment_1)
{ after(grammarAccess.getPolicyAccess().getNameAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Policy__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getGroup_2()); }
(rule__Policy__Group_2__0)
{ after(grammarAccess.getPolicyAccess().getGroup_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__Policy__Group_2__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__Group_2__0__Impl
	rule__Policy__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__Group_2__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getLeftCurlyBracketKeyword_2_0()); }

	'{' 

{ after(grammarAccess.getPolicyAccess().getLeftCurlyBracketKeyword_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Policy__Group_2__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__Group_2__1__Impl
	rule__Policy__Group_2__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__Group_2__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1()); }
(rule__Policy__UnorderedGroup_2_1)
{ after(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Policy__Group_2__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__Group_2__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__Group_2__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getRightCurlyBracketKeyword_2_2()); }

	'}' 

{ after(grammarAccess.getPolicyAccess().getRightCurlyBracketKeyword_2_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__Policy__Group_2_1_0__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__Group_2_1_0__0__Impl
	rule__Policy__Group_2_1_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__Group_2_1_0__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getPermissionKeyword_2_1_0_0()); }

	'permission' 

{ after(grammarAccess.getPolicyAccess().getPermissionKeyword_2_1_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Policy__Group_2_1_0__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__Group_2_1_0__1__Impl
	rule__Policy__Group_2_1_0__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__Group_2_1_0__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getPermissionAssignment_2_1_0_1()); }
(rule__Policy__PermissionAssignment_2_1_0_1)
{ after(grammarAccess.getPolicyAccess().getPermissionAssignment_2_1_0_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Policy__Group_2_1_0__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__Group_2_1_0__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__Group_2_1_0__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getSemicolonKeyword_2_1_0_2()); }

	';' 

{ after(grammarAccess.getPolicyAccess().getSemicolonKeyword_2_1_0_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__Policy__Group_2_1_1__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__Group_2_1_1__0__Impl
	rule__Policy__Group_2_1_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__Group_2_1_1__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getQueryKeyword_2_1_1_0()); }

	'query' 

{ after(grammarAccess.getPolicyAccess().getQueryKeyword_2_1_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Policy__Group_2_1_1__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__Group_2_1_1__1__Impl
	rule__Policy__Group_2_1_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__Group_2_1_1__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getQueryAssignment_2_1_1_1()); }
(rule__Policy__QueryAssignment_2_1_1_1)
{ after(grammarAccess.getPolicyAccess().getQueryAssignment_2_1_1_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Policy__Group_2_1_1__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__Group_2_1_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__Group_2_1_1__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getSemicolonKeyword_2_1_1_2()); }

	';' 

{ after(grammarAccess.getPolicyAccess().getSemicolonKeyword_2_1_1_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__Policy__Group_2_1_2__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__Group_2_1_2__0__Impl
	rule__Policy__Group_2_1_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__Group_2_1_2__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getPatternKeyword_2_1_2_0()); }

	'pattern' 

{ after(grammarAccess.getPolicyAccess().getPatternKeyword_2_1_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Policy__Group_2_1_2__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__Group_2_1_2__1__Impl
	rule__Policy__Group_2_1_2__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__Group_2_1_2__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getPatternAssignment_2_1_2_1()); }
(rule__Policy__PatternAssignment_2_1_2_1)
{ after(grammarAccess.getPolicyAccess().getPatternAssignment_2_1_2_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Policy__Group_2_1_2__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__Group_2_1_2__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__Group_2_1_2__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getSemicolonKeyword_2_1_2_2()); }

	';' 

{ after(grammarAccess.getPolicyAccess().getSemicolonKeyword_2_1_2_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__Association__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group__0__Impl
	rule__Association__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getAssociationKeyword_0()); }

	'Association' 

{ after(grammarAccess.getAssociationAccess().getAssociationKeyword_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getGroup_1()); }
(rule__Association__Group_1__0)
{ after(grammarAccess.getAssociationAccess().getGroup_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__Association__Group_1__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1__0__Impl
	rule__Association__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getLeftCurlyBracketKeyword_1_0()); }

	'{' 

{ after(grammarAccess.getAssociationAccess().getLeftCurlyBracketKeyword_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group_1__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1__1__Impl
	rule__Association__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getTesztAssignment_1_1()); }
(rule__Association__TesztAssignment_1_1)?
{ after(grammarAccess.getAssociationAccess().getTesztAssignment_1_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group_1__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1__2__Impl
	rule__Association__Group_1__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2()); }
(rule__Association__UnorderedGroup_1_2)
{ after(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group_1__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getRightCurlyBracketKeyword_1_3()); }

	'}' 

{ after(grammarAccess.getAssociationAccess().getRightCurlyBracketKeyword_1_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}










rule__Association__Group_1_2_0__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_0__0__Impl
	rule__Association__Group_1_2_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_0__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getDefaultPermissionKeyword_1_2_0_0()); }

	'defaultPermission' 

{ after(grammarAccess.getAssociationAccess().getDefaultPermissionKeyword_1_2_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group_1_2_0__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_0__1__Impl
	rule__Association__Group_1_2_0__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_0__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getDefaultPermissionAssignment_1_2_0_1()); }
(rule__Association__DefaultPermissionAssignment_1_2_0_1)
{ after(grammarAccess.getAssociationAccess().getDefaultPermissionAssignment_1_2_0_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group_1_2_0__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_0__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_0__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getSemicolonKeyword_1_2_0_2()); }

	';' 

{ after(grammarAccess.getAssociationAccess().getSemicolonKeyword_1_2_0_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__Association__Group_1_2_1__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_1__0__Impl
	rule__Association__Group_1_2_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_1__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getOverrideKeyword_1_2_1_0()); }

	'override' 

{ after(grammarAccess.getAssociationAccess().getOverrideKeyword_1_2_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group_1_2_1__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_1__1__Impl
	rule__Association__Group_1_2_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_1__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getOverrideAssignment_1_2_1_1()); }
(rule__Association__OverrideAssignment_1_2_1_1)
{ after(grammarAccess.getAssociationAccess().getOverrideAssignment_1_2_1_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group_1_2_1__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_1__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getSemicolonKeyword_1_2_1_2()); }

	';' 

{ after(grammarAccess.getAssociationAccess().getSemicolonKeyword_1_2_1_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__Association__Group_1_2_2__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_2__0__Impl
	rule__Association__Group_1_2_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_2__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getTargetKeyword_1_2_2_0()); }

	'target' 

{ after(grammarAccess.getAssociationAccess().getTargetKeyword_1_2_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group_1_2_2__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_2__1__Impl
	rule__Association__Group_1_2_2__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_2__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getTargetAssignment_1_2_2_1()); }
(rule__Association__TargetAssignment_1_2_2_1)
{ after(grammarAccess.getAssociationAccess().getTargetAssignment_1_2_2_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group_1_2_2__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_2__2__Impl
	rule__Association__Group_1_2_2__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_2__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getFullStopKeyword_1_2_2_2()); }

	'.' 

{ after(grammarAccess.getAssociationAccess().getFullStopKeyword_1_2_2_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group_1_2_2__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_2__3__Impl
	rule__Association__Group_1_2_2__4
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_2__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getTarget_idAssignment_1_2_2_3()); }
(rule__Association__Target_idAssignment_1_2_2_3)
{ after(grammarAccess.getAssociationAccess().getTarget_idAssignment_1_2_2_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group_1_2_2__4
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_2__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_2__4__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getSemicolonKeyword_1_2_2_4()); }

	';' 

{ after(grammarAccess.getAssociationAccess().getSemicolonKeyword_1_2_2_4()); }
)

;
finally {
	restoreStackSize(stackSize);
}












rule__Association__Group_1_2_3__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_3__0__Impl
	rule__Association__Group_1_2_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_3__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getPoliciesKeyword_1_2_3_0()); }

	'policies' 

{ after(grammarAccess.getAssociationAccess().getPoliciesKeyword_1_2_3_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group_1_2_3__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_3__1__Impl
	rule__Association__Group_1_2_3__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_3__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getPoliciesAssignment_1_2_3_1()); }
(rule__Association__PoliciesAssignment_1_2_3_1)
{ after(grammarAccess.getAssociationAccess().getPoliciesAssignment_1_2_3_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group_1_2_3__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_3__2__Impl
	rule__Association__Group_1_2_3__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_3__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getGroup_1_2_3_2()); }
(rule__Association__Group_1_2_3_2__0)*
{ after(grammarAccess.getAssociationAccess().getGroup_1_2_3_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group_1_2_3__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_3__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_3__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getSemicolonKeyword_1_2_3_3()); }

	';' 

{ after(grammarAccess.getAssociationAccess().getSemicolonKeyword_1_2_3_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}










rule__Association__Group_1_2_3_2__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_3_2__0__Impl
	rule__Association__Group_1_2_3_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_3_2__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getCommaKeyword_1_2_3_2_0()); }

	',' 

{ after(grammarAccess.getAssociationAccess().getCommaKeyword_1_2_3_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Association__Group_1_2_3_2__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__Group_1_2_3_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Group_1_2_3_2__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getPoliciesAssignment_1_2_3_2_1()); }
(rule__Association__PoliciesAssignment_1_2_3_2_1)
{ after(grammarAccess.getAssociationAccess().getPoliciesAssignment_1_2_3_2_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}







rule__Policy__UnorderedGroup_2_1
    @init {
    	int stackSize = keepStackSize();
		getUnorderedGroupHelper().enter(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1());
    }
:
	rule__Policy__UnorderedGroup_2_1__0
	
	{getUnorderedGroupHelper().canLeave(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1())}?
	
;
finally {
	getUnorderedGroupHelper().leave(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1());
	restoreStackSize(stackSize);
}


rule__Policy__UnorderedGroup_2_1__Impl
	@init {
		int stackSize = keepStackSize();
		boolean selected = false;
    }
:
		(

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1(), 0)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1(), 0);
	 				}
	 				{
	 				  selected = true;
	 				}
					(
					
						{ before(grammarAccess.getPolicyAccess().getGroup_2_1_0()); }
						(rule__Policy__Group_2_1_0__0)
						{ after(grammarAccess.getPolicyAccess().getGroup_2_1_0()); }
					)
 				)
			)  |

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1(), 1)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1(), 1);
	 				}
	 				{
	 				  selected = true;
	 				}
					(
					
						{ before(grammarAccess.getPolicyAccess().getGroup_2_1_1()); }
						(rule__Policy__Group_2_1_1__0)
						{ after(grammarAccess.getPolicyAccess().getGroup_2_1_1()); }
					)
 				)
			)  |

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1(), 2)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1(), 2);
	 				}
	 				{
	 				  selected = true;
	 				}
					(
					
						{ before(grammarAccess.getPolicyAccess().getGroup_2_1_2()); }
						(rule__Policy__Group_2_1_2__0)
						{ after(grammarAccess.getPolicyAccess().getGroup_2_1_2()); }
					)
 				)
			)  

		)
;
finally {
	if (selected)
		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1());
	restoreStackSize(stackSize);
}


rule__Policy__UnorderedGroup_2_1__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__UnorderedGroup_2_1__Impl
	rule__Policy__UnorderedGroup_2_1__1?
;
finally {
	restoreStackSize(stackSize);
}


rule__Policy__UnorderedGroup_2_1__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__UnorderedGroup_2_1__Impl
	rule__Policy__UnorderedGroup_2_1__2?
;
finally {
	restoreStackSize(stackSize);
}


rule__Policy__UnorderedGroup_2_1__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Policy__UnorderedGroup_2_1__Impl
;
finally {
	restoreStackSize(stackSize);
}








rule__Association__UnorderedGroup_1_2
    @init {
    	int stackSize = keepStackSize();
		getUnorderedGroupHelper().enter(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2());
    }
:
	rule__Association__UnorderedGroup_1_2__0
	
	{getUnorderedGroupHelper().canLeave(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2())}?
	
;
finally {
	getUnorderedGroupHelper().leave(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2());
	restoreStackSize(stackSize);
}


rule__Association__UnorderedGroup_1_2__Impl
	@init {
		int stackSize = keepStackSize();
		boolean selected = false;
    }
:
		(

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 0)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 0);
	 				}
	 				{
	 				  selected = true;
	 				}
					(
					
						{ before(grammarAccess.getAssociationAccess().getGroup_1_2_0()); }
						(rule__Association__Group_1_2_0__0)
						{ after(grammarAccess.getAssociationAccess().getGroup_1_2_0()); }
					)
 				)
			)  |

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 1)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 1);
	 				}
	 				{
	 				  selected = true;
	 				}
					(
					
						{ before(grammarAccess.getAssociationAccess().getGroup_1_2_1()); }
						(rule__Association__Group_1_2_1__0)
						{ after(grammarAccess.getAssociationAccess().getGroup_1_2_1()); }
					)
 				)
			)  |

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 2)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 2);
	 				}
	 				{
	 				  selected = true;
	 				}
					(
					
						{ before(grammarAccess.getAssociationAccess().getGroup_1_2_2()); }
						(rule__Association__Group_1_2_2__0)
						{ after(grammarAccess.getAssociationAccess().getGroup_1_2_2()); }
					)
 				)
			)  |

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 3)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 3);
	 				}
	 				{
	 				  selected = true;
	 				}
					(
					
						{ before(grammarAccess.getAssociationAccess().getGroup_1_2_3()); }
						(rule__Association__Group_1_2_3__0)
						{ after(grammarAccess.getAssociationAccess().getGroup_1_2_3()); }
					)
 				)
			)  

		)
;
finally {
	if (selected)
		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2());
	restoreStackSize(stackSize);
}


rule__Association__UnorderedGroup_1_2__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__UnorderedGroup_1_2__Impl
	rule__Association__UnorderedGroup_1_2__1?
;
finally {
	restoreStackSize(stackSize);
}


rule__Association__UnorderedGroup_1_2__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__UnorderedGroup_1_2__Impl
	rule__Association__UnorderedGroup_1_2__2?
;
finally {
	restoreStackSize(stackSize);
}


rule__Association__UnorderedGroup_1_2__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__UnorderedGroup_1_2__Impl
	rule__Association__UnorderedGroup_1_2__3?
;
finally {
	restoreStackSize(stackSize);
}


rule__Association__UnorderedGroup_1_2__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Association__UnorderedGroup_1_2__Impl
;
finally {
	restoreStackSize(stackSize);
}










rule__Model__ElementsAssignment
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModelAccess().getElementsElementsParserRuleCall_0()); }
	ruleElements{ after(grammarAccess.getModelAccess().getElementsElementsParserRuleCall_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__NameAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getNameIDTerminalRuleCall_1_0()); }
	RULE_ID{ after(grammarAccess.getPolicyAccess().getNameIDTerminalRuleCall_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__PermissionAssignment_2_1_0_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getPermissionPermissionTypeEnumRuleCall_2_1_0_1_0()); }
	rulePermissionType{ after(grammarAccess.getPolicyAccess().getPermissionPermissionTypeEnumRuleCall_2_1_0_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__QueryAssignment_2_1_1_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getQuerySTRINGTerminalRuleCall_2_1_1_1_0()); }
	RULE_STRING{ after(grammarAccess.getPolicyAccess().getQuerySTRINGTerminalRuleCall_2_1_1_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Policy__PatternAssignment_2_1_2_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getPolicyAccess().getPatternSTRINGTerminalRuleCall_2_1_2_1_0()); }
	RULE_STRING{ after(grammarAccess.getPolicyAccess().getPatternSTRINGTerminalRuleCall_2_1_2_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Association__TesztAssignment_1_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getTesztSTRINGTerminalRuleCall_1_1_0()); }
	RULE_STRING{ after(grammarAccess.getAssociationAccess().getTesztSTRINGTerminalRuleCall_1_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Association__DefaultPermissionAssignment_1_2_0_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getDefaultPermissionPermissionTypeEnumRuleCall_1_2_0_1_0()); }
	rulePermissionType{ after(grammarAccess.getAssociationAccess().getDefaultPermissionPermissionTypeEnumRuleCall_1_2_0_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Association__OverrideAssignment_1_2_1_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getOverridePermissionTypeEnumRuleCall_1_2_1_1_0()); }
	rulePermissionType{ after(grammarAccess.getAssociationAccess().getOverridePermissionTypeEnumRuleCall_1_2_1_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Association__TargetAssignment_1_2_2_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getTargetTargetTypeEnumRuleCall_1_2_2_1_0()); }
	ruleTargetType{ after(grammarAccess.getAssociationAccess().getTargetTargetTypeEnumRuleCall_1_2_2_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Association__Target_idAssignment_1_2_2_3
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getTarget_idSTRINGTerminalRuleCall_1_2_2_3_0()); }
	RULE_STRING{ after(grammarAccess.getAssociationAccess().getTarget_idSTRINGTerminalRuleCall_1_2_2_3_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Association__PoliciesAssignment_1_2_3_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getPoliciesPolicyCrossReference_1_2_3_1_0()); }
(
{ before(grammarAccess.getAssociationAccess().getPoliciesPolicyIDTerminalRuleCall_1_2_3_1_0_1()); }
	RULE_ID{ after(grammarAccess.getAssociationAccess().getPoliciesPolicyIDTerminalRuleCall_1_2_3_1_0_1()); }
)
{ after(grammarAccess.getAssociationAccess().getPoliciesPolicyCrossReference_1_2_3_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Association__PoliciesAssignment_1_2_3_2_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAssociationAccess().getPoliciesPolicyCrossReference_1_2_3_2_1_0()); }
(
{ before(grammarAccess.getAssociationAccess().getPoliciesPolicyIDTerminalRuleCall_1_2_3_2_1_0_1()); }
	RULE_ID{ after(grammarAccess.getAssociationAccess().getPoliciesPolicyIDTerminalRuleCall_1_2_3_2_1_0_1()); }
)
{ after(grammarAccess.getAssociationAccess().getPoliciesPolicyCrossReference_1_2_3_2_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


