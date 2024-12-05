package com.ikerpc123.tarea3dwesiker.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ikerpc123.tarea3dwesiker.modelo.Persona;
import com.ikerpc123.tarea3dwesiker.modelo.Planta;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{

	@Query("SELECT p FROM Persona p WHERE p.email = :email")
	Persona findByEmail(@Param("email") String email);
	
	default boolean existsByEmail(Persona p) {
		List<Persona> listaPersonas = findAll();
		for(Persona aux: listaPersonas) {
			if(p.getEmail().equals(aux.getEmail()))
				return true;
		}
		return false;
	}
}
