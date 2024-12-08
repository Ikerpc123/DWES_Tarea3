package com.ikerpc123.tarea3dwesiker.servicios;

import java.text.ParseException;
import java.util.List;

import com.ikerpc123.tarea3dwesiker.modelo.Ejemplar;
import com.ikerpc123.tarea3dwesiker.modelo.Mensaje;
import com.ikerpc123.tarea3dwesiker.modelo.Persona;

public interface ServicioMensaje {

	public boolean insertarMensaje(Mensaje mensaje);
	public List<Mensaje> findAll();
	public List<Mensaje> findByEjemplar(Ejemplar ejemplar);
	List<Mensaje> findByPersona(Persona persona);
	List<Mensaje> findByFechaRango(String fechaInicio, String fechaFin) throws ParseException;
}
