package com.trabalhoFinal.configuration;

import com.trabalhoFinal.domain.Autor;
import com.trabalhoFinal.domain.Editora;
import com.trabalhoFinal.domain.Genero;
import com.trabalhoFinal.domain.Livro;
import com.trabalhoFinal.repository.AutorRepository;
import com.trabalhoFinal.repository.EditoraRepository;
import com.trabalhoFinal.repository.GeneroRepository;
import com.trabalhoFinal.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class DBConfig {

    //@Bean
    public CommandLineRunner commandLineRunner(AutorRepository autorRepository,
                                               EditoraRepository editoraRepository,
                                               GeneroRepository generoRepository,
                                               LivroRepository livroRepository) {
        return args -> {
            // Instantiate 10 Autor objects with random names and emails
            List<Autor> autores = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Autor autor = new Autor((long) i, getRandomName(), "author" + i + "@example.com", null);
                autores.add(autor);
            }
            autorRepository.saveAll(autores);

            // Instantiate 10 Editora objects with imaginative names
            List<Editora> editoras = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Editora editora = new Editora();
                editora.setNome("Fantastic Publishers Inc. " + i);
                editora.setLocalizacao("City " + i);
                editoras.add(editora);
            }
            editoraRepository.saveAll(editoras);

            // Instantiate 10 Genero objects with creative names
            List<Genero> generos = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Genero genero = new Genero((long) i, "Adventure " + i, null);
                generos.add(genero);
            }
            generoRepository.saveAll(generos);

            // Instantiate 10 Livro objects with interesting titles and random author associations
            List<Livro> livros = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Livro livro = new Livro((long) i, getRandomBookTitle(), 2000 + i, null, null, getRandomAuthors(autores));
                livros.add(livro);
            }
            livroRepository.saveAll(livros);
        };
    }

    private String getRandomName() {
        String[] names = {"Alice", "Bob", "Charlie", "Diana", "Eva", "Felix", "Grace", "Hector", "Ivy", "Jack"};
        return names[new Random().nextInt(names.length)];
    }

    private String getRandomBookTitle() {
        String[] titles = {"The Enigmatic Echo", "Whispers of Wonderland", "Stardust Symphony", "Eternal Eclipse", "Chronicles of Chaos", "Spectral Serenade", "Mystic Mirage", "Celestial Secrets", "Harmony of Heroes", "Luminous Labyrinth"};
        return titles[new Random().nextInt(titles.length)];
    }

    private List<Autor> getRandomAuthors(List<Autor> allAuthors) {
        List<Autor> authors = new ArrayList<>();
        Random random = new Random();
        int numAuthors = random.nextInt(3) + 1; // Randomly choose 1 to 3 authors for a book
        for (int i = 0; i < numAuthors; i++) {
            authors.add(allAuthors.get(random.nextInt(allAuthors.size())));
        }
        return authors;
    }
}

