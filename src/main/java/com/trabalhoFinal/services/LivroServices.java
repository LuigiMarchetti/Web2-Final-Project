package com.trabalhoFinal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhoFinal.domain.Livro;
import com.trabalhoFinal.repository.LivroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LivroServices {

    @Autowired
    private LivroRepository livroRepository;

    // Obter todos os livros
    public List<Livro> getAllLivros() {
        return livroRepository.findAll();
    }

    // Obter um livro por ID
    public Livro getLivroById(Long livroId) {
        Optional<Livro> livroOptional = livroRepository.findById(livroId);
        return livroOptional.orElse(null);
    }

    // Criar um novo livro
    public Livro createLivro(Livro livro) {
        // Pode adicionar lógica de validação antes de salvar no banco de dados
        return livroRepository.save(livro);
    }

    // Atualizar um livro existente
    public Livro updateLivro(Long livroId, Livro livro) {
        Optional<Livro> livroOptional = livroRepository.findById(livroId);

        if (livroOptional.isPresent()) {
            Livro livroExistente = livroOptional.get();

            // Atualiza as propriedades do livro existente com os novos valores
            livroExistente.setTitulo(livro.getTitulo());
            livroExistente.setAnoPublicacao(livro.getAnoPublicacao());
            livroExistente.setEditora(livro.getEditora());
            livroExistente.setGenero(livro.getGenero());

            // Pode adicionar mais lógica de validação antes de salvar no banco de dados

            return livroRepository.save(livroExistente);
        } else {
            return null; // Livro não encontrado
        }
    }

    // Excluir um livro
    public void deleteLivro(Long livroId) {
        livroRepository.deleteById(livroId);
    }
}
