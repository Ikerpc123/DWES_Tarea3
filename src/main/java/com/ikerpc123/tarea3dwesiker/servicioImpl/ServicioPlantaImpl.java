package com.ikerpc123.tarea3dwesiker.servicioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.tarea3dwesiker.modelo.Planta;
import com.ikerpc123.tarea3dwesiker.repositorios.PlantaRepository;
import com.ikerpc123.tarea3dwesiker.servicios.ServicioEjemplar;
import com.ikerpc123.tarea3dwesiker.servicios.ServicioPlanta;

@Service
public class ServicioPlantaImpl implements ServicioPlanta{
	
	@Autowired
	PlantaRepository plantarepo;
	
	@Override
	public boolean validarPlanta(Planta p) {
		
		if(plantarepo.existeCodigo(p)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean insertarPlanta(Planta p) {
		try {
	        plantarepo.saveAndFlush(p);
	        return true;
	    } catch (Exception e) {
	        System.err.println("Error al insertar planta: " + e.getMessage());
	        return false;
	    }
	}
	
	@Override
	public boolean actualizar(Planta planta) {
		try {
            int filasActualizadas = plantarepo.actualizarDatos(planta.getNombreComun(), planta.getNombreCientifico(), planta.getCodigo());
            return filasActualizadas > 0;
        } catch (Exception e) {
            System.err.println("Error al actualizar la planta: " + e.getMessage());
            return false;
        }
    }
	
	public List<Planta> findAll()
	{
		return plantarepo.findAll();
	}
	
	@Override
	public Planta findByCodigo(String codigo) {
		return plantarepo.findByCodigo(codigo);
	}
}
