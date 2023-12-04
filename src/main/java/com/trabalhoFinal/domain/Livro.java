package com.trabalhoFinal.domain;
import java.io.Serializable;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
public class Livro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "livro_Id")
    private Long livroId;

    private String titulo;

    private Integer anoPublicacao;

    @OneToOne()
    @JoinColumn(name = "editora_id")
    private Editora editora;

    @OneToOne()
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @ManyToMany()
    @JoinTable(
            name="autor_livro",
            joinColumns = @JoinColumn(name="livro_Id"),
            inverseJoinColumns = @JoinColumn(name="autor_id"),
            uniqueConstraints = @UniqueConstraint(
                    name = "autor_livro_PK",
                    columnNames = {"livro_Id", "autor_id"}
            )
    )
    //@JsonIgnore
    private List<Autor> autores;

    public Livro(Long livroId, String titulo, Integer anoPublicacao, Editora editora, Genero genero, List<Autor> autores) {
        this.livroId = livroId;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.editora = editora;
        this.genero = genero;
        this.autores = autores;
    }

    public Livro() {
    }


// Getters e Setters

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((livroId == null) ? 0 : livroId.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        result = prime * result + ((anoPublicacao == null) ? 0 : anoPublicacao.hashCode());
        result = prime * result + ((editora == null) ? 0 : editora.hashCode());
        result = prime * result + ((genero == null) ? 0 : genero.hashCode());
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
        Livro other = (Livro) obj;
        if (livroId == null) {
            if (other.livroId != null)
                return false;
        } else if (!livroId.equals(other.livroId))
            return false;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        if (anoPublicacao == null) {
            if (other.anoPublicacao != null)
                return false;
        } else if (!anoPublicacao.equals(other.anoPublicacao))
            return false;
        if (editora == null) {
            if (other.editora != null)
                return false;
        } else if (!editora.equals(other.editora))
            return false;
        if (genero == null) {
            if (other.genero != null)
                return false;
        } else if (!genero.equals(other.genero))
            return false;
        return true;
    }

    

}
