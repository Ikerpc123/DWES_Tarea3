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
		
//		Planta p1 = new Planta();
//		p1.setNombreComun("Margarita");
//		p1.setCodigo(p1.getNombreComun().toUpperCase());
//		p1.setNombreCientifico("Margaritae");
//		
//		if(!servplanta.validarPlanta(p1))
//			System.out.println("ERROR JAJAJAJA");
//		
//		Ejemplar ej1 = new Ejemplar();
//		ej1.setIdPlanta(p1);
//		p1.getEjemplares().add(ej1);
//		
//		servplanta.insertarPlanta(p1);
//		
//		String nombreejemplar = p1.getNombreComun().toUpperCase()+"_"+ servejemplar.ultimoIdEjemplarByPlanta(p1);
//		ej1.setNombre(nombreejemplar);
//		servejemplar.actualizar(ej1);
		
		System.out.println("---------------------");
		
		menuInicio.mostrarMenuInicial();
		
		
		System.out.println("FIN");
	}
}
