package com.ikerpc123.tarea3dwesiker.vista;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
import com.ikerpc123.tarea3dwesiker.servicioImpl.ServicioCredencialImpl;
import com.ikerpc123.tarea3dwesiker.servicioImpl.ServicioEjemplarImpl;
import com.ikerpc123.tarea3dwesiker.servicioImpl.ServicioMensajeImpl;
import com.ikerpc123.tarea3dwesiker.servicioImpl.ServicioPersonaImpl;

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
	
	/**
     * Método que muestra el menú principal para la gestión de mensajes, permitiendo al usuario 
     * anotar un mensaje de seguimiento, mostrar mensajes filtrados o salir.
     */
    public void mostrarMenuMensajes(String usuario) {
    	Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Gestión de Mensajes ---");
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
            System.out.println("\n--- Filtrar Mensajes ---");
            System.out.println("1. Filtrar por persona");
            System.out.println("2. Filtrar por rango de fechas");
            System.out.println("3. Filtrar por tipo de planta");
            System.out.println("4. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = leerOpcion(scanner);

            // Filtrar los mensajes según la opción seleccionada por el usuario
            switch (opcion) {
                case 1:
                    filtrarPorPersona(usuario); // Filtrar mensajes por persona
                    break;
                case 2:
                    //filtrarPorRangoFechas(); // Filtrar mensajes por rango de fechas
                    break;
                case 3:
                    //filtrarPorTipoPlanta(); // Filtrar mensajes por tipo de planta
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
     */
//    private void filtrarPorRangoFechas() {
//        try {
//            System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
//            String fechaInicio = scanner.nextLine();
//            System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
//            String fechaFin = scanner.nextLine();
//
//            Set<Mensaje> mensajes = mensajeServicio.filtrarMensajesPorRangoFechas(fechaInicio, fechaFin);
//            mostrarMensajes(mensajes);
//        } catch (Exception e) {
//            System.err.println("Formato de fecha inválido.");
//        }
//    }

    /**
     * Filtra los mensajes por el código de la planta relacionada.
     */
//    private void filtrarPorTipoPlanta() {
//        System.out.print("Ingrese el código de la planta: ");
//        String codigoPlanta = scanner.nextLine();
//        Set<Mensaje> mensajes = mensajeServicio.filtrarMensajesPorTipoPlanta(codigoPlanta);
//        mostrarMensajes(mensajes);
//    }

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
                String nombrePersona = (persona != null) ? persona.getNombre() : "Desconocido";
                System.out.printf("Fecha: %s | Mensaje: %s | Persona: %s%n",
                        mensaje.getFechahora(), mensaje.getMensaje(), nombrePersona);
            }
        }
    }

    // Métodos auxiliares para manejo de entradas

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

    /**
     * Lee un valor numérico largo de la entrada del usuario.
     * 
     * @return El valor ingresado por el usuario.
     */
//    private long leerLong() {
//        try {
//            return scanner.nextLong();
//        } catch (InputMismatchException e) {
//            System.err.println("Entrada inválida. Ingrese un número válido.");
//            scanner.nextLine();
//            return -1;
//        }
//    }
}
