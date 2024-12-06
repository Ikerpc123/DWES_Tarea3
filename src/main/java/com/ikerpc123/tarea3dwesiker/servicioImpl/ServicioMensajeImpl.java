package com.ikerpc123.tarea3dwesiker.servicioImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.tarea3dwesiker.modelo.Mensaje;
import com.ikerpc123.tarea3dwesiker.repositorios.MensajeRepository;
import com.ikerpc123.tarea3dwesiker.servicios.ServicioMensaje;

@Service
public class ServicioMensajeImpl implements ServicioMensaje{
	
	@Autowired
	MensajeRepository mensajerepo;

	@Override
	public void insertarMensaje(Mensaje mensaje) {
		mensajerepo.saveAndFlush(mensaje);
	}

}
