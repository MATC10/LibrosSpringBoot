package com.example.libros.repositories;

import com.example.libros.models.Editorial;
import com.example.libros.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepositorioEditoriales extends JpaRepository<Editorial, Long> {
    public ArrayList<Editorial> findAll();

    public Editorial findById(long id);

    public Editorial save(Editorial editorial);


}