/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCInput;
import WTSpec.WTCOutput;
import WTSpec.WTCParam;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit63}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit63Validator {
	boolean validate();

	boolean validateInput__iAzimuth(WTCInput value);
	boolean validateInput__iOrder(WTCInput value);
	boolean validateInput__iTargetHole(WTCInput value);
	boolean validateInput__iInvReadyRef(WTCInput value);
	boolean validateInput__iEncoderSpeed(WTCInput value);
	boolean validateInput__iInductiveSensor1(WTCInput value);
	boolean validateInput__iInductiveSensor2(WTCInput value);
	boolean validateOutput__oMotorTorque(WTCOutput value);
	boolean validateOutput__oMotorSense(WTCOutput value);
	boolean validateOutput__oFlIntoPosition(WTCOutput value);
	boolean validateOutput__oStatusFromDll(WTCOutput value);
	boolean validateOutput__oPositionSetpoint(WTCOutput value);
	boolean validateOutput__oTorqueAve(WTCOutput value);
	boolean validateOutput__oErrorAve(WTCOutput value);
	boolean validateOutput__oSpeedAve(WTCOutput value);
	boolean validateOutput__oWref(WTCOutput value);
	boolean validateOutput__oTargetPosition(WTCOutput value);
	boolean validateOutput__oKeepState(WTCOutput value);
	boolean validateOutput__oSeekState(WTCOutput value);
	boolean validateOutput__oMotorize(WTCOutput value);
	boolean validateOutput__oRealObjetive(WTCOutput value);
	boolean validateOutput__oControlMode(WTCOutput value);
	boolean validateParameter__pOffset(WTCParam value);
	boolean validateParameter__pEncoderOffset(WTCParam value);
	boolean validateParameter__pNextHoleDegrees(WTCParam value);
	boolean validateParameter__pPosRate1(WTCParam value);
	boolean validateParameter__pPosRate2(WTCParam value);
	boolean validateParameter__pMaxSpeed(WTCParam value);
	boolean validateParameter__pSampleTime(WTCParam value);
	boolean validateParameter__pMaxTorque(WTCParam value);
	boolean validateParameter__pAverageTime(WTCParam value);
	boolean validateParameter__pSpeedAverageTime(WTCParam value);
	boolean validateParameter__pMaxAcceleration(WTCParam value);
	boolean validateParameter__pKpp(WTCParam value);
	boolean validateParameter__pKps(WTCParam value);
	boolean validateParameter__pKis(WTCParam value);
	boolean validateParameter__pFilterOK(WTCParam value);
	boolean validateParameter__pFilterFreq(WTCParam value);
	boolean validateParameter__pFilterDamp(WTCParam value);
	boolean validateParameter__pKp2(WTCParam value);
	boolean validateParameter__pKp1(WTCParam value);
	boolean validateParameter__pKi1(WTCParam value);
	boolean validateParameter__pKi2(WTCParam value);
	boolean validateParameter__pPosBand(WTCParam value);
	boolean validateBhvParam__bpControlMode(String value);
}
