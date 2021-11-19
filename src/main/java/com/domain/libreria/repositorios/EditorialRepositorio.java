package com.domain.libreria.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.domain.libreria.entidades.Editorial;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {

	@Query("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
	public Editorial buscarEditorialPorNombre(@Param("nombre") String nombre);
	
	@Query("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
	public List<Editorial> listarEditorial(@Param("nombre") String nombre);
	
	@Query("SELECT e FROM Editorial e WHERE e.alta = true")
	public List<Editorial> buscarActivos();
}
