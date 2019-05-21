package data_structures.true_graph;

import java.util.Iterator;


public class SeparateChainingHash<K extends Comparable<K>,V> implements ITablaHash<K,V>{


	//Constantes

	/**
	 * Factor de carga
	 */
	private static final float FACTOR_CARGA = (float) 5.0;

	//Atributos

	/**
	 *Arreglo que contiene listas encadenadas como elementos 
	 */
	private BusquedaSecuencialST<K,V>[] bs;

	/**
	 * Número de listas
	 */
	private float M=0;

	/**
	 *Número de elementos (incluye los elementos dentro de las keys en caso de que haya)
	 */
	private int N = 0;

	/**
	 * Número de keys
	 */
	private float aux=0;

	/**
	 * Número de gets
	 */
	private float numGet = 0;

	/**
	 * Suma de todos los tiempos en los que se ha ejecutado un get en milisegundos
	 */
	private float getTime = 0;

	/**
	 * Número de rehashes
	 */
	private int numRehash = 0;


	/////Constructor
	public SeparateChainingHash(int n)
	{
		N =n;
		bs = new BusquedaSecuencialST[n];

	}

	/**
	 *@return Devuelve el número de keys en la tabla
	 */
	public int getAux()
	{
		return (int)aux;
	}

	/**
	 * @return retorna el número de gets ejecutados
	 */
	public int getnumGet()
	{
		return (int)numGet;
	}

	/**
	 * @return devuleve el número de rehashes ejecutados
	 */
	public int getNumRehash()
	{
		return numRehash;
	}

	/**
	 * @return el número de elementos en la lista
	 */
	public float getN()
	{
		return N;
	}

	/**
	 * @return el factor de carga.
	 */
	public float div()
	{
		if(M!= 0)
			return aux/M;
		else
			return 0;
	}

	/**
	 * @return el promedio de gets ejecutados
	 */
	public float getPromOfGets()
	{
		if(numGet != 0)
			return getTime / numGet;
		else
			return numGet;
	}

	/**
	 * @param el, elemento del que se quiere obtener el hash, el hash es menor al número máximo de elementos
	 * @return un número con el hash del elemento.
	 */
	public int hash(K el)
	{return (el.hashCode() & 0x7fffffff) % (int)N; 
	}

	/**
	 * Duplica el tamaño del arreglo y vuelve a distribuir los elementos
	 */
	public void rehash()
	{
		BusquedaSecuencialST<K,V> t = new BusquedaSecuencialST();

		Iterator<K> kes = keys();

		while(kes.hasNext())
		{
			K g = kes.next();
			t.put(g, get(g));
		}	

		N = 2*N;
		bs = new BusquedaSecuencialST[(int)(N)];

		Iterator<K> itr2 = t.keys();
		aux = 0;
		M=0;
		while(itr2.hasNext())
		{
			K nxt2 = itr2.next();
			put(nxt2, t.get(nxt2));
		}
		numRehash++;
	}


	@Override
	public V delete(K key) {
		V valor =null;

		int k = hash(key);
		if (bs[k] != null) {
			aux--; N--;
			valor=bs[k].get(key);
			bs[k].delete(key);
		}

		return valor;
	}

	@Override
	public V get(K el) {
		numGet++;
		V rt = null;
		long startTime = System.currentTimeMillis();
		try {
			rt = (V) bs[hash(el)].get(el);
		}
		catch(java.lang.NullPointerException e){
			
		}
		long endTime = System.currentTimeMillis();

		getTime += endTime - startTime;
		return rt;
	}

	@Override
	public void put(K el, V infoVertex) {
		if(div() > FACTOR_CARGA){
			rehash();	
		}
		else{
			int n = hash(el);
			if(bs[n] != null){
				aux += bs[n].put(el, infoVertex);	
			}
			else{
				bs[n] = new BusquedaSecuencialST<K,V>();
				aux += bs[n].put(el, infoVertex);
				M++; //Agrega una nueva lista
			}

		}

	}



	@Override
	public Iterator<K> keys() {
		Iterator<K> t = new Iterator<K>() {

			int i=0;
			int j=0;
			Iterator<K> keys2;
			boolean st = true;
			BusquedaSecuencialST<K,V> h;
			public boolean hasNext() {
				return j <aux;
			}


			@Override
			public K next() {

				if(st && i< N){
					h = bs[i];			
					if(h!= null){ 
						keys2 = h.keys(); 
						st = false; 
						return next();}
					else{ 
						i++; 
						return next();
					}
				}
				else
				{
					if(keys2.hasNext()){
						j++;
						return keys2.next();
					}
					else
					{
						i++;
						st =true;
						return (K)next();
					}
				}

			}
		};

		return t;
	}


}