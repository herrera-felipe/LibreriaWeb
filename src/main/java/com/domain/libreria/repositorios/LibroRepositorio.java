package com.domain.libreria.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.domain.libreria.entidades.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {

	@Query("SELECT l FROM Libro l WHERE l.autor.id = :id")
	public List<Libro> buscarLibroPorAutor(@Param("id") String id);
	
	@Query("SELECT l FROM Libro l WHERE l.editorial.id = :id")
	public List<Libro> buscarLibroPorEditorial(@Param("id") String id);
	
	@Query("SELECT l FROM Libro l WHERE l.anio = :anio")
	public List<Libro> buscarLibroPorAnio(@Param("anio") Integer anio);
	
	@Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
	public List<Libro> buscarLibroPorTitulo(@Param("titulo") String titulo);
	
	@Query("SELECT l FROM Libro l WHERE l.isbn = :isbn")
	public Libro buscarLibroPorIsbn(@Param("isbn") Long isbn);
	
	@Query("SELECT a FROM Libro a WHERE a.alta = true ")
	public List<Libro> buscarActivos();
}
