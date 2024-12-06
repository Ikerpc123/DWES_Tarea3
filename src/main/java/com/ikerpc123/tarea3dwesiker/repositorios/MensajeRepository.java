package com.ikerpc123.tarea3dwesiker.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikerpc123.tarea3dwesiker.modelo.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long>{
	
	

}
