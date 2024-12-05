package com.ikerpc123.tarea3dwesiker.servicioImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.tarea3dwesiker.modelo.Credencial;
import com.ikerpc123.tarea3dwesiker.repositorios.CredencialRepository;
import com.ikerpc123.tarea3dwesiker.servicios.ServicioCredencial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServicioCredencialImpl implements ServicioCredencial{

	@Autowired
	CredencialRepository credenrepo;
	@Autowired
	ServicioPersonaImpl servPersona;
	@PersistenceContext
    private EntityManager entityManager;
	
	public boolean validar(String usuario, String password) {
        Credencial credencial = credenrepo.findByUsuario(usuario);
        
        return credencial != null && credencial.getPassword().equals(password);
    }
	
	public boolean esAdministrador(String usuario, String password) {
        Credencial credencial = credenrepo.findByUsuario(usuario);
        
        return credencial != null 
                && "admin".equals(credencial.getUsuario()) 
                && "admin".equals(credencial.getPassword());
    }
	
	public boolean insertarCredencial(Credencial c)
	{
		try {
	        if (!entityManager.contains(c.getPersona())) {
	            c.setPersona(entityManager.merge(c.getPersona()));
	        }
	        credenrepo.saveAndFlush(c);
	        return true;
	    } catch (Exception e) {
	        System.err.println("Error al insertar credenciales: " + e.getMessage());
	        return false;
	    }
	}
}
