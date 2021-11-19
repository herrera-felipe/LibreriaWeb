package com.domain.libreria.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.libreria.entidades.Autor;
import com.domain.libreria.errores.ErrorServicio;
import com.domain.libreria.repositorios.AutorRepositorio;

@Service
public class AutorServicio {

	@Autowired
	private AutorRepositorio autorRepositorio;

	@Transactional
	public Autor guardarAutor(String nombre) throws ErrorServicio {

		validar(nombre);

		Autor autor = new Autor(); // Instanciamos la Entidad

		// Seteamos atributos
		autor.setNombre(nombre);
		autor.setAlta(true);

		// Persistimos con el repositorio
		return autorRepositorio.save(autor);
	}

	@Transactional
	public void modificarAutor(String id, String nombre) throws ErrorServicio {

		validar(nombre);

		Optional<Autor> respuesta = autorRepositorio.findById(id);

		if (respuesta.isPresent()) {
			Autor autor = respuesta.get();
			autor.setNombre(nombre);
			autorRepositorio.save(autor);
		} else {
			throw new ErrorServicio("No se encontró el autor solicitado");
		}
	}

	@Transactional
	public void alta(String id) throws ErrorServicio {

		Optional<Autor> respuesta = autorRepositorio.findById(id);

		if (respuesta.isPresent()) {
			Autor autor = respuesta.get();
			autor.setAlta(true);
			autorRepositorio.save(autor);
		} else {
			throw new ErrorServicio("No se encontró el autor solicitado");
		}
	}

	@Transactional
	public void baja(String id) throws ErrorServicio {

		Optional<Autor> respuesta = autorRepositorio.findById(id);

		if (respuesta.isPresent()) {
			Autor autor = respuesta.get();
			autor.setAlta(false);
			autorRepositorio.save(autor);
		} else {
			throw new ErrorServicio("No se encontró el autor solicitado");
		}
	}

	@Transactional(readOnly = true)
	public List<Autor> listarTodos() {
		return autorRepositorio.findAll();
	}

	@Transactional(readOnly = true)
	public List<Autor> listarActivos() {
		return autorRepositorio.buscarActivos();
	}

	@Transactional(readOnly = true)
	public List<Autor> buscarPorNombre(String nombre) {

		List<Autor> autores = listarTodos(); // Traemos todos los autores

		List<Autor> encontrados = new ArrayList<>();

		// Recorremos la lista
		for (Autor autor : autores) {
			String aux = autor.getNombre();

			if (aux.contains(nombre) || aux.equalsIgnoreCase(nombre)) {
				encontrados.add(autor);
			}
		}

		if (encontrados.isEmpty() || encontrados == null) {
			return null;
		}

		return encontrados;
	}

	@Transactional(readOnly = true)
	public Autor buscarPorId(String id) {

		Optional<Autor> respuesta = autorRepositorio.findById(id);

		Autor autor = new Autor();

		if (respuesta.isPresent()) {
			autor = respuesta.get();
		} else {
			return null;
		}

		return autor;
	}

	public void validar(String nombre) throws ErrorServicio {
		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorServicio("El nombre no puede estar vacio.");
		}
	}

}
