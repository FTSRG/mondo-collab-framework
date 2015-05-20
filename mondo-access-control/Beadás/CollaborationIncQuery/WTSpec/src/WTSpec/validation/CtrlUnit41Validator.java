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

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit41}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit41Validator {
	boolean validate();

	boolean validateInput__iInput1(WTCInput value);
	boolean validateInput__iInput2(WTCInput value);
	boolean validateInput__iInput3(WTCInput value);
	boolean validateOutput__oOutput(WTCOutput value);
	boolean validateParameter__pParam1(WTCParam value);
	boolean validateParameter__pParam2(WTCParam value);
	boolean validateParameter__pParam3(WTCParam value);
	boolean validateFault__fFault1(WTCFault value);
	boolean validateFault__fFault2(WTCFault value);
	boolean validateFault__fFault3(WTCFault value);
	boolean validateErrorReaction__eReaction1(WTCErrorReaction value);
	boolean validateErrorReaction__eReaction2(WTCErrorReaction value);
	boolean validateGuard__gGuardToMain(String value);
	boolean validateGuard__gGuardFromMain(String value);
	boolean validateGuard__gGuardToOn(String value);
	boolean validateGuard__gGuardToOff(String value);
}
