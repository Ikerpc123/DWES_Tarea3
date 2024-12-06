package com.ikerpc123.tarea3dwesiker.servicios;

import com.ikerpc123.tarea3dwesiker.modelo.Persona;

public interface ServicioPersona {

	public boolean validarEmail(String email);
	public boolean insertarPersona(Persona p);
	public Persona findByEmail(String email);
	public Persona findById(Long id);
}
