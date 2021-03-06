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
 * A sample validator interface for {@link WTSpec.CtrlUnit25}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit25Validator {
	boolean validate();

	boolean validateInput__iStatus(WTCInput value);
	boolean validateInput__iOverloadAlarm(WTCInput value);
	boolean validateInput__iAlarmCounter(WTCInput value);
	boolean validateOutput__oAlarmCounter(WTCOutput value);
	boolean validateFault__fOverload(WTCFault value);
	boolean validateTimer__tTimer(WTCTimer value);
	boolean validateTimer__tSyncDelay(WTCTimer value);
}
