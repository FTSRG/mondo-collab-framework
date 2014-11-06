/**
 */
package operationtracemodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deteleted Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link operationtracemodel.DeteletedElement#getDeletedObject <em>Deleted Object</em>}</li>
 * </ul>
 * </p>
 *
 * @see operationtracemodel.OperationtracemodelPackage#getDeteletedElement()
 * @model
 * @generated
 */
public interface DeteletedElement extends EObject {

	/**
	 * Returns the value of the '<em><b>Deleted Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deleted Object</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deleted Object</em>' attribute.
	 * @see #setDeletedObject(Object)
	 * @see operationtracemodel.OperationtracemodelPackage#getDeteletedElement_DeletedObject()
	 * @model required="true"
	 * @generated
	 */
	Object getDeletedObject();

	/**
	 * Sets the value of the '{@link operationtracemodel.DeteletedElement#getDeletedObject <em>Deleted Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deleted Object</em>' attribute.
	 * @see #getDeletedObject()
	 * @generated
	 */
	void setDeletedObject(Object value);
} // DeteletedElement
