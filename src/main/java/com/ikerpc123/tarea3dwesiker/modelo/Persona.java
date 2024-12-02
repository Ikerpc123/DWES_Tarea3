package com.ikerpc123.tarea3dwesiker.modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="personas")
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column
	private String nombre;
	
	@Column(unique=true)
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="persona_id")
	private Credencial credencial;
	
	public Persona() {}
	
	
}
