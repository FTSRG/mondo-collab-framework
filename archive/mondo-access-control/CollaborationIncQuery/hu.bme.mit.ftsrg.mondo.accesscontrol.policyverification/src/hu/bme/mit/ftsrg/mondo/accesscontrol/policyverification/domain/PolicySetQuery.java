package hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain;

import hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.Policy;
import hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.PolicySet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class PolicySetQuery extends PolicySet {
	private List<PolicyQuery> policies;

	public PolicySetQuery(PolicySet policySet) {
		super(policySet);
		this.policies = new ArrayList<PolicyQuery>();
		for (Policy policy : policySet.getPolicies()) {
			policies.add(new PolicyQuery(policy));
		}
	}

	public PolicySetQuery(Target targetType, String guid,
			List<PolicyQuery> policies) {
		super(targetType, guid, null);
		this.policies = policies;
	}

	public PolicySetQuery(Target targetType, String guid,
			List<PolicyQuery> policies, Permission permissionOverride,
			Permission defaultPermission) {
		super(targetType, guid, null, permissionOverride, defaultPermission);
		this.policies = policies;
	}

	public List<PolicyQuery> getPolicyQueries() {
		return policies;
	}

	/**
	 * Ki�rt�kel egy szab�lyrendszert �s visszat�r a ki�rt�kelt jogosults�ggal.
	 * Az els� szab�ly�tk�z�s detekt�l�sa eset�n viiszat�r.
	 * 
	 * @param engine
	 * @return GlobalEnums.Permission | null
	 * @throws IncQueryException
	 */
	public Permission executePolicyPatterns(Resource resource)
			throws IncQueryException {
		return executePolicySet(resource, false);
	}

	/**
	 * Lefuttatja az �sszes lek�rdez�st, ezzel inicializ�lja azokat �s
	 * beregisztr�lja a v�ltoz�sok nyomok�vet�s�re.
	 * 
	 * @param resource
	 * @throws IncQueryException
	 */
	public void initPolicyPatterns(Resource resource) throws IncQueryException {
		executePolicySet(resource, true);
	}

	private Permission executePolicySet(Resource resource,
			boolean initialization) throws IncQueryException {
		boolean permissionDiff = false;
		Permission executedPolicies = null;
		boolean matchFound = false;
		Iterator<PolicyQuery> policyIterator = policies.iterator();
		// A szab�lygy�jtem�ny ki�rt�kel�se addig, am�g elt�r�st nem
		// tapasztalhat� az alap�rtelmezett jogosults�ggal
		while ((initialization == true || permissionDiff == false)
				&& policyIterator.hasNext()) {
			PolicyQuery policy = (PolicyQuery) policyIterator.next();
			boolean policyMatchFound = policy.executeQueryOnPattern(resource,
					initialization);
			if (initialization == false && policyMatchFound) {
				matchFound = true;
				if (policy.getPermission() != defaultPermission) {
					permissionDiff = true;
					if (policy.getPermission() != executedPolicies) {
						executedPolicies = policy.getPermission();
					}
				}
			}
		}
		if (matchFound) {
			if (permissionDiff == true) {
				// Szab�lygy�jtem�nyben tal�lhat� tilt�s �s enged�lyez�s is
				// A jogosults�g fel�l�r� tulajdons� d�nti el a jogosult�s
				// kezel�st
				return permissionOverride;
			} else {
				// Szab�lygy�jtem�ny nem mutat elt�r�st az alap�rtelmezett
				// jogosults�gt�l
				return defaultPermission;
			}
		} else {
			// Nincs tal�lat egy szab�lyn�l sem
			return null;
		}
	}
}
