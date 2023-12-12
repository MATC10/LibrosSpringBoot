package com.example.libros.controllers;

import com.example.libros.models.Editorial;
import com.example.libros.models.Libro;
import com.example.libros.services.ServicioEditoriales;
import com.example.libros.services.ServicioLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@RequestMapping("/crud/editoriales")
@Controller
public class EditorialController {

    @Autowired
    ServicioLibros servicioLibros;
    @Autowired
    ServicioEditoriales servicioEditoriales;

    @GetMapping("")
    //importo el model del spring framework
    public String listarEditoriales(Model model){
        model.addAttribute("listaEditoriales", servicioEditoriales.findAll());
        return "crud_editoriales";
    }

    @GetMapping("/add")
    public String addEditorial(Model model){
        model.addAttribute("editorial", new Editorial());
        return "formulario_editoriales";
    }

    //el compañero de este getmapping es el postmapping
    @PostMapping("/postadd")
    //lo que pone arriba en el atributo es lo que hay que poner en el modelattributo
    public String postAddEditoriales(@ModelAttribute("editorial") Editorial editorial){
        servicioEditoriales.save(editorial);
        return "redirect:/crud/editoriales";
    }

    @GetMapping("/update/{id}")
    public String updateEditoriales(Model model, @PathVariable long id){
        Editorial editorial = servicioEditoriales.findById(id);
        model.addAttribute("editorial", editorial);
        return "formulario_editoriales";
    }

    @PostMapping("/postupdate")
    public String postUpdateEditoriales(@ModelAttribute("editorial") Editorial editorial){
        servicioEditoriales.save(editorial);
        return "redirect:/crud/editoriales";
    }

}
