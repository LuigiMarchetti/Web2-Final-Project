package com.trabalhoFinal.services;

import com.trabalhoFinal.dtos.DeleteMessageDTO;
import com.trabalhoFinal.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.trabalhoFinal.domain.Editora;
import com.trabalhoFinal.repository.EditoraRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraServices {

    @Autowired
    private EditoraRepository editoraRepository;

    public List<Editora> getAllEditoras() {
        return editoraRepository.findAll();
    }

    public Editora getEditoraById(Long editoraId) {
        Optional<Editora> editoraOptional = editoraRepository.findById(editoraId);
        return editoraOptional.orElse(null);
    }

    public Editora createEditora(Editora editora) {
        return editoraRepository.save(editora);
    }

    public Editora updateEditora(Long editoraId, Editora editora) {
        Optional<Editora> editoraOptional = editoraRepository.findById(editoraId);

        if (editoraOptional.isPresent()) {
            Editora editoraExistente = editoraOptional.get();
            editoraExistente.setNome(editora.getNome());
            editoraExistente.setLocalizacao(editora.getLocalizacao());

            return editoraRepository.save(editoraExistente);
        } else {
            return null; // Editora não encontrada
        }
    }

    public ResponseEntity<DeleteMessageDTO> deleteEditora(Long editoraId) {
        try {
            editoraRepository.deleteById(editoraId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DeleteMessageDTO(Constants.ERRO, e.getMessage()));
        }

        return ResponseEntity.ok(new DeleteMessageDTO(Constants.OK, Constants.OK));
    }
}