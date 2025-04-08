package br.edu.ifgoiano;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "editora")
@NamedQueries({
        @NamedQuery(name = "Editora.findAll", query = "FROM Editora e"),
        @NamedQuery(name = "Editora.findById", query = "SELECT e FROM Editora e WHERE e.id = ?1"),
        @NamedQuery(name = "Editora.findByNome", query = "SELECT e FROM Editora e WHERE e.nome = ?1"),
        @NamedQuery(name = "Editora.findByLivro", query = "SELECT e FROM Editora e JOIN e.livros l WHERE l.id = ?1"),
        @NamedQuery(name = "Editora.findByAutor", query = "SELECT DISTINCT e FROM Editora e JOIN e.autores a WHERE a.id = ?1")
})
public class Editora implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "editora_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "editora", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Livro> livros;

    @ManyToMany(mappedBy = "editoras", fetch = FetchType.LAZY)
    private List<Autor> autores;


    public Editora() {
    }

    public Editora(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Editora editora)) return false;
        return Objects.equals(id, editora.id) &&
                Objects.equals(nome, editora.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @Override
    public String toString() {
        return "Editora{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
