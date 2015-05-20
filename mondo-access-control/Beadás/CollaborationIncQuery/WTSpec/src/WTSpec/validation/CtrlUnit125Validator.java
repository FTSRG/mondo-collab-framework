/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCInput;
import WTSpec.WTCOutput;
import WTSpec.WTCParam;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit125}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit125Validator {
	boolean validate();

	boolean validateInput__iInvRol(WTCInput value);
	boolean validateOutput__oSpeedRef(WTCOutput value);
	boolean validateParameter__pSpeed(WTCParam value);
	boolean validateParameter__pOffset(WTCParam value);
}
