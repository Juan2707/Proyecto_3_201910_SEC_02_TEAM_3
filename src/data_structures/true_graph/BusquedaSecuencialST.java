package data_structures.true_graph;

import java.util.Iterator;

public class BusquedaSecuencialST<K extends Comparable<K>,V> {

	/**
	 * Representa al primer nodo de la lista encadenada
	 */
	private Node first; 

	/**
	 * Tamaño de la lista
	 */
	private int size = 0;

	/**
	 * Clase que representa cualquier nodo de la lista.
	 */
	private class Node
	{ 
		K key;
		V val;
		Node next;
		public Node(K key, V val, Node next)
		{
			this.key = key;
			this.val = val;
			this.next = next;
		}

		public K getKey()
		{
			return key;
		}

		public Node getNext()
		{
			return next;
		}

	}

	/**
	 * Retorna un elemento igual al buscado.
	 * @param el, elemento buscado.
	 * @return el elemento buscado en caso de que exista, null en caso contrario.
	 */
	public V get(K el)
	{ 
		for (Node x = first; x != null; x = x.next)
			if (el.equals(x.key))
				return x.val; 
		return null; 
	}

	/**
	 * Agrega un elemento a la lista encadenada, si el elemento ya existe y su valor es una busquedaSecuencialST
	 * agrega el elemento en la busquedaSecuencial, de lo contrario reemplaza el valor del elemento por el valor agregado.a
	 * @param el, representa al elemento que quiere ser agregado.
	 * @param val, el valor de dicho elemento.
	 */
	public int put(K el, V val)
	{ 
		for (Node x = first; x != null; x = x.next){
			if (el.equals(x.key))
			{ 
				if(val instanceof BusquedaSecuencialST){
					((BusquedaSecuencialST<K,V>)x.val).concat((BusquedaSecuencialST<K,V>)val);
					return 0;
				}
				else {
					x.val = val; 
					return 0;
				} 
			}
		}

		first = new Node(el, val, first); 
		size++;
		return 1;
	} 

	public void delete(K key)
	{
		for (Node x = first; x != null; x = x.next)
			if (key.equals(x.key)){
				x.val = null; x.key = null; 
				size--;
			}
	}

	/**
	 * @return el tamaño de la lista encadenada.
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Concatena una nueva lista con esta.
	 * @param list2 lista a la que se quiere concatenar
	 */
	public void concat( BusquedaSecuencialST<K,V> list2)
	{
		Iterator<K> itrL2 = list2.keys();
		while(itrL2.hasNext())
		{
			K element = itrL2.next();
			this.put(element, list2.get(element));
		}
	}

	/**
	 * 
	 * @return
	 */
	public Iterator<K> keys()
	{
		Iterator<K> t = new Iterator<K>() {

			private int currentIndex = 0;
			Node currentElement = first;

			public boolean hasNext() {
				return currentIndex < size && currentElement != null;
			}

			public K next() {
				Node h = currentElement;
				currentElement = h.getNext();
				currentIndex++;
				return h.getKey();			
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		return t;
	}
}
