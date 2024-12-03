package com.ikerpc123.tarea3dwesiker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.ikerpc123.tarea3dwesiker.modelo.*;
import com.ikerpc123.tarea3dwesiker.servicioImpl.ServicioEjemplarImpl;
import com.ikerpc123.tarea3dwesiker.servicioImpl.ServicioPlantaImpl;
import com.ikerpc123.tarea3dwesiker.servicios.*;
import com.ikerpc123.tarea3dwesiker.vista.MenuInicio;

public class Principal implements CommandLineRunner{
	
	@Autowired
	ServicioPlantaImpl servplanta;
	@Autowired
	ServicioEjemplarImpl servejemplar;
	@Autowired
	MenuInicio menuInicio;
	
	@Override
	public void run(String... args) throws Exception{
		
		System.out.println("INI");
		
		
		System.out.println("---------------------");
		
		menuInicio.mostrarMenuInicial();
		
		
		System.out.println("FIN");
	}
}
