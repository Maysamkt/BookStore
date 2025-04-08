package br.edu.ifgoiano;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "autor")
@NamedQueries({
        @NamedQuery(name = "Autor.findByLivro", query = "SELECT a FROM Autor a JOIN a.livros l WHERE l.id = ?1"),
        @NamedQuery(name = "Autor.findByEditora", query = "SELECT DISTINCT a FROM Autor a JOIN a.editoras e WHERE e.id = ?1")
})
public class Autor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "autor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;


    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Livro> livros;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "autor_editora",
            joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns = @JoinColumn(name = "editora_id")
    )
    private List<Editora> editoras;

    public Autor() {
    }

    public Autor( Long id, String nome ) {
        this.id = id;
        this.nome = nome;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Editora> getEditoras() {
        return editoras;
    }

    public void setEditoras(List<Editora> editoras) {
        this.editoras = editoras;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autor autor)) return false;
        return Objects.equals(id, autor.id) && Objects.equals(nome, autor.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
