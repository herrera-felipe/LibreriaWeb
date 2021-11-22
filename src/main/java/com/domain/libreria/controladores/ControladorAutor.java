package com.domain.libreria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.domain.libreria.servicios.AutorServicio;

@Controller
@RequestMapping("/autor")
public class ControladorAutor {

	@Autowired
	private AutorServicio autorServicio; // Intsanciamos el servicio para acceder a los metodos del CRUD
	
	@GetMapping("/registro")
	public String autorForm() {
		return "autorform";
	}

	/*
	 * Metodo que sera llamado con el submit del formulario de registro
	 */
	@PostMapping("/registro")
	public String autorSubmit(ModelMap modelo, @RequestParam String nombre) {
		
		try {
			autorServicio.guardarAutor(nombre);
			
			modelo.put("exito", "Registro Exitoso");
			return "autorform";
			
		} catch (Exception e) {
			
			modelo.put("error", "Fallo el registro");
			return "autorform";
		}
	}
}
