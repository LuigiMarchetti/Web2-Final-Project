package com.trabalhoFinal.domain;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "editora")
public class Editora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "editora_id")
    private Long editoraId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "localizacao")
    private String localizacao;

    @OneToOne(mappedBy = "editora")
    private Livro editora;

    // Getters e Setters


    public Editora() {
    }

    public Editora(Long editoraId, String nome, String localizacao, Livro editora) {
        this.editoraId = editoraId;
        this.nome = nome;
        this.localizacao = localizacao;
        this.editora = editora;
    }

    public Livro getEditora() {
        return editora;
    }

    public void setEditora(Livro editora) {
        this.editora = editora;
    }

    public Long getEditoraId() {
        return editoraId;
    }

    public void setEditoraId(Long editoraId) {
        this.editoraId = editoraId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((editoraId == null) ? 0 : editoraId.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((localizacao == null) ? 0 : localizacao.hashCode());
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
        Editora other = (Editora) obj;
        if (editoraId == null) {
            if (other.editoraId != null)
                return false;
        } else if (!editoraId.equals(other.editoraId))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (localizacao == null) {
            if (other.localizacao != null)
                return false;
        } else if (!localizacao.equals(other.localizacao))
            return false;
        return true;
    }

   

}