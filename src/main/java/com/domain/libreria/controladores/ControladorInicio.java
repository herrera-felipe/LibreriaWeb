package com.domain.libreria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ControladorInicio {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    

    @GetMapping("/autor")
    public String resgistroAutor() {
        return "autorform";
    }

    @GetMapping("/editorial")
    public String resgistroEditorial() {
        return "editorialform";
    }
}
