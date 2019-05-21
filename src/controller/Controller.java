package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.google.gson.Gson;
import com.opencsv.CSVReader;

import data_structures.*;
import data_structures.grafo.*;
import data_structures.true_graph.*;
import lector.*;
import lector.Node;
import model.violations.VOMovingViolations;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import view.MovingViolationsManagerView;

public class Controller {

	// Componente vista (consola)
	private MovingViolationsManagerView view;

	//TODO Definir los atributos de estructuras de datos del modelo del mundo del proyecto

	RedBlackBST<String, Node> Node;
	
	RedBlackBST<String, VOMovingViolations> infracciones;

	ArrayList<Way> ways;

	ArrayList<arcs> arcos;

	ArrayList<String> llaves;

	Graph<String, Node, Integer> grafoData;
	
	private String[] listaMes;

	/**
	 * Metodo constructor
	 */
	public Controller()
	{
		ways = new ArrayList<Way>();
		arcos= new ArrayList();
		llaves = new ArrayList();
		infracciones = new RedBlackBST<String, VOMovingViolations>();
		Node = new RedBlackBST<String, Node>();
		listaMes = new String[]{"January" , "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		grafoData = new Graph<String, Node, Integer>(745747);
		view = new MovingViolationsManagerView();
		
		loadMovingViolations();
	}

	/**
	 * Metodo encargado de ejecutar los  requerimientos segun la opcion indicada por el usuario
	 */
	public void run(){

		long startTime;
		long endTime;
		long duration;

		Scanner sc = new Scanner(System.in);
		boolean fin = false;


		while(!fin){
			view.printMenu();

			int option = sc.nextInt();
			int idVertice1 = 0;
			int idVertice2 = 0;


			switch(option){

			case 0:
				String RutaArchivo = "";
				view.printMessage("Escoger el grafo a cargar: (1) Downtown  o (2)Ciudad Completa.");
				int ruta = sc.nextInt();
				if(ruta == 1)
					RutaArchivo = ""; //TODO Dar la ruta del archivo de Downtown
				else
					RutaArchivo = "./data/finalGraph.json"; //TODO Dar la ruta del archivo de la ciudad completa

				startTime = System.currentTimeMillis();
				loadJSON(RutaArchivo);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				// TODO Informar el total de vértices y el total de arcos que definen el grafo cargado
				break;



			case 1:

				view.printMessage("Ingrese El id del primer vertice (Ej. 901839): ");
				idVertice1 = sc.nextInt();
				view.printMessage("Ingrese El id del segundo vertice (Ej. 901839): ");
				idVertice2 = sc.nextInt();


				startTime = System.currentTimeMillis();
				caminoCostoMinimoA1(idVertice1, idVertice2);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/* 
				TODO Consola: Mostrar el camino a seguir con sus vértices (Id, Ubicación Geográfica),
				el costo mínimo (menor cantidad de infracciones), y la distancia estimada (en Km).

				TODO Google Maps: Mostrar el camino resultante en Google Maps 
				(incluyendo la ubicación de inicio y la ubicación de destino).
				 */
				break;

			case 2:
				view.printMessage("2A. Consultar los N v�rtices con mayor n�mero de infracciones. Ingrese el valor de N: ");
				int n = sc.nextInt();


				startTime = System.currentTimeMillis();
				mayorNumeroVerticesA2(n);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/* 
				TODO Consola: Mostrar la informacion de los n vertices 
				(su identificador, su ubicación (latitud, longitud), y el total de infracciones) 
				Mostra el número de componentes conectadas (subgrafos) y los  identificadores de sus vertices 

				TODO Google Maps: Marcar la localización de los vértices resultantes en un mapa en
				Google Maps usando un color 1. Destacar la componente conectada más grande (con
				más vértices) usando un color 2. 
				 */
				break;

			case 3:			

				view.printMessage("Ingrese El id del primer vertice (Ej. 901839): ");
				idVertice1 = sc.nextInt();
				view.printMessage("Ingrese El id del segundo vertice (Ej. 901839): ");
				idVertice2 = sc.nextInt();


				startTime = System.currentTimeMillis();
				caminoLongitudMinimoaB1(idVertice1, idVertice2);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");

				/*
				   TODO Consola: Mostrar  el camino a seguir, informando
					el total de vértices, sus vértices (Id, Ubicación Geográfica) y la distancia estimada (en Km).

				   TODO Google Maps: Mostre el camino resultante en Google Maps (incluyendo la
					ubicación de inicio y la ubicación de destino).
				 */
				break;

			case 4:		
				double lonMin;
				double lonMax;
				view.printMessage("Ingrese la longitud minima (Ej. -87,806): ");
				lonMin = sc.nextDouble();
				view.printMessage("Ingrese la longitud m�xima (Ej. -87,806): ");
				lonMax = sc.nextDouble();

				view.printMessage("Ingrese la latitud minima (Ej. 44,806): ");
				double latMin = sc.nextDouble();
				view.printMessage("Ingrese la latitud m�xima (Ej. 44,806): ");
				double latMax = sc.nextDouble();

				view.printMessage("Ingrese el n�mero de columnas");
				int columnas = sc.nextInt();
				view.printMessage("Ingrese el n�mero de filas");
				int filas = sc.nextInt();


				startTime = System.currentTimeMillis();
				definirCuadriculaB2(lonMin,lonMax,latMin,latMax,columnas,filas);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar el número de vértices en el grafo
					resultado de la aproximación. Mostar el identificador y la ubicación geográfica de cada
					uno de estos vértices. 

				   TODO Google Maps: Marcar las ubicaciones de los vértices resultantes de la
					aproximación de la cuadrícula en Google Maps.
				 */
				break;

			case 5:

				startTime = System.currentTimeMillis();
				arbolMSTKruskalC1();
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar los vértices (identificadores), los arcos incluidos (Id vértice inicial e Id vértice
					final), y el costo total (distancia en Km) del árbol.

				   TODO Google Maps: Mostrar el árbol generado resultante en Google Maps: sus vértices y sus arcos.
				 */

				break;

			case 6:

				startTime = System.currentTimeMillis();
				arbolMSTPrimC2();
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar los vértices (identificadores), los arcos incluidos (Id vértice inicial e Id vértice
				 	final), y el costo total (distancia en Km) del árbol.

				   TODO Google Maps: Mostrar el árbol generado resultante en Google Maps: sus vértices y sus arcos.
				 */
				break;

			case 7:

				startTime = System.currentTimeMillis();
				caminoCostoMinimoDijkstraC3();
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar de cada camino resultante: su secuencia de vértices (identificadores) y su costo (distancia en Km).

				   TODO Google Maps: Mostrar los caminos de costo mínimo en Google Maps: sus vértices
					y sus arcos. Destaque el camino más largo (en distancia) usando un color diferente
				 */
				break;

			case 8:
				view.printMessage("Ingrese El id del primer vertice (Ej. 901839): ");
				idVertice1 = sc.nextInt();
				view.printMessage("Ingrese El id del segundo vertice (Ej. 901839): ");
				idVertice2 = sc.nextInt();

				startTime = System.currentTimeMillis();
				caminoMasCortoC4(idVertice1, idVertice2);
				endTime = System.currentTimeMillis();
				duration = endTime - startTime;
				view.printMessage("Tiempo del requerimiento: " + duration + " milisegundos");
				/*
				   TODO Consola: Mostrar del camino resultante: su secuencia de vértices (identificadores), 
				   el total de infracciones y la distancia calculada (en Km).

				   TODO Google Maps: Mostrar  el camino resultante en Google Maps: sus vértices y sus arcos.	  */
				break;

			case 9: 	
				fin = true;
				sc.close();
				break;
			}
		}
	}


	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia


	/**
	 * Cargar el Grafo No Dirigido de la malla vial: Downtown o Ciudad Completa
	 * @param rutaArchivo 
	 */

	@SuppressWarnings("deprecation")
	public void loadJSON(String rutaArchivo){
		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();
		try {
			JSONArray json = (JSONArray) parser.parse(new FileReader(rutaArchivo));
			JSONObject actual = null;
			JSONArray arrayInfracciones = null;
			JSONArray arrayAdjacente = null;

			SeparateChainingHash<String, Queue<Arco<String, String>>> arcosEnCola = new SeparateChainingHash<String, Queue<Arco<String, String>>>(822250);
			String id = "";
			String idAdyacente = "";
			String idInfraccion = "";
			Double lat = null;
			Double lon = null;

			Iterator <String> iteratorArrayInfracciones = null;
			Iterator <String> iteratorArrayAdjacente = null;

			Queue<Arco<String, String>> colaTemp;

			Node nodoAdyacente = null;
			Node nodo;
			
			VOMovingViolations violation;

			for (Object object : json ) {

				actual = (JSONObject) object;
				id =  (String) actual.get("id");
				lat = Double.parseDouble( actual.get("lat").toString() );
				lon = Double.parseDouble( actual.get("lon").toString() );

				arrayInfracciones = (JSONArray) actual.get("infractions");
				arrayAdjacente = (JSONArray) actual.get("adj");
				iteratorArrayInfracciones = arrayInfracciones.iterator();
				iteratorArrayAdjacente = arrayAdjacente.iterator();

				idAdyacente = "";

				nodoAdyacente = null;

				nodo = new Node(id, lon, lat);
				
				while(iteratorArrayInfracciones.hasNext()) {
					idInfraccion = iteratorArrayInfracciones.next();
					if ( infracciones.contains(idInfraccion) ) {
						violation = infracciones.get(idInfraccion);
						nodo.agregarInfraccion(violation);
					}
				}

				
				grafoData.addVertex(id, nodo);
				if (arcosEnCola.get(id) != null) {
					for (Arco arco : arcosEnCola.get(id)) {
						if ( grafoData.getInfoArc(arco.darVerticeFin().toString(), arco.darVerticeIni().toString()) != null ) {
							grafoData.addEdge(arco.darVerticeIni().toString(), arco.darVerticeFin().toString(), Integer.parseInt(arco.darInfo().toString()));
						}
					}
					colaTemp = new Queue<Arco<String, String>>();
					arcosEnCola.put(id, colaTemp);
				}


				while (iteratorArrayAdjacente.hasNext()) {
					idAdyacente = iteratorArrayAdjacente.next();
					nodoAdyacente = grafoData.getInfoVertex(idAdyacente);
					if(nodoAdyacente != null) {
						grafoData.addEdge(id, idAdyacente, 0);
					}
					else {
						Arco<String, String> arco = new Arco(id, idAdyacente, 0);
						if (arcosEnCola.get(idAdyacente) != null) {
							arcosEnCola.get(idAdyacente).enqueue(arco);
						}
						else {
							colaTemp = new Queue<Arco<String, String>>();
							colaTemp.enqueue(arco);
							arcosEnCola.put(idAdyacente, colaTemp);
						}
					}
				}

			}

			view.printMessage(Integer.toString(grafoData.E()));
			view.printMessage(Integer.toString(grafoData.V()));
			

			while (grafoData.verts().hasNext()) {
				String key = grafoData.verts().next();
				
				while(grafoData.getInfoVertex(key).darInfracciones().iterator().hasNext()) {
					VOMovingViolations VOMoving = grafoData.getInfoVertex(key).darInfracciones().iterator().next();
					view.printMessage(VOMoving.getAddressId());
					
				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	/**
	 * Cargar las infracciones de un semestre de 2018
	 * @param numeroSemestre numero del semestre a cargar (1 o 2)
	 * @return objeto con el resultado de la carga de las infracciones
	 */
	public void loadMovingViolations() {
		int total = 0;
		Queue<VOMovingViolations> colaTemp;
		VOMovingViolations infraccion;

		String dataFile;
		try{
			for(int f = 0 ; f < 12 ; f++){
				dataFile = "." + File.separator + "data" + File.separator + listaMes[f] + "_wgs84.csv";
				@SuppressWarnings("deprecation")
				CSVReader reader = new CSVReader(new FileReader(dataFile), ';' , '"' , 1); 
				
				String [] nextLine;
				while ((nextLine = reader.readNext()) != null) {
					total++;
					if (nextLine.length == 18)
						infraccion = new VOMovingViolations(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5], nextLine[6], nextLine[7], nextLine[8], nextLine[9], nextLine[10], nextLine[11], nextLine[12], nextLine[13], nextLine[14], nextLine[15], nextLine[16], nextLine[17]);
					else 
						infraccion = new VOMovingViolations(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5], nextLine[6], nextLine[7], nextLine[8], nextLine[9], nextLine[10], nextLine[11], nextLine[12], " ", nextLine[13], nextLine[14], nextLine[15], nextLine[16]);
					
					infracciones.put(infraccion.getObjectId(), infraccion);
				}
				reader.close();

			}
		}
		catch (Exception e){
			// TODO: handle exception			
			e.printStackTrace();
			System.out.println("Fall�");
		}
		
		view.printMessage(Integer.toString(infracciones.size()));
		
	}


	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia
	/**
	 * Requerimiento 1A: Encontrar el camino de costo m�nimo para un viaje entre dos ubicaciones geogr�ficas.
	 * @param idVertice2 
	 * @param idVertice1 
	 */
	public void caminoCostoMinimoA1(int idVertice1, int idVertice2) {
		// TODO Auto-generated method stub
	}

	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia
	/**
	 * Requerimiento 2A: Determinar los n v�rtices con mayor n�mero de infracciones. Adicionalmente identificar las
	 * componentes conectadas (subgrafos) que se definan �nicamente entre estos n v�rtices
	 * @param  int n: numero de vertices con mayor numero de infracciones  
	 */
	public void mayorNumeroVerticesA2(int n) {
		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia
	/**
	 * Requerimiento 1B: Encontrar el camino m�s corto para un viaje entre dos ubicaciones geogr�ficas 
	 * @param idVertice2 
	 * @param idVertice1 
	 */
	public void caminoLongitudMinimoaB1(int idVertice1, int idVertice2) {
		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia
	/**
	 * Requerimiento 2B:  Definir una cuadricula regular de N columnas por M filas. que incluya las longitudes y latitudes dadas
	 * @param  lonMin: Longitud minima presente dentro de la cuadricula
	 * @param  lonMax: Longitud maxima presente dentro de la cuadricula
	 * @param  latMin: Latitud minima presente dentro de la cuadricula
	 * @param  latMax: Latitud maxima presente dentro de la cuadricula
	 * @param  columnas: Numero de columnas de la cuadricula
	 * @param  filas: Numero de filas de la cuadricula
	 */
	public void definirCuadriculaB2(double lonMin, double lonMax, double latMin, double latMax, int columnas,
			int filas) {
		// TODO Auto-generated method stub
	}

	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia
	/**
	 * Requerimiento 1C:  Calcular un �rbol de expansi�n m�nima (MST) con criterio distancia, utilizando el algoritmo de Kruskal.
	 */
	public void arbolMSTKruskalC1() {
		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia
	/**
	 * Requerimiento 2C: Calcular un �rbol de expansi�n m�nima (MST) con criterio distancia, utilizando el algoritmo de Prim. (REQ 2C)
	 */
	public void arbolMSTPrimC2() {
		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia
	/**
	 * Requerimiento 3C: Calcular los caminos de costo m�nimo con criterio distancia que conecten los v�rtices resultado
	 * de la aproximaci�n de las ubicaciones de la cuadricula N x M encontrados en el punto 5.
	 */
	public void caminoCostoMinimoDijkstraC3() {
		// TODO Auto-generated method stub

	}

	// TODO El tipo de retorno de los m�todos puede ajustarse seg�n la conveniencia
	/**
	 * Requerimiento 4C:Encontrar el camino m�s corto para un viaje entre dos ubicaciones geogr�ficas escogidas aleatoriamente al interior del grafo.
	 * @param idVertice2 
	 * @param idVertice1 
	 */
	public void caminoMasCortoC4(int idVertice1, int idVertice2) {
		// TODO Auto-generated method stub

	}

}
