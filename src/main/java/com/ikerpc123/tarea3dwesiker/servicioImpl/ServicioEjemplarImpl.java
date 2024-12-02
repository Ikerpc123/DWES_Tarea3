package com.ikerpc123.tarea3dwesiker.servicioImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.tarea3dwesiker.modelo.Ejemplar;
import com.ikerpc123.tarea3dwesiker.modelo.Planta;
import com.ikerpc123.tarea3dwesiker.repositorios.EjemplarRepository;

@Service
public class ServicioEjemplarImpl {
	
	@Autowired
	EjemplarRepository ejemplarrepo;
	
	public Long ultimoIdEjemplarByPlanta(Planta p) {
		if(p!=null)
			return ejemplarrepo.ultimoIdEjemplarByPlanta(p);
		else
			return null;
	}
	
	public void actualizar(Ejemplar e) {
		ejemplarrepo.saveAndFlush(e);
	}
}
