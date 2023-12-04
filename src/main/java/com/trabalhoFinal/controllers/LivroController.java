package com.trabalhoFinal.controllers;
import com.trabalhoFinal.domain.Livro;
import com.trabalhoFinal.dtos.DeleteMessageDTO;
import com.trabalhoFinal.services.LivroServices;
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
@RequestMapping("/livros")
@Tag(name = "Book")
public class LivroController {

    @Autowired
    private LivroServices livroService;

    @Operation(
            summary = "List of Books",
            description = "Read all Books list",
            tags = "Book",
            method = "GET",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Livro.class, type = "array"))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No Books found",
                            content = @Content(array = @ArraySchema)),
            }
    )
    @GetMapping
    public ResponseEntity<List<Livro>> getAllLivros() {
        List<Livro> livros = livroService.getAllLivros();
        if (!livros.isEmpty()) {
            return new ResponseEntity<>(livros, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "One Book",
            description = "Read referenced Book according to the informed id",
            tags = "Book",
            method = "GET",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Livro.class, type = "Book"))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not found",
                            content = @Content(array = @ArraySchema))
            }
    )
    @GetMapping("/{livroId}")
    public ResponseEntity<Livro> getLivroById(@PathVariable Long livroId) {
        Livro livro = livroService.getLivroById(livroId);
        if (livro != null) {
            return new ResponseEntity<>(livro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Insert Book",
            description = "Insert Book according to the informed parameters in body",
            tags = "Book",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Livro.class, type = "Book")))
            }
    )
    @PostMapping
    public ResponseEntity<Livro> createLivro(@RequestBody Livro livro) {
        Livro novoLivro = livroService.createLivro(livro);
        return new ResponseEntity<>(novoLivro, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Update Book",
            description = "Update referenced Book according to the informed parameters in body and id",
            tags = "Book",
            method = "PUT",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Livro.class, type = "Book"))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not found",
                            content = @Content(array = @ArraySchema))
            }
    )
    @PutMapping("/{livroId}")
    public ResponseEntity<Livro> updateLivro(@PathVariable Long livroId, @RequestBody Livro livro) {
        Livro livroAtualizado = livroService.updateLivro(livroId, livro);
        if (livroAtualizado != null) {
            return new ResponseEntity<>(livroAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Operation(
            summary = "Delete Book",
            description = "Delete referenced Book according to the informed id",
            tags = "Book",
            method = "DELETE",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Book deleted",
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
    @DeleteMapping("/{livroId}")
    public ResponseEntity<DeleteMessageDTO> deleteLivro(@PathVariable Long livroId) {
        return livroService.deleteLivro(livroId);
    }
}
