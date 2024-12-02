package com.ikerpc123.tarea3dwesiker.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.tarea3dwesiker.modelo.Planta;
import com.ikerpc123.tarea3dwesiker.repositorios.EjemplarRepository;
import com.ikerpc123.tarea3dwesiker.repositorios.PlantaRepository;

@Service
public class ServicioPlanta {

	@Autowired
	PlantaRepository plantarepo;
	
	ServicioEjemplar servejemplar;
	
	public boolean validarPlanta(Planta p) {
		
		if(plantarepo.existeCodigo(p)) {
			return false;
		}
		
		
		
		return true;
	}
	
	public void insertarPlanta(Planta p) {
		plantarepo.saveAndFlush(p);
		
	}
	
}
