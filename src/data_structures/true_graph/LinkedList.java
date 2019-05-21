package data_structures.true_graph;


import java.util.Iterator;

public class LinkedList<T> implements ILinkedList<T>{


	/**
	 * El primer nodo a agregar a la lista
	 */
	private Node<T> node1;

	/**
	 * El ñltimo nodo de la lista
	 */
	private Node<T> last;

	/**
	 * Representa el tamaño actual de la lista
	 */
	private int tamañoAct;

	/**
	 * 
	 */
	private Node<T> currentElement;


	//Constructor//

	public LinkedList()
	{
		node1 = null;
		last = null;
		tamañoAct = -1;
		currentElement = null;
	}


	/**
	 * Iterador de la lista
	 */
	public Iterator<T> iterator() {

		Iterator<T> t = new Iterator<T>() {

			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < getSize() && getCurrentElement() != null;
			}

			@Override
			public T next() {
				Node<T> h = currentElement;
				currentElement = h.darNext();
				currentIndex++;
				return h.darElemento();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		return t;
	}


	/**
	 * Retorna un integer con el tamabo actual de la lista
	 */
	public Integer getSize() {
		return tamañoAct + 1;
	}

	/**
	 * Agrega un elemento al comienzo de la lista
	 * @param element, elemento a ser agregado
	 */
	public void add(T element)
	{
		Node<T> g = new Node<T>(element);
		tamañoAct++;

		if(node1== null){
			node1 = g;
			last = g;
		}
		else{
			g.changeNext(node1);
			node1.changePrev(g);
			node1 = g;
		}
		currentElement = g;

	}

	/**
	 * Agrega un elemento genñrico al final de la lista
	 * @param element, elemento a ser agregado
	 */
	public void addAtEnd(T element)
	{
		Node<T> g = new Node<T>(element);

		if(node1 == null)
		{
			last = g;
			node1 = g;
			currentElement = node1;
		}
		else{
			last.changeNext(g);
			g.changePrev(last);
			last = g;
		}
		tamañoAct++;
	}

	/**
	 * Agrega un elemento en el ñndice dado. EL tamaño de la lista aumenta en uno.
	 * @param k, el ñndice donde se quiere agregar el elemento.
	 * @param element, el elemento que se quiere agregar.
	 */
	public void addAtK(int k, T element)
	{

		if(k ==0)
		{
			add(element);
		}

		else{
			Node<T> prv = getNode(getElement(k-1));
			Node<T> nxt = prv.darNext();

			if(nxt != null)
			{
				Node<T> yy = new Node<T>(element);

				prv.changeNext(yy);
				nxt.changePrev(yy);
				yy.changeNext(nxt);
				yy.changePrev(prv);
				tamañoAct++;

			}
			else{
				addAtEnd(element);
			}
		}

	}

	/**
	 * BUsca el elemento en el ñndice dado y lo retorma
	 * @param index, el ñndice dado
	 */
	public T getElement(int index)
	{
		T rta = null;
		if(index < getSize()){

		if(index == 0)
		{
			rta = node1.darElemento();
		}
		else if(index == tamañoAct)
		{
			rta = last.darElemento();
		}
		else{
			int cont =-1;
			Iterator iter = iterator();
			T f = null;

			while(iter.hasNext() && cont < index)
			{
				f = (T)iter.next();
				cont++;
			}
			rta = f;

		}

		}
		return rta;
	}

	/**
	 * Busca el elemento dado por parametro y lo elimina
	 * @param el, es el elemento a eliminar
	 */
	public void delete(T el)
	{
		if(node1.darElemento().equals(el))
		{
			deleteAtK(0);
		}
		else{
			Node<T> aEliminar = getNode(el);
			Node<T> prv = aEliminar.darPrev();
			Node<T> nxt = aEliminar.darNext();

			prv.changeNext(nxt);
			nxt.changePrev(prv);
			aEliminar.changeNext(null);
			aEliminar.changePrev(null);
			tamañoAct--;


		}
	}



	@Override
	public T deleteAtK(int k) {
		T rta = null;

		if(k < getSize()){
			if(k ==0)
			{
				Node<T> kk = node1.darNext();
				Node<T> eliminar = node1;

				if(kk != null){
					node1.changeNext(null);
					kk.changePrev(null);
					node1= kk;
					currentElement = kk;
					tamañoAct --;
				}

				else{
					node1 = null;

				}
				rta = eliminar.darElemento();

			}

			else{

				Node<T> act = getNode(getElement(k));

				if(act != null){
					Node<T> prv= act.darPrev();
					Node<T> nxt = act.darNext();

					if(nxt != null)
					{
						prv.changeNext(nxt);
						nxt.changePrev(prv);
						act.changeNext(null);
						act.changePrev(null);
						rta = act.darElemento();
					}

					else{
						prv.changeNext(null);
						act.changePrev(null);
						last = prv;
						rta = act.darElemento();

					}

					tamañoAct--;
				}
			}
		}
		return rta;
	}

	/**
	 * Retorna el nodo que contiene el elemento dado por parámetro
	 * @param element, elemento dado
	 * @return retornó un nodo con el elemento dado
	 */
	public Node<T> getNode(T element)
	{
		Node<T> rta = null;

		if(element != null){
			currentElement = node1;
			Iterator<T> itr = iterator();
			int y = 0;

			while(itr.hasNext() && y == 0)
			{
				if(currentElement.darElemento().equals(element))
				{
					rta = currentElement;
					y = 1;
				}
				else
				{
					itr.next();
				}
			}

			currentElement = node1;
		}
		return rta;
	}

	@Override
	public T next() {
		return currentElement.darNext().darElemento();
	}


	@Override
	public T previous() {
		return currentElement.darPrev().darElemento();
	}


	@Override
	public T getCurrentElement() {
		if(currentElement != null)
			return currentElement.darElemento();
		else
			return null;
	}





}
