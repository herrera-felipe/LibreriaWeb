package com.domain.libreria.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.domain.libreria.entidades.Autor;
import com.domain.libreria.entidades.Editorial;
import com.domain.libreria.entidades.Libro;
import com.domain.libreria.errores.ErrorServicio;
import com.domain.libreria.servicios.AutorServicio;
import com.domain.libreria.servicios.EditorialServicio;
import com.domain.libreria.servicios.LibroServicio;

@Controller
@RequestMapping("/libro")
public class ControladorLibro {

	@Autowired
	private LibroServicio libroServicio; // Intsanciamos el Servicio para acceder a los metodos del CRUD

	@Autowired
	private AutorServicio autorServicio;

	@Autowired
	private EditorialServicio editorialServicio;

	@GetMapping("/registro")
	public String libroForm(ModelMap modelo) {

		List<Autor> autores = autorServicio.listarTodos();
		List<Editorial> editoriales = editorialServicio.listarTodos();

		// el modelo mostrara la listas en el html
		modelo.put("autores", autores);
		modelo.put("editoriales", editoriales);
		return "libroform";
	}

	/*
	 * Metodo que sera llamado en el formulario de registro de libro
	 */
	@PostMapping("/registro")
	public String libroSubmit(ModelMap modelo, @RequestParam @Nullable Long isbn, @RequestParam @Nullable String titulo,
			@RequestParam @Nullable Integer anio, @RequestParam @Nullable Integer ejemplares,
			@RequestParam @Nullable String nombreAutor, @RequestParam @Nullable String nombreEditorial) {

		try {
			libroServicio.guardarLibro(isbn, titulo, anio, ejemplares, nombreAutor, nombreEditorial);

			modelo.put("exito", "Registro Exitoso");

			return "redirect:/libro/registro";

		} catch (Exception e) {

			modelo.put("error", "Fallo el registro");
			return "libroform";
		}
	}

	@GetMapping("/lista")
	public String lista(ModelMap modelo) {

		try {
			// Traemos la lista de libros
			List<Libro> librosLista = libroServicio.listarTodos();

			modelo.addAttribute("libros", librosLista); // "libros" contiene la lista y con esta variable se accede
														// desde el html
			return "lista-libro";
		} catch (Exception e) {
			return "lista-libro";
		}
	}

	@GetMapping("/baja/{id}") // el {id} es la pathvariable respectiva de c/u de los libros
	public String baja(@PathVariable String id) {

		try {
			libroServicio.baja(id);
			return "redirect:/libro/lista";

		} catch (Exception e) {
			return "redirect:/libro/lista";
		}
	}

	@GetMapping("/alta/{id}")
	public String alta(@PathVariable String id) {

		try {
			libroServicio.alta(id);
			return "redirect:/libro/lista";

		} catch (Exception e) {
			return "redirect:/libro/lista";
		}
	}

	@GetMapping("/modificar/{id}")
	public String modificar(@PathVariable String id, ModelMap modelo) {

		List<Autor> autores = autorServicio.listarTodos();
		List<Editorial> editoriales = editorialServicio.listarTodos();

		// el modelo mostrara la listas en el html
		modelo.put("autores", autores);
		modelo.put("editoriales", editoriales);

		modelo.put("libro", libroServicio.buscarPorId(id)); // Inyectamos el libro al html

		return "libro-form-modificar";
	}

	@PostMapping("/modificar/{id}")
	public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam @Nullable Long isbn,
			@RequestParam @Nullable String titulo, @RequestParam @Nullable Integer anio,
			@RequestParam @Nullable Integer ejemplares, @RequestParam @Nullable String nombreAutor,
			@RequestParam @Nullable String nombreEditorial) {

		try {
			libroServicio.modificarLibro(id, isbn, titulo, anio, ejemplares, nombreAutor, nombreEditorial);

			modelo.put("exito", "Modificación Exitosa");
			return "redirect:/libro/lista";

		} catch (ErrorServicio e) {
			modelo.put("error", "No se pudo realizar la modificación");
			return "libro-form-modificar";
		}
	}
}
