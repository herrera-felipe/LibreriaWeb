package com.domain.libreria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.domain.libreria.servicios.EditorialServicio;

@Controller
@RequestMapping("/editorial")
public class ControladorEditorial {

	@Autowired
	private EditorialServicio editorialServicio; // Instanciamos el Servicio para acceder a los metodos del CRUD

	@GetMapping("/registro")
	public String editorialForm() {
		return "editorialform";
	}
	
	@PostMapping("/registro")
	public String editorialSubmit(ModelMap modelo, @RequestParam String nombre) {
		
		try {
			editorialServicio.guardarEditorial(nombre);
			
			modelo.put("exito", "Registro Exitoso");
			return "editorialform";
			
		} catch (Exception e) {
			
			modelo.put("error", "Fallo el registro");
			return "editorialform";
		}
		
	}
}
