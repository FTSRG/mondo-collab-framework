/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCInput;
import WTSpec.WTCOutput;
import WTSpec.WTCParam;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit100}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit100Validator {
	boolean validate();

	boolean validateInput__iPinPosition(WTCInput value);
	boolean validateOutput__oPinSafetyCmd(WTCOutput value);
	boolean validateParameter__pPinNoBackPos(WTCParam value);
	boolean validateParameter__pPinExtendedPos(WTCParam value);
	boolean validateParameter__pPinRetractedPos(WTCParam value);
	boolean validateParameter__pPinHysteresis(WTCParam value);
	boolean validateBhvParam__bpMode(String value);
}
