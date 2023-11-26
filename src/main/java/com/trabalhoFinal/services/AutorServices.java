package com.trabalhoFinal.services;

import com.trabalhoFinal.domain.Autor;
import com.trabalhoFinal.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServices {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> getAllAutores() {
        return autorRepository.findAll();
    }

    public Autor getAutorById(Long autorId) {
        Optional<Autor> autorOptional = autorRepository.findById(autorId);
        return autorOptional.orElse(null);
    }

    public Autor createAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    public Autor updateAutor(Long autorId, Autor autor) {
        Optional<Autor> autorOptional = autorRepository.findById(autorId);

        if (autorOptional.isPresent()) {
            Autor autorExistente = autorOptional.get();
            autorExistente.setNome(autor.getNome());
            autorExistente.setEmail(autor.getEmail());

            return autorRepository.save(autorExistente);
        } else {
            return null; // Autor não encontrado
        }
    }

    public void deleteAutor(Long autorId) {
        autorRepository.deleteById(autorId);
    }
}