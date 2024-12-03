package com.ikerpc123.tarea3dwesiker.vista;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikerpc123.tarea3dwesiker.servicioImpl.ServicioPlantaImpl;

@Component
public class MenuInicio {
	
	@Autowired
	MenuPlanta menuPlanta;
	
	public void mostrarMenuInicial() {
		
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        do {
        	System.out.println("\n======== Menú Principal ========");
            System.out.println("  1. Iniciar Sesión");
            System.out.println("  2. Entrar como Invitado");
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
                    this.menuAdmin();
                    break;
                case 2:
                	this.menuInvitado();
                    break;
                case 3:
                    System.err.println("\nSaliendo del programa... Hasta luego.");
                    break;
                default:
                    System.err.println("Error: Opción no válida. Intente nuevamente.");
            }

        } while (opcion != 3);

        scanner.close();
    }
	 
	public void menuAdmin() {
		
		Scanner scanner = new Scanner(System.in);
        int opcion = -1;
        do {
        	
            System.out.println("\n======= Menú Administrador =======");
            System.out.println("  1. Gestionar plantas");
            System.out.println("  2. Gestionar ejemplares");
            System.out.println("  3. Gestionar mensajes");
            System.out.println("  4. Registrar persona");
            System.out.println("  5. Cerrar Sesión");
            System.out.println("==================================");

            boolean entradaValida = false;
            while (!entradaValida) {
                try {
                    System.out.print("Seleccione una opción (1-5): ");
                    opcion = Integer.parseInt(scanner.nextLine());
                    if (opcion >= 1 && opcion <= 5) {
                        entradaValida = true;
                    } else {
                        System.err.println("Error: Seleccione una opción válida entre 1 y 5.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: Ingrese un número válido.");
                }
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Gestión de Plantas ---");
                    menuPlanta.gestionarPlanta();
                    break;
                case 2:
                    System.out.println("\n--- Gestión de Ejemplares ---");
                    //menuEjemplar.mostrarMenu();
                    break;
                case 3:
                    System.out.println("\n--- Gestión de Mensajes ---");
                    //menuMensajes.mostrarMenuMensajes();
                    break;
                case 4:
                    System.out.println("\n--- Registro de Persona ---");
                    //menuRegistro.mostrarMenuRegistro();
                    break;
                case 5:
                    System.err.println("Cerrando sesión...");
                    break;
                default:
                    System.err.println("Error: Opción no válida.");
                    break;
            }

        } while (opcion != 5);

	        System.err.println("Sesión finalizada. Hasta luego.");
	}
	 
	public void menuInvitado() {
		
		Scanner scanner = new Scanner(System.in);
		int opcion = -1;

		do {
			
			System.out.println("\n======== Menú Invitado ========");
			System.out.println("  1. Ver Plantas");
			System.out.println("  2. Volver");
			System.out.println("================================");
		
			// Validación de entrada
			boolean entradaValida = false;
			while (!entradaValida) {
				try {
					System.out.print("Seleccione una opción (1-2): ");
					opcion = Integer.parseInt(scanner.nextLine());
					if (opcion >= 1 && opcion <= 2) {
						entradaValida = true;
					} else {
						System.err.println("Error: Seleccione una opción válida entre 1 y 2.");
					}
				} catch (NumberFormatException e) {
					System.err.println("Error: Ingrese un número válido.");
				}
			}
		
			switch (opcion) {
			 	
        		case 1:
        			menuPlanta.verPlantas();
        			break;
		        case 2:
		            System.err.println("\nVolviendo al menú principal...");
		            break;
		        default:
		            System.err.println("Opción no válida. Intente nuevamente.");
			}
		 
		} while (opcion != 2);
	}
}
