package org.mondo.collaboration.online.rap.widgets;

import java.util.Date;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.mondo.collaboration.online.core.LensSessionManager;
import org.mondo.collaboration.online.rap.UINotifierManager;
import org.mondo.collaboration.online.rap.UISessionManager;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.FutureCallback;

@SuppressWarnings("serial")
public class WhiteboardChatView extends WhiteboardGenericView {
	public WhiteboardChatView() {
	}

	private static final String PLACEHOLDER = "";
	
	private static AddChatToWhiteboard addCallback;
	private static RemoveFromWhiteboard removeCallback;
	
	// Static constructor for registering the global changes.
	static {
		addCallback = new AddChatToWhiteboard();
		UINotifierManager.register(LensSessionManager.EVENT_WHITEBOARD_SESSION_OPENED, RWT.getUISession(), addCallback);
		removeCallback = new RemoveFromWhiteboard();
		UINotifierManager.register(LensSessionManager.EVENT_WHITEBOARD_SESSION_CLOSED, RWT.getUISession(), removeCallback);
	}
	
	public static final String ID = "org.mondo.collaboration.online.rap.widgets.WhiteboardChatView"; //$NON-NLS-1$
	public static final String EVENT_NEW_MESSAGE = "org.mondo.collaboration.online.rap.widgets.WhiteboardChatView.new.message";

	private volatile static Map<URI, String> messages = Maps.newHashMap();
	private TableViewer userList;
	private Text txtMessage;

	private UpdateCallback callback;

	private void sendMessage() {
		String message = txtMessage.getText();
		if(message != null && !message.trim().isEmpty()) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("#");
			Date now = new Date();
		    String strDate = ModelExplorer.DATE_FORMAT.format(now);
		    sb.append(strDate);
		    sb.append(" ");
			sb.append(ModelExplorer.getCurrentStorageAccess().getUsername());
			sb.append(": ");
			sb.append(message);
			sb.append(System.lineSeparator());
			
			String fullMessage = messages.get(currentURI);
			fullMessage = sb.toString().concat(fullMessage == null ? "" : fullMessage);
			messages.put(currentURI, fullMessage);
			UINotifierManager.notifySuccess(EVENT_NEW_MESSAGE, currentURI);
			
			txtMessage.setText("");
		}
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {

		super.createPartControl(parent);
		
		addCallback.setMessageSource(this);
		removeCallback.setMessageSource(this);
				
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		{
			userList = new TableViewer(container);
			Table table = userList.getTable();
			table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
			userList.setContentProvider(new UserContentProvider());
			userList.setLabelProvider(new DecoratingLabelProvider(new UserStatusLabelProvider(), null));
			userList.setInput(PLACEHOLDER);
		}
		txtMessagePool = new Text(container, SWT.READ_ONLY | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		txtMessagePool.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		{
			txtMessagePool.setText("");
		}
		
		callback = new UpdateCallback();
		UINotifierManager.register(UISessionManager.EVENT_USER_ONLINE, RWT.getUISession(), callback);
		UINotifierManager.register(UISessionManager.EVENT_USER_INACTIVE, RWT.getUISession(), callback);
		UINotifierManager.register(UISessionManager.EVENT_USER_OFFLINE, RWT.getUISession(), callback);
		UINotifierManager.register(UISessionManager.EVENT_USER_REMOVED, RWT.getUISession(), callback);

		
		Button btnSend = new Button(container, SWT.None);
		btnSend.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnSend.setText("Send");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				super.mouseUp(e);
				sendMessage();
			}
			
		});
		txtMessage = new Text(container, SWT.BORDER);
		txtMessage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtMessage.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					sendMessage();
				}
			}

		});

	}

	@Override
	public void dispose() {
		super.dispose();
		UINotifierManager.register(UISessionManager.EVENT_USER_ONLINE, RWT.getUISession(), callback);
		UINotifierManager.register(UISessionManager.EVENT_USER_INACTIVE, RWT.getUISession(), callback);
		UINotifierManager.register(UISessionManager.EVENT_USER_OFFLINE, RWT.getUISession(), callback);
		UINotifierManager.register(UISessionManager.EVENT_USER_REMOVED, RWT.getUISession(), callback);

	}
	
	@Override
	protected void updateView() {
		super.updateView();
		if(userList == null) return;
		if(userList.getControl().isDisposed()) return;
		userList.getControl().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				if(userList.getControl().isDisposed()) return;
				userList.refresh();
			}
		});
	}
	
	@Override
	protected void updateView(URI uri) {
		super.updateView(uri);
		if(userList == null) return;
		if(userList.getControl().isDisposed()) return;
		userList.getControl().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				userList.setInput(uri);
				userList.refresh();
			}
		});
	}
	
	@Override
	public void setFocus() {
		txtMessage.setFocus();
	}
	
	@Override
	protected Map<URI, String> getMessages() {
		return messages;
	}

	@Override
	protected String getNewMessageEventId() {
		return EVENT_NEW_MESSAGE;
	}

	private final class UpdateCallback implements FutureCallback<Object> {
		@Override
		public void onSuccess(Object arg0) {
			updateView();
		}

		@Override
		public void onFailure(Throwable arg0) {
			// TODO Auto-generated method stub
		}
	}

	protected static class AddChatToWhiteboard extends AddToWhiteboard {
		@Override
		public String getViewKind() {
			return "Model chat";
		}
	}
	
	private class UserContentProvider implements IStructuredContentProvider {

		private Object input;

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			this.input = newInput;
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if(input instanceof URI) {
				return LensSessionManager.getUsersForURI((URI) input).toArray();
			}
			return new Object[]{PLACEHOLDER};
		}
		
	}
	
	private class UserStatusLabelProvider extends LabelProvider {

		@Override
		public Image getImage(Object element) {
			if(UISessionManager.getActiveUsers(currentURI).contains(element)) {
				return Activator.getDefault().getImageRegistry().get("active");
			}
			if(UISessionManager.getInactiveUsers(currentURI).contains(element)) {
				return Activator.getDefault().getImageRegistry().get("inactive");
			}
			if(UISessionManager.getOfflineUsers(currentURI).contains(element)) {
				return Activator.getDefault().getImageRegistry().get("offline");
			}
			return null;
		}
	}
}
