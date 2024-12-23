package com.ikerpc123.tarea3dwesiker.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;

import com.ikerpc123.tarea3dwesiker.modelo.*;

import jakarta.transaction.Transactional;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long>{

	@Query("SELECT e FROM Ejemplar e WHERE e.nombre = :nombre")
	Ejemplar findByNombre(@Param("nombre") String nombre);
	
	@Transactional
	@Modifying
	@Query("UPDATE Ejemplar e SET e.nombre = :nombre WHERE e.id = :id")
	int actualizarNombreEjemplar(@Param("id") Long id, @Param("nombre") String nombre);
	
	@Query("SELECT e FROM Ejemplar e WHERE e.planta = :planta")
	List<Ejemplar> findByPlanta(@Param("planta") Planta planta);
	
	default Long ultimoIdEjemplarByPlanta(Planta p) {
		List<Ejemplar> lista = findAll();
		if(!lista.isEmpty()) {
			long ret = 0;
			for(Ejemplar e : lista)
				if(e.getIdPlanta().getId().equals(p.getId()))
					ret++;
			return ret + 1;
		}
		return 1L;
	}

}
