package test;

import junit.framework.TestCase;
import model.estructuras.Graph;
import model.estructuras.Vertice;

public class GrafoTest<A, K, V> extends TestCase{

	private Graph<Integer, Integer, Integer> grafo;

	public void setupEscenario1( )
	{
		grafo = new Graph<Integer, Integer, Integer>();

		try
		{			
			// TODO Auto-generated catch block
			grafo.addVertex(1, 1);
			grafo.addVertex(2, 2);
			grafo.addVertex(3, 3);
			grafo.addVertex(4, 4);
			grafo.addVertex(5, 5);
		}
		catch( Exception e )
		{
			e.printStackTrace();
			fail( );
		}
	}

	public void setupEscenario2( )
	{
		grafo = new Graph<Integer, Integer, Integer>();

		try
		{
			// TODO Auto-generated catch block
			System.out.println(grafo.V());
			grafo.addVertex(1, 1);
			System.out.println(grafo.getInfoVertex(1)+",   " +grafo.V());
			grafo.addVertex(2, 2);
			System.out.println("sii");
			grafo.addVertex(3, 3);
			grafo.addVertex(4, 4);
			System.out.println("aaa");
			grafo.addVertex(5, 5);
			System.out.println(grafo.getInfoVertex(1)+"");

		}
		catch( Exception e )
		{
			fail( );
		}

		try
		{
			// TODO Auto-generated catch block
			grafo.addEdge(1, 2, 1);
			grafo.addEdge(2, 3, 2);
			grafo.addEdge(3, 4, 3);
			grafo.addEdge(4, 5, 4);
			grafo.addEdge(5, 1, 5);
		}
		catch( Exception e )
		{
			fail( );
		}
	}

	public void testAgregar( )
	{
		setupEscenario1( );

		try
		{
			// TODO Auto-generated catch block
			grafo.addVertex(7, 7);
			grafo.addEdge(7, 2, 7);
			grafo.addEdge(7, 3, 8);
		}	
		catch( Exception e )
		{
			fail();
		}
	}

	public void testAgregarVerticeError( )
	{
		setupEscenario2( );

		try
		{
			grafo.addVertex(1, 1);
			// TODO Auto-generated catch block
			fail( "El vértice ingresado ya existe" );
		}
		catch( Exception e )
		{
		}
	}

	public void testAgregarArcoError( ) 
	{
		setupEscenario2( );

		try {
			grafo.addEdge(2, 3, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail( "Uno de los vértices conectados por el arco no existe" );


	}
}
