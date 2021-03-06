package org.mondo.collaboration.client.command.collaboration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.ws.rs.core.MediaType;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.mondo.collaboration.client.Activator;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class Commit extends AbstractHandler implements IHandler {

	
	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		Update up = new Update();
		up.execute(null);
		String modelFilePath = up.getLastModelPath();
		String modelFileName = up.getLastModelName();
		String projectName = up.getLastProjectName();
		
//		File mergedModel = Activator.merge(projectName, branchName, false);
		Client client = Activator.getClient();
		String url = "http://localhost:9090/services/emfgit/collaboration";
		try {
			File workingCopy = new File(modelFilePath);
			InputStream fileInStream = new FileInputStream(workingCopy);
			String sContentDisposition = "attachment; filename=\"" + modelFileName + "\"";
			WebResource resource = client.resource(url)
				.path("commit")
				.queryParam("projectName", projectName)
				.queryParam("branchName", "")
				.queryParam("username", Activator.user.getUsername())
				.queryParam("password", Activator.user.getPassword());
			ClientResponse response = resource.type(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", sContentDisposition)
                .post(ClientResponse.class, fileInStream); 
			if(response.getStatus() == 403) {
				MessageBox messageBox = new MessageBox(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.ICON_ERROR);
			    messageBox.setMessage("Invalid username and/or password.");
			}
			System.out.println("response: " + response.getStatus());
			// System.out.println("Commit response: " + resp);
		} catch(UniformInterfaceException e) {
			System.out.println("POST failed - " + e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		String projectName = "mondo_test";
		String branchName = Activator.getBranchName(projectName);
		if(branchName == null) {
			branchName = "";
		}
		return Activator.user.isSet() && !Activator.modelFolderIsEmpty(projectName + "\\" + branchName);
	}

	@Override
	public boolean isHandled() {
		return true;
	}

}
