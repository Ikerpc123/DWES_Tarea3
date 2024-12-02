package com.ikerpc123.tarea3dwesiker.servicioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.tarea3dwesiker.modelo.Planta;
import com.ikerpc123.tarea3dwesiker.repositorios.PlantaRepository;
import com.ikerpc123.tarea3dwesiker.servicios.ServicioEjemplar;

@Service
public class ServicioPlantaImpl {
	
	@Autowired
	PlantaRepository plantarepo;
	
	ServicioEjemplar servejemplar;
	
	public boolean validarPlanta(Planta p) {
		
		if(plantarepo.existeCodigo(p)) {
			return false;
		}
		
		return true;
	}
	
	public boolean insertarPlanta(Planta p) {
		try {
	        plantarepo.saveAndFlush(p);
	        return true;
	    } catch (Exception e) {
	        System.err.println("Error al insertar planta: " + e.getMessage());
	        return false;
	    }
	}
	
	public List<Planta> findAll()
	{
		return plantarepo.findAll();
	}
}
