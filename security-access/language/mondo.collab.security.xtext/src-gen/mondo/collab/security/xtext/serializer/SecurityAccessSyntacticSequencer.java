/*
 * generated by Xtext
 */
package mondo.collab.security.xtext.serializer;

import com.google.inject.Inject;
import java.util.List;
import mondo.collab.security.xtext.services.SecurityAccessGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public class SecurityAccessSyntacticSequencer extends AbstractSyntacticSequencer {

	protected SecurityAccessGrammarAccess grammarAccess;
	protected AbstractElementAlias match_ImportResource_SemicolonKeyword_2_q;
	protected AbstractElementAlias match_Rule_SemicolonKeyword_7_q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (SecurityAccessGrammarAccess) access;
		match_ImportResource_SemicolonKeyword_2_q = new TokenAlias(false, true, grammarAccess.getImportResourceAccess().getSemicolonKeyword_2());
		match_Rule_SemicolonKeyword_7_q = new TokenAlias(false, true, grammarAccess.getRuleAccess().getSemicolonKeyword_7());
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		return "";
	}
	
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if(match_ImportResource_SemicolonKeyword_2_q.equals(syntax))
				emit_ImportResource_SemicolonKeyword_2_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_Rule_SemicolonKeyword_7_q.equals(syntax))
				emit_Rule_SemicolonKeyword_7_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Ambiguous syntax:
	 *     ';'?
	 *
	 * This ambiguous syntax occurs at:
	 *     importURI=STRING (ambiguity) (rule end)
	 */
	protected void emit_ImportResource_SemicolonKeyword_2_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ';'?
	 *
	 * This ambiguous syntax occurs at:
	 *     pattern=[Pattern|STRING] (ambiguity) '}' (rule end)
	 *     pattern=[Pattern|STRING] (ambiguity) bindings+=Binding
	 */
	protected void emit_Rule_SemicolonKeyword_7_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}