package com.trabalhoFinal.controllers;

import com.trabalhoFinal.domain.Autor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trabalhoFinal.services.AutorServices;

import java.util.List;

@RestController
@RequestMapping("/autores")
@Tag(name = "Author")
public class AutorController {

    @Autowired
    private AutorServices autorService;

    @Operation(
            summary = "List of authors",
            description = "Read all Authors list",
            tags = "Author",
            method = "GET",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sucess",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Autor.class, type = "array"))),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No content")
            }
    )
    @GetMapping
    public ResponseEntity<List<Autor>> getAllAutores() {
        List<Autor> autores = autorService.getAllAutores();
        if (autores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(autores, HttpStatus.OK);
    }

    @Operation(
            summary = "One Author",
            description = "Read referenced Author according to the informed id",
            tags = "Author",
            method = "GET",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sucess",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Autor.class, type = "Author"))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found")
            }
    )
    @GetMapping("/{autorId}")
    public ResponseEntity<Autor> getAutorById(@PathVariable Long autorId) {
        Autor autor = autorService.getAutorById(autorId);
        if (autor != null) {
            return new ResponseEntity<>(autor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Insert Author",
            description = "Insert Author according to the informed parameters in body",
            tags = "Author",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Autor.class, type = "Author")))
            }
    )
    @PostMapping
    public ResponseEntity<Autor> createAutor(@RequestBody Autor autor) {
        Autor novoAutor = autorService.createAutor(autor);
        return new ResponseEntity<>(novoAutor, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update Author",
            description = "Update referenced Author according to the informed parameters in body and id",
            tags = "Author",
            method = "PUT",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sucess",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Autor.class, type = "Author"))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found")
            }
    )
    @PutMapping("/{autorId}")
    public ResponseEntity<Autor> updateAutor(@PathVariable Long autorId, @RequestBody Autor autor) {
        Autor autorAtualizado = autorService.updateAutor(autorId, autor);
        if (autorAtualizado != null) {
            return new ResponseEntity<>(autorAtualizado, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            summary = "Delete Author",
            description = "Delete referenced Author according to the informed id",
            tags = "Author",
            method = "DELETE",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No content")
            }
    )
    @DeleteMapping("/{autorId}")
    public ResponseEntity<Void> deleteAutor(@PathVariable Long autorId) {
        autorService.deleteAutor(autorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}