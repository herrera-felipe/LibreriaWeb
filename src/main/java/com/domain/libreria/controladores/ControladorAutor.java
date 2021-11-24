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

import com.domain.libreria.entidades.Autor;
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
	
	@GetMapping("/lista")
	public String lista(ModelMap modelo) {
		
		try {
			//Traemos la lista de autores
			List<Autor> autoresLista = autorServicio.listarTodos();
			
			modelo.addAttribute("autores", autoresLista); // la var "autores" lleva la ista al html
			return "lista-autor";
			
		} catch (Exception e) {
			return "lista-autor";
		}
		
	}
	
	@GetMapping("/baja/{id}")
	public String baja(@PathVariable String id) {
		
		try {
			autorServicio.baja(id);
			return "redirect:/autor/lista";
			
		} catch (Exception e) {
			return "redirect:/autor/lista";
		}
	}
	
	@GetMapping("/alta/{id}")
	public String alta(@PathVariable String id) {
		
		try {
			autorServicio.alta(id);
			return "redirect:/autor/lista";
			
		} catch (Exception e) {
			return "redirect:/autor/lista";
		}
	}
	
	@GetMapping("/modificar/{id}") // {id} es una PathVariable
	public String modificar(@PathVariable String id, ModelMap modelo) {
		
		modelo.put("autor", autorServicio.buscarPorId(id)); // Inyectamos al html el autor al que corresponde el id
		
		return "autor-form-modificar";
	}
	
	@PostMapping("/modificar/{id}")
	public String modificar(@PathVariable String id, @RequestParam String nombre, ModelMap modelo) {
		
		try {
			autorServicio.modificarAutor(id, nombre);
			
			modelo.put("exito", "Modificación Exitosa");
			return "redirect:/autor/lista";
			
		} catch (Exception e) {
			
			modelo.put("error", "No se realizo la modificación");
			return "autor-form-modificar";
		}
	}
}
