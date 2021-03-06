/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCInput;
import WTSpec.WTCOutput;
import WTSpec.WTCTimer;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit106}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit106Validator {
	boolean validate();

	boolean validateInput__i1(WTCInput value);
	boolean validateOutput__o1(WTCOutput value);
	boolean validateTimer__t1(WTCTimer value);
	boolean validateTimer__t2(WTCTimer value);
	boolean validateBhvParam__bpMode(String value);
}
