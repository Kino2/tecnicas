package com.example;

import java.util.ArrayList;

public class OfertaActividades {
	static ArrayList<Actividad> nomina;
	
	public void NuevaActividad(Actividad act) {
		if(nomina==null) nomina = new ArrayList<Actividad>();
		if(nomina.indexOf(act)<0) nomina.add(act);
	}
	
	static public void BorrarActividad(Actividad act) {
		
		if(nomina.indexOf(act)<0) nomina.remove(act);
	}
	
	public int CantidadActividades() {
		return nomina.size();
	}

	static ArrayList<Actividad>  GetNomina() {
	return nomina;
	}
	
}