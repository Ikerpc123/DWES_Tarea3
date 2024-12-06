package com.ikerpc123.tarea3dwesiker.servicioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.tarea3dwesiker.modelo.Ejemplar;
import com.ikerpc123.tarea3dwesiker.modelo.Planta;
import com.ikerpc123.tarea3dwesiker.repositorios.EjemplarRepository;
import com.ikerpc123.tarea3dwesiker.servicios.ServicioCredencial;
import com.ikerpc123.tarea3dwesiker.servicios.ServicioEjemplar;

@Service
public class ServicioEjemplarImpl implements ServicioEjemplar{
	
	@Autowired
	EjemplarRepository ejemplarrepo;
	
	public Long ultimoIdEjemplarByPlanta(Planta p) {
		if(p!=null)
			return ejemplarrepo.ultimoIdEjemplarByPlanta(p);
		else
			return null;
	}
	
	public void insertarEjemplar(Ejemplar e) {
		ejemplarrepo.saveAndFlush(e);
	}
	
	public List<Ejemplar> findAll() {
		return ejemplarrepo.findAll();
	}
}
