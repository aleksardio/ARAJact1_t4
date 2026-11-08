package com.ejemplo.act1.controllers;

import java.util.List;

import com.ejemplo.act1.models.Materia;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/materias")
public class MateriasController {

    private final List<Materia> materias = List.of(
            new Materia(1, "Programación Web", "Adelina Martinez Nieto"),
            new Materia(2, "Administracion de redes", "Omar Robledo Cabrera"),
            new Materia(3, "Software para toma de desiciones", "Moises Adrian Hernandez Luis"),
            new Materia(4, "Sistemas programables", "Antonio Erick Wong Diaz"));

    @GetMapping
    public List<Materia> materias() {
        return materias;
    }

    @GetMapping("/total")
    public int total() {
        return materias.size();
    }
}
