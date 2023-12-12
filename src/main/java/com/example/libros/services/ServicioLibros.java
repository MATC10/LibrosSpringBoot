package com.example.libros.services;

import com.example.libros.models.Libro;
import com.example.libros.repositories.RepositorioLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioLibros {

    @Autowired
    RepositorioLibros repositorioLibros;

    public ArrayList<Libro> findAll(){
        return repositorioLibros.findAll();
    }
    public Libro findById(long id){
        return repositorioLibros.findById(id);
    }
    public Libro save (Libro libro){
        return repositorioLibros.save(libro);
    }
    public void deleteById(long id){
        repositorioLibros.deleteById(id);
    }
    public ArrayList<Libro> find3Last(){
        return repositorioLibros.find3Last();
    }

}
