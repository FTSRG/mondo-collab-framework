/*
 * generated by Xtext
 */
package hu.bme.mit.ftsrg.mondo.accesscontrol.policydsl.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import hu.bme.mit.ftsrg.mondo.accesscontrol.policydsl.ui.internal.PolicyActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class PolicyExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return PolicyActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return PolicyActivator.getInstance().getInjector(PolicyActivator.HU_BME_MIT_FTSRG_MONDO_ACCESSCONTROL_POLICYDSL_POLICY);
	}
	
}