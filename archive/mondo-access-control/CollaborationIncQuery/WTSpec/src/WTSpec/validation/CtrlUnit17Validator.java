/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.WTCFault;
import WTSpec.WTCParam;
import WTSpec.WTCTimer;

/**
 * A sample validator interface for {@link WTSpec.CtrlUnit17}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CtrlUnit17Validator {
	boolean validate();

	boolean validateParameter__pParam(WTCParam value);
	boolean validateFault__fWarning(WTCFault value);
	boolean validateFault__fFault(WTCFault value);
	boolean validateTimer__tTimer(WTCTimer value);
}
