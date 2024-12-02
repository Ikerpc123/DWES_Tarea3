package com.ikerpc123.tarea3dwesiker.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

import com.ikerpc123.tarea3dwesiker.modelo.*;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long>{

	default Long ultimoIdEjemplarByPlanta(Planta p) {
		List<Ejemplar> lista = findAll();
		if(!lista.isEmpty()) {
			long ret = 0;
			for(Ejemplar e : lista)
//				if(e.getPlanta().getId().equals(p.getId()))
					ret++;
			return ret;
		}
		return 0L;
	}
}
