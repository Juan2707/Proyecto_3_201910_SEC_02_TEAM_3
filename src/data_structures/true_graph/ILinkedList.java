package data_structures.true_graph;

/**
 * Abstract Data Type for a linked list of generic objects
 * This ADT should contain the basic operations to manage a list
 * add, addAtEnd, AddAtK, getElement, getCurrentElement, getSize, delete, deleteAtK
 * next, previous
 * @param <T>
 */
public interface ILinkedList<T> extends Iterable<T> {

	Integer getSize();

	/**
	 * 
	 * @param element
	 */
	public void add(T element);

	/**
	 * 
	 * @param element
	 */
	public void addAtEnd(T element);

	/**
	 * 
	 * @param element
	 */
	public default void addAtK(T element)
	{}

	/**
	 * 
	 * @param element
	 */
	public T getElement(int index);

	/**
	 * 
	 * @param element
	 */
	public T getCurrentElement();

	/**
	 * 
	 * @param element
	 */
	public void delete(T element);

	/**
	 * 
	 * @param element
	 */
	public T deleteAtK(int k);

	/**
	 * 
	 */
	public T next();

	/**
	 * 
	 */
	public T previous();

}
