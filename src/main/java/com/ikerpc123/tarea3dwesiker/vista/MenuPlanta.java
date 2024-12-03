package com.ikerpc123.tarea3dwesiker.vista;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikerpc123.tarea3dwesiker.modelo.Planta;
import com.ikerpc123.tarea3dwesiker.servicioImpl.ServicioPlantaImpl;

@Component
public class MenuPlanta {
	
	@Autowired
	ServicioPlantaImpl servplanta;
	
	/**
     * Método para ver todas las plantas registradas en el sistema.
     * 
     * @param plantaServicio Servicio para obtener las plantas.
     */
    public void verPlantas() {
        List<Planta> plantas = servplanta.findAll();
        if (plantas.isEmpty()) {
            System.out.println("No hay plantas registradas.");
        } else {
            System.out.println("\n======== Lista de Plantas ========");
            for (Planta planta : plantas) {
                System.out.println("Código: " + planta.getCodigo());
                System.out.println("Nombre Común: " + planta.getNombreComun());
                System.out.println("Nombre Científico: " + planta.getNombreCientifico());
                System.out.println("----------------------------------");
            }
        }
    }

    /**
     * Método para insertar una nueva planta en el sistema.
     * 
     * @param servplanta Servicio para agregar la planta.
     */
    public void insertarPlanta() {
        Scanner scanner = new Scanner(System.in);
        Planta p1 = new Planta();
        
        System.out.println("\n--- Insertar Nueva Planta ---");
        System.out.print("Ingrese el código de la planta: ");
        String codigo = scanner.nextLine().trim();
        p1.setCodigo(codigo.toUpperCase());

        System.out.print("Ingrese el nombre común de la planta: ");
        String nombreComun = scanner.nextLine().trim();
        p1.setNombreComun(nombreComun);

        System.out.print("Ingrese el nombre científico de la planta: ");
        String nombreCientifico = scanner.nextLine().trim();
        p1.setNombreCientifico(nombreCientifico);

        if (servplanta.insertarPlanta(p1)) {
            System.out.println("Planta insertada correctamente.");
        } else {
            System.err.println("Error al insertar la planta. Verifique los datos e intente nuevamente.");
        }
    }

    /**
     * Método para gestionar las plantas. Permite insertar, modificar o salir del menú de gestión de plantas.
     * 
     * @param plantaServicio Servicio para gestionar las plantas.
     */
	public void gestionarPlanta() {
	    Scanner scanner = new Scanner(System.in);
	    int opcion = -1;
	
	    do {
	        System.out.println("\n======== Gestión de Plantas ========");
	        System.out.println("  1. Insertar una nueva planta");
	        System.out.println("  2. Modificar una planta");
	        System.out.println("  3. Salir");
	        System.out.println("====================================");
	
	        // Validación de entrada
	        boolean entradaValida = false;
	        while (!entradaValida) {
	            try {
	                System.out.print("Seleccione una opción (1-3): ");
	                opcion = Integer.parseInt(scanner.nextLine());
	                if (opcion >= 1 && opcion <= 3) {
	                    entradaValida = true;
	                } else {
	                    System.out.println("Error: Seleccione una opción válida entre 1 y 3.");
	                }
	            } catch (NumberFormatException e) {
	                System.err.println("Error: Ingrese un número válido.");
	            }
	        }
	
	        switch (opcion) {
	            case 1:
	            	this.insertarPlanta();
	                break;
	            case 2:
	                //modificarPlanta(plantaServicio);
	            	System.out.print("Aquí iría el modificarPlanta");
	                break;
	            case 3:
	                System.err.println("Saliendo de la gestión de plantas...");
	                break;
	            default:
	                System.err.println("Opción no válida. Intente nuevamente.");
	        }
	    } while (opcion != 3);
	}
//
//    /**
//     * Método para modificar una planta existente en el sistema.
//     * 
//     * @param plantaServicio Servicio para actualizar la planta.
//     */
//    public void modificarPlanta(PlantaServicio plantaServicio) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("\n--- Modificar Planta ---");
//        System.out.print("Ingrese el código de la planta a modificar: ");
//        String codigo = scanner.nextLine().trim();
//
//        System.out.print("Ingrese el nuevo nombre común de la planta: ");
//        String nuevoNombreComun = scanner.nextLine().trim();
//
//        System.out.print("Ingrese el nuevo nombre científico de la planta: ");
//        String nuevoNombreCientifico = scanner.nextLine().trim();
//
//        Planta planta = new Planta(codigo, nuevoNombreComun, nuevoNombreCientifico);
//
//        boolean exito = plantaServicio.actualizar(planta);
//        if (exito) {
//            System.out.println("Planta actualizada exitosamente.");
//        } else {
//            System.err.println("Error al actualizar la planta. Verifique los datos ingresados.");
//        }
//    }

}
