package com.domain.libreria.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.libreria.entidades.Editorial;
import com.domain.libreria.errores.ErrorServicio;
import com.domain.libreria.repositorios.EditorialRepositorio;

@Service
public class EditorialServicio {

	@Autowired
	private EditorialRepositorio editorialRepositorio;

	@Transactional
	public Editorial guardarEditorial(String nombre) throws ErrorServicio {

		validar(nombre);

		Editorial editorial = new Editorial(); // Instanciamos la Entidad

		// Seteamos atributos
		editorial.setNombre(nombre);
		editorial.setAlta(true);

		// Persistimos con el repositorio
		return editorialRepositorio.save(editorial);
	}

	@Transactional
	public void modificarEditorial(String id, String nombre) throws ErrorServicio {

		validar(nombre);

		Optional<Editorial> respuesta = editorialRepositorio.findById(id); // Buscamos la editorial

		if (respuesta.isPresent()) {
			Editorial editorial = respuesta.get(); // Si trae resultado la agregamos a la entidad
			editorial.setNombre(nombre); // Seteamos el nuevo valor
			editorialRepositorio.save(editorial); // Persistimos en la BD
		} else {
			throw new ErrorServicio("No se encontró la editorial solicitada");
		}
	}

	@Transactional
	public void alta(String id) throws ErrorServicio {

		Optional<Editorial> respuesta = editorialRepositorio.findById(id); // Buscamos la editorial

		if (respuesta.isPresent()) {
			Editorial editorial = respuesta.get(); // Si trae resultado la agregamos a la entidad
			editorial.setAlta(true); // Damos alta
			editorialRepositorio.save(editorial); // Persistimos en la BD
		} else {
			throw new ErrorServicio("No se encontró la editorial solicitada");
		}
	}
	
	@Transactional
	public void baja(String id) throws ErrorServicio {

		Optional<Editorial> respuesta = editorialRepositorio.findById(id); // Buscamos la editorial

		if (respuesta.isPresent()) {
			Editorial editorial = respuesta.get(); // Si trae resultado la agregamos a la entidad
			editorial.setAlta(false); // damos la baja
			editorialRepositorio.save(editorial); // Persistimos en la BD
		} else {
			throw new ErrorServicio("No se encontró la editorial solicitada");
		}
	}

	@Transactional(readOnly = true)
	public List<Editorial> listarTodos() {
		return editorialRepositorio.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Editorial> listarActivos() {
		return editorialRepositorio.buscarActivos();
	}
	
	@Transactional(readOnly = true)
	public Editorial buscarPorId(String id) {
		return editorialRepositorio.getOne(id);
	}
	
	@Transactional(readOnly = true)
	public List<Editorial> buscarPorNombre(String nombre) {
		
		List<Editorial> listaEditorial = listarTodos(); //traemos todas las editoriales
		
		List<Editorial> encontrados = new ArrayList<>();
		
		// Recorremos la lista
		for (Editorial editorial : listaEditorial) {
			String aux = editorial.getNombre();
			
			if (aux.contains(nombre) || aux.equalsIgnoreCase(nombre)) {
				encontrados.add(editorial);
			}
		}
		
		if (encontrados.isEmpty() || encontrados == null) {
			return null;
		}
		
		return encontrados;
	}
	
	public void validar(String nombre) throws ErrorServicio {
		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorServicio("El nombre no puede estar vacio.");
		}
	}

}
