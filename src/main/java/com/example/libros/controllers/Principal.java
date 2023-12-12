package com.example.libros.controllers;

import com.example.libros.models.Libro;
import com.example.libros.services.ServicioEditoriales;
import com.example.libros.services.ServicioLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RequestMapping("/libros")
@Controller
public class Principal {
    @Autowired
    ServicioLibros servicioLibros;

    @Autowired
    ServicioEditoriales servicioEditoriales;

    @GetMapping("")
    public String inicio(Model model){
        //recupera todas las peliculas
        ArrayList<Libro> libros=servicioLibros.findAll();
        model.addAttribute("listaLibros", libros);

        //ultimos 3 libros
        model.addAttribute("ultimosLibros", servicioLibros.find3Last());


        return "index";
    }

    @GetMapping("/{id}")
    public String libro(@PathVariable long id, Model model){
        Libro libro=servicioLibros.findById(id);
        //El nombre de "pelicula" es el que voy a usar en la vista detalle.html
        model.addAttribute("libro", libro);
        model.addAttribute("nuevoLibro", new Libro());

        //El nombre que pongo en el return es el que tendrá el archivo .html
        return "detalle";
    }


}
