package com.ikerpc123.tarea3dwesiker.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

import com.ikerpc123.tarea3dwesiker.modelo.*;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long>{

}
