package data_structures.true_graph;

import java.util.Iterator;

/**
 * Clase que modela un grafo no dirigido.
 *
 * @param <K> Clase generica que modela una llave de un vertice del grafo.
 * @param <V> Clase generica que modela el valor guardado en los vertices del grafo.
 * @param <A> Clase generica que modela el valor guardado en un arco.
 */
public class Graph<K extends Comparable<K>,V,A> {

	
	//Atributos
	
	/**
	 * Numero de vertices que hay en el grafo en el momento
	 */
    private int numVertices;
    
    /**
     * Numero de arcos que hay en el Grafo en el momento
     */
    private int numArcos;
    
    /**
     * Vertices del grafo 
     */
    @SuppressWarnings("rawtypes")
	private SeparateChainingHash<K,Vertice> vertices;

    //Constructor
    
    
    /**
     * Construye un grafo del tamaño especificado
     * @param tamano
     */
    @SuppressWarnings("rawtypes")
	public Graph(int tamano) {
        if (tamano < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        numVertices = 0;
        numArcos = 0;
        vertices = new SeparateChainingHash<K,Vertice>(tamano);
    }
    
    
    
    //Metodos
    
    /**
     * Retorna el numero de Vertices
     * @return int numero de vertices.
     */
    
    public int V() {
        return numVertices;
    }
    
    
    /**
     * Retorna el numero de Arcos  
     * @return int numero de arcos.
     */
    public int E()
    {
    	return numArcos;
    }
    
    /**
     * Añade un vertice a el grafo.
     * @param idVertex Llave del grafo que se va a añadir
     * @param infoVertex Informacion que va a contener el grafo.
     */
    
    public void addVertex(K idVertex, V infoVertex)
    {
    	Vertice<K, V> vertice = new Vertice<K, V>(idVertex, infoVertex); 
    	vertices.put(idVertex, vertice);
    	numVertices++;
    }
    
    /**
     * Valida si el vertice existe
     * @param llave La llave del vertice.
     */
    private void validateVertex(K llave) {
        if(vertices.get(llave).equals(null))
            throw new IllegalArgumentException("El vertice no existe");
    }
    
    /**
     * Añade un arco al grafo
     * @param idVertexIni Vertice ininial el arco
     * @param idVertexFin Vertice final del arco
     * @param infoArc Informacion del arco
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void addEdge(K idVertexIni, K idVertexFin, A infoArc) {
        validateVertex(idVertexIni);
        validateVertex(idVertexFin);
        numArcos++;
        Arco arco = new Arco(idVertexIni, idVertexFin, infoArc);
        vertices.get(idVertexIni).add(arco);
        vertices.get(idVertexFin).add(arco);
    }
    
    
    /**
     * Da la informacion del vertice con la llave dada.
     * @param idVertex Llave del vertice que se quiere revisar 
     * @return V Informacion del vertice
     */
    @SuppressWarnings("unchecked")
	public V getInfoVertex(K idVertex)
	{
    	V rta = null;
    	try {
			rta = (V) vertices.get(idVertex).darValor();
		}
		catch(java.lang.NullPointerException e){
			
		}
		return rta;
	}
    
    
    /**
     * Cambia la informacion del vertice con la llave dada por el valor dado.
     * @param idVertex Llave del vertice al cual sse le va a cambiar la informacion.
     * @param infoVertex Informacion nueva.
     */
    @SuppressWarnings("unchecked")
	public void setInfoVertex(K idVertex, V infoVertex)
    {
    	vertices.get(idVertex).cambiarValor(infoVertex);
    }
    
    /**
     * Retorna la informacion del arco que conecta los dos vertices.
     * @param idVertexIni 
     * @param idVertexFin
     * @return A informacion del arco buscado
     */
    @SuppressWarnings({ "unchecked" })
	public A getInfoArc(K idVertexIni, K idVertexFin)
    {
    	A rta = null;
    	try {
			rta =  (A) vertices.get(idVertexIni).getArco(idVertexFin).darInfo();
		}
		catch(java.lang.NullPointerException e){
			
		}
		return rta;
    }
    
    /**
     * Cambia la informacion del arco por la pasada por parametro.
     * @param idVertexIni Vertice inicial del arco
     * @param idVertexFin Vertice final del arco
     * @param infoArc Informacion nueva del arco.
     */
    @SuppressWarnings("unchecked")
	public void setInfoArc(K idVertexIni, K idVertexFin,A infoArc)
    {
    	vertices.get(idVertexIni).getArco(idVertexFin).cambiarInfo(infoArc);
    }

    /**
     * Iterador de las llaves de los vertices adyacentes al vertice pasado por parametro.
     * @param idVertex Id del vertice del cual se neesitan los adyacentes.
     * @return
     */
    @SuppressWarnings("unchecked")
	public Iterator<K> adj(K idVertex) 
    {
    	return vertices.get(idVertex).adj();
    }
    
    public Iterator<K> verts() 
    {
    	return vertices.keys(); 	
    }
    
    @SuppressWarnings("rawtypes")
	public SeparateChainingHash<K, Vertice> vertices()
    {
    	return vertices;
    }
}
