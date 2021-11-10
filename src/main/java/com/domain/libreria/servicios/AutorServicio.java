package com.domain.libreria.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.libreria.entidades.Autor;
import com.domain.libreria.errores.ErrorServicio;
import com.domain.libreria.repositorios.AutorRepositorio;

@Service
public class AutorServicio {

	@Autowired
	private AutorRepositorio autorRepositorio;
	
	public Autor guardarAutor(String nombre) throws ErrorServicio {
		
		validar(nombre);
		
		Autor autor = new Autor(); // Instanciamos la Entidad
		
		// Seteamos atributos
		autor.setNombre(nombre);
		autor.setAlta(true); 
		
		// Persistimos con el repositorio
		return autorRepositorio.save(autor);
	}
	
	public void validar(String nombre) throws ErrorServicio {
		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorServicio("El nombre no puede estar vacio.");
		}
	}
	
}
