package com.ikerpc123.tarea3dwesiker.repositorios;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.ikerpc123.tarea3dwesiker.modelo.*;

import jakarta.transaction.Transactional;

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
	
	@Query("SELECT p FROM Planta p WHERE p.codigo = :codigo")
	Planta findByCodigo(@Param("codigo") String codigo);
	
	@Modifying
    @Transactional
    @Query("UPDATE Planta p SET p.nombreComun = :nombreComun, p.nombreCientifico = :nombreCientifico WHERE p.codigo = :codigo")
    int actualizarDatos(@Param("nombreComun") String nombreComun, 
                        @Param("nombreCientifico") String nombreCientifico,
                        @Param("codigo") String codigo);
}
