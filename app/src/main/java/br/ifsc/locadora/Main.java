package br.ifsc.locadora;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=== SISTEMA DE LOCACAO DE JOGOS ===");

        // Usuário
        int idUsuario = random.nextInt(1000) + 1;

        System.out.print("Nome do usuario: ");
        String nome = entrada.nextLine();

        System.out.print("Email: ");
        String email = entrada.nextLine();

        System.out.print("Telefone: ");
        String telefone = entrada.nextLine();

        System.out.print("Senha: ");
        String senha = entrada.nextLine();

        Usuario cliente = new Usuario(idUsuario, nome, email, telefone, senha);

        // Cadastro da plataforma
        int idPlataforma = random.nextInt(1000) + 1;

        System.out.print("Nome da plataforma: ");
        String nomePlataforma = entrada.nextLine();

        System.out.print("Descricao da plataforma: ");
        String descricao = entrada.nextLine();

        Plataforma plataforma = new Plataforma(idPlataforma, nomePlataforma, descricao);

        // Cadastro do jogo
        int idJogo = random.nextInt(1000) + 1;

        System.out.print("Titulo do jogo: ");
        String titulo = entrada.nextLine();

        System.out.print("Genero do jogo: ");
        String genero = entrada.nextLine();

        Jogo jogo = new Jogo(idJogo, titulo, genero);

        // Locação
        System.out.print("\nEstoque disponivel: ");
        int estoque = entrada.nextInt();

        System.out.print("Preco diario: ");
        double precoDiario = entrada.nextDouble();

        JogoPlataforma jogoPlataforma = new JogoPlataforma(jogo, plataforma, estoque, precoDiario);

        System.out.print("\nQuantidade de dias de locacao: ");
        int dias = entrada.nextInt();

        TempoLocacaoJogo tempo = new TempoLocacaoJogo(dias);

        int idLocacao = random.nextInt(10000) + 1;

        LocacaoJogo locacao = new LocacaoJogo(idLocacao, cliente, jogoPlataforma, tempo);

        System.out.println("\n=== DETALHES DA LOCACAO ===");
        System.out.println(locacao);

        entrada.close();
    }
}
