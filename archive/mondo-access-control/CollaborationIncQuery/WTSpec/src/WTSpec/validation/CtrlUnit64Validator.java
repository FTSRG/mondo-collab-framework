/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCErrorReaction;
import WTSpec.WTCOutput;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit64}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit64Validator {
	boolean validate();

	boolean validateOutput__oOutput(WTCOutput value);
	boolean validateErrorReaction__erErrorReaction(WTCErrorReaction value);
}
