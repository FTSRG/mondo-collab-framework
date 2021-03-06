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
 * A sample validator interface for {@link WTSpec.CtrlUnit18}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit18Validator {
	boolean validate();

	boolean validateInput__iOrderCw(WTCInput value);
	boolean validateInput__iOrderCcw(WTCInput value);
	boolean validateInput__iFault(WTCInput value);
	boolean validateInput__iCmdDisable(WTCInput value);
	boolean validateInput__iCmdReset(WTCInput value);
	boolean validateInput__iStaReadyToOn(WTCInput value);
	boolean validateOutput__oControlWord(WTCOutput value);
	boolean validateParameter__pCmdDisabled(WTCParam value);
	boolean validateParameter__pCmdEnabled(WTCParam value);
	boolean validateParameter__pCmdCw(WTCParam value);
	boolean validateParameter__pCmdCcw(WTCParam value);
	boolean validateParameter__pCmdReset(WTCParam value);
	boolean validateParameter__pCmdSwitchOn(WTCParam value);
	boolean validateParameter__pCmdBraking(WTCParam value);
	boolean validateTimer__tDelay(WTCTimer value);
}
