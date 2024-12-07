package com.ikerpc123.tarea3dwesiker.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ikerpc123.tarea3dwesiker.modelo.Ejemplar;
import com.ikerpc123.tarea3dwesiker.modelo.Mensaje;
import com.ikerpc123.tarea3dwesiker.modelo.Persona;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long>{

	@Query("SELECT m FROM Mensaje m WHERE m.ejemplar = :ejemplar")
    List<Mensaje> findByEjemplar(@Param("ejemplar") Ejemplar ejemplar);
	
	@Query("SELECT m FROM Mensaje m WHERE m.persona = :persona")
    List<Mensaje> findByPersona(@Param("persona") Persona persona);
}
