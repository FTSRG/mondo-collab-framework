/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCInput;
import WTSpec.WTCOutput;
import WTSpec.WTCParam;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit113}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit113Validator {
	boolean validate();

	boolean validateInput__iWindSpeed(WTCInput value);
	boolean validateInput__iWindSpeedAverage(WTCInput value);
	boolean validateOutput__oExcessiveWindAcceleration(WTCOutput value);
	boolean validateParameter__pConsideredTime(WTCParam value);
	boolean validateParameter__pWindAccelerationLimit(WTCParam value);
	boolean validateParameter__pHiWindLimitReset(WTCParam value);
	boolean validateParameter__pMaxAbsoluteWindSpeed(WTCParam value);
}
