package com.ikerpc123.tarea3dwesiker.servicios;

import com.ikerpc123.tarea3dwesiker.modelo.Credencial;

public interface ServicioCredencial {

	public boolean validar(String usuario, String password);
	public boolean esAdministrador(String usuario, String password);
	public boolean insertarCredencial(Credencial c);
	Credencial findByUsuario(String usuario);
}
