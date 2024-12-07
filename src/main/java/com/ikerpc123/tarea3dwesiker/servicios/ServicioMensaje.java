package com.ikerpc123.tarea3dwesiker.servicios;

import java.util.List;

import com.ikerpc123.tarea3dwesiker.modelo.Ejemplar;
import com.ikerpc123.tarea3dwesiker.modelo.Mensaje;

public interface ServicioMensaje {

	public void insertarMensaje(Mensaje mensaje);
	public List<Mensaje> findAll();
	public List<Mensaje> findByEjemplar(Ejemplar ejemplar);
}
