package eu.mondo.collaboration.online.client.modelcontroller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;

import eu.mondo.collaboration.online.client.application.CollaborationPage;


@JavaScript({ 
	"app://VAADIN/client/visjs/vis.js",
	"app://VAADIN/client/jquery/jquery-2.1.0.min.js",
	"app://VAADIN/client/GraphicalSyntaxHandler.js",
	"collaboration-connector.js", "collaborationcomponent.js" 
})
@StyleSheet({ 
	"app://VAADIN/client/visjs/vis.css",
	"app://VAADIN/client/MondoOnline.css" 
})
public class CollaborationComponent extends AbstractJavaScriptComponent {
	private ModelController mc;
	 
	private final CollaborationPage ownerPage;
	
	public interface ValueChangeListener extends Serializable {
		void valueChange();
	}

	ArrayList<ValueChangeListener> listeners = new ArrayList<ValueChangeListener>();

	public void addValueChangeListener(ValueChangeListener listener) {
		listeners.add(listener);
	}

	public void setValue(String value) {
		getState().value = value;
	}

	public String getValue() {
		return getState().value;
	}

	public void setModel(JSONObject model) {
		System.out.println("updating model...");
		getState().setModel(model);
	}

	public JSONObject getModel() {
		return getState().model;
	}

	@Override
	protected SharedState getState() {
		return (SharedState) super.getState();
	}
	
	/*
	private void initModelController(CollaborationComponent cc) {
	}
	*/
	
	private void updateModel(JSONObject newModel) {
		this.ownerPage.publishModel(newModel);
	}
	
	private List<String> getSubtreeIdentifiers(JSONObject object) {
		List<String> subtreeIdentifiers = new ArrayList<String>();
		String[] names = JSONObject.getNames(object);
		for(String name: names) {
			Object property;
			try {
				property = object.get(name);
				if(property instanceof JSONArray) {
					subtreeIdentifiers.add(name);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return subtreeIdentifiers;
	}
	
	private JSONObject connectElementToRootNode(JSONObject node, JSONObject element) {
		try {
			String type = element.getString("type");
			String typeSetName = element.getString("type");
			JSONObject newElement =  new JSONObject();
			// TODO set additional EMF properties
			newElement.put("name", element.getString("name"));
			if(type.equals("WTCtrl")) {
				type = "CtrlUnit10";
			}
			String eClass = "http://WTSpec/2.0#//" + type;
			newElement.put("eClass", eClass);
			if(!node.has(typeSetName)) {
				JSONArray newType = new JSONArray();
				newType.put(newElement);
				node.put(typeSetName, newType);
			} else {
				node.getJSONArray(typeSetName).put(newElement);
			}
			return node;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private JSONObject tryToInsertIntoSubtree(JSONObject node, JSONObject element) {
		List<String> subtreeIdentifiers = getSubtreeIdentifiers(node);
		for(String identifier: subtreeIdentifiers) {
			try {
				JSONArray subtree = node.getJSONArray(identifier);
				for(int i = 0; i < subtree.length(); i++) {
					JSONObject currentNode = subtree.getJSONObject(i);
					boolean success = false;
					if(currentNode.getString("name").equals(element.getString("parentName"))) {
						currentNode = connectElementToRootNode(currentNode, element);
						node.getJSONArray(identifier).put(i, currentNode);
						success = true;
					} else {
						currentNode = tryToInsertIntoSubtree(currentNode, element);
						if(currentNode != null) {
							success = true;
						}
					}
					if(success) {
						return node;
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private JSONObject tryToUpdateSubtree(JSONObject node, JSONObject original, JSONObject edited) {
		List<String> subtreeIdentifiers = getSubtreeIdentifiers(node);
		for(String identifier: subtreeIdentifiers) {
			try {
				JSONArray subtree = node.getJSONArray(identifier);
				for(int i = 0; i < subtree.length(); i++) {
					JSONObject currentNode = subtree.getJSONObject(i);
					boolean success = false;
					if(currentNode.getString("name").equals(original.getString("name"))) {
						currentNode = updateNode(currentNode, edited);
						node.getJSONArray(identifier).put(i, currentNode);
						success = true;
					} else {
						currentNode = tryToUpdateSubtree(currentNode, original, edited);
						if(currentNode != null) {
							success = true;
						}
					}
					if(success) {
						return node;
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private JSONObject updateNode(JSONObject original, JSONObject edited) {
		String[] props = JSONObject.getNames(edited);
		try {
			for(String prop: props) {
				original.put(prop, edited.get(prop));
			} 
			return original;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private JSONObject tryToDeleteInSubtree(JSONObject node, JSONObject nodeToDelete) {
		List<String> subtreeIdentifiers = getSubtreeIdentifiers(node);
		for(String identifier: subtreeIdentifiers) {
			try {
				JSONArray subtree = node.getJSONArray(identifier);
				for(int i = 0; i < subtree.length(); i++) {
					JSONObject currentNode = subtree.getJSONObject(i);
					boolean success = false;
					if(currentNode.getString("name").equals(nodeToDelete.getString("name"))) {
						JSONArray newSubtree = removeItemFromJsonArray(subtree, i);
						node.put(identifier, newSubtree);
						success = true;
					} else {
						currentNode = tryToDeleteInSubtree(currentNode, nodeToDelete);
						if(currentNode != null) {
							success = true;
						}
					}
					if(success) {
						return node;
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private JSONArray removeItemFromJsonArray(JSONArray array, int index) {
		JSONArray newArray = new JSONArray();
		try {
			for(int i = 0; i < array.length(); i++) {
				if(i != index) {
					newArray.put(array.get(i));
				} 
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newArray;
	}
	
	public CollaborationComponent(CollaborationPage page) {
		// initModelController(this);
		this.ownerPage = page;
		
		addFunction("addElement", new JavaScriptFunction() {
			@Override
			public void call(JSONArray arguments) throws JSONException {
				JSONObject element = arguments.getJSONObject(0);
				JSONObject newModel = getState().model;
				System.out.println("add: " + element.toString());
				System.out.println("Model: " + newModel);
				// if root's child
				if(element.getString("parentName").equals("")) {
					// if no children with this type
					newModel = connectElementToRootNode(newModel, element);
				} else {
					newModel = tryToInsertIntoSubtree(newModel, element);
				}
				getState().setModel(newModel);
				updateModel(newModel);
			}
		});
		
		addFunction("editElement", new JavaScriptFunction() {
			@Override
			public void call(JSONArray arguments) throws JSONException {
				JSONObject elements = arguments.getJSONObject(0);
				JSONObject original = elements.getJSONObject("original");
				JSONObject edited = elements.getJSONObject("edited");
				
				JSONObject newModel = getState().model;
				newModel = tryToUpdateSubtree(newModel, original, edited);
				
				getState().setModel(newModel);
				updateModel(newModel);
			}
		});
		
		// deletes nodes and subnodes
		addFunction("deleteElement", new JavaScriptFunction() {
			@Override
			public void call(JSONArray arguments) throws JSONException {
				JSONObject data = arguments.getJSONObject(0);
				JSONObject nodeToDelete = data.getJSONObject("node");
				
				// TODO validate action
				JSONObject newModel = getState().model;
				newModel = tryToDeleteInSubtree(newModel, nodeToDelete);
				getState().setModel(newModel);
				updateModel(newModel);
				/*
				// delete node
				JSONArray nodesInSharedModel = getState().model.getJSONArray("nodes");
				JSONArray newNodes = new JSONArray();
				for(int i = 0; i < nodesInSharedModel.length(); i++) {
					JSONObject currentNode = nodesInSharedModel.getJSONObject(i);
					if(!currentNode.get("id").equals(nodeId)) {
						newNodes.put(currentNode);
					}
				}
				
				// delete edges
				JSONArray edgesInSharedModel = getState().model.getJSONArray("edges");
				JSONArray newEdges = new JSONArray();
				for(int i = 0; i < edgesInSharedModel.length(); i++) {
					JSONObject currentEdge = edgesInSharedModel.getJSONObject(i);
					boolean goodToGo = true;
					for(int j = 0; j < edgeIds.length(); j++){
						if(currentEdge.get("id").equals(edgeIds.get(j))) {
							goodToGo = false;
							break;
						}
					}
					if(goodToGo) {
						newEdges.put(currentEdge);
					}
				}
				newModel.put("nodes", newNodes);
				newModel.put("edges", newEdges);
				// System.out.println("Model after delete: " + newModel.toString());
				getState().setModel(newModel);
				updateModel(newModel);
				*/
			}
		});
		
		addFunction("loadModel", new JavaScriptFunction() {
			@Override
			public void call(JSONArray arguments) throws JSONException {
				if(getState().model != null) {
					getState().setModel(getState().model);
				}
			}
		});
	}
}