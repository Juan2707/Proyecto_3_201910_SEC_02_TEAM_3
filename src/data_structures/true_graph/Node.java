package data_structures.true_graph;

public class Node<T> {


	/**
	 * Representa el tipo genérico al cual corresponde el nodo
	 */
	private T element;

	/**
	 * Representa el nodo siguiente
	 */
	private Node<T> next;

	/**
	 * Representa el nodo anterior
	 */
	private Node<T> prev;


	//Constructor
	public Node(T elmnt)
	{
		this.element = elmnt;
		next = null;
		prev = null;
	}

	/**
	 * 
	 */
	public void changeNext(Node<T> n)
	{
		next = n;
	}

	/**
	 * 
	 */
	public void changePrev(Node<T> n)
	{
		prev = n;
	}

	/**
	 * 
	 */
	public Node<T> darNext()
	{
		return next;
	}

	/**
	 * 
	 */
	public Node<T> darPrev()
	{
		return prev;
	}

	public T darElemento()
	{
		return element;
	}

	/**
	 * 
	 * @param elmto
	 */
	public void setElemento(T elmto)
	{
		element = elmto;
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public boolean equals(Node<T> n)
	{
		if(this.hashCode() == n.hashCode())
			return true;
		else
			return false;
	}

}
