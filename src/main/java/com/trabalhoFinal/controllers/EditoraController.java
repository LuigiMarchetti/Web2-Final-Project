package com.trabalhoFinal.controllers;


import com.trabalhoFinal.domain.Editora;
import com.trabalhoFinal.dtos.DeleteMessageDTO;
import com.trabalhoFinal.services.EditoraServices;
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
@RequestMapping("/editoras")
@Tag(name = "Publishing Company")
public class EditoraController {

    @Autowired
    private EditoraServices editoraService;


    @Operation(
            summary = "List of publishing companies",
            description = "Read all Publishing Companies list",
            tags = "Publishing Company",
            method = "GET",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Editora.class, type = "array"))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No publishing companies found",
                            content = @Content(array = @ArraySchema)),
            }
    )
    @GetMapping
    public ResponseEntity<List<Editora>> getAllEditoras() {
        List<Editora> editoras = editoraService.getAllEditoras();
        return new ResponseEntity<>(editoras, HttpStatus.OK);
    }


    @Operation(
            summary = "One Publishing Company",
            description = "Read referenced Publishing Company according to the informed id",
            tags = "Publishing Company",
            method = "GET",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Editora.class, type = "Publishing Company"))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Publishing Company not found",
                            content = @Content(array = @ArraySchema))
            }
    )
    @GetMapping("/{editoraId}")
    public ResponseEntity<Editora> getEditoraById(@PathVariable Long editoraId) {
        Editora editora = editoraService.getEditoraById(editoraId);
        if (editora != null) {
            return new ResponseEntity<>(editora, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Operation(
            summary = "Insert Publishing Company",
            description = "Insert Publishing Company according to the informed parameters in body",
            tags = "Publishing Company",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Editora.class, type = "Publishing Company")))
            }
    )
    @PostMapping
    public ResponseEntity<Editora> createEditora(@RequestBody Editora editora) {
        Editora novaEditora = editoraService.createEditora(editora);
        return new ResponseEntity<>(novaEditora, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Update Publishing Company",
            description = "Update referenced Publishing Company according to the informed parameters in body and id",
            tags = "Publishing Company",
            method = "PUT",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Editora.class, type = "Publishing Company"))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Publishing Company not found",
                            content = @Content(array = @ArraySchema))
            }
    )
    @PutMapping("/{editoraId}")
    public ResponseEntity<Editora> updateEditora(@PathVariable Long editoraId, @RequestBody Editora editora) {
        Editora editoraAtualizada = editoraService.updateEditora(editoraId, editora);
        if (editoraAtualizada != null) {
            return new ResponseEntity<>(editoraAtualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Operation(
            summary = "Delete Publishing Company",
            description = "Delete referenced Publishing Company according to the informed id",
            tags = "Publishing Company",
            method = "DELETE",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Publishing Company deleted",
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
    @DeleteMapping("/{editoraId}")
    public ResponseEntity<DeleteMessageDTO> deleteEditora(@PathVariable Long editoraId) {
        return editoraService.deleteEditora(editoraId);
    }
}