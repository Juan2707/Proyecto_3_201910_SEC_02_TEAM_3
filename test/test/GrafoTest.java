package test;

import junit.framework.TestCase;
import model.estructuras.Graph;
import model.estructuras.Vertice;

public class GrafoTest<A, K, V> extends TestCase{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Grafo sobre el que se van a hacer las pruebas.
	 */
	private Graph<Integer, Integer, Integer> grafo;

	// -----------------------------------------------------------------
	// Escenarios de prueba
	// -----------------------------------------------------------------


	/**
	 * Crea un grafo con 5 v�rtices y 0 arcos
	 */
	public void setupEscenario1( )
	{
		// Crear el grafo vacio
		grafo = new Graph<Integer, Integer, Integer>();

		// Crear los v�rtices
		try
		{
			grafo.addVertex(1, 1);
			grafo.addVertex(2, 2);
			grafo.addVertex(3, 3);
			grafo.addVertex(4, 4);
			grafo.addVertex(5, 5);
		}
		catch( Exception e )
		{
			// Esto no deber� a suceder
			e.printStackTrace();
			fail( );
		}
	}



	/**
	 * Crea un grafo con 5 v�rtices en forma de lista encadenada
	 */
	public void setupEscenario2( )
	{
		// Crear el grafo vacio
		grafo = new Graph<Integer, Integer, Integer>();

		// Crear los v�rtices
		try
		{
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
			// Esto no deber�a suceder
			fail( );
		}

		// Agregar los v�rtices
		try
		{
			grafo.addEdge(1, 2, 1);
			grafo.addEdge(2, 3, 2);
			grafo.addEdge(3, 4, 3);
			grafo.addEdge(4, 5, 4);
			grafo.addEdge(5, 1, 5);
		}
		catch( Exception e )
		{
			// Esto no deber� a
			fail( );
		}
	}

	
	// -----------------------------------------------------------------
	// Pruebas para m�todos modificadores
	// -----------------------------------------------------------------

	/**
	 * Pruebas de casos normales para el m�todo <code>agregarVertice</code>
	 */
	public void testAgregar( )
	{
		// Crear el grafo vacio
		setupEscenario1( );

		try
		{
			grafo.addVertex(7, 7);
			grafo.addEdge(7, 2, 7);
			grafo.addEdge(7, 3, 8);
		}	
		catch( Exception e )
		{
			fail();
		}
	}

	/**
	 * Pruebas de casos erroneos para el m�todo <code>agregarVertice</code>
	 */
	public void testAgregarVerticeError( )
	{
		setupEscenario2( );

		// Ingresar un vertice repetido
		try
		{
			grafo.addVertex(1, 1);
			fail( "El v�rtice ingresado ya existe" );
		}
		catch( Exception e )
		{
			// Este es el comportamiento esperado
		}
	}

	/**
	 * Pruebas de casos erroneos para el m�todo <code>agregarArco</code>
	 */
	public void testAgregarArcoError( )
	{
		// Crear un grafo con 5 vertices en forma de lista encadenada
		setupEscenario2( );

		// Ingresar un arco entre vertices inexistentes
		try
		{
			grafo.addEdge(2, 3, 2);
			fail( "Uno de los v�rtices conectados por el arco no existe" );
		}
		catch( Exception e )
		{
			// Comportamiento esperado
		}
		
	}

	
	/**
	 * Pruebas de casos normales para el m�todo <code>hayCamino</code>
	 */
//	public void testHayCamino( )
//	{
//		setupEscenario2( );
//
//		try
//		{
//			assertTrue( "Algoritmo invalido para detecci�n de caminos", grafo.hayCamino( 0, 3 ) );
//			assertTrue( "Algoritmo invalido para detecci�n de caminos", grafo.hayCamino( 0, 5 ) );
//			assertFalse( "Algoritmo invalido para detecci�n de caminos", grafo.hayCamino( 0, 0 ) );
//		}
//		catch( Exception e )
//		{
//			fail( "el vertice " + e.darIdentificador( ) + " si existe" );
//		}
//	}

}
