/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCErrorReaction;
import WTSpec.WTCFault;
import WTSpec.WTCInput;
import WTSpec.WTCOutput;
import WTSpec.WTCParam;
import WTSpec.WTCTimer;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit89}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit89Validator {
	boolean validate();

	boolean validateInput__iAngleReference1(WTCInput value);
	boolean validateInput__iAngleReference2(WTCInput value);
	boolean validateInput__iAngleReference3(WTCInput value);
	boolean validateInput__iSpeedReference(WTCInput value);
	boolean validateInput__iAccelerationReference(WTCInput value);
	boolean validateInput__iPitchManualControl(WTCInput value);
	boolean validateInput__iPitchManualAngle(WTCInput value);
	boolean validateInput__iMaintenanceStatus(WTCInput value);
	boolean validateInput__iGeneratorSpeed(WTCInput value);
	boolean validateInput__iPitchBraked(WTCInput value);
	boolean validateOutput__oPitchAngleRef1(WTCOutput value);
	boolean validateOutput__oPitchAngleRef2(WTCOutput value);
	boolean validateOutput__oPitchAngleRef3(WTCOutput value);
	boolean validateOutput__oPitchSpeedRef(WTCOutput value);
	boolean validateOutput__oPitchAccelerationRef(WTCOutput value);
	boolean validateOutput__oPitchStopCommand(WTCOutput value);
	boolean validateParameter__pGeneratorSpeed(WTCParam value);
	boolean validateParameter__pPitchBrakeAngle(WTCParam value);
	boolean validateParameter__pPitchStdSpeed(WTCParam value);
	boolean validateParameter__pPitchStdAcceleration(WTCParam value);
	boolean validateFault__fManualStop(WTCFault value);
	boolean validateTimer__tMaxStopOperationLength(WTCTimer value);
	boolean validateErrorReaction__erStop(WTCErrorReaction value);
}