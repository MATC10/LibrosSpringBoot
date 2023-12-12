package com.example.libros.repositories;

import com.example.libros.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface RepositorioLibros extends JpaRepository<Libro, Long> {
    public ArrayList<Libro> findAll();
    public Libro findById(long id);
    public Libro save (Libro libro);
    public void deleteById(long id);

    //ultimos 3 libros en ser añadidos
    @Query("select c from Libro c order by c.id DESC limit 3")
    public ArrayList<Libro> find3Last();

    //primeros 3 libros en ser añadidos
    @Query("select c from Libro c order by c.id ASC limit 3")
    public ArrayList<Libro> find3First();


}
