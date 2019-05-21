package data_structures.true_graph;

import java.util.Iterator;

public interface ITablaHash<K extends Comparable<K>,V> {

	/**
	 * Agrega la dupla o sobrescribe el valor de una llave existente.
	 * @param key
	 * @param value
	 */
	public void put(K key, V value);
	
	/**
	 * Retorna el valor asociado a la llave.
	 * @param key
	 * @return value
	 */
	public V get(K key);
	
	/**
	 * Borra la dupla y retorna el valor asociado a la llave.
	 * @param key
	 * @return value
	 */
	public V delete(K key);
	
	/**
	 * Retorna la lista iterable de llaves.
	 * @return lista de llaves.
	 */
	public Iterator<K> keys();

	
	
}
