package com.domain.libreria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.domain.libreria.servicios.LibroServicio;

@Controller
@RequestMapping("/libro")
public class ControladorLibro {
	
	@Autowired
	private LibroServicio libroServicio; //Intsanciamos el Servicio para acceder a los metodos del CRUD

	@GetMapping("/registro")
	public String libroForm() {
		return "libroform";
	}

	/*
	 * Metodo que sera llamado en el formulario de registro de libro
	 */
	@PostMapping("/registro")
	public String libroSubmit(ModelMap modelo, @RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio,
			@RequestParam Integer ejemplares, @RequestParam String nombreAutor, @RequestParam String nombreEditorial) {
		
		try {
			libroServicio.guardarLibro(isbn, titulo, anio, ejemplares, nombreAutor, nombreEditorial);
			
			modelo.put("exito", "Registro Exitoso");
			return "libroform";
			
		} catch (Exception e) {
			
			modelo.put("error", "Fallo el registro");
			return "libroform";
		}
	}

}
