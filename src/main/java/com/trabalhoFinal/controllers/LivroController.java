package com.trabalhoFinal.controllers;

import com.trabalhoFinal.domain.Livro;
import com.trabalhoFinal.services.LivroServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroServices livroService;

    // Endpoint para obter todos os livros
    @GetMapping
    public ResponseEntity<List<Livro>> getAllLivros() {
        List<Livro> livros = livroService.getAllLivros();
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    // Endpoint para obter um livro por ID
    @GetMapping("/{livroId}")
    public ResponseEntity<Livro> getLivroById(@PathVariable Long livroId) {
        Livro livro = livroService.getLivroById(livroId);
        if (livro != null) {
            return new ResponseEntity<>(livro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para criar um novo livro
    @PostMapping
    public ResponseEntity<Livro> createLivro(@RequestBody Livro livro) {
        Livro novoLivro = livroService.createLivro(livro);
        return new ResponseEntity<>(novoLivro, HttpStatus.CREATED);
    }

    // Endpoint para atualizar um livro existente
    @PutMapping("/{livroId}")
    public ResponseEntity<Livro> updateLivro(@PathVariable Long livroId, @RequestBody Livro livro) {
        Livro livroAtualizado = livroService.updateLivro(livroId, livro);
        if (livroAtualizado != null) {
            return new ResponseEntity<>(livroAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para excluir um livro
    @DeleteMapping("/{livroId}")
    public ResponseEntity<Void> deleteLivro(@PathVariable Long livroId) {
        livroService.deleteLivro(livroId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
