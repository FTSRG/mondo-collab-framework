/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCFault;
import WTSpec.WTCInput;
import WTSpec.WTCOutput;
import WTSpec.WTCParam;
import WTSpec.WTCTimer;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit133}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit133Validator {
	boolean validate();

	boolean validateInput__iYawStatus(WTCInput value);
	boolean validateInput__iYawCurrent1(WTCInput value);
	boolean validateInput__iYawCurrent2(WTCInput value);
	boolean validateInput__iYawCurrent3(WTCInput value);
	boolean validateInput__iYawCurrent4(WTCInput value);
	boolean validateInput__iOverloadAlarmCounter(WTCInput value);
	boolean validateOutput__oOverloadAlarmCounter(WTCOutput value);
	boolean validateParameter__pMaximumTorque(WTCParam value);
	boolean validateParameter__pMinimumTorque(WTCParam value);
	boolean validateParameter__pTorqueDifferenceLimit(WTCParam value);
	boolean validateFault__fFault(WTCFault value);
	boolean validateTimer__tTimer(WTCTimer value);
}
