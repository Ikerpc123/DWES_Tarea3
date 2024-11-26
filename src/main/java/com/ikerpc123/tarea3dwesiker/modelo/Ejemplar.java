package com.ikerpc123.tarea3dwesiker.modelo;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="ejemplares")
public class Ejemplar implements Serializable{
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idplanta")
    private Planta planta;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idejemplar")
    private List<Mensaje> mensajes = new LinkedList<Mensaje>();*/
    
    public Ejemplar() {}

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    /*public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }*/
}
