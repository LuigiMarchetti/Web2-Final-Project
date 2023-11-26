package com.trabalhoFinal.services;

import com.trabalhoFinal.domain.Genero;
import com.trabalhoFinal.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroServices {

    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> getAllGeneros() {
        return generoRepository.findAll();
    }

    public Genero getGeneroById(Long generoId) {
        Optional<Genero> generoOptional = generoRepository.findById(generoId);
        return generoOptional.orElse(null);
    }

    public Genero createGenero(Genero genero) {
        return generoRepository.save(genero);
    }

    public Genero updateGenero(Long generoId, Genero genero) {
        Optional<Genero> generoOptional = generoRepository.findById(generoId);

        if (generoOptional.isPresent()) {
            Genero generoExistente = generoOptional.get();
            generoExistente.setNome(genero.getNome());

            return generoRepository.save(generoExistente);
        } else {
            return null; // Gênero não encontrado
        }
    }

    public void deleteGenero(Long generoId) {
        generoRepository.deleteById(generoId);
    }
}
