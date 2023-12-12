package com.example.libros.services;

import com.example.libros.models.Editorial;
import com.example.libros.models.Libro;
import com.example.libros.repositories.RepositorioEditoriales;
import com.example.libros.repositories.RepositorioLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioEditoriales {
    @Autowired
    RepositorioEditoriales repositorioEditoriales;

    public ArrayList<Editorial> findAll(){
        return repositorioEditoriales.findAll();
    }

    public Editorial findById(long id){
        return repositorioEditoriales.findById(id);
    }

    public Editorial save(Editorial pelicula){
        //if(pelicula.getNacionalidad().equals(null))
        return repositorioEditoriales.save(pelicula);
    }


    public void deleteById(long id){
        repositorioEditoriales.deleteById(id);
    }


}
