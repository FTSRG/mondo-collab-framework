/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCInput;
import WTSpec.WTCOutput;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit68}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit68Validator {
	boolean validate();

	boolean validateInput__iValue1(WTCInput value);
	boolean validateInput__iValue2(WTCInput value);
	boolean validateOutput__oResult(WTCOutput value);
	boolean validateBhvParam__bpOperationType(String value);
}