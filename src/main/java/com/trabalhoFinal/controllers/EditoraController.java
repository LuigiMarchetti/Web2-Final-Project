package com.trabalhoFinal.controllers;


import com.trabalhoFinal.domain.Editora;
import com.trabalhoFinal.dtos.DeleteMessageDTO;
import com.trabalhoFinal.services.EditoraServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editoras")
public class EditoraController {

    @Autowired
    private EditoraServices editoraService;

    @GetMapping
    public ResponseEntity<List<Editora>> getAllEditoras() {
        List<Editora> editoras = editoraService.getAllEditoras();
        return new ResponseEntity<>(editoras, HttpStatus.OK);
    }

    @GetMapping("/{editoraId}")
    public ResponseEntity<Editora> getEditoraById(@PathVariable Long editoraId) {
        Editora editora = editoraService.getEditoraById(editoraId);
        if (editora != null) {
            return new ResponseEntity<>(editora, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Editora> createEditora(@RequestBody Editora editora) {
        Editora novaEditora = editoraService.createEditora(editora);
        return new ResponseEntity<>(novaEditora, HttpStatus.CREATED);
    }

    @PutMapping("/{editoraId}")
    public ResponseEntity<Editora> updateEditora(@PathVariable Long editoraId, @RequestBody Editora editora) {
        Editora editoraAtualizada = editoraService.updateEditora(editoraId, editora);
        if (editoraAtualizada != null) {
            return new ResponseEntity<>(editoraAtualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{editoraId}")
    public ResponseEntity<DeleteMessageDTO> deleteEditora(@PathVariable Long editoraId) {
        return editoraService.deleteEditora(editoraId);
    }
}