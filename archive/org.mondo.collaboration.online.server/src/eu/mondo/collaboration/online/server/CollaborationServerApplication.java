package eu.mondo.collaboration.online.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ServerEndpoint("/mondoonlineserver")
public class CollaborationServerApplication {
	private static Set<Session> connections = 
	    Collections.synchronizedSet(new HashSet<Session>());
	
	private static List<CollaborationSession> sessions;
	
	private static HashMap<String, String> sessionIdsToModelPaths;
	
	// assign connections to collaboration session IDs once they join it
	private static HashMap<String, List<Session>> sessionsConnections;
	
	public CollaborationServerApplication() {
		System.out.println("Initialize server...");
		sessionIdsToModelPaths = new HashMap<String, String>();
		if(sessions == null) {
			System.out.println("Load models...");
			try {
				this.initSessions();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void loadModelsForUser(String userName, String password, Session source) {
		URL url;
		try {
			JSONObject user = new JSONObject();
			user.put("userName", userName);
			user.put("password", password);
			String userString = user.toString();
			url = new URL("http://localhost:8070/services/modelHandler/getModelsForUser");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.addRequestProperty("userData", userString);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Length", "" + Integer.toString(userString.getBytes().length));
			
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(userString);
			wr.flush();
			wr.close();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			line = br.readLine();
			if(line.equals("CHECKOUT_FAILED")) {
				JSONObject request = new JSONObject();
				request.put("operation", "checkoutFailed");
				this.sendRequestInParts(request, source);
				return;
			}
			JSONArray modelsData = new JSONArray(line);
			System.out.println("Available models for [" + userName + "]");
			JSONObject request = new JSONObject();
			request.put("operation", "avaialbleModelsForuser");
			request.put("models", modelsData);
			System.out.println("Send available models to: " + source.getId());
			this.sendRequestInParts(request, source);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private void initSessions() throws MalformedURLException {
		System.out.println("Initalizing sessions...");
		sessionsConnections = new HashMap<String, List<Session>>();
		sessions = new ArrayList<CollaborationSession>();
		Integer id = 0;
	}

	@OnOpen
	public void onOpen(Session connection) {
		connections.add(connection);
	    System.out.println("num of clients: " + connections.size());
	}
	
	@OnMessage
	public void onMessage(String message, Session connection) 
		throws IOException {
		try {
			System.out.println("OnMessage - server");
			JSONObject request = new JSONObject(message);
			String operation = request.getString("operation");

			System.out.println("Process request: " + operation);
			if(operation.equals("getSessions")) {
				System.out.println("sendSessions...");
				this.sendSessions(connection);
			} else if(operation.equals("startSession")) {
				System.out.println("startSession...");
				JSONObject sessionData = request.getJSONObject("startSessionData");
				this.startSession(sessionData, connection);
			} else if(operation.equals("finishSession")) {
				System.out.println("finishSession...");
				String sessionId = request.getString("sessionId");
				String commitMessage = request.getString("commitMessage");
				JSONObject user = request.getJSONObject("user");
				finishSession(sessionId, commitMessage, user);
			} else if(operation.equals("joinSession")) {
				System.out.println("joinSession...");
				JSONObject user = request.getJSONObject("user");
				User newUser = new User(
					user.getString("name"),
					user.getString("password")
				);
				String sessionId = request.getString("sessionId");
				if(this.addUserToSession(newUser, sessionId)) {
					List<Session> conns = sessionsConnections.get(sessionId);
					conns.add(connection);
					sessionsConnections.put(sessionId, conns);
					if(positionsInitialized(sessionId)) {
						this.sendPositions(sessionId, connection);
					}
					this.sendModel(sessionId, connection);
					this.publishUsers(sessionId);
					this.notifyClient(connection);
				}
			} else if(operation.equals("leaveSession")) {
				System.out.println("leaveSession...");
				JSONObject user = request.getJSONObject("user");
				User userToRemove = new User(
					user.getString("name"),
					user.getString("password")
				);
				String sessionId = request.getString("sessionId");
				if(this.removeUserFromSession(userToRemove, sessionId)) {
					List<Session> conns = sessionsConnections.get(sessionId);
					conns.remove(connection);
					sessionsConnections.put(sessionId, conns);
					this.publishUsers(sessionId);
				}
			} else if(operation.equals("getModel")) {
				System.out.println("getModel...");
				String sessionId = request.getString("sessionId");
				if(this.positionsInitialized(sessionId)) {
					this.sendPositions(sessionId, connection);
				} 
				this.sendModel(sessionId, connection);
			} else if(operation.equals("modifyModel")) {
				System.out.println("modifyModel...");
				if(this.modifyModel(new JSONObject(request.toString()))) {
					this.publishModification(
						request.getString("sessionId"), 
						connection,
						request.getJSONObject("data")
					);
				}
			} else if(operation.equals("publishModel")) {
				System.out.println("publishModel...");
				String sessionId = request.getString("sessionId");
				String newModel = request.getString("model");
				if(this.setModel(sessionId, new JSONObject(newModel))) {
					this.publishModel(sessionId, connection, newModel);
				}
			} else if(operation.equals("publishPositions")) {
				System.out.println("publishPositions...");
				String sessionId = request.getString("sessionId");
				String positions = request.getString("positions");
				this.setPositions(sessionId, positions);
				this.setPositionsInitialized(sessionId);
			} else if(operation.equals("publishNodePosition")) {
				System.out.println("publishNodePosition...");
				String sessionId = request.getString("sessionId");
				JSONObject nodeData = new JSONObject(request.getString("nodeData"));
				if(this.setNodePosition(sessionId, nodeData)) {
					this.publishNodePosition(sessionId, connection, nodeData);
				}
			} else if(operation.equals("getAvailableModelsForUser")) {
				System.out.println("getAvailableModelsForUser...");
				loadModelsForUser(
					request.getString("userName"), 
					request.getString("password"), 
					connection
				);
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}   
	}


	private void publishModification(String sessionId, Session source, JSONObject modificationData) {
		try {
			JSONObject request = new JSONObject();
			request.put("operation", "modifyModel");
			request.put("data", modificationData);
			List<Session> conns = sessionsConnections.get(sessionId);
			synchronized(conns) {
				for(Session connection : conns) {
					if(!source.getId().equals(connection.getId())) {
						System.out.println("Sending modification data to: " + connection.getId());
						this.sendRequestInParts(request, connection);
					}
				}
		    } 
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private boolean modifyModel(JSONObject request) {
		try {
			String sessionId = request.getString("sessionId");
			for(CollaborationSession s: sessions) {
				if(s.getId().equals(sessionId)) {
					JSONObject results = ModelModifier.modifyModel(
						s.getModel(), 
						s.getPositions(), 
						request.getJSONObject("data")
					); 
					if(results != null) {
						Object newModel = results.get("model");
						Object newPositions = results.get("positions");
						if(!newModel.equals("none")) {
							s.setModel((JSONObject) newModel);
						}
						if(!newPositions.equals("none")) {
							s.setPositions((JSONObject) newPositions);
						}
					}
					return true;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void notifyClient(Session connection) {
		try {
			JSONObject request = new JSONObject();
			request.put("operation", "modelTransferComplete");
			this.sendRequestInParts(request, connection);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void publishNodePosition(String sessionId, Session source, JSONObject nodeData) {
		// publish node position only for those who are in the same collaboration session
		List<Session> relevantConnections = sessionsConnections.get(sessionId);
        // Iterate over the connected sessions
		synchronized(relevantConnections){
			System.out.println("Publishing new node position: " + nodeData);
	    	// and broadcast the received message
			for(Session conn : relevantConnections){
				if(!conn.getId().equals(source.getId())) {
					this.sendNodePosition(sessionId, conn, nodeData);
				}
			}
	    } 
	}

	private void setPositionsInitialized(String sessionId) {
		for(CollaborationSession s: sessions) {
			if(s.getId().equals(sessionId)) {
				s.setPositionsInitialized(true);
			}
		}
	}

	private boolean positionsInitialized(String sessionId) {
		for(CollaborationSession s: sessions) {
			if(s.getId().equals(sessionId)) {
				return s.isInitialized();
			}
		}
		return false;
	}

	private boolean setNodePosition(String sessionId, JSONObject nodeData) {
		for(CollaborationSession s: sessions) {
			if(s.getId().equals(sessionId)) {
				JSONObject positions = s.getPositions();
				for(int i = 0; i < positions.length(); i++) {
					try {
						// the key is the id of the node
						positions.put(
							nodeData.getString("node"), 
							nodeData.getJSONObject("newPosition")
						);
						s.setPositions(positions);
						return true;
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}
	
	private void publishUsers(String sessionId) {
		try {
			JSONObject request = new JSONObject();
			JSONArray users = this.getJsonUsersOfSession(sessionId);
			request.put("operation", "updateUsers");
			request.put("users", users);
			List<Session> conns = sessionsConnections.get(sessionId);
			synchronized(conns){
				System.out.println("Publishing users: " + users.toString());
				for(Session connection : conns){
					System.out.println("Send users to: " + connection.getId());
					this.sendRequestInParts(request, connection);
					// connection.getBasicRemote().sendText(request.toString());
				}
		    } 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
	}

	private JSONArray getJsonUsersOfSession(String sessionId) {
		try {
			JSONArray jsonUsers = new JSONArray();
			for(CollaborationSession s: sessions) {
				if(s.getId().equals(sessionId)) {
					for(User u: s.getUsers()) {
						JSONObject jsonUser = new JSONObject();
						jsonUser.put("name", u.getUserName());
						jsonUser.put("password", u.getPassword());
						jsonUsers.put(jsonUser);
					}
				}
			}
			return jsonUsers;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean addUserToSession(User newUser, String sessionId) {
		for(CollaborationSession s: sessions) {
			if(s.getId().equals(sessionId)) {
				s.addUser(newUser);
				return true;
			}
		}
		return false;
	}
	  
	private boolean removeUserFromSession(User userToRemove, String sessionId) {
		for(CollaborationSession s: sessions) {
			if(s.getId().equals(sessionId)) {
				System.out.println("removing user - " + userToRemove.getUserName());
				System.out.println(sessions.get(sessions.indexOf(s)).getUsers().size());
				sessions.get(sessions.indexOf(s)).removeUser(userToRemove);
				System.out.println(sessions.get(sessions.indexOf(s)).getUsers().size());
				return true;
			}
		}
		return false;
	}
	
	private void publishSessions() {
		try {
			JSONObject request = new JSONObject();
			JSONArray jsonSessions = this.prepareSessionsToSend();
			request.put("operation", "updateSessions");
			request.put("sessions", jsonSessions);
			synchronized(connections){
				System.out.println("Publishing sessions: " + jsonSessions.toString());
				for(Session connection : connections){
					System.out.println("Send sessions to: " + connection.getId());
					this.sendRequestInParts(request, connection);
					// connection.getBasicRemote().sendText(request.toString());
				}
		    } 
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

	private boolean startSession(JSONObject sessionData, Session source) {
		try {
			// TODO check if already started 
			URL url = new URL("http://localhost:8070/services/modelHandler/loadModel");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Length", "" + Integer.toString(sessionData.toString().getBytes().length));
			
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(sessionData.toString());
			wr.flush();
			wr.close();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String modelData;
			modelData = br.readLine();
			br.close();
			System.out.println("Service response: " + modelData);
			conn.disconnect();
			
			if(modelData.equals("failed")) {
				System.out.println("Failed to load model." );
			} else {
				JSONObject jsonModelData = new JSONObject(modelData);
				String sessionId = sessions.size() + "";
				String sessionName = jsonModelData.getString("name");
				
				CollaborationSession newSession = new CollaborationSession(
					sessionId, 
					sessionName, 
					CollaborationSession.STATE_OPEN
				);
				newSession.setModel(jsonModelData.getJSONObject("model"));
				sessions.add(newSession);
	        	sessionsConnections.put(sessionId, new ArrayList<Session>());
				JSONObject newJsonSession = new JSONObject();
				newJsonSession.put("name", sessionName); 
				newJsonSession.put("model", jsonModelData.getJSONObject("model")); 
				
				sessionIdsToModelPaths.put(sessionId, sessionData.getString("modelPath"));
				
				JSONObject request = new JSONObject();
				request.put("operation", "newSession");
				request.put("collaborationSession", newJsonSession);
				request.put("leader", sessionData.getString("userId"));
				broadcastRequest(request, null);
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return true;
	}

	private boolean finishSession(String sessionId, String commitMessage, JSONObject user) {
		for(CollaborationSession s: sessions) {
			if(s.getId().equals(sessionId)) {
				// kick users
				List<Session> relevantConnections = sessionsConnections.get(sessionId);
				synchronized(relevantConnections){
					try {
						JSONObject request = new JSONObject();
						request.put("operation", "leaveSession");
						for(Session conn: relevantConnections){
							this.sendRequestInParts(request, conn);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					} 
			    } 
				persistModel(sessionId, s.getTitle(), s.getModel(), commitMessage, user);
				s.finishSession();
				try {
					JSONObject request = new JSONObject();
					request.put("operation", "finishSession");
					request.put("sessionId", sessionId);
					broadcastRequest(request, null);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}

	private void persistModel(String sessionId, String title, JSONObject model, String commitMessage, JSONObject user)  {
		System.out.println("Persisting model...");
		try {
			JSONObject postData = new JSONObject();
			postData.put("id", sessionId);
			postData.put("title", title);
			postData.put("model", model);
			postData.put("commitMessage", commitMessage);
			postData.put("modelPath", sessionIdsToModelPaths.get(sessionId));
			postData.put("user", user);
			String postDataString = postData.toString();
			
			URL url = new URL("http://localhost:8070/services/modelHandler/persistModel");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Length", "" + Integer.toString(postDataString.getBytes().length));
			
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(postDataString);
			wr.flush();
			wr.close();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String modelData;
			modelData = br.readLine();
			br.close();
			System.out.println("Service response: " + modelData);
			conn.disconnect();
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendModel(String sessionId, Session connection) {
		try {
			JSONObject model = null;
			for(CollaborationSession s: sessions) {
				if(s.getId().equals(sessionId)) {
					model = s.getModel();
					break;
				}
			}
			System.out.println("Send model to: " + connection.getId());
			
			JSONObject request = new JSONObject();
			request.put("operation", "updateModel");
			request.put("model", model);
			this.sendRequestInParts(request, connection);
			// connection.getBasicRemote().sendText(request.toString());
		} catch (JSONException e1) {
			e1.printStackTrace();
		} 	
	} 
	
	private void sendNodePosition(String sessionId, Session conn, JSONObject nodeData) {
		try {
			JSONObject request = new JSONObject();
			request.put("operation", "updateNodePosition");
			request.put("nodeData", nodeData);
			this.sendRequestInParts(request, conn);
		} catch (JSONException e1) {
			e1.printStackTrace();
		} 
		
	}
	
	private void sendPositions(String sessionId, Session connection) {
		try {
			JSONObject positions = null;
			for(CollaborationSession s: sessions) {
				if(s.getId().equals(sessionId)) {
					positions = s.getPositions();
					break;
				}
			}
			System.out.println("Send positions to: " + connection.getId());
			System.out.println(positions.toString());
			JSONObject request = new JSONObject();
			request.put("operation", "updatePositions");
			request.put("positions", positions);
			this.sendRequestInParts(request, connection);
			// connection.getBasicRemote().sendText(request.toString());
		} catch (JSONException e1) {
			e1.printStackTrace();
		} 	
	} 
 
	private void sendRequestInParts(JSONObject request, Session connection) {
		String messageToSend = request.toString();
		connection.getAsyncRemote().sendText(messageToSend);
	}
	
	private void sendSessions(Session connection) {
		try {
			// prepare data for CollaborationSessions
			JSONArray jsonSessions = this.prepareSessionsToSend();
			System.out.println("Send open sessions list to: " + connection.getId());
			JSONObject request = new JSONObject();
			request.put("operation", "updateSessions");
			request.put("sessions", jsonSessions);
			this.sendRequestInParts(request, connection);
			// connection.getBasicRemote().sendText(request.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		} 
	}

	private JSONArray prepareSessionsToSend() {
		try {
			JSONArray jsonSessions = new JSONArray();
			for (CollaborationSession s: sessions) {
	        	JSONObject session = new JSONObject();
	        	
				session.put("id", s.getId());
	        	session.put("title", s.getTitle());
	        	session.put("state", s.getState());
	        	jsonSessions.put(session);
		    }
			return jsonSessions;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void publishModel(String sessionId, Session source, String newModel) {
		// publish model only for those who are in the same collaboration session
		List<Session> relevantConnections = sessionsConnections.get(sessionId);
        // Iterate over the connected sessions
		synchronized(relevantConnections){
			System.out.println("Publishing model: " + newModel);
	    	// and broadcast the received message
			for(Session conn : relevantConnections){
				if(!conn.getId().equals(source.getId())) {
					this.sendModel(sessionId, conn);
				}
			}
	    } 
	}

	private void setPositions(String sessionId, String newPositions) {
		try {
			this.setPositions(sessionId, new JSONObject(newPositions));
		} catch (JSONException e) {
			e.printStackTrace();
	    }
	}
	
	private boolean setModel(String sessionId, JSONObject newModel) {
		for(CollaborationSession s: sessions) {
			if(s.getId().equals(sessionId)) {
				s.setModel(newModel);
				return true;
			}
		}
		return false;
	}
	
	private void setPositions(String sessionId, JSONObject newPositions) {
		for(CollaborationSession s: sessions) {
			if(s.getId().equals(sessionId)) {
				s.setPositions(newPositions);
				return;
			}
		}
	}

	@OnClose
	public void onClose(Session connection) {
	    // Remove session from the connected sessions set
		System.out.println("removing connection from websocket...");
		connections.remove(connection);
	}
	
	private void broadcastRequest(JSONObject request, Session exception) {
		synchronized(connections){
			System.out.println("Broadcasting request: " + request.toString());
	    	// and broadcast the received message
			for(Session conn : connections){
				if(exception == null || !conn.getId().equals(exception.getId())) {
					this.sendRequestInParts(request, conn);
				}
			}
	    } 
	}
}
