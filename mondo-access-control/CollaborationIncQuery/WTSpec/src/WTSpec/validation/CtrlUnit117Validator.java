/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCInput;
import WTSpec.WTCOutput;
import WTSpec.WTCTimer;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit117}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit117Validator {
	boolean validate();

	boolean validateInput__iYawBrakeCommand(WTCInput value);
	boolean validateInput__iYawElectrobrakeStatus(WTCInput value);
	boolean validateInput__iYawHydraulicbrakeStatus(WTCInput value);
	boolean validateInput__iInhibitYaw(WTCInput value);
	boolean validateOutput__oYawBrakeStatus(WTCOutput value);
	boolean validateOutput__oYawElectrobrakeOrder(WTCOutput value);
	boolean validateOutput__oYawHydraulicbrakeOrder(WTCOutput value);
	boolean validateOutput__oBrakesSetProblem(WTCOutput value);
	boolean validateOutput__oBrakesLiftedProblem(WTCOutput value);
	boolean validateTimer__tYawBrakeActionsDelay(WTCTimer value);
}
