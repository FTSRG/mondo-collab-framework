/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCInput;
import WTSpec.WTCOutput;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit7}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit7Validator {
	boolean validate();

	boolean validateInput__iAnalogInput(WTCInput value);
	boolean validateOutput__oAnalogCompensated(WTCOutput value);
}
