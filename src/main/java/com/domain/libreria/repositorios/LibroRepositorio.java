package com.domain.libreria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.libreria.entidades.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {

}
