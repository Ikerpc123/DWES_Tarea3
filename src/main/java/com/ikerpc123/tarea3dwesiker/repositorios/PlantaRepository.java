package com.ikerpc123.tarea3dwesiker.repositorios;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import com.ikerpc123.tarea3dwesiker.modelo.*;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long>{

	default boolean existeCodigo(Planta p) {
		List<Planta> listaPlantas = findAll();
		for(Planta aux: listaPlantas) {
			if(p.getCodigo().equals(aux.getCodigo()))
				return true;
		}
		return false;
	}
}
