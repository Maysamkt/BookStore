package br.edu.ifgoiano;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "livro")
@NamedQueries({
        @NamedQuery(name = "Livro.findAll", query = "FROM Livro l"),
        @NamedQuery(name = "Livro.findById", query = "SELECT l FROM Livro l WHERE l.id = ?1"),
        @NamedQuery(name = "Livro.findByTitulo", query = "SELECT l FROM Livro l WHERE l.titulo = ?1"),
        @NamedQuery(name = "Livro.findByAutor", query = "SELECT l FROM Livro l WHERE l.autor.id = ?1"),
        @NamedQuery(name = "Livro.findByEditora", query = "SELECT l FROM Livro l WHERE l.editora.id = ?1")
})
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "livro_id")
    private long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "ano_pub", nullable = false)
    private int anoPub;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "editora_id", nullable = false)
    private Editora editora;

    public Livro() {
    }

    public Livro(String titulo, int anoPub, String isbn, Autor autor, Editora editora) {
        this.titulo = titulo;
        this.anoPub = anoPub;
        this.isbn = isbn;
        this.autor = autor;
        this.editora = editora;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoPub() {
        return anoPub;
    }

    public void setAnoPub(int anoPub) {
        this.anoPub = anoPub;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livro livro)) return false;
        return id == livro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anoPub=" + anoPub +
                ", isbn='" + isbn + '\'' +
                ", autor=" + (autor != null ? autor.getNome() : "null") +
                ", editora=" + (editora != null ? editora.getNome() : "null") +
                '}';
    }
}

