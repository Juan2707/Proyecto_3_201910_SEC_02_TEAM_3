package model.estructuras;

public class Arco<K, V, A> {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * V�rtice desde el cual sale el arco
	 */
	private K origen;

	/**
	 * V�rtice hacia el cual va el arco
	 */
	private K destino;

	/**
	 * Elemento en el arco
	 */
	private A infoArco;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor del arco
	 * @param idVertexIni V�rtice desde el cual sale el arco
	 * @param idVertexFin V�rtice hacia donde se dirige el arco
	 * @param pInfoArco Elemento en el arco
	 */
	public Arco( K idVertexIni, K idVertexFin, A pInfoArco )
	{
		origen = idVertexIni;
		destino = idVertexFin;
		infoArco = pInfoArco;
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Devuelve el elemento del arco
	 * @return Elemento en el arco
	 */
	public A darInfoArco( )
	{
		return infoArco;
	}

	/**
	 * Devuelve el v�rtice de destino del arco
	 * @return v�rtice de destino del arco
	 */
	public K darVerticeDestino( )
	{
		return destino;
	}

	/**
	 * Devuelve el v�rtice de origen del arco
	 * @return v�rtice de origen del arco
	 */
	public K darVerticeOrigen( )
	{
		return origen;
	}

	public void setInf(A pInf){
		infoArco=pInf;
	}

}

