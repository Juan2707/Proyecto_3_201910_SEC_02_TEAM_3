package lector;

import data_structures.grafo.IArco;

public class arcs implements IArco{

	private String inic;
	
	private String finall;
	
	private int peso;
	
	public arcs (String pInic, String pFinal, int pPeso) {
		inic = pInic;
		finall = pFinal;
		peso = pPeso;
	}
	
	public void setFinal(String xd){
		finall=xd;
	}
	public String getFinal(){
		return finall;
	}
	public void setInic(String xd){
		inic=xd;
	}
	public String getInic(){
		return inic;
	}
	public int darPeso(){
		return peso;
	}
}
