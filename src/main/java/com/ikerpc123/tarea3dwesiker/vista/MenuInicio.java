package com.ikerpc123.tarea3dwesiker.vista;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuInicio {
	
	@Autowired
	MenuPlanta menuPlanta;
	
	 public void mostrarMenuInicial() {
	        Scanner scanner = new Scanner(System.in);
	        int opcion = -1;

	        do {
	            System.out.println("\n======== Menú Principal ========");
	            System.out.println("  1. Insertar Planta");
	            System.out.println("  2. Ver Plantas");
	            System.out.println("  3. Salir");
	            System.out.println("================================");

	            boolean entradaValida = false;
	            while (!entradaValida) {
	                try {
	                    System.out.print("Seleccione una opción (1-3): ");
	                    opcion = Integer.parseInt(scanner.nextLine());
	                    if (opcion >= 1 && opcion <= 3) {
	                        entradaValida = true;
	                    } else {
	                        System.err.println("Error: Seleccione una opción válida entre 1 y 3.");
	                    }
	                } catch (NumberFormatException e) {
	                    System.err.println("Error: Ingrese un número válido.");
	                }
	            }

	            switch (opcion) {
	                case 1:
	                    menuPlanta.insertarPlanta();
	                    break;
	                case 2:
	                	menuPlanta.verPlantas();
	                    break;
	                case 3:
	                    System.out.println("\nSaliendo del programa... Hasta luego.");
	                    break;
	                default:
	                    System.err.println("Error: Opción no válida. Intente nuevamente.");
	            }

	        } while (opcion != 3);

	        scanner.close();
	    }
}
