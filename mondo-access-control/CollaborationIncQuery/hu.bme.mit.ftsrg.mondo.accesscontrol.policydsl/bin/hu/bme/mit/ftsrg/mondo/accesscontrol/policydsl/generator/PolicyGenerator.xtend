/*
 * generated by Xtext
 */
package hu.bme.mit.ftsrg.mondo.accesscontrol.policydsl.generator

import com.google.gson.Gson
import hu.bme.mit.ftsrg.mondo.accesscontrol.policydsl.policy.Association
import hu.bme.mit.ftsrg.mondo.accesscontrol.policydsl.policy.Policy
import hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.GlobalEnums
import hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.GlobalEnums.Permission
import hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.GlobalEnums.Target
import hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.PolicySet
import java.io.File
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator

import static extension org.eclipse.xtext.EcoreUtil2.*

/**
 * Generates code from your model files on save.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#TutorialCodeGeneration
 */
class PolicyGenerator implements IGenerator {
	
	Map<String, hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.Policy> policyMap = new HashMap<String, hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.Policy>;
	List<PolicySet> associationList = null;
	String fileName = null;
	Gson gson = new Gson();
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		associationList = new ArrayList<PolicySet>();
		var int extSeparatorIndex = resource.normalizedURI.lastSegment.lastIndexOf(".");
		if(extSeparatorIndex != -1){
			fileName = resource.normalizedURI.lastSegment.substring(0, extSeparatorIndex);
		}
		fileName += ".json";
		
		resource.allContents.filter(typeof(Policy)).forEach[
			var hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.Policy tmpPolicy = getPolicy(it);
			if(tmpPolicy != null){
				policyMap.put(it.name, tmpPolicy);	
			}
						
		]
		resource.allContents.filter(typeof(Association)).forEach[
			var ArrayList<hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.Policy> tmpPolicies = new ArrayList<hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.Policy>();
			for(obj : it.policies){
				if(policyMap.containsKey(obj.name)){
					tmpPolicies.add(policyMap.get(obj.name));
				}
			}
			var Permission tmpPermission = GlobalEnums.searchEnum(GlobalEnums.Permission, it.override.getName());
			var Permission tmpDefaultPermission = GlobalEnums.searchEnum(GlobalEnums.Permission, it.defaultPermission.getName());
			var Target tmpTarget = GlobalEnums.searchEnum(GlobalEnums.Target, it.target.getName());
			var PolicySet policySet = null;
			if(tmpTarget != null && tmpPermission != null && tmpDefaultPermission != null){
				policySet = new PolicySet(tmpTarget, it.target_id, tmpPolicies, tmpPermission, tmpDefaultPermission);
			} else {
				if(tmpTarget != null && tmpPermission != null){
					policySet = new PolicySet(tmpTarget, it.target_id, tmpPolicies, tmpPermission);
				}
			}
			if(policySet != null){
				associationList.add(policySet);
			}
		]
		
		fsa.generateFile(fileName, gson.toJson(associationList))
	}
	
	def hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.Policy getPolicy(Policy it){
		if(it.eClass.name == 'Policy'){
			var Permission tmpPermission = GlobalEnums.searchEnum(GlobalEnums.Permission, it.permission.getName());
			var File tmpQueryFile = new File(it.query);
			var hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.Policy tmpPolicy = null;
			
			if(tmpPermission != null && tmpQueryFile.exists() && !tmpQueryFile.isDirectory()){
				tmpPolicy = new hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.Policy(it.name, tmpPermission, tmpQueryFile, it.pattern);
				
				return tmpPolicy;
			}	
		}
		return null;
	}
}
