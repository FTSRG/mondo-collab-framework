package org.mondo.collaboration.security.macl.tao;

import org.eclipse.incquery.patternlanguage.emf.ui.EMFPatternLanguageExecutableExtensionFactory;
import org.osgi.framework.Bundle;

public class MACLIncQueryLanguageExecutableExtensionFactory extends EMFPatternLanguageExecutableExtensionFactory {

	@Override
    protected Bundle getBundle() {
        return Activator.getDefault().getBundle();
    }
	
}
