/**
 */
package eu.mondo.collaboration.operationtracemodel.example.WTSpec.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import eu.mondo.collaboration.operationtracemodel.example.WTSpec.CtrlUnit18;
import eu.mondo.collaboration.operationtracemodel.example.WTSpec.WTSpecPackage;

/**
 * This is the item provider adapter for a {@link eu.mondo.collaboration.operationtracemodel.example.WTSpec.CtrlUnit18} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CtrlUnit18ItemProvider
	extends wtcItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CtrlUnit18ItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addInput__iOrderCwPropertyDescriptor(object);
			addInput__iOrderCcwPropertyDescriptor(object);
			addInput__iFaultPropertyDescriptor(object);
			addInput__iCmdDisablePropertyDescriptor(object);
			addInput__iCmdResetPropertyDescriptor(object);
			addInput__iStaReadyToOnPropertyDescriptor(object);
			addOutput__oControlWordPropertyDescriptor(object);
			addParameter__pCmdDisabledPropertyDescriptor(object);
			addParameter__pCmdEnabledPropertyDescriptor(object);
			addParameter__pCmdCwPropertyDescriptor(object);
			addParameter__pCmdCcwPropertyDescriptor(object);
			addParameter__pCmdResetPropertyDescriptor(object);
			addParameter__pCmdSwitchOnPropertyDescriptor(object);
			addParameter__pCmdBrakingPropertyDescriptor(object);
			addTimer__tDelayPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Input iOrder Cw feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInput__iOrderCwPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CtrlUnit18_Input__iOrderCw_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CtrlUnit18_Input__iOrderCw_feature", "_UI_CtrlUnit18_type"),
				 WTSpecPackage.eINSTANCE.getCtrlUnit18_Input__iOrderCw(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Input iOrder Ccw feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInput__iOrderCcwPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CtrlUnit18_Input__iOrderCcw_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CtrlUnit18_Input__iOrderCcw_feature", "_UI_CtrlUnit18_type"),
				 WTSpecPackage.eINSTANCE.getCtrlUnit18_Input__iOrderCcw(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Input iFault feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInput__iFaultPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CtrlUnit18_Input__iFault_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CtrlUnit18_Input__iFault_feature", "_UI_CtrlUnit18_type"),
				 WTSpecPackage.eINSTANCE.getCtrlUnit18_Input__iFault(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Input iCmd Disable feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInput__iCmdDisablePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CtrlUnit18_Input__iCmdDisable_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CtrlUnit18_Input__iCmdDisable_feature", "_UI_CtrlUnit18_type"),
				 WTSpecPackage.eINSTANCE.getCtrlUnit18_Input__iCmdDisable(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Input iCmd Reset feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInput__iCmdResetPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CtrlUnit18_Input__iCmdReset_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CtrlUnit18_Input__iCmdReset_feature", "_UI_CtrlUnit18_type"),
				 WTSpecPackage.eINSTANCE.getCtrlUnit18_Input__iCmdReset(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Input iSta Ready To On feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInput__iStaReadyToOnPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CtrlUnit18_Input__iStaReadyToOn_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CtrlUnit18_Input__iStaReadyToOn_feature", "_UI_CtrlUnit18_type"),
				 WTSpecPackage.eINSTANCE.getCtrlUnit18_Input__iStaReadyToOn(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Output oControl Word feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOutput__oControlWordPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CtrlUnit18_Output__oControlWord_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CtrlUnit18_Output__oControlWord_feature", "_UI_CtrlUnit18_type"),
				 WTSpecPackage.eINSTANCE.getCtrlUnit18_Output__oControlWord(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Parameter pCmd Disabled feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addParameter__pCmdDisabledPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CtrlUnit18_Parameter__pCmdDisabled_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CtrlUnit18_Parameter__pCmdDisabled_feature", "_UI_CtrlUnit18_type"),
				 WTSpecPackage.eINSTANCE.getCtrlUnit18_Parameter__pCmdDisabled(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Parameter pCmd Enabled feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addParameter__pCmdEnabledPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CtrlUnit18_Parameter__pCmdEnabled_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CtrlUnit18_Parameter__pCmdEnabled_feature", "_UI_CtrlUnit18_type"),
				 WTSpecPackage.eINSTANCE.getCtrlUnit18_Parameter__pCmdEnabled(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Parameter pCmd Cw feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addParameter__pCmdCwPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CtrlUnit18_Parameter__pCmdCw_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CtrlUnit18_Parameter__pCmdCw_feature", "_UI_CtrlUnit18_type"),
				 WTSpecPackage.eINSTANCE.getCtrlUnit18_Parameter__pCmdCw(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Parameter pCmd Ccw feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addParameter__pCmdCcwPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CtrlUnit18_Parameter__pCmdCcw_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CtrlUnit18_Parameter__pCmdCcw_feature", "_UI_CtrlUnit18_type"),
				 WTSpecPackage.eINSTANCE.getCtrlUnit18_Parameter__pCmdCcw(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Parameter pCmd Reset feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addParameter__pCmdResetPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CtrlUnit18_Parameter__pCmdReset_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CtrlUnit18_Parameter__pCmdReset_feature", "_UI_CtrlUnit18_type"),
				 WTSpecPackage.eINSTANCE.getCtrlUnit18_Parameter__pCmdReset(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Parameter pCmd Switch On feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addParameter__pCmdSwitchOnPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CtrlUnit18_Parameter__pCmdSwitchOn_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CtrlUnit18_Parameter__pCmdSwitchOn_feature", "_UI_CtrlUnit18_type"),
				 WTSpecPackage.eINSTANCE.getCtrlUnit18_Parameter__pCmdSwitchOn(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Parameter pCmd Braking feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addParameter__pCmdBrakingPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CtrlUnit18_Parameter__pCmdBraking_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CtrlUnit18_Parameter__pCmdBraking_feature", "_UI_CtrlUnit18_type"),
				 WTSpecPackage.eINSTANCE.getCtrlUnit18_Parameter__pCmdBraking(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Timer tDelay feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTimer__tDelayPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CtrlUnit18_Timer__tDelay_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CtrlUnit18_Timer__tDelay_feature", "_UI_CtrlUnit18_type"),
				 WTSpecPackage.eINSTANCE.getCtrlUnit18_Timer__tDelay(),
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This returns CtrlUnit18.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CtrlUnit18"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((CtrlUnit18)object).getSysId();
		return label == null || label.length() == 0 ?
			getString("_UI_CtrlUnit18_type") :
			getString("_UI_CtrlUnit18_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}
