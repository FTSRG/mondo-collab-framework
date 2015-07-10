/*******************************************************************************
 * Copyright (c) 2004-2015 Gabor Bergmann and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gabor Bergmann - initial API and implementation
 *******************************************************************************/

package org.mondo.collaboration.security.lens.emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.incquery.runtime.base.api.BaseIndexOptions;
import org.eclipse.incquery.runtime.base.comprehension.EMFModelComprehension;
import org.mondo.collaboration.security.lens.util.LiveTable;

/**
 * Connects a given EMF model to the security lens.
 * @author Bergmann Gabor
 *
 */
public class ModelIndexer {
	private URI baseURI;
	private ResourceSet root;
	//private Resource unrootedElements = new XMIResourceImpl();
	
	public ModelIndexer(URI baseURI, ResourceSet root) {
		super();
		this.baseURI = baseURI;
		this.root = root;
	}
	
	private LiveTable indexedResources = new LiveTable();
	private LiveTable indexedResourceRootContents = new LiveTable(); 
	private LiveTable indexedEObjects = new LiveTable(); 
	private LiveTable indexedEObjectReferences = new LiveTable(); 
	private LiveTable indexedEObjectAttributes = new LiveTable();
	
	public URI getBaseURI() {
		return baseURI;
	}
	public ResourceSet getRoot() {
		return root;
	}
	public LiveTable getIndexedResources() {
		return indexedResources;
	}
	public LiveTable getIndexedResourceRootContents() {
		return indexedResourceRootContents;
	}
	public LiveTable getIndexedEObjects() {
		return indexedEObjects;
	}
	public LiveTable getIndexedEObjectReferences() {
		return indexedEObjectReferences;
	}
	public LiveTable getIndexedEObjectAttributes() {
		return indexedEObjectAttributes;
	} 
	
	EMFModelComprehension comprehension = new EMFModelComprehension(new BaseIndexOptions(false, true));
	
	private EContentAdapter adapter = new EMFAdapter(this);
	
	URI uriToRelativePath(URI resourceURI) {
		final URI relative = resourceURI.deresolve(baseURI, false, true, true);
		if (!relative.isRelative()) { // wow, an absolute reference
			//if (relative.isFile() || relative.isPlatformResource() || relative.isArchive() || relative.host() != null) {
			if (! relative.isPlatformPlugin()) { // only plugin absolute URIs are allowed
				// this is breaking the sandbox...
				throw new IllegalArgumentException(
						"Resource URI: " + resourceURI + 
						"is trying to leave the confines of the base URI: " + baseURI);
				// TODO handle better, e.g. return null and skip resource entirely.
			}
		}
		return relative;
	}
	URI relativePathToURI(URI relativeURI) {
		return relativeURI.resolve(baseURI, false);
	}

}
