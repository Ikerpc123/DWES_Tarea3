package com.ikerpc123.tarea3dwesiker.servicios;

import java.util.List;

import com.ikerpc123.tarea3dwesiker.modelo.Planta;

public interface ServicioPlanta {
	
	public boolean validarPlanta(Planta p);
	
	public void insertarPlanta(Planta p);
	
	public List<Planta> findAll();
	
}
