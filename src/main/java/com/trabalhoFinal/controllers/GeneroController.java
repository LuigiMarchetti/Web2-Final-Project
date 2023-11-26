package com.trabalhoFinal.controllers;

import com.trabalhoFinal.domain.Genero;
import com.trabalhoFinal.services.GeneroServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroServices generoService;
    

    @GetMapping
    public ResponseEntity<List<Genero>> getAllGeneros() {
        List<Genero> generos = generoService.getAllGeneros();
        return new ResponseEntity<>(generos, HttpStatus.OK);
    }

    @GetMapping("/{generoId}")
    public ResponseEntity<Genero> getGeneroById(@PathVariable Long generoId) {
        Genero genero = generoService.getGeneroById(generoId);
        if (genero != null) {
            return new ResponseEntity<>(genero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Genero> createGenero(@RequestBody Genero genero) {
        Genero novoGenero = generoService.createGenero(genero);
        return new ResponseEntity<>(novoGenero, HttpStatus.CREATED);
    }

    @PutMapping("/{generoId}")
    public ResponseEntity<Genero> updateGenero(@PathVariable Long generoId, @RequestBody Genero genero) {
        Genero generoAtualizado = generoService.updateGenero(generoId, genero);
        if (generoAtualizado != null) {
            return new ResponseEntity<>(generoAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{generoId}")
    public ResponseEntity<Void> deleteGenero(@PathVariable Long generoId) {
        generoService.deleteGenero(generoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}