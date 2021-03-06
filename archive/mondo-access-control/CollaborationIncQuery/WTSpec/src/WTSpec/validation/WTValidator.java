/**
 *
 * $Id$
 */
package WTSpec.validation;

import WTSpec.Subsystem;
import WTSpec.SystemErrorReaction;
import WTSpec.SystemFault;
import WTSpec.SystemInput;
import WTSpec.SystemOutput;
import WTSpec.SystemParam;
import WTSpec.SystemTimer;
import WTSpec.SystemVariable;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link WTSpec.WT}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface WTValidator {
	boolean validate();

	boolean validateSysId(String value);
	boolean validateModel(String value);
	boolean validateVersion(String value);
	boolean validateItsSubsystems(EList<Subsystem> value);
	boolean validateItsInputs(EList<SystemInput> value);
	boolean validateItsOutputs(EList<SystemOutput> value);
	boolean validateItsParams(EList<SystemParam> value);
	boolean validateItsTimers(EList<SystemTimer> value);
	boolean validateItsFaults(EList<SystemFault> value);
	boolean validateItsVariables(EList<SystemVariable> value);
	boolean validateItsErrorReactions(EList<SystemErrorReaction> value);
}
