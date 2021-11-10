package com.domain.libreria.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.libreria.entidades.Editorial;
import com.domain.libreria.errores.ErrorServicio;
import com.domain.libreria.repositorios.EditorialRepositorio;

@Service
public class EditorialServicio {

	@Autowired
	private EditorialRepositorio editorialRepositorio;

	public Editorial guardarEditorial(String nombre) throws ErrorServicio {

		validar(nombre);

		Editorial editorial = new Editorial(); // Instanciamos la Entidad

		// Seteamos atributos
		editorial.setNombre(nombre);
		editorial.setAlta(true);

		// Persistimos con el repositorio
		return editorialRepositorio.save(editorial);
	}

	
	public void validar(String nombre) throws ErrorServicio {
		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorServicio("El nombre no puede estar vacio.");
		}
	}

}
