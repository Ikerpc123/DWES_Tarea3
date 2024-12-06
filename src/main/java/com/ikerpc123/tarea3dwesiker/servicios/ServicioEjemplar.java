package com.ikerpc123.tarea3dwesiker.servicios;

import com.ikerpc123.tarea3dwesiker.modelo.Ejemplar;
import com.ikerpc123.tarea3dwesiker.modelo.Planta;

public interface ServicioEjemplar {
	
	public Long ultimoIdEjemplarByPlanta(Planta p);
	public void insertarEjemplar(Ejemplar e);
}
