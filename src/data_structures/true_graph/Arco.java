package data_structures.true_graph;

/**
 * Clase que modela un arco de un grafo.
 *
 * @param <A> Clase generica que modela la informacion del arco
 */
public class Arco <A, K>{

	
	//Atributos
	
	/**
	 * id del Vertice inicial del arco.
	 */
	private K verticeIni;
	
	/**
	 * id del Vertice final del arco.
	 */
	private K verticeFin;
	
	/**
	 * Informacion del arco.
	 */
	private A info;
	
	
	//Constructor
	
	/**
	 * Contructor de un arco.
	 * @param idVertexIni id del Vertice inicial del arco
	 * @param idVertexFin id del Vertice final del arco
	 * @param infoArc informacion del arco
	 */
	public Arco(K idVertexIni, K idVertexFin, A infoArc) {
		
		verticeIni = idVertexIni;
		
		verticeFin = idVertexFin;
		
		info = infoArc;
		
	}
	
	//Metodos
	
	/**
	 * Retorna el id del vertice inicial
	 * @return K llave del vertivce inicial
	 */
	public K darVerticeIni(){
		return verticeIni;
	}
	
	/**
	 * Retorna el id del vertice final
	 * @return K llave del vertivce final
	 */
	public K darVerticeFin(){
		return verticeFin;
	}
	
	/**
	 * Retorna la informacion del arco
	 * @return A info del arco
	 */
	public A darInfo(){
		return info;
	}

	
	/**
	 * Cambia la informacion por la dada por parametro.
	 * @param infoArc informacion nueva.
	 */
	public void cambiarInfo(A infoArc) {

		info = infoArc;
	}
	

}
