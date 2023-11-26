package com.trabalhoFinal.domain;

import java.io.Serializable;

import jakarta.persistence.*;
//import java.util.Objects;

@Entity
@Table(name = "genero")
public class Genero implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genero_id")
    private Long generoId;

   // @Column(name = "nome")
    private String nome;

    
    // Getters e Setters

    public Genero(Long generoId, String nome) {
        this.generoId = generoId;
        this.nome = nome;
    }

    public Long getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Long generoId) {
        this.generoId = generoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((generoId == null) ? 0 : generoId.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Genero other = (Genero) obj;
        if (generoId == null) {
            if (other.generoId != null)
                return false;
        } else if (!generoId.equals(other.generoId))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    
}