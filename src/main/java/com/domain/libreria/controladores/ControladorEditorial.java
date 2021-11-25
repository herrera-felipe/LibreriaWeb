package com.domain.libreria.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.domain.libreria.entidades.Editorial;
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
	
	@GetMapping("/lista")
	public String lista(ModelMap modelo) {
		
		try {
			List<Editorial> editorialesLista = editorialServicio.listarTodos();
			
			modelo.put("editoriales", editorialesLista); // Inyectamos la lista en la tabla html
			
			return "lista-editorial";
			
		} catch (Exception e) {
			return "lista-editorial";
		}
	}
	
	@GetMapping("/baja/{id}") // El id es el pathvariable
	public String baja(@PathVariable String id) {
		
		try {
			editorialServicio.baja(id); // Damos la baja
			
			return "redirect:/editorial/lista"; // y actualizamos la vista
			
		} catch (Exception e) {
			
			return "redirect:/editorial/lista";
		}
	}
	
	@GetMapping("/alta/{id}") // El id es el pathvariable
	public String alta(@PathVariable String id) {
		
		try {
			editorialServicio.alta(id); // Damos alta
			
			return "redirect:/editorial/lista"; // Actualizamos la vista
			
		} catch (Exception e) {
			
			return "redirect:/editorial/lista";
		}
	}
	
	@GetMapping("/modificar/{id}") // El di es el Pathvariable
	public String modificar(@PathVariable String id, ModelMap modelo) {
		 
		modelo.put("editorial", editorialServicio.buscarPorId(id)); // Inyectamos los datos de la editorial que contiene el id
		
		return "editorial-form-modificar";
	}
	
	@PostMapping("/modificar/{id}")
	public String modificarSubmit(@PathVariable String id,@RequestParam String nombre, ModelMap modelo) {
		
		try {
			editorialServicio.modificarEditorial(id, nombre); // Realizamos la modificacion
			
			modelo.put("exito", "Modificación Exitosa");
			
			return "redirect:/editorial/lista";
			
		} catch (Exception e) {
			
			modelo.put("error", "No se realizo la modificación");
			
			return "editorial-form-modificar";
		}
	}
}
