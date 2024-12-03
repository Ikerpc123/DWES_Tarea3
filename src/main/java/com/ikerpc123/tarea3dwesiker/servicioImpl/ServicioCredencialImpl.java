package com.ikerpc123.tarea3dwesiker.servicioImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.tarea3dwesiker.modelo.Credencial;
import com.ikerpc123.tarea3dwesiker.repositorios.CredencialRepository;

@Service
public class ServicioCredencialImpl {

	@Autowired
	CredencialRepository credenrepo;
	
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
}
