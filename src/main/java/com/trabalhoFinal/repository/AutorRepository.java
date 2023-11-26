package com.trabalhoFinal.repository;

import com.trabalhoFinal.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    // Você pode adicionar métodos de consulta personalizados aqui, se necessário
}
