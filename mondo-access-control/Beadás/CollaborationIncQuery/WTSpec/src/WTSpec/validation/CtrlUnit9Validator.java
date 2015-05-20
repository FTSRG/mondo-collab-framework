/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCInput;
import WTSpec.WTCOutput;
import WTSpec.WTCParam;
import WTSpec.WTCTimer;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit9}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit9Validator {
	boolean validate();

	boolean validateInput__iValueToCheck(WTCInput value);
	boolean validateInput__iDontCheckInput(WTCInput value);
	boolean validateInput__iLimitValue(WTCInput value);
	boolean validateInput__iLimitReset(WTCInput value);
	boolean validateOutput__oLimitReached(WTCOutput value);
	boolean validateParameter__pLimitValue(WTCParam value);
	boolean validateParameter__pLimitReset(WTCParam value);
	boolean validateTimer__tDelay(WTCTimer value);
	boolean validateBhvParam__bpMode(String value);
	boolean validateBhvParam__bpLimitsType(String value);
}
