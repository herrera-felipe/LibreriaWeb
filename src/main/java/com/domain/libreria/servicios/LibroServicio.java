package com.domain.libreria.servicios;


import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.libreria.entidades.Autor;
import com.domain.libreria.entidades.Editorial;
import com.domain.libreria.entidades.Libro;
import com.domain.libreria.errores.ErrorServicio;
import com.domain.libreria.repositorios.AutorRepositorio;
import com.domain.libreria.repositorios.EditorialRepositorio;
import com.domain.libreria.repositorios.LibroRepositorio;


@Service
public class LibroServicio {

	@Autowired
	private LibroRepositorio libroRepositorio;
	
	@Autowired
	private AutorRepositorio autorRespositorio;
	
	@Autowired
	private AutorServicio autorServicio;
	
	@Autowired
	private EditorialRepositorio editorialRepositorio;
	
	@Autowired
	private EditorialServicio editorialServicio;
	
	
	public void guardarLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, String nombreAutor, String nombreEditorial) throws ErrorServicio {
	
		// Validamos los datos del formulario
		validar(isbn, titulo, anio, ejemplares, nombreAutor, nombreEditorial);
		
		// Buscamos el autor en la BD
		Autor autor = autorRespositorio.buscarAutorPorNombre(nombreAutor); 
		
		// Sino existe el Autor, lo creamos
		if (Objects.isNull(autor)) {
			autor = autorServicio.guardarAutor(nombreAutor); 
		}
		
		// Buscamos la Editorial
		Editorial editorial = editorialRepositorio.buscarEditorialPorNombre(nombreEditorial);
		
		// Sino existe la Editorial, la creamos
		if (Objects.isNull(editorial)) {
			editorial = editorialServicio.guardarEditorial(nombreEditorial);
		}
		
		
		Libro libro = new Libro(); // Intanciamos la Entidad
		
		// Seteamos los valores
		libro.setIsbn(isbn);
		libro.setTitulo(titulo);
		libro.setAnio(anio);
		libro.setEjemplares(ejemplares);
		libro.setAutor(autor);
		libro.setEditorial(editorial);
		libro.setAlta(true);
		
		// Persistimos en la BD
		libroRepositorio.save(libro);
	}
	
	public void modificarLibro(String titulo, Integer anio, Integer ejemplares, String nombreAutor, String nombreEditorial) {
		
	}
	
	public List<Libro> listarLibros(){
		return null;
	}
	
	public void buscarLibro() {
		
	}
	
	/*
	 * Valida que los campos del formulario no sean nullos
	 */
	public void validar(Long isbn, String titulo, Integer anio, Integer ejemplares, String autor, String editorial) throws ErrorServicio {
		
		if (isbn == null || isbn <=0 || isbn.toString().isEmpty()) {
			throw new ErrorServicio("El campo Isbn no puede estar vacio, ni tener el valor de cero");
		}
		
		if (titulo == null || titulo.isEmpty()) {
			throw new ErrorServicio("El campo Titulo no puede estar vacio.");
		}
		
		if (anio == null || anio <= 0 || anio.toString().isEmpty()) {
			throw new ErrorServicio("El campo Año no puede estar vacio.");
		}
		
		if (ejemplares == null || ejemplares <= 0 || ejemplares.toString().isEmpty()) {
			throw new ErrorServicio("El campo Ejemplares no puede estar vacio.");
		}
		
		if (autor == null || autor.isEmpty()) {
			throw new ErrorServicio("El campo Autor no puede estar vacio.");
		} else {
			 
		}
		
		if (editorial == null || editorial.isEmpty()) {
			throw new ErrorServicio("El campo Editorial no puede estar vacio.");
		}
	}
	
}
