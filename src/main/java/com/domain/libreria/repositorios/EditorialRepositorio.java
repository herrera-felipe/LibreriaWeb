package com.domain.libreria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.domain.libreria.entidades.Editorial;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {

	@Query("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
	public Editorial buscarEditorialPorNombre(@Param("nombre") String nombre);
}
