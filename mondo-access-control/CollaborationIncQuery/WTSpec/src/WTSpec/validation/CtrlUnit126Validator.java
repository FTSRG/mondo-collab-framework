/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCInput;
import WTSpec.WTCOutput;
import WTSpec.WTCParam;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit126}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit126Validator {
	boolean validate();

	boolean validateInput__iTorqueVal(WTCInput value);
	boolean validateInput__iInvRol(WTCInput value);
	boolean validateOutput__oMaxTorque(WTCOutput value);
	boolean validateOutput__oMinTorque(WTCOutput value);
	boolean validateParameter__pMaxTorque(WTCParam value);
	boolean validateParameter__pMinTorque(WTCParam value);
}
