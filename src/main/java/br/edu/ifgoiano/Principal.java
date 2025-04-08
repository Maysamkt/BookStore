package br.edu.ifgoiano;




public class Principal {
    public static void main(String[] args) {
        Controller gerenciador = new Controller();
        gerenciador.setup();

        gerenciador.criar();
        gerenciador.ler();
        gerenciador.atualizar();
     //   gerenciador.excluir();

        System.out.println("📚 Livros por Autor:");
        gerenciador.livrosPorAutor(1L);

        System.out.println("\n👨‍💼 Autores por Livro:");
        gerenciador.autoresPorLivro(1L);

        System.out.println("\n🏛️ Editoras por Livro:");
        gerenciador.editorasPorLivro(1L);

        System.out.println("\n📘 Livros por Editora:");
        gerenciador.livrosPorEditora(1L);

        System.out.println("\n👨‍💼 Autores por Editora:");
        gerenciador.autoresPorEditora(1L);

        System.out.println("\n🏢 Editoras por Autor:");
        gerenciador.editorasPorAutor(1L);

        gerenciador.sair();
    }
}
