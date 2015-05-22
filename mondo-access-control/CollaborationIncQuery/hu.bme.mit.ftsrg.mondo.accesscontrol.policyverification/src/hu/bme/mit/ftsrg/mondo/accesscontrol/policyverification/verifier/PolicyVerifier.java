package hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.verifier;

import hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.PolicySetFactory;
import hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.PolicySetQuery;
import hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.GlobalEnums;
import hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.GlobalEnums.Permission;
import hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.GlobalEnums.Target;
import hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.profiles.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.incquery.patternlanguage.emf.EMFPatternLanguageStandaloneSetup;
import org.eclipse.incquery.patternlanguage.emf.specification.SpecificationBuilder;
import org.eclipse.incquery.patternlanguage.helper.CorePatternLanguageHelper;
import org.eclipse.incquery.patternlanguage.patternLanguage.Pattern;
import org.eclipse.incquery.patternlanguage.patternLanguage.PatternModel;

public class PolicyVerifier {

	Properties config = new Properties();
	PolicySetFactory userPolicies;
	PolicySetFactory groupPolicies;
	ArrayList<PolicySetQuery> actualUserPolicies;
	ArrayList<PolicySetQuery> actualGroupPolicies;
	User actualUser;
	URI modelURI;
	Resource resource;
	SpecificationBuilder builder;
	Integer lastExecutionStep;

	public PolicyVerifier(URI modelURI) {
		userPolicies = new PolicySetFactory(Target.USER);
		groupPolicies = new PolicySetFactory(Target.GROUP);
		this.modelURI = modelURI;
		this.resource = loadModel(modelURI);

		try {
			config.load(this.getClass().getClassLoader().getResourceAsStream("config/config.properties"));
			builder = new SpecificationBuilder();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PolicyVerifier(Resource resource, User actualUser) {
		userPolicies = new PolicySetFactory(Target.USER);
		groupPolicies = new PolicySetFactory(Target.GROUP);
		this.resource = resource;

		try {
			config.load(this.getClass().getClassLoader().getResourceAsStream("config/config.properties"));
			builder = new SpecificationBuilder();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Resource getResource() {
		return resource;
	}
	
	public Integer getLastExecutionStep(){
		return lastExecutionStep;
	}

	/**
	 * Be�ll�tja a rendszer aktu�lis felhaszn�l�j�t
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		this.actualUser = user;
		actualUserPolicies = userPolicies.getPolicySet(actualUser.getName());
		actualGroupPolicies = groupPolicies.getPolicySet(actualUser.getRole().getRoleName());
	}

	/**
	 * Szab�lyzat le�r� f�jlok beolvas�sa
	 */
	public void loadResources() {
		System.out.println(config.getProperty("policyDir"));
		File policyDir = new File(config.getProperty("policyDir"));
		if (policyDir.exists() && policyDir.isDirectory()) {
			File[] listOfFiles = policyDir.listFiles();

			// JSON f�jlok kisz�r�se
			for (File file : listOfFiles) {
				if (file.isFile() && file.getName().endsWith(".json")) {
					userPolicies.loadDataFromJson(file.getAbsolutePath());
					groupPolicies.loadDataFromJson(file.getAbsolutePath());
				}
			}
		}
	}

	/**
	 * Ecore modell bet�lt�se
	 * 
	 * @param fileURI
	 * @return
	 */
	protected Resource loadModel(URI fileURI) {
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(fileURI, true);
		return resource;
	}

	/**
	 * Visszat�r egy beazonos�tott IncQuery mint�val
	 * 
	 * @param fileURI
	 *            IncQuery .eiq f�jl el�r�si �tvonala
	 * @param fqn
	 *            Pattern azonos�t�ja
	 * @return
	 */
	public static Pattern getPattern(URI fileURI, String fqn) {
		Pattern pattern = null;
		new EMFPatternLanguageStandaloneSetup().createInjectorAndDoEMFRegistration();
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource patternResource = resourceSet.getResource(fileURI, true);

		if (patternResource != null) {
			if (patternResource.getErrors().size() == 0 && patternResource.getContents().size() >= 1) {
				EObject topElement = patternResource.getContents().get(0);
				if (topElement instanceof PatternModel) {
					for (Pattern _pattern : ((PatternModel) topElement).getPatterns()) {
						if (fqn.equals(CorePatternLanguageHelper.getFullyQualifiedName(_pattern))) {
							pattern = _pattern;
							break;
						}
					}
				}
			}
		}
		return pattern;
	}

	/**
	 * Beregiszt�lja az aktu�lis keres�si mint�kat
	 */
	public void initPatterns() {
		if (actualUser != null) {
			userPolicies.initPolicyPatterns(resource, actualUser.getName());
			groupPolicies.initPolicyPatterns(resource, actualUser.getRole().getRoleName());
		}
	}

	public void init(User user) {
		new DefaultRealm();
		setUser(user);
		initPatterns();
	}

	/**
	 * Ki�rt�keli a felhaszn�l�hoz �s a csoportj�hoz tartoz� szab�lyrendszerket.
	 * A felhaszn�l�hoz tartoz� szab�lyok magasabb priorit�suak.
	 * 
	 * @return
	 */
	public Permission executeAccessControl(int step) {
		if (actualUser != null) {
			lastExecutionStep = step;
			
			Permission userPermission = userPolicies.executeAccessControl(resource,
					actualUser.getName());
			Permission groupPermission = groupPolicies.executeAccessControl(resource, actualUser
					.getRole().getRoleName());

			if (userPermission != null) {
				return userPermission;
			}
			if (groupPermission != null) {
				return groupPermission;
			}
			return Permission.ALLOW;
		} else {
			System.err.println("Hiba: nincs be�ll�tva aktu�lis felhaszn�l�!");
			return Permission.DENY;
		}
	}

	public Permission printExecutedResult(int step) {
		Permission p = executeAccessControl(step);
		System.out.println("Hozz�f�r�si jogosults�g ellen�rz�se");
		System.out.println("\tFelhaszn�l�: " + actualUser.getName());
		System.out.println("\tCsoport: " + actualUser.getRole().getRoleName());
		if(p.equals(GlobalEnums.Permission.ALLOW)){
			System.out.println("Hozz�f�r�s: Enged�lyezve!");
		} else {
			System.err.println("Hozz�f�r�s: MEGTAGADVA!");
		}
		return p;
	}
}
