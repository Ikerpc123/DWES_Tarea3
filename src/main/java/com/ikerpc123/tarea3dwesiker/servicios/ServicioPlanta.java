package com.ikerpc123.tarea3dwesiker.servicios;

import java.util.List;

import com.ikerpc123.tarea3dwesiker.modelo.Planta;

public interface ServicioPlanta {
	
	public boolean validarPlanta(Planta p);
	
	public boolean insertarPlanta(Planta p);
	
	public List<Planta> findAll();
	
	public Planta findByCodigo(String codigo);
	
	public boolean actualizar(Planta planta);
	
}
