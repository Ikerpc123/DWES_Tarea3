package com.ikerpc123.tarea3dwesiker.servicioImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.tarea3dwesiker.modelo.Persona;
import com.ikerpc123.tarea3dwesiker.repositorios.PersonaRepository;
import com.ikerpc123.tarea3dwesiker.servicios.ServicioPersona;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServicioPersonaImpl implements ServicioPersona{

	@Autowired
	PersonaRepository personarepo;
	
	@Override
	public boolean validarEmail(String email) {
		
		return false;
	}

	@Override
	public boolean insertarPersona(Persona p) {
		try {
			personarepo.saveAndFlush(p);
	        return true;
	    } catch (Exception e) {
	        System.err.println("Error en el registro: " + e.getMessage());
	        return false;
	    }
	}

	@Override
	public Persona findByEmail(String email) {
		
		return personarepo.findByEmail(email);
	}

}
