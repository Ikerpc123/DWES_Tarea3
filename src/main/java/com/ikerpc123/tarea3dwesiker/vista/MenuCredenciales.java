package com.ikerpc123.tarea3dwesiker.vista;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikerpc123.tarea3dwesiker.modelo.Credencial;
import com.ikerpc123.tarea3dwesiker.modelo.Persona;
import com.ikerpc123.tarea3dwesiker.servicioImpl.ServicioCredencialImpl;
import com.ikerpc123.tarea3dwesiker.servicioImpl.ServicioPersonaImpl;

@Component
public class MenuCredenciales {
	
	@Autowired
	MenuPlanta menuPlanta;
	@Autowired
	ServicioCredencialImpl servCredencial;
	@Autowired
	ServicioPersonaImpl servPersona;
	@Autowired
	MenuEjemplar menuEjemplar;
	@Autowired
	MenuMensaje menuMensajes;
	
	String usuario;
	
	public void iniciarSesion(Scanner scanner) {
        System.out.print("\nIngrese el nombre de usuario: ");
        usuario = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String contra = scanner.nextLine();

        if (servCredencial.validar(usuario, contra)) {
            System.out.println("Inicio de sesión exitoso.");

            if (servCredencial.esAdministrador(usuario, contra)) {
                menuAdmin(scanner);
            } else {
                menuPersonal(scanner);
            }
        } else {
            System.err.println("Credenciales incorrectas. Intente nuevamente.");
        }
    }
	
	public void menuAdmin(Scanner scanner) {
		
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
                    menuPlanta.gestionarPlanta();
                    break;
                case 2:
                    menuEjemplar.mostrarMenu(usuario);
                    break;
                case 3:
                    menuMensajes.mostrarMenuMensajes(usuario);
                    break;
                case 4:
                    this.menuRegistro(scanner);
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
	
	public void menuPersonal(Scanner scanner) {
        int opcion = -1;

        do {
            System.out.println("\n======== Menú Personal ========");
            System.out.println("  1. Gestionar ejemplares");
            System.out.println("  2. Gestionar mensajes");
            System.out.println("  3. Cerrar Sesión");
            System.out.println("================================");

            // Validación de entrada del usuario
            boolean entradaValida = false;
            while (!entradaValida) {
                try {
                    System.out.print("Seleccione una opción (1-3): ");
                    opcion = Integer.parseInt(scanner.nextLine());
                    if (opcion >= 1 && opcion <= 3) {
                        entradaValida = true; // Opción válida, salimos del bucle
                    } else {
                        System.err.println("Error: Seleccione una opción válida entre 1 y 3.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: Ingrese un número válido.");
                }
            }

            switch (opcion) {
                case 1:
                    menuEjemplar.mostrarMenu(usuario);
                    break;
                case 2:
                    menuMensajes.mostrarMenuMensajes(usuario);
                    break;
                case 3:
                    System.err.println("\nCerrando sesión... Hasta luego.");
                    break;
                default:
                    System.err.println("Error: Opción no válida.");
            }

        } while (opcion != 3);
    }
	
	public void menuRegistro(Scanner scanner) {

        System.out.println("\n--- Registro de Nueva Persona ---");

        System.out.print("Ingrese el nombre real de la persona: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el email de la persona: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese el nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine();

        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setEmail(email);

        Credencial credencial = new Credencial();
        
        if (servPersona.insertarPersona(persona)) {
        	
        	persona = servPersona.findByEmail(email);
        	
        	credencial.setPassword(password);
        	credencial.setUsuario(nombreUsuario);
        	credencial.setPersona(persona);
        	
        	if(servCredencial.insertarCredencial(credencial))
        		System.out.println("Registro exitoso. La persona ha sido añadida al sistema.");
        	else
        		System.err.println("El registro ha fallado. Verifique los datos e intente nuevamente.");
        } 
        else {
            System.err.println("El registro ha fallado. Verifique los datos e intente nuevamente.");
        }
    }
}
