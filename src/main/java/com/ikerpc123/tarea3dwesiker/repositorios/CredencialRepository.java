package com.ikerpc123.tarea3dwesiker.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ikerpc123.tarea3dwesiker.modelo.Credencial;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long>{

	@Query("SELECT c FROM Credencial c WHERE c.usuario = :usuario")
	Credencial findByUsuario(@Param("usuario") String usuario);
}
