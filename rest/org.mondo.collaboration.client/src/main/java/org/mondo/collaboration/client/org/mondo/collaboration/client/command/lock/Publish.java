package org.mondo.collaboration.client.org.mondo.collaboration.client.command.lock;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.rmi.activation.Activator;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;


public class Publish implements IHandler {

	public void addHandlerListener(IHandlerListener handlerListener) {

	}

	public void dispose() {
	
	}
	
	public void test()
	{
		try {
			 
			Thread .currentThread ().setContextClassLoader(this.getClass().getClassLoader()); 
			
			Client client = ClientBuilder.newClient();
	
			String url="http://localhost:9090/services/emfgit";
			
			
			for(IFile item:getProjectsLockFiles())
			{
				WebTarget target=client.target(url).path("/upload").queryParam("projectName", getProjectName())
						.queryParam("fileName", item.getName());
				
				File fileToUpload=item.getRawLocation().makeAbsolute().toFile();

				 Entity<File> entity=Entity.entity(fileToUpload, MediaType.APPLICATION_OCTET_STREAM);
				 
				Response response=	target.request(MediaType.APPLICATION_OCTET_STREAM)
					.post(entity);
			}
	 
		  } catch (Exception e) {
	 
			e.printStackTrace();
	 
		  }
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {

		try {
			test();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean isEnabled() {

		return true;
	}

	public boolean isHandled() {
		return true;
	}

	public void removeHandlerListener(IHandlerListener handlerListener) {
	
	}

	public ArrayList<IFile> getProjectsLockFiles() {

		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		if (window != null) {
			IStructuredSelection selection = (IStructuredSelection) window
					.getSelectionService().getSelection();
			Object firstElement = selection.getFirstElement();
			if (firstElement instanceof IAdaptable) {
				IProject project = (IProject) ((IAdaptable) firstElement)
						.getAdapter(IProject.class);
				
				IPath path = project.getFullPath();
				IResource folder=project.getFolder(org.mondo.collaboration.client.org.mondo.collaboration.client.Activator.lockDirName);
				
				return getAllFile(folder);
			}
		}
		
		return null;
	}
	
	
	ArrayList<IFile> files;
	public ArrayList<IFile> getAllFile(IResource item)
	{
		 files=new ArrayList<IFile>();
		
		 travelResources(item);
		
		 return files;
	}
	
	private void travelResources(IResource resource)
	{
		
		if(resource.getType()==IResource.FILE)
		{
			files.add((IFile) resource);
			System.out.println(resource.getFullPath());
		}
		else if(resource.getType()==IResource.FOLDER)
		{
			try {
				for(IResource item : ((IFolder) resource).members()  )
				{
					travelResources(item);
				}
			} catch (CoreException e) {
				
				//silently fail
				e.printStackTrace();
			}
		}
	}
	
	
	public String getProjectName() {

		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		if (window != null) {
			IStructuredSelection selection = (IStructuredSelection) window
					.getSelectionService().getSelection();
			Object firstElement = selection.getFirstElement();
			if (firstElement instanceof IAdaptable) {
				IProject project = (IProject) ((IAdaptable) firstElement)
						.getAdapter(IProject.class);
				
				return project.getName();
			}
		}
		
		return null;
	}

}
