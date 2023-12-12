package com.example.libros.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String resumen;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    //@JsonFormat(pattern = "dd/MM/yyyy") para convertir el formato de la fecha en json
    private LocalDate fecha;

    //que tenga varios valores
    private String nacionalidad;

    private String imagen;

    private String autor;

    //lo logico sería tener otra tabla editorial
    //punto adicional: crea la barra de autoresjjjjj
    @ManyToOne
    private Editorial editorial;
}
