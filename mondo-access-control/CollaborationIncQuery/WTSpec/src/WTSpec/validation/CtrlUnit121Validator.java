/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCInput;
import WTSpec.WTCOutput;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit121}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit121Validator {
	boolean validate();

	boolean validateInput__iManualYawPreCommand(WTCInput value);
	boolean validateInput__iMaxTwistPreCommand(WTCInput value);
	boolean validateInput__iRetwistPreCommand(WTCInput value);
	boolean validateInput__iRelWindPreCommand(WTCInput value);
	boolean validateOutput__oYawCommand(WTCOutput value);
}
