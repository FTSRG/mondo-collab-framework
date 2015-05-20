/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCInput;
import WTSpec.WTCOutput;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit83}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit83Validator {
	boolean validate();

	boolean validateInput__iPinSafetyCmd(WTCInput value);
	boolean validateInput__iPinAutoCmd(WTCInput value);
	boolean validateInput__iPinManualCmd(WTCInput value);
	boolean validateInput__iLockingSet(WTCInput value);
	boolean validateInput__iManualMode(WTCInput value);
	boolean validateInput__iSafetyBlock(WTCInput value);
	boolean validateOutput__oPinEnable(WTCOutput value);
	boolean validateOutput__oPinExtend(WTCOutput value);
	boolean validateOutput__oPinRetract(WTCOutput value);
}
