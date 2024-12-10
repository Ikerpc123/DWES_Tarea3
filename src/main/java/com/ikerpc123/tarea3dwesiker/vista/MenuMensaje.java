package com.ikerpc123.tarea3dwesiker.vista;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikerpc123.tarea3dwesiker.modelo.Credencial;
import com.ikerpc123.tarea3dwesiker.modelo.Ejemplar;
import com.ikerpc123.tarea3dwesiker.modelo.Mensaje;
import com.ikerpc123.tarea3dwesiker.modelo.Persona;
import com.ikerpc123.tarea3dwesiker.modelo.Planta;
import com.ikerpc123.tarea3dwesiker.servicioImpl.ServicioCredencialImpl;
import com.ikerpc123.tarea3dwesiker.servicioImpl.ServicioEjemplarImpl;
import com.ikerpc123.tarea3dwesiker.servicioImpl.ServicioMensajeImpl;
import com.ikerpc123.tarea3dwesiker.servicioImpl.ServicioPersonaImpl;
import com.ikerpc123.tarea3dwesiker.servicioImpl.ServicioPlantaImpl;

@Component
public class MenuMensaje {
	
	@Autowired
	ServicioEjemplarImpl ejemplarServicio;
	@Autowired
	ServicioPersonaImpl personaServivio;
	@Autowired
	ServicioMensajeImpl mensajeServicio;
	@Autowired
	ServicioCredencialImpl credenServicio;
	@Autowired
	ServicioPlantaImpl plantaServicio;
	
	/**
     * Método que muestra el menú principal para la gestión de mensajes, permitiendo al usuario 
     * anotar un mensaje de seguimiento, mostrar mensajes filtrados o salir.
     */
    public void mostrarMenuMensajes(String usuario) {
    	Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Gestión de Mensajes (Usuario: "+usuario+")---");
            System.out.println("1. Anotar mensaje de seguimiento");
            System.out.println("2. Mostrar mensajes filtrados");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerOpcion(scanner);

            // Procesar la opción seleccionada por el usuario
            switch (opcion) {
                case 1:
                    anotarMensajeSeguimiento(scanner, usuario); // Opción para anotar un mensaje
                    break;
                case 2:
                    mostrarMensajesFiltrados(usuario); // Opción para mostrar mensajes filtrados
                    break;
                case 3:
                    System.out.println("Saliendo del menú de mensajes...");
                    break;
                default:
                    System.err.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 3);
    }

    /**
     * Método para anotar un nuevo mensaje de seguimiento.
     * Permite ingresar el ID de un ejemplar, el ID de la persona que realiza la anotación 
     * y el contenido del mensaje. Luego, el mensaje es guardado en el sistema.
     */
    private void anotarMensajeSeguimiento(Scanner scanner, String usuario) {
        LocalDateTime fechaActual = LocalDateTime.now();
        Ejemplar ejemplar = new Ejemplar();
        String nombreEjemplar;
        
        try {
        	// Mostrar todos los ejemplares disponibles
            List<Ejemplar> ejemplares = ejemplarServicio.findAll();
            if (ejemplares.isEmpty()) {
                System.err.println("No hay ejemplares disponibles.");
                return;
            }

            System.out.println("\n--- Ejemplares Disponibles ---");
            for (int i = 0; i < ejemplares.size(); i++) {
                ejemplar = ejemplares.get(i);
                System.out.printf("[%d] %s (Planta: %s)%n", 
                                  i + 1, 
                                  ejemplar.getNombre(), 
                                  ejemplar.getIdPlanta().getNombreComun());
            }
            
            nombreEjemplar = ejemplar.getNombre();

            System.out.print("\nSeleccione un ejemplar por número: ");
            int seleccion = scanner.nextInt();
            scanner.nextLine();

            if (seleccion < 1 || seleccion > ejemplares.size()) {
                System.err.println("Selección inválida.");
                return;
            }

            Credencial credencial = credenServicio.findByUsuario(usuario);
            Persona persona = personaServivio.findById(credencial.getPersona().getId());
            
            ejemplar = ejemplarServicio.findByNombre(nombreEjemplar);

            System.out.print("Ingrese el mensaje de seguimiento: ");
            String contenidoMensaje = scanner.nextLine();

            Mensaje mensaje = new Mensaje(Date.from(fechaActual.atZone(ZoneId.systemDefault()).toInstant()), contenidoMensaje, persona, ejemplar);

            if (mensajeServicio.insertarMensaje(mensaje)) {
                System.out.println("Mensaje registrado exitosamente.");
            } else {
                System.err.println("Error al registrar el mensaje.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Por favor, ingrese datos correctos.");
            scanner.nextLine();
        }
    }

    /**
     * Método para mostrar los mensajes filtrados según diferentes criterios.
     * El usuario puede elegir filtrar los mensajes por persona, rango de fechas, 
     * tipo de planta o volver al menú anterior.
     */
    private void mostrarMensajesFiltrados(String usuario) {
        int opcion;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\n--- Filtrar Mensajes (Usuario: "+usuario+")---");
            System.out.println("1. Filtrar por usuario actual");
            System.out.println("2. Filtrar por rango de fechas");
            System.out.println("3. Filtrar por tipo de planta");
            System.out.println("4. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = leerOpcion(scanner);

            
            switch (opcion) {
                case 1:
                    filtrarPorPersona(usuario);
                    break;
                case 2:
                    filtrarPorRangoFechas(usuario);
                    break;
                case 3:
                    filtrarPorTipoPlanta(); // Filtrar mensajes por tipo de planta
                    break;
                case 4:
                    System.out.println("Volviendo al menú anterior...");
                    break;
                default:
                    System.err.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    /**
     * Filtra los mensajes por el ID de la persona que los realizó.
     */
    private void filtrarPorPersona(String usuario) {
    	Credencial credencial = credenServicio.findByUsuario(usuario);
    	Persona persona = personaServivio.findById(credencial.getPersona().getId());
    	
        List<Mensaje> mensajes = mensajeServicio.findByPersona(persona);
        mostrarMensajes(mensajes, persona);
    }

    /**
     * Filtra los mensajes dentro de un rango de fechas dado por el usuario.
     * @throws ParseException 
     */
    private void filtrarPorRangoFechas(String usuario) {
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	Credencial credencial = credenServicio.findByUsuario(usuario);
    	Persona persona = personaServivio.findById(credencial.getPersona().getId());
    	
    	System.out.println("Ingrese la fecha de inicio (formato: yyyy-MM-dd): ");
        String fechaInicio = scanner.nextLine().trim();

        System.out.println("Ingrese la fecha de fin (formato: yyyy-MM-dd): ");
        String fechaFin = scanner.nextLine().trim();

        List<Mensaje> mensajes = new ArrayList<>();
		try {
			mensajes = mensajeServicio.findByFechaRango(fechaInicio, fechaFin);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}

        mostrarMensajes(mensajes, persona);
    }

    /**
     * Filtra los mensajes por el código de la planta relacionada.
     */
    private void filtrarPorTipoPlanta() {
    	Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el código de la planta: ");
        String codigoPlanta = scanner.nextLine().toUpperCase();
        
        Planta planta = plantaServicio.findByCodigo(codigoPlanta);
        List<Ejemplar> ejemplares = ejemplarServicio.findByPlanta(planta);
        
        if (ejemplares.isEmpty())
        	System.err.println("No se han encontrado ejemplares con el código insertado");
        
        else {
	        for (Ejemplar ejemplar : ejemplares) {
	            List<Mensaje> mensajes = mensajeServicio.findByEjemplar(ejemplar);
	
	            for (Mensaje mensaje : mensajes) {
	                System.out.printf("%nEjemplar: %s  |  Fecha: %s  |  Mensaje: %s  |  Autor: %s"
	                				+ "%n--------------------------------------------------------------------%n",
	                                  ejemplar.getNombre(),
	                                  mensaje.getFechahora(),
	                                  mensaje.getMensaje(),
	                                  mensaje.getPersona().getNombre());
	            }
	        }
        }
    }

    /**
     * Muestra los mensajes filtrados por el usuario, con la información relevante 
     * como la fecha, el contenido y el nombre de la persona que realizó la anotación.
     * 
     * @param mensajes Conjunto de mensajes a mostrar.
     */
    private void mostrarMensajes(List<Mensaje> mensajes, Persona persona) {
        if (mensajes.isEmpty()) {
            System.out.println("No se encontraron mensajes.");
        } else {
            System.out.println("\n--- Mensajes de seguimiento ---");
            for (Mensaje mensaje : mensajes) {
                System.out.printf("Fecha: %s | Mensaje: %s |%n",
                        mensaje.getFechahora(), mensaje.getMensaje());
            }
        }
    }

    /**
     * Lee una opción numérica de la entrada del usuario.
     * 
     * @return La opción seleccionada por el usuario.
     */
    private int leerOpcion(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Ingrese un número.");
            scanner.nextLine();
            return -1;
        }
    }
}
