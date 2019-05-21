package data_structures.true_graph;

import java.util.Iterator;

/**
 * Clase que modela un vertice de un grafo.
 *
 * @param <K> Llave identificadora unica de el vertice.
 * @param <V> Valor guardado en e vertice.
 */
public class Vertice <K,V>{

	// Atributos

	/**
	 * Llave de el vertice
	 */
	private K key;


	/**
	 * Valor guardado en el grafo
	 */
	private V value;

	/**
	 * Indica si el vertice ha sido visitado.
	 */
	private boolean marcado;

	/**
	 * Vertices adyacentes
	 */
	@SuppressWarnings("rawtypes")
	private LinkedList<Arco> adyacentes;

	//Constructor

	/**
	 * Constructor de un vertice.
	 * @param idVertex
	 * @param infoVertex
	 */
	public Vertice(K idVertex, V infoVertex) {

		key = idVertex;

		value = infoVertex;

		marcado = false;

		adyacentes = new LinkedList<Arco>();

	}

	//Metodos


	/**
	 * Retorna la llave identificadora del vertice.
	 * @return K La llave del vertice.
	 */
	public K darLLave()
	{
		return key;
	}

	/**
	 * Retorna el valor guardado en el vertice.
	 * @return V valor del vertice.
	 */
	public V darValor()
	{
		return value;
	}


	/**
	 * Retorna si el vertice esta marcado o no.
	 * @return Boolean True si esta marcado, False si no.
	 */

	public boolean marcado()
	{
		return marcado;
	}


	/**
	 * Retorna el iterador de la lista de adyacentes
	 * @return iterador de arcos.
	 */
	@SuppressWarnings("rawtypes")
	public Iterator<Arco> adyacentes()
	{
		return adyacentes.iterator();
	}


	/**
	 * Modifica si el vertice ha sido visitado o no.
	 * @param cambio Boolean True si se quiere marcar el vertice como visitado, False si se quiere desmarcar el vertice. 
	 */
	public void modificarMarcado(boolean cambio)
	{
		marcado = cambio;
	}


	/**
	 * Añade un arco.
	 * @param adyacente arco nuevo
	 */
	@SuppressWarnings("rawtypes")
	public void add(Arco adyacente)
	{

		adyacentes.add(adyacente);

	}


	/**
	 * Cambia la informacion del vertice.
	 * @param infoVertex informacion nueva
	 */
	public void cambiarValor(V infoVertex) {

		value = infoVertex;

	}

	/**
	 * Retorna el arco que va del vertice al vertice indicado por parametro.
	 * @param idVertexFin Vertice al que va el arco buscado.
	 * @return El arco buscado.
	 */
	@SuppressWarnings("rawtypes")
	public Arco getArco(K idVertexFin) {

		Iterator<Arco> itr = adyacentes.iterator();
		while(itr.hasNext())
		{
			Arco act = itr.next();
			
			if(act.darVerticeFin().equals(idVertexFin))
				return act;
		}

		return null;

	}

	/**
	 * Iterador de llaves de los vertices adyacentes.
	 * @return Llaves de los adyacentes.
	 */
	public Iterator<K> adj() {

		Iterator<K> t = new Iterator<K>() {

			private int currentIndex = 0;
			Iterator<Arco> itr = adyacentes.iterator();

			@Override
			public boolean hasNext() {
				return currentIndex < adyacentes.getSize() && itr.hasNext();
			}

			@SuppressWarnings("unchecked")
			@Override
			public K next() {

				currentIndex++;
				return (K) itr.next().darVerticeFin();

			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		return t;
	}



}
