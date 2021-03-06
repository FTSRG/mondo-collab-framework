/*
* generated by Xtext
*/
grammar InternalPolicy;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package hu.bme.mit.ftsrg.mondo.accesscontrol.policydsl.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package hu.bme.mit.ftsrg.mondo.accesscontrol.policydsl.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import hu.bme.mit.ftsrg.mondo.accesscontrol.policydsl.services.PolicyGrammarAccess;

}

@parser::members {

 	private PolicyGrammarAccess grammarAccess;
 	
    public InternalPolicyParser(TokenStream input, PolicyGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "Model";	
   	}
   	
   	@Override
   	protected PolicyGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleModel
entryRuleModel returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getModelRule()); }
	 iv_ruleModel=ruleModel 
	 { $current=$iv_ruleModel.current; } 
	 EOF 
;

// Rule Model
ruleModel returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{ 
	        newCompositeNode(grammarAccess.getModelAccess().getElementsElementsParserRuleCall_0()); 
	    }
		lv_elements_0_0=ruleElements		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getModelRule());
	        }
       		add(
       			$current, 
       			"elements",
        		lv_elements_0_0, 
        		"Elements");
	        afterParserOrEnumRuleCall();
	    }

)
)*
;





// Entry rule entryRulePolicy
entryRulePolicy returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getPolicyRule()); }
	 iv_rulePolicy=rulePolicy 
	 { $current=$iv_rulePolicy.current; } 
	 EOF 
;

// Rule Policy
rulePolicy returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='Policy' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getPolicyAccess().getPolicyKeyword_0());
    }
(
(
		lv_name_1_0=RULE_ID
		{
			newLeafNode(lv_name_1_0, grammarAccess.getPolicyAccess().getNameIDTerminalRuleCall_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getPolicyRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"ID");
	    }

)
)(	otherlv_2='{' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getPolicyAccess().getLeftCurlyBracketKeyword_2_0());
    }
(

(
	{ 
	  getUnorderedGroupHelper().enter(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1());
	}
	(
		(

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1(), 0)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1(), 0);
	 				}
					({true}?=>(	otherlv_4='permission' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getPolicyAccess().getPermissionKeyword_2_1_0_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getPolicyAccess().getPermissionPermissionTypeEnumRuleCall_2_1_0_1_0()); 
	    }
		lv_permission_5_0=rulePermissionType		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getPolicyRule());
	        }
       		set(
       			$current, 
       			"permission",
        		lv_permission_5_0, 
        		"PermissionType");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_6=';' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getPolicyAccess().getSemicolonKeyword_2_1_0_2());
    }
))
					{ 
	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1());
	 				}
 				)
			)  |

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1(), 1)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1(), 1);
	 				}
					({true}?=>(	otherlv_7='query' 
    {
    	newLeafNode(otherlv_7, grammarAccess.getPolicyAccess().getQueryKeyword_2_1_1_0());
    }
(
(
		lv_query_8_0=RULE_STRING
		{
			newLeafNode(lv_query_8_0, grammarAccess.getPolicyAccess().getQuerySTRINGTerminalRuleCall_2_1_1_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getPolicyRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"query",
        		lv_query_8_0, 
        		"STRING");
	    }

)
)	otherlv_9=';' 
    {
    	newLeafNode(otherlv_9, grammarAccess.getPolicyAccess().getSemicolonKeyword_2_1_1_2());
    }
))
					{ 
	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1());
	 				}
 				)
			)  |

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1(), 2)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1(), 2);
	 				}
					({true}?=>(	otherlv_10='pattern' 
    {
    	newLeafNode(otherlv_10, grammarAccess.getPolicyAccess().getPatternKeyword_2_1_2_0());
    }
(
(
		lv_pattern_11_0=RULE_STRING
		{
			newLeafNode(lv_pattern_11_0, grammarAccess.getPolicyAccess().getPatternSTRINGTerminalRuleCall_2_1_2_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getPolicyRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"pattern",
        		lv_pattern_11_0, 
        		"STRING");
	    }

)
)	otherlv_12=';' 
    {
    	newLeafNode(otherlv_12, grammarAccess.getPolicyAccess().getSemicolonKeyword_2_1_2_2());
    }
))
					{ 
	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1());
	 				}
 				)
			)  

		)+
	  	{getUnorderedGroupHelper().canLeave(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1())}?	
	)
)
	{ 
	  getUnorderedGroupHelper().leave(grammarAccess.getPolicyAccess().getUnorderedGroup_2_1());
	}

)	otherlv_13='}' 
    {
    	newLeafNode(otherlv_13, grammarAccess.getPolicyAccess().getRightCurlyBracketKeyword_2_2());
    }
))
;





// Entry rule entryRuleAssociation
entryRuleAssociation returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getAssociationRule()); }
	 iv_ruleAssociation=ruleAssociation 
	 { $current=$iv_ruleAssociation.current; } 
	 EOF 
;

// Rule Association
ruleAssociation returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='Association' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getAssociationAccess().getAssociationKeyword_0());
    }
(	otherlv_1='{' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getAssociationAccess().getLeftCurlyBracketKeyword_1_0());
    }
(
(
		lv_teszt_2_0=RULE_STRING
		{
			newLeafNode(lv_teszt_2_0, grammarAccess.getAssociationAccess().getTesztSTRINGTerminalRuleCall_1_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAssociationRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"teszt",
        		lv_teszt_2_0, 
        		"STRING");
	    }

)
)?(

(
	{ 
	  getUnorderedGroupHelper().enter(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2());
	}
	(
		(

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 0)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 0);
	 				}
					({true}?=>(	otherlv_4='defaultPermission' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getAssociationAccess().getDefaultPermissionKeyword_1_2_0_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getAssociationAccess().getDefaultPermissionPermissionTypeEnumRuleCall_1_2_0_1_0()); 
	    }
		lv_defaultPermission_5_0=rulePermissionType		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getAssociationRule());
	        }
       		set(
       			$current, 
       			"defaultPermission",
        		lv_defaultPermission_5_0, 
        		"PermissionType");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_6=';' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getAssociationAccess().getSemicolonKeyword_1_2_0_2());
    }
))
					{ 
	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2());
	 				}
 				)
			)  |

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 1)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 1);
	 				}
					({true}?=>(	otherlv_7='override' 
    {
    	newLeafNode(otherlv_7, grammarAccess.getAssociationAccess().getOverrideKeyword_1_2_1_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getAssociationAccess().getOverridePermissionTypeEnumRuleCall_1_2_1_1_0()); 
	    }
		lv_override_8_0=rulePermissionType		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getAssociationRule());
	        }
       		set(
       			$current, 
       			"override",
        		lv_override_8_0, 
        		"PermissionType");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_9=';' 
    {
    	newLeafNode(otherlv_9, grammarAccess.getAssociationAccess().getSemicolonKeyword_1_2_1_2());
    }
))
					{ 
	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2());
	 				}
 				)
			)  |

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 2)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 2);
	 				}
					({true}?=>(	otherlv_10='target' 
    {
    	newLeafNode(otherlv_10, grammarAccess.getAssociationAccess().getTargetKeyword_1_2_2_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getAssociationAccess().getTargetTargetTypeEnumRuleCall_1_2_2_1_0()); 
	    }
		lv_target_11_0=ruleTargetType		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getAssociationRule());
	        }
       		set(
       			$current, 
       			"target",
        		lv_target_11_0, 
        		"TargetType");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_12='.' 
    {
    	newLeafNode(otherlv_12, grammarAccess.getAssociationAccess().getFullStopKeyword_1_2_2_2());
    }
(
(
		lv_target_id_13_0=RULE_STRING
		{
			newLeafNode(lv_target_id_13_0, grammarAccess.getAssociationAccess().getTarget_idSTRINGTerminalRuleCall_1_2_2_3_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getAssociationRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"target_id",
        		lv_target_id_13_0, 
        		"STRING");
	    }

)
)	otherlv_14=';' 
    {
    	newLeafNode(otherlv_14, grammarAccess.getAssociationAccess().getSemicolonKeyword_1_2_2_4());
    }
))
					{ 
	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2());
	 				}
 				)
			)  |

			( 
				{getUnorderedGroupHelper().canSelect(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 3)}?=>(
					{ 
	 				  getUnorderedGroupHelper().select(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2(), 3);
	 				}
					({true}?=>(	otherlv_15='policies' 
    {
    	newLeafNode(otherlv_15, grammarAccess.getAssociationAccess().getPoliciesKeyword_1_2_3_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getAssociationRule());
	        }
        }
	otherlv_16=RULE_ID
	{
		newLeafNode(otherlv_16, grammarAccess.getAssociationAccess().getPoliciesPolicyCrossReference_1_2_3_1_0()); 
	}

)
)(	otherlv_17=',' 
    {
    	newLeafNode(otherlv_17, grammarAccess.getAssociationAccess().getCommaKeyword_1_2_3_2_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getAssociationRule());
	        }
        }
	otherlv_18=RULE_ID
	{
		newLeafNode(otherlv_18, grammarAccess.getAssociationAccess().getPoliciesPolicyCrossReference_1_2_3_2_1_0()); 
	}

)
))*	otherlv_19=';' 
    {
    	newLeafNode(otherlv_19, grammarAccess.getAssociationAccess().getSemicolonKeyword_1_2_3_3());
    }
))
					{ 
	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2());
	 				}
 				)
			)  

		)+
	  	{getUnorderedGroupHelper().canLeave(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2())}?	
	)
)
	{ 
	  getUnorderedGroupHelper().leave(grammarAccess.getAssociationAccess().getUnorderedGroup_1_2());
	}

)	otherlv_20='}' 
    {
    	newLeafNode(otherlv_20, grammarAccess.getAssociationAccess().getRightCurlyBracketKeyword_1_3());
    }
))
;





// Entry rule entryRuleElements
entryRuleElements returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getElementsRule()); }
	 iv_ruleElements=ruleElements 
	 { $current=$iv_ruleElements.current; } 
	 EOF 
;

// Rule Elements
ruleElements returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getElementsAccess().getPolicyParserRuleCall_0()); 
    }
    this_Policy_0=rulePolicy
    { 
        $current = $this_Policy_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getElementsAccess().getAssociationParserRuleCall_1()); 
    }
    this_Association_1=ruleAssociation
    { 
        $current = $this_Association_1.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Rule PermissionType
rulePermissionType returns [Enumerator current=null] 
    @init { enterRule(); }
    @after { leaveRule(); }:
((	enumLiteral_0='UNSET' 
	{
        $current = grammarAccess.getPermissionTypeAccess().getUNSETEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_0, grammarAccess.getPermissionTypeAccess().getUNSETEnumLiteralDeclaration_0()); 
    }
)
    |(	enumLiteral_1='DENY' 
	{
        $current = grammarAccess.getPermissionTypeAccess().getDENYEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_1, grammarAccess.getPermissionTypeAccess().getDENYEnumLiteralDeclaration_1()); 
    }
)
    |(	enumLiteral_2='ALLOW' 
	{
        $current = grammarAccess.getPermissionTypeAccess().getALLOWEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_2, grammarAccess.getPermissionTypeAccess().getALLOWEnumLiteralDeclaration_2()); 
    }
));



// Rule TargetType
ruleTargetType returns [Enumerator current=null] 
    @init { enterRule(); }
    @after { leaveRule(); }:
((	enumLiteral_0='GROUP' 
	{
        $current = grammarAccess.getTargetTypeAccess().getGROUPEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_0, grammarAccess.getTargetTypeAccess().getGROUPEnumLiteralDeclaration_0()); 
    }
)
    |(	enumLiteral_1='USER' 
	{
        $current = grammarAccess.getTargetTypeAccess().getUSEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_1, grammarAccess.getTargetTypeAccess().getUSEREnumLiteralDeclaration_1()); 
    }
));



RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


