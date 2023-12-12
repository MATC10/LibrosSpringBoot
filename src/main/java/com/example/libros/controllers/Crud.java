package com.example.libros.controllers;

import com.example.libros.models.Editorial;
import com.example.libros.models.Libro;
import com.example.libros.services.ServicioEditoriales;
import com.example.libros.services.ServicioLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/crud/libros")
public class Crud {
    @Autowired
    ServicioLibros servicioLibros;
    @Autowired
    ServicioEditoriales servicioEditoriales;

    @GetMapping("")
    //importo el model del spring framework
    public String listarLibros(Model model){
        model.addAttribute("listaLibros", servicioLibros.findAll());

        return "crud";
    }

    @GetMapping("/add")
    public String addLibro(Model model){
        model.addAttribute("libro", new Libro());


        //para ver todas las editoriales en el formulario de libro
        ArrayList<Editorial> listaEditoriales = servicioEditoriales.findAll();
        model.addAttribute("listaEditoriales", listaEditoriales);


        return "formulario";
    }

    //el compañero de este getmapping es el postmapping
    @PostMapping("/postadd")
    //lo que pone arriba en el atributo es lo que hay que poner en el modelattributo
    public String postAddLibros(@ModelAttribute("libro") Libro libro){
        servicioLibros.save(libro);
        return "redirect:/crud/libros/add";
    }

    @GetMapping("/update/{id}")
    public String updateLibros(Model model, @PathVariable long id){
        Libro libro = servicioLibros.findById(id);
        model.addAttribute("libro", libro);

        //para ver todas las editoriales en el formulario de libro
        ArrayList<Editorial> listaEditoriales = servicioEditoriales.findAll();
        model.addAttribute("listaEditoriales", listaEditoriales);

        return "formulario";
    }


    @PostMapping("/postupdate")
    public String postUpdateLibros(@ModelAttribute("libro") Libro libro){
        servicioLibros.save(libro);
        return "redirect:/crud/libros";
    }

    @GetMapping("/delete/{id}")
    public String deleteLibros(@PathVariable long id, Model model){
        servicioLibros.deleteById(id);
        return "redirect:/crud/libros";
    }
}
