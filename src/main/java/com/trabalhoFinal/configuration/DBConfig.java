package com.trabalhoFinal.configuration;

import com.trabalhoFinal.domain.Autor;
import com.trabalhoFinal.domain.Livro;
import com.trabalhoFinal.repository.AutorRepository;
import com.trabalhoFinal.repository.EditoraRepository;
import com.trabalhoFinal.repository.GeneroRepository;
import com.trabalhoFinal.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
    @Bean
    CommandLineRunner commandLineRunner(AutorRepository autorRepository,
                                        EditoraRepository editoraRepository,
                                        GeneroRepository generoRepository,
                                        LivroRepository livroRepository) {
        return args -> {
            Autor autor = new Autor(1L,"Luigi G. Marchetti","luigi@gmail.com", null);
            Autor autor2 = new Autor(2L,"André Heller","Andre@gmail.com", null);

            Livro livro = new Livro(1L, "Harry Potter", 1980, null, null, null);

            autorRepository.save(autor);
            //autorRepository.save(autor2);
            //livroRepository.save(livro);
        };
    }
}
