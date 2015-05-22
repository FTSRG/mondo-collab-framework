/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCInput;
import WTSpec.WTCOutput;
import WTSpec.WTCParam;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit19}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit19Validator {
	boolean validate();

	boolean validateInput__iPitchTorque(WTCInput value);
	boolean validateOutput__oPitchRms(WTCOutput value);
	boolean validateParameter__pDivisor(WTCParam value);
}
