package controller;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;

import model.estructuras.Queue;
import model.violations.VOMovingViolations;
import view.View;

public class Controller {
	
	private String[] listaMes;

	Queue<VOMovingViolations> cola;

	View view;

	public Controller() {
		cola = new Queue<VOMovingViolations>();
		view = new View();
		listaMes = new String[]{"January" , "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

	}


	public void run(){

		Scanner sc = new Scanner(System.in);
		boolean fin = false;

		while(!fin){
			view.printMenu();

			int option = sc.nextInt();

			switch(option){

			case 0:
				view.printMessage("Ingrese semestre a cargar (1 o 2)");
				int semestre = sc.nextInt();
				view.printMessage(Integer.toString(this.loadMovingViolations(semestre)));
				break;

			case 1:	
				fin = true;
				sc.close();
				break;
			}


		}
	}



	/**
	 * Cargar las infracciones de un semestre de 2018
	 * @param numeroSemestre numero del semestre a cargar (1 o 2)
	 * @return objeto con el resultado de la carga de las infracciones
	 */
	public int loadMovingViolations(int numeroSemestre) {
		int limSup;
		int limInf;
		int total = 0;
		Queue<VOMovingViolations> colaTemp;
		VOMovingViolations infraccion;

		if ( numeroSemestre == 1 ){
			limSup = 6;
			limInf = 0;
		}
		else{
			limSup = 12;
			limInf = 6;
		}
		String dataFile;
		try{
			for(int f = limInf ; f < limSup ; f++){
				dataFile = "." + File.separator + "data" + File.separator + listaMes[f] + "_wgs84.csv";
				CSVReader reader = new CSVReader(new FileReader(dataFile)); 
				reader.skip(1);
				view.printMessage(listaMes[f]);
				String [] nextLine;
				while ((nextLine = reader.readNext()) != null) {
					view.printMessage(nextLine[0]);
					total++;
					if (nextLine.length == 18)
						infraccion = new VOMovingViolations(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5], nextLine[6], nextLine[7], nextLine[8], nextLine[9], nextLine[10], nextLine[11], nextLine[12], nextLine[13], nextLine[14], nextLine[15], nextLine[16], nextLine[17]);
					else 
						infraccion = new VOMovingViolations(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5], nextLine[6], nextLine[7], nextLine[8], nextLine[9], nextLine[10], nextLine[11], nextLine[12], " ", nextLine[13], nextLine[14], nextLine[15], nextLine[16]);

				}
				reader.close();

			}
			
			view.printMessage("Se cargo exitosmente");
			return total;
		}
		catch (Exception e)
		{
			// TODO: handle exception			
			e.printStackTrace();
			System.out.println("Falló");
		}
		
		return 0;
		
	}


}
