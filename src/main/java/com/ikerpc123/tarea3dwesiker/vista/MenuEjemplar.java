package com.ikerpc123.tarea3dwesiker.vista;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
public class MenuEjemplar {
	
	@Autowired
	ServicioPlantaImpl plantaServicio;
	@Autowired
	ServicioEjemplarImpl ejemplarServicio;
	@Autowired
	ServicioCredencialImpl credenServicio;
	@Autowired
	ServicioPersonaImpl personaServivio;
	@Autowired
	ServicioMensajeImpl mensajeServicio;
	
	public void mostrarMenu(String usuario) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n======= Gestión Ejemplares (Usuario: "+usuario+") =======");
            System.out.println("  1. Registrar nuevo ejemplar");
            System.out.println("  2. Filtrar por tipo de planta");
            System.out.println("  3. Ver mensajes de un ejemplar");
            System.out.println("  4. Volver");
            System.out.println("==================================");

            opcion = leerOpcion(scanner, 1, 4);

            switch (opcion) {
                case 1:
                    registrarEjemplar(scanner, usuario);
                    break;
                case 2:
                    filtrarPlanta(scanner);
                    break;
                case 3:
                    verMensajesDeSeguimiento(scanner);
                    break;
                case 4:
                    System.err.println("\nVolviendo al menú principal...");
                    break;
            }
        } while (opcion != 4);
    }

    /**
     * Método para registrar un nuevo ejemplar en el sistema.
     * El ejemplar se asocia a una planta existente, y se crea un mensaje de seguimiento
     * que se almacena en el sistema.
     * 
     * @param scanner El objeto Scanner usado para leer la entrada del usuario.
     */
    private void registrarEjemplar(Scanner scanner, String usuario) {
        Date fechaActual = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formatoFecha.format(fechaActual);

        try {
            System.out.println("\n--- Registro de Ejemplar ---");
            System.out.print("Ingrese el identificador de la planta: ");
            String codigo = scanner.nextLine().trim();

            Planta planta = plantaServicio.findByCodigo(codigo);
            if (planta != null) {
            	
                Ejemplar nuevoEjemplar = new Ejemplar();
                nuevoEjemplar.setIdPlanta(planta);
                
                Long planta_id = ejemplarServicio.ultimoIdEjemplarByPlanta(planta);
                nuevoEjemplar.setNombre(planta.getCodigo().toUpperCase() + "_" + planta_id.toString());
                
                ejemplarServicio.insertarEjemplar(nuevoEjemplar);
                
                List<Ejemplar> ejemplares = ejemplarServicio.findAll();
                Ejemplar ultimoEjemplar = ejemplares.get(ejemplares.size() - 1);

                Credencial credencial = credenServicio.findByUsuario(usuario);
                Persona persona = personaServivio.findById(credencial.getPersona().getId());
                Mensaje mensaje = new Mensaje(fechaActual, "Primer mensaje", 
                                              persona, ultimoEjemplar);
                
                mensajeServicio.insertarMensaje(mensaje);
                System.out.println("Ejemplar registrado exitosamente.");
            } else {
                System.err.println("No existe una planta con el identificador proporcionado.");
            }
        } catch (Exception e) {
            System.err.println("Error al registrar el ejemplar: " + e.getMessage());
        }
    }

    /**
     * Método para filtrar los ejemplares por el tipo de planta, permitiendo al usuario ingresar
     * códigos de plantas y mostrando una lista de ejemplares asociados a las plantas seleccionadas.
     * 
     * @param scanner El objeto Scanner usado para leer la entrada del usuario.
     */
    private void filtrarPlanta(Scanner scanner) {
        try {
            List<String> plantas = new ArrayList<>();
            String input;

            while (true) {
                System.out.print("Ingrese un código de planta (escriba 'fin' para terminar): ");
                input = scanner.nextLine().trim().toUpperCase();

                if (input.equalsIgnoreCase("fin")) {
                    break;
                }

                plantas.add(input);
            }

            List<Planta> plantasSeleccionadas = new ArrayList<>();
            for (String codigo : plantas) {
                Planta planta = plantaServicio.findByCodigo(codigo);
                if (planta != null) {
                    plantasSeleccionadas.add(planta);
                } else {
                    System.err.println("Planta con código '" + codigo + "' no encontrada.");
                }
            }

            if (!plantasSeleccionadas.isEmpty()) {
                mostrarTablaEjemplares(plantasSeleccionadas);
            } else {
                System.err.println("No se encontraron plantas válidas.");
            }
        } catch (Exception e) {
            System.err.println("Error al filtrar plantas: " + e.getMessage());
        }
    }

    /**
     * Método que muestra una tabla con los ejemplares asociados a las plantas seleccionadas.
     * Muestra el nombre del ejemplar, la planta asociada, el número de mensajes y la fecha del último mensaje.
     * 
     * @param plantas La lista de plantas seleccionadas para filtrar los ejemplares.
     */
    private void mostrarTablaEjemplares(List<Planta> plantas) {
        System.out.printf("\n%-20s %-15s %-25s %s%n", 
                          "Nombre Ejemplar", "Planta", "Nº Mensajes", "Último Mensaje");
        System.out.println("-------------------------------------------------------------------------------");

        for (Planta planta : plantas) {
            List<Ejemplar> ejemplares = ejemplarServicio.findAll();

            for (Ejemplar ejemplar : ejemplares) {
                if (ejemplar.getIdPlanta().getCodigo().contentEquals(planta.getCodigo())) {
                    int numMensajes = contarMensajes(ejemplar);
                    Date ultimaFecha = obtenerUltimaFechaMensaje(ejemplar);
                    
                    System.out.printf("%-20s %-15s %-25d %s%n",
                                      ejemplar.getNombre(),
                                      planta.getNombreComun(),
                                      numMensajes,
                                      (ultimaFecha != null ? ultimaFecha.toString() : "Sin mensajes"));
                }
            }
        }
    }

    /**
     * Método para contar el número de mensajes asociados a un ejemplar.
     * 
     * @param ejemplar El ejemplar para el cual contar los mensajes.
     * @return El número de mensajes asociados al ejemplar.
     */
    private int contarMensajes(Ejemplar ejemplar) {
        List<Mensaje> mensajes = mensajeServicio.findAll();
        return (int) mensajes.stream().filter(m -> m.getEjemplar().getId() == ejemplar.getId()).count();
    }

    /**
     * Método para obtener la última fecha de mensaje asociada a un ejemplar.
     * 
     * @param ejemplar El ejemplar del cual obtener la última fecha de mensaje.
     * @return La fecha del último mensaje, o {@code null} si no existen mensajes.
     */
    private Date obtenerUltimaFechaMensaje(Ejemplar ejemplar) {
        List<Mensaje> mensajes = mensajeServicio.findAll();
        return mensajes.stream()
                .filter(m -> m.getEjemplar().getId() == ejemplar.getId())
                .map(Mensaje::getFechahora)
                .max(Date::compareTo)
                .orElse(null);
    }

    /**
     * Método para visualizar los mensajes de seguimiento asociados a un ejemplar específico.
     * Muestra todos los mensajes de seguimiento, incluyendo la fecha, contenido y autor del mensaje.
     * 
     * @param scanner El objeto Scanner usado para leer la entrada del usuario.
     */
    private void verMensajesDeSeguimiento(Scanner scanner) {
        try {
            // Mostrar todos los ejemplares disponibles
            List<Ejemplar> ejemplares = ejemplarServicio.findAll();
            if (ejemplares.isEmpty()) {
                System.err.println("No hay ejemplares disponibles.");
                return;
            }

            System.out.println("\n--- Ejemplares Disponibles ---");
            for (int i = 0; i < ejemplares.size(); i++) {
                Ejemplar ejemplar = ejemplares.get(i);
                System.out.printf("[%d] %s (Planta: %s)%n", 
                                  i + 1, 
                                  ejemplar.getNombre(), 
                                  ejemplar.getIdPlanta().getNombreComun());
            }

            // Pedir al usuario que seleccione un ejemplar
            System.out.print("\nSeleccione un ejemplar por número: ");
            int seleccion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (seleccion < 1 || seleccion > ejemplares.size()) {
                System.err.println("Selección inválida.");
                return;
            }

            Ejemplar ejemplarSeleccionado = ejemplares.get(seleccion - 1);

            // Mostrar mensajes relacionados con el ejemplar seleccionado
            List<Mensaje> mensajes = mensajeServicio.findByEjemplar(ejemplarSeleccionado);

            if (mensajes.isEmpty()) {
                System.out.println("No hay mensajes para este ejemplar.");
                return;
            }

            System.out.println("\n--- Mensajes de Seguimiento ---");
            
            System.out.printf("\n%-20s %-30s %-25s%n", 
                    "Autor", "Fecha", "Mensaje");
            System.out.println("-----------------------------------------------------------------------");
            
            for (Mensaje mensaje : mensajes) {
            	Persona persona = personaServivio.findById(mensaje.getPersona().getId());
            	String nombreAutor = (persona != null) ? persona.getNombre() : "Desconocido";
            	
            	System.out.printf("%-20s %-30s %-25s%n",
            			nombreAutor,
            			mensaje.getFechahora(), 
                        mensaje.getMensaje());
            }
            
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Ingrese un número válido.");
            scanner.nextLine(); // Consumir la entrada incorrecta
        } catch (Exception e) {
            System.err.println("Error al consultar mensajes: " + e.getMessage());
        }
    }


    /**
     * Método auxiliar para leer una opción numérica del menú.
     * Asegura que la opción seleccionada sea válida dentro del rango proporcionado.
     * 
     * @param scanner El objeto Scanner usado para leer la entrada del usuario.
     * @param min El valor mínimo permitido para la opción.
     * @param max El valor máximo permitido para la opción.
     * @return La opción seleccionada por el usuario.
     */
    private int leerOpcion(Scanner scanner, int min, int max) {
        int opcion;
        while (true) {
            try {
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion >= min && opcion <= max) {
                    return opcion;
                }
                System.err.println("Opción fuera de rango. Intente nuevamente.");
            } catch (NumberFormatException e) {
                System.err.println("Entrada no válida. Ingrese un número.");
            }
        }
    }
}

