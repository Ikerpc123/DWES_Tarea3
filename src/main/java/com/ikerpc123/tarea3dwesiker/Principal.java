package com.ikerpc123.tarea3dwesiker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.ikerpc123.tarea3dwesiker.modelo.*;
import com.ikerpc123.tarea3dwesiker.servicios.*;

public class Principal implements CommandLineRunner{
	
	@Autowired
	ServicioPlanta servplant;
	
	@Autowired
	ServicioEjemplar servejemplar;
	
	@Override
	public void run(String... args) throws Exception{
		
		System.out.println("INI");
		
		Planta p = new Planta();
		//servplant.validarplanta(p);
		System.out.println("---------------------");
		
		System.out.println("FIN");
	}
}
