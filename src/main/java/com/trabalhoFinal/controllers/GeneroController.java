package com.trabalhoFinal.controllers;

import com.trabalhoFinal.domain.Autor;
import com.trabalhoFinal.domain.Editora;
import com.trabalhoFinal.domain.Genero;
import com.trabalhoFinal.dtos.DeleteMessageDTO;
import com.trabalhoFinal.services.GeneroServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
@Tag(name = "Genre")
public class GeneroController {

    @Autowired
    private GeneroServices generoService;

    @Operation(
            summary = "List of Genres",
            description = "Read all Genres list",
            tags = "Genre",
            method = "GET",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Genero.class, type = "array"))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No Genres found",
                            content = @Content(array = @ArraySchema)),
            }
    )
    @GetMapping
    public ResponseEntity<List<Genero>> getAllGeneros() {
        List<Genero> generos = generoService.getAllGeneros();
        return new ResponseEntity<>(generos, HttpStatus.OK);
    }


    @Operation(
            summary = "One Genre",
            description = "Read referenced Genre according to the informed id",
            tags = "Genre",
            method = "GET",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Genero.class, type = "Genre"))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Genre not found",
                            content = @Content(array = @ArraySchema))
            }
    )
    @GetMapping("/{generoId}")
    public ResponseEntity<Genero> getGeneroById(@PathVariable Long generoId) {
        Genero genero = generoService.getGeneroById(generoId);
        if (genero != null) {
            return new ResponseEntity<>(genero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Operation(
            summary = "Insert Genre",
            description = "Insert Genre according to the informed parameters in body",
            tags = "Genre",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Genero.class, type = "Genre")))
            }
    )
    @PostMapping
    public ResponseEntity<Genero> createGenero(@RequestBody Genero genero) {
        Genero novoGenero = generoService.createGenero(genero);
        return new ResponseEntity<>(novoGenero, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Update Genre",
            description = "Update referenced Genre according to the informed parameters in body and id",
            tags = "Genre",
            method = "PUT",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Genero.class, type = "Genre"))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Genre not found",
                            content = @Content(array = @ArraySchema))
            }
    )
    @PutMapping("/{generoId}")
    public ResponseEntity<Genero> updateGenero(@PathVariable Long generoId, @RequestBody Genero genero) {
        Genero generoAtualizado = generoService.updateGenero(generoId, genero);
        if (generoAtualizado != null) {
            return new ResponseEntity<>(generoAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Operation(
            summary = "Delete Genre",
            description = "Delete referenced Genre according to the informed id",
            tags = "Genre",
            method = "DELETE",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Genre deleted",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = DeleteMessageDTO.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = DeleteMessageDTO.class)))


            }
    )
    @DeleteMapping("/{generoId}")
    public ResponseEntity<DeleteMessageDTO> deleteGenero(@PathVariable Long generoId) {
        return generoService.deleteGenero(generoId);
    }
}