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
		
		Planta p1 = new Planta();
		p1.setNombreComun("ROSA");
		p1.setNombreCientifico("Rosae");
		
		if(!servplant.validarPlanta(p1))
			System.out.println("ERROR JAJAJAJA");
		
		Ejemplar ej1 = new Ejemplar();
//		ej1
		System.out.println("---------------------");
		
		System.out.println("FIN");
	}
}
