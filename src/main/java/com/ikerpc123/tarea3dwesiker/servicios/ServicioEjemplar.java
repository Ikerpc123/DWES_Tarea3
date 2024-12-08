package com.ikerpc123.tarea3dwesiker.servicios;

import java.util.List;

import com.ikerpc123.tarea3dwesiker.modelo.Ejemplar;
import com.ikerpc123.tarea3dwesiker.modelo.Planta;

public interface ServicioEjemplar {
	
	Long ultimoIdEjemplarByPlanta(Planta p);
	void insertarEjemplar(Ejemplar e);
	List<Ejemplar> findAll();
	Ejemplar findByNombre(String nombre);
	List<Ejemplar> findByPlanta(Planta planta);
	
}
