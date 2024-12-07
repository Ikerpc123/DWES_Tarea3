package com.ikerpc123.tarea3dwesiker.servicioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.tarea3dwesiker.modelo.Ejemplar;
import com.ikerpc123.tarea3dwesiker.modelo.Mensaje;
import com.ikerpc123.tarea3dwesiker.modelo.Persona;
import com.ikerpc123.tarea3dwesiker.repositorios.MensajeRepository;
import com.ikerpc123.tarea3dwesiker.servicios.ServicioMensaje;

@Service
public class ServicioMensajeImpl implements ServicioMensaje{
	
	@Autowired
	MensajeRepository mensajerepo;

	@Override
	public boolean insertarMensaje(Mensaje mensaje) {
		try {
			mensajerepo.saveAndFlush(mensaje);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	@Override
	public List<Mensaje> findAll() {
		return mensajerepo.findAll();
	}
	
	@Override
	public List<Mensaje> findByEjemplar(Ejemplar ejemplar) {
		return mensajerepo.findByEjemplar(ejemplar);
	}

	@Override
	public List<Mensaje> findByPersona(Persona persona) {
		return mensajerepo.findByPersona(persona);
	}


}
