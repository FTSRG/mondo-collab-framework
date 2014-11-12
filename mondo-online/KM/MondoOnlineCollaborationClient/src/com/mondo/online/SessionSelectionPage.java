package com.mondo.online;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;

@StyleSheet({ 
	"app://VAADIN/client/MondoOnline.css" 
})
public class SessionSelectionPage extends AbsoluteLayout implements View {
	
    public static final String NAME = "SessionSelection";
    
	private Navigator navigator;

	private Application application;

	private List<CollaborationSession> sessions;
	
	public SessionSelectionPage(final Navigator navigator, Application application) {
		System.out.println("Construct SessionSelectionPage");
		this.application = application;
		this.sessions = new ArrayList<CollaborationSession>();
		this.navigator = navigator;
		setSizeFull();
	}
	
	/*
	private List<CollaborationSession> getSessions() {
		String pathToResFolder = "D:\\Eclipse\\Eclipse_EE\\workspace_EE\\MondoOnlineCollaborationClient\\res";
		final File folder = new File(pathToResFolder);
		List<CollaborationSession> sessions = new ArrayList<CollaborationSession>();
		int id = 0;
		for (final File fileEntry : folder.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	        	String modelPath = pathToResFolder + fileEntry.getName(); 
	        	sessions.add(new CollaborationSession(id, fileEntry.getName(), modelPath));
	        	id++;
	        }
	    }
		return sessions;
	}
	*/
	
	private void loadOpenSessions() {
		this.application.getWebsocketClient().loadOpenSessions();
	}

	public void setSessionsList(JSONArray jsonSessions) {
		this.sessions.clear();
		try {
			for(int i = 0; i < jsonSessions.length(); i++) {
				CollaborationSession newSession = new CollaborationSession(
					jsonSessions.getJSONObject(i).getString("id"),
					jsonSessions.getJSONObject(i).getString("title")
				);
				this.sessions.add(newSession);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.updateSessionsView();
	}

	private void updateSessionsView() {
		Panel sessionSelection = this.initSessionsView();
		addComponent(sessionSelection); //, "left: 10px; top: 40px;");
		
	}
	private Panel initSessionsView() {
		Panel panel = new Panel();
		panel.setSizeFull();
		addComponent(panel, "left: 20px; top: 20px;");

		CustomLayout custom = new CustomLayout("sessionSelectionPage");
		// custom.addStyleName("customlayoutexample");
		
		Table sessionsTable = new Table();
		sessionsTable.setSizeFull();
		sessionsTable.setHeight("300px");
		sessionsTable.addContainerProperty("ID",  Integer.class, null);
		sessionsTable.addContainerProperty("Name", String.class, null);
		sessionsTable.addContainerProperty("State", String.class, null);
		sessionsTable.setSelectable(true);
		
		int rowNum = 2;
		for(CollaborationSession s: this.sessions) {
			String state = s.isOpen() ? "Open" : "Closed";
			sessionsTable.addItem(new Object[]{
				Integer.parseInt(s.getId()), 
				s.getTitle(), 
				state
				}, rowNum
			);
			rowNum++;
		}

		custom.addComponent(sessionsTable, "sessionsTable");
		Button buttonLogout = new Button("Log out"); 
		Button buttonJoinSession = new Button("Join session");
		Button buttonOpenSession = new Button("Open session");
		Button buttonFinishSession = new Button("Finish session");
		
		Navigator navigator = this.navigator;
		Application app = this.application;
		buttonLogout.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				logout();
			}
		});
		buttonJoinSession.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Object rowId = sessionsTable.getValue();
				if(rowId != null) {
					int sessionId = ((Integer)sessionsTable.getContainerProperty(rowId,"ID").getValue()).intValue();
					boolean isOpen = sessionsTable.getContainerProperty(rowId,"State")
						.getValue() == "Open" ? true : false;
					if(isOpen) {
						navigator.navigateTo(CollaborationPage.NAME);
					} else {
						Notification.show("Selected session is closed.");
					}
				} else {
					Notification.show("Please select a session");
				}
			}
		});
		
		custom.addComponent(buttonLogout, "buttonLogout");
		custom.addComponent(buttonJoinSession, "buttonJoinSession");
		custom.addComponent(buttonOpenSession, "buttonOpenSession");
		custom.addComponent(buttonFinishSession, "buttonFinishSession");

		panel.setContent(custom);	
		return panel;
	}
	
	private void logout() {
		this.application.unbindUser();
		this.navigator.navigateTo(LoginPage.NAME);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		this.loadOpenSessions();
	}
}
