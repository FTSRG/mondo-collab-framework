/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCFault;
import WTSpec.WTCInput;
import WTSpec.WTCOutput;
import WTSpec.WTCTimer;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit35}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit35Validator {
	boolean validate();

	boolean validateInput__iFeedback(WTCInput value);
	boolean validateOutput__oOutput(WTCOutput value);
	boolean validateFault__fFaultOn(WTCFault value);
	boolean validateFault__fFaultOff(WTCFault value);
	boolean validateTimer__tDelayOn(WTCTimer value);
	boolean validateTimer__tDelayOff(WTCTimer value);
	boolean validateBhvParam__bpLogicType(String value);
}
