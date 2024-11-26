package com.ikerpc123.tarea3dwesiker.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikerpc123.tarea3dwesiker.repositorios.EjemplarRepository;

@Service
public class ServicioEjemplar {

	@Autowired
	EjemplarRepository ejemplarrepo;
}
