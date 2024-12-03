package com.ikerpc123.tarea3dwesiker.servicios;

public interface ServicioCredencial {

	public boolean validar(String usuario, String password);
	public boolean esAdministrador(String usuario, String password);
}
