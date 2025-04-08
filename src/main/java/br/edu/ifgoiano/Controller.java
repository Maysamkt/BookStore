package br.edu.ifgoiano;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Controller {
    private SessionFactory sessionFactory;

    public void setup() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException("Erro ao configurar o Hibernate", e);
        }
    }

    public void criar() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Autor autor = new Autor();
        autor.setNome("João Silva");
        session.save(autor);

        Editora editora = new Editora();
        editora.setNome("Arqueiro");
        session.save(editora);

        Livro livro = new Livro();
        livro.setTitulo("Livro");
        livro.setAnoPub(2009);
        livro.setIsbn("978-85-333-0227-3");
        livro.setAutor(autor);
        livro.setEditora(editora);

        session.save(livro);
        session.getTransaction().commit();
        session.close();
    }

    public void ler() {
        Session session = sessionFactory.openSession();
        Livro livro = session.get(Livro.class, 1L); // Evite usar números com zero à esquerda

        if (livro != null) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Ano de Publicação: " + livro.getAnoPub());
            System.out.println("ISBN: " + livro.getIsbn());
            System.out.println("Autor: " + livro.getAutor().getNome());
            System.out.println("Editora: " + livro.getEditora().getNome());
        } else {
            System.out.println("Livro não encontrado.");
        }

        session.close();
    }

    public void atualizar() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Autor autor = new Autor(1L, "Maria Silva");
        Editora editora = new Editora(1L, "Miramar");

        Livro livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("Programação");
        livro.setAnoPub(2013);
        livro.setIsbn("978-65-89999-01-3");
        livro.setAutor(autor);
        livro.setEditora(editora);

        session.update(livro);
        session.getTransaction().commit();
        session.close();
    }

    public void excluir() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Livro livro = session.get(Livro.class, 2L);
        if (livro != null) {
            session.delete(livro);
        } else {
            System.out.println("Livro com ID 2 não encontrado para exclusão.");
        }

        session.getTransaction().commit();
        session.close();
    }

    public void livrosPorAutor(long autorId) {
        Session session = sessionFactory.openSession();
        List<Livro> livros = session
                .createNamedQuery("Livro.findByAutor", Livro.class)
                .setParameter(1, autorId)
                .getResultList();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o autor com ID: " + autorId);
        } else {
            livros.forEach(l -> System.out.println("Livro: " + l.getTitulo()));
        }

        session.close();
    }

    public void autoresPorLivro(long livroId) {
        Session session = sessionFactory.openSession();
        List<Autor> autores = session.createNamedQuery("Autor.findByLivro", Autor.class)
                .setParameter(1, livroId)
                .getResultList();
        autores.forEach(a -> System.out.println(a.getNome()));
        session.close();
    }

    public void editorasPorLivro(long livroId) {
        Session session = sessionFactory.openSession();
        List<Editora> editoras = session.createNamedQuery("Editora.findByLivro", Editora.class)
                .setParameter(1, livroId)
                .getResultList();
        editoras.forEach(e -> System.out.println(e.getNome()));
        session.close();
    }

    public void livrosPorEditora(long editoraId) {
        Session session = sessionFactory.openSession();
        List<Livro> livros = session.createNamedQuery("Livro.findByEditora", Livro.class)
                .setParameter(1, editoraId)
                .getResultList();
        livros.forEach(l -> System.out.println(l.getTitulo()));
        session.close();
    }

    public void autoresPorEditora(long editoraId) {
        Session session = sessionFactory.openSession();
        List<Autor> autores = session.createNamedQuery("Autor.findByEditora", Autor.class)
                .setParameter(1, editoraId)
                .getResultList();
        autores.forEach(a -> System.out.println(a.getNome()));
        session.close();
    }

    public void editorasPorAutor(long autorId) {
        Session session = sessionFactory.openSession();
        List<Editora> editoras = session.createNamedQuery("Editora.findByAutor", Editora.class)
                .setParameter(1, autorId)
                .getResultList();
        editoras.forEach(e -> System.out.println(e.getNome()));
        session.close();
    }


    public void sair() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
