package br.ifsc.locadora;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Optional;
import java.util.InputMismatchException;


public class Main {
    private static Scanner entrada = new Scanner(System.in);
    private static Random random = new Random();

    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Plataforma> plataformas = new ArrayList<>();
    private static List<JogoPlataforma> jogosPlataforma = new ArrayList<>();
    private static List<Console> consoles = new ArrayList<>();
    private static List<Acessorio> acessorios = new ArrayList<>();
    private static List<LocacaoJogo> locacoesJogo = new ArrayList<>();
    private static List<LocacaoConsole> locacoesConsole = new ArrayList<>();

    public static void main(String[] args) {
        seedDadosIniciais();
        menuPrincipal();
    }

    private static void menuPrincipal() {
        while (true) {
            System.out.println("\n=== SISTEMA LOCADORA ===");
            System.out.println("1 - Cadastrar usuario");
            System.out.println("2 - Login");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            int op = lerInt();

            switch (op) {
                case 1 -> cadastrarUsuario();
                case 2 -> login();
                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    private static void cadastrarUsuario() {
        System.out.println("\n--- Cadastro de Usuario ---");
        int id = gerarIdUsuario();
        System.out.print("Nome: ");
        String nome = lerLinha();
        System.out.print("Email: ");
        String email = lerLinha();
        System.out.print("Telefone: ");
        String telefone = lerLinha();
        System.out.print("Senha: ");
        String senha = lerLinha();
        System.out.print("E administrador? (true/false): ");
        boolean admin = lerBoolean();

        Usuario u = new Usuario(id, nome, email, telefone, senha, admin);
        usuarios.add(u);
        System.out.println("Usuario cadastrado: " + u);
    }

    private static void login() {
        System.out.println("\n--- Login ---");
        System.out.print("Email: ");
        String email = lerLinha();
        System.out.print("Senha: ");
        String senha = lerLinha();

        Optional<Usuario> opt = usuarios.stream()
                .filter(x -> x.getEmail().equalsIgnoreCase(email) && x.getSenha().equals(senha))
                .findFirst();

        if (opt.isEmpty()) {
            System.out.println("Credenciais invalidas.");
            return;
        }

        Usuario logado = opt.get();
        System.out.println("Bem vindo, " + logado.getNome() + "!");
        if (logado.isAdministrador()) {
            menuAdmin(logado);
        } else {
            menuCliente(logado);
        }
    }


    /* ---------- Menus ---------- */

    private static void menuCliente(Usuario u) {
        while (true) {
            System.out.println("\n--- Menu Cliente ---");
            System.out.println("1 - Listar jogos disponiveis");
            System.out.println("2 - Alugar jogo");
            System.out.println("3 - Alugar console (uso local)");
            System.out.println("4 - Historico de locacoes");
            System.out.println("5 - Atualizar meus dados");
            System.out.println("0 - Logout");
            System.out.print("Escolha: ");
            int op = lerInt();

            switch (op) {
                case 1 -> listarJogos();
                case 2 -> alugarJogo(u);
                case 3 -> alugarConsole(u);
                case 4 -> históricoUsuario(u);
                case 5 -> atualizarDadosUsuario(u);
                case 0 -> {
                    System.out.println("Logout...");
                    return;
                }
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    private static void menuAdmin(Usuario admin) {
        while (true) {
            System.out.println("\n--- Menu Administrador ---");
            System.out.println("1 - Listar jogos disponiveis");
            System.out.println("2 - Alugar jogo");
            System.out.println("3 - Alugar console (uso local)");
            System.out.println("4 - Historico de locacoes");
            System.out.println("5 - Atualizar meus dados");
            System.out.println("6 - Gerenciar usuarios");
            System.out.println("7 - Gerenciar jogos e plataformas");
            System.out.println("8 - Gerenciar consoles e acessorios");
            System.out.println("9 - Listar todas as locacoes (jogos e consoles)");
            System.out.println("10 - Ver disponibilidade (estoque jogos / consoles em uso)");
            System.out.println("0 - Logout");
            System.out.print("Escolha: ");

            int op = lerInt();

            switch (op) {
                // --- Funções de Cliente ---
                case 1 -> listarJogos();
                case 2 -> alugarJogo(admin);
                case 3 -> alugarConsole(admin);
                case 4 -> históricoUsuario(admin);
                case 5 -> atualizarDadosUsuario(admin);

                // --- Funções de Administrador ---
                case 6 -> gerenciarUsuarios();
                case 7 -> gerenciarJogosPlataformas();
                case 8 -> gerenciarConsolesAcessorios();
                case 9 -> listarTodasLocacoes();
                case 10 -> verDisponibilidade();

                case 0 -> {
                    System.out.println("Logout...");
                    return;
                }

                default -> System.out.println("Opção inválida.");
            }
        }
    }

    /* ---------- Funcionalidades Cliente ---------- */

    private static void listarJogos() {
        System.out.println("\n--- Jogos disponiveis ---");
        if (jogosPlataforma.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado.");
            return;
        }
        int idx = 1;
        for (JogoPlataforma jp : jogosPlataforma) {
            System.out.printf("%d) %s - Plataforma: %s | Preco: R$ %.2f | Estoque: %d%n",
                    idx++,
                    jp.getJogo().getTitulo(),
                    jp.getPlataforma().getNome(),
                    jp.getPrecoDiario(),
                    jp.getEstoque());
        }
    }

    private static void alugarJogo(Usuario u) {
        if (jogosPlataforma.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado.");
            return;
        }

        listarJogos();
        System.out.print("Escolha o numero do jogo que quer alugar: ");
        int escolha = lerInt();
        if (escolha < 1 || escolha > jogosPlataforma.size()) {
            System.out.println("Escolha invalida.");
            return;
        }
        JogoPlataforma escolhido = jogosPlataforma.get(escolha - 1);

        if (escolhido.getEstoque() <= 0) {
            System.out.println("Este jogo esta sem estoque no momento.");
            return;
        }

        System.out.print("Quantidade de dias de locacao: ");
        int dias = lerInt();

        int idLoc = gerarIdLocacao();
        LocacaoJogo loc = new LocacaoJogo(idLoc, u, escolhido, dias);
        escolhido.diminuirEstoque();
        locacoesJogo.add(loc);

        System.out.println("Locacao realizada:");
        System.out.println(loc);
    }

    private static void alugarConsole(Usuario u) {
        if (consoles.isEmpty()) {
            System.out.println("Nenhum console cadastrado.");
            return;
        }

        System.out.println("\n--- Consoles ---");
        for (int i = 0; i < consoles.size(); i++) {
            Console c = consoles.get(i);
            System.out.printf("%d) %s - R$ %.2f/h%n", i + 1, c.getNome(), c.getPrecoHora());
        }
        System.out.print("Escolha o console: ");
        int escolha = lerInt();
        if (escolha < 1 || escolha > consoles.size()) {
            System.out.println("Escolha invalida.");
            return;
        }
        Console consoleEscolhido = consoles.get(escolha - 1);

        // Seleção de acessórios
        List<Acessorio> selecionados = new ArrayList<>();
        if (!acessorios.isEmpty()) {
            System.out.println("\nAcessorios disponíveis (digite 0 para terminar):");
            for (int i = 0; i < acessorios.size(); i++) {
                System.out.printf("%d) %s%n", i + 1, acessorios.get(i).getNome());
            }
            while (true) {
                System.out.print("Escolha numero do acessorio (0 encerra): ");
                int a = lerInt();
                if (a == 0) break;
                if (a < 1 || a > acessorios.size()) {
                    System.out.println("Invalido.");
                } else {
                    selecionados.add(acessorios.get(a - 1));
                    System.out.println("Adicionado: " + acessorios.get(a - 1).getNome());
                }
            }
        }

        System.out.print("Tempo de uso (horas): ");
        int horas = lerInt();

        int idLoc = gerarIdLocacao();
        LocacaoConsole loc = new LocacaoConsole(idLoc, u, consoleEscolhido, selecionados, horas);
        locacoesConsole.add(loc);

        System.out.println("Locacao de console criada:");
        System.out.println(loc);
    }

    private static void históricoUsuario(Usuario u) {
        System.out.println("\n--- Historico de Locacoes de Jogos ---");
        boolean achou = false;
        for (LocacaoJogo lj : locacoesJogo) {
            if (lj.toString().contains(u.getNome())) {
                System.out.println(lj);
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhuma locacao de jogo encontrada para voce.");

        System.out.println("\n--- Historico de Uso de Consoles ---");
        achou = false;
        for (LocacaoConsole lc : locacoesConsole) {
            if (lc.toString().contains(u.getNome())) {
                System.out.println(lc);
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum uso de console encontrado para voce.");
    }

    private static void atualizarDadosUsuario(Usuario u) {
        while (true) {
            System.out.println("\n--- Atualizar dados ---");
            System.out.println("1 - Nome");
            System.out.println("2 - Email");
            System.out.println("3 - Telefone");
            System.out.println("4 - Senha");
            System.out.println("0 - Cancelar");
            System.out.print("Escolha: ");

            int opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Novo nome: ");
                    u.setNome(lerString());
                    System.out.println("Nome atualizado com sucesso!");
                }
                case 2 -> {
                    System.out.print("Novo email: ");
                    u.setEmail(lerString());
                    System.out.println("Email atualizado com sucesso!");
                }
                case 3 -> {
                    System.out.print("Novo telefone: ");
                    u.setTelefone(lerString());
                    System.out.println("Telefone atualizado com sucesso!");
                }
                case 4 -> {
                    System.out.print("Nova senha: ");
                    u.setSenha(lerString());
                    System.out.println("Senha atualizada com sucesso!");
                }
                case 0 -> {
                    System.out.println("Atualização cancelada.");
                    return;
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }



    /* ---------- Funcionalidades Admin (CRUD) ---------- */

    private static void gerenciarUsuarios() {
        while (true) {
            System.out.println("\n--- Gerenciar Usuarios ---");
            System.out.println("1 - Listar\n2 - Cadastrar\n3 - Atualizar\n4 - Remover\n0 - Voltar");
            System.out.print("Escolha: ");
            int op = lerInt();

            switch (op) {
                case 1 -> {
                    System.out.println("\nUsuarios:");
                    for (Usuario us : usuarios) System.out.println(us);
                }
                case 2 -> cadastrarUsuario();
                case 3 -> {
                    System.out.print("Email do usuario a atualizar: ");
                    String email = lerLinha();
                    Optional<Usuario> opt = usuarios.stream().filter(x -> x.getEmail().equalsIgnoreCase(email)).findFirst();
                    if (opt.isPresent()) {
                        atualizarDadosUsuario(opt.get());
                    } else System.out.println("Usuario nao encontrado.");
                }
                case 4 -> {
                    System.out.print("Email do usuario a remover: ");
                    String email = lerLinha();
                    usuarios.removeIf(x -> x.getEmail().equalsIgnoreCase(email));
                    System.out.println("Removido (se existia).");
                }
                case 0 -> { return; }
                default -> System.out.println("Invalido.");
            }
        }
    }

    private static void gerenciarJogosPlataformas() {
        while (true) {
            System.out.println("\n--- Gerenciar Jogos e Plataformas ---");
            System.out.println("1 - Listar jogos\n2 - Cadastrar plataforma\n3 - Cadastrar jogo na plataforma\n4 - Atualizar estoque/preço\n5 - Remover jogo\n0 - Voltar");
            System.out.print("Escolha: ");
            int op = lerInt();
            entrada.nextLine();

            switch (op) {
                case 1 -> listarJogos();
                case 2 -> {
                    int id = gerarId();
                    System.out.print("Nome da plataforma: ");
                    String nome = lerLinha();
                    System.out.print("Descricao: ");
                    String desc = lerLinha();
                    Plataforma p = new Plataforma(id, nome, desc);
                    plataformas.add(p);
                    System.out.println("Plataforma cadastrada: " + p);
                }
                case 3 -> {
                    if (plataformas.isEmpty()) {
                        System.out.println("Cadastre uma plataforma primeiro.");
                        break;
                    }
                    System.out.print("Titulo do jogo: ");
                    String titulo = lerLinha();
                    System.out.print("Genero: ");
                    String genero = lerLinha();
                    System.out.println("Escolha plataforma:");
                    for (int i = 0; i < plataformas.size(); i++) {
                        System.out.printf("%d) %s%n", i + 1, plataformas.get(i).getNome());
                    }
                    int pindex = lerInt();
                    if (pindex < 1 || pindex > plataformas.size()) {
                        System.out.println("Invalido.");
                        break;
                    }
                    Plataforma escolhido = plataformas.get(pindex - 1);
                    System.out.print("Estoque: ");
                    int estoque = lerInt();
                    System.out.print("Preço diario: ");
                    double preco = lerDouble();
                    Jogo j = new Jogo(gerarId(), titulo, genero);
                    JogoPlataforma jp = new JogoPlataforma(j, escolhido, estoque, preco);
                    jogosPlataforma.add(jp);
                    System.out.println("Jogo cadastrado: " + jp);
                }
                case 4 -> {
                    listarJogos();
                    System.out.print("Escolha jogo para atualizar (numero): ");
                    int idx = lerInt();
                    if (idx < 1 || idx > jogosPlataforma.size()) {
                        System.out.println("Invalido.");
                        break;
                    }
                    JogoPlataforma jp = jogosPlataforma.get(idx - 1);
                    System.out.print("Novo estoque (atual " + jp.getEstoque() + "): ");
                    int novoEst = lerInt();
                    JogoPlataforma novo = new JogoPlataforma(jp.getJogo(), jp.getPlataforma(), novoEst, jp.getPrecoDiario());
                    jogosPlataforma.set(idx - 1, novo);
                    System.out.println("Atualizado.");
                }
                case 5 -> {
                    listarJogos();
                    System.out.print("Escolha jogo para remover (número): ");
                    int idx = lerInt();
                    if (idx >= 1 && idx <= jogosPlataforma.size()) {
                        jogosPlataforma.remove(idx - 1);
                        System.out.println("Removido.");
                    } else System.out.println("Invalido.");
                }
                case 0 -> { return; }
                default -> System.out.println("Invalido.");
            }
        }
    }

    private static void gerenciarConsolesAcessorios() {
        while (true) {
            System.out.println("\n--- Gerenciar Consoles e Acessórios ---");
            System.out.println("1 - Listar consoles\n2 - Cadastrar console\n3 - Atualizar console\n4 - Remover console\n5 - Listar acessorios\n6 - Cadastrar acessorio\n7 - Remover acessorio\n0 - Voltar");
            System.out.print("Escolha: ");
            int op = lerInt();
            entrada.nextLine();

            switch (op) {
                case 1 -> {
                    if (consoles.isEmpty()) System.out.println("Nenhum console.");
                    else consoles.forEach(c -> System.out.println(c));
                }
                case 2 -> {
                    int id = gerarId();
                    System.out.print("Nome do console: ");
                    String nome = lerLinha();
                    System.out.print("Preco por hora: ");
                    double preco = lerDouble();
                    Console c = new Console(id, nome, preco);
                    consoles.add(c);
                    System.out.println("Console cadastrado: " + c);
                }
                case 3 -> {
                    if (consoles.isEmpty()) { System.out.println("Nenhum console."); break; }
                    for (int i = 0; i < consoles.size(); i++) {
                        System.out.printf("%d) %s%n", i + 1, consoles.get(i).getNome());
                    }
                    System.out.print("Escolha console: ");
                    int idx = lerInt();
                    if (idx < 1 || idx > consoles.size()) { System.out.println("Invalido."); break; }
                    Console old = consoles.get(idx - 1);
                    System.out.print("Novo nome (enter para manter '" + old.getNome() + "'): ");
                    String nn = lerLinha();
                    System.out.print("Novo preco (digite -1 para manter " + old.getPrecoHora() + "): ");
                    double np = lerDouble();
                    String nomeFinal = nn.isBlank() ? old.getNome() : nn;
                    double precoFinal = np < 0 ? old.getPrecoHora() : np;
                    Console novo = new Console(old.getId(), nomeFinal, precoFinal);
                    consoles.set(idx - 1, novo);
                    System.out.println("Atualizado.");
                }
                case 4 -> {
                    if (consoles.isEmpty()) { System.out.println("Nenhum console."); break; }
                    for (int i = 0; i < consoles.size(); i++) {
                        System.out.printf("%d) %s%n", i + 1, consoles.get(i).getNome());
                    }
                    System.out.print("Escolha console para remover: ");
                    int idx = lerInt();
                    if (idx >= 1 && idx <= consoles.size()) consoles.remove(idx - 1);
                }
                case 5 -> {
                    if (acessorios.isEmpty()) System.out.println("Nenhum acessorio.");
                    else acessorios.forEach(a -> System.out.println(a));
                }
                case 6 -> {
                    int id = gerarId();
                    System.out.print("Nome do acessorio: ");
                    String nome = lerLinha();
                    Acessorio a = new Acessorio(id, nome);
                    acessorios.add(a);
                    System.out.println("Acessorio cadastrado: " + a);
                }
                case 7 -> {
                    if (acessorios.isEmpty()) { System.out.println("Nenhum acessorio."); break; }
                    for (int i = 0; i < acessorios.size(); i++) {
                        System.out.printf("%d) %s%n", i + 1, acessorios.get(i).getNome());
                    }
                    System.out.print("Escolha acessorio para remover: ");
                    int idx = lerInt();
                    if (idx >= 1 && idx <= acessorios.size()) acessorios.remove(idx - 1);
                }
                case 0 -> { return; }
                default -> System.out.println("Invalido.");
            }
        }
    }

    private static void listarTodasLocacoes() {
        System.out.println("\n--- Locacoes de Jogos ---");
        if (locacoesJogo.isEmpty()) System.out.println("Nenhuma locacao de jogo.");
        else locacoesJogo.forEach(l -> System.out.println(l));
        System.out.println("\n--- Locacoes de Consoles ---");
        if (locacoesConsole.isEmpty()) System.out.println("Nenhuma locacao de console.");
        else locacoesConsole.forEach(l -> System.out.println(l));
    }

    private static void verDisponibilidade() {
        System.out.println("\n--- Disponibilidade de Jogos (estoque atual) ---");
        if (jogosPlataforma.isEmpty()) System.out.println("Nenhum jogo.");
        else jogosPlataforma.forEach(jp -> System.out.printf("%s (%s) - Estoque: %d%n",
                jp.getJogo().getTitulo(), jp.getPlataforma().getNome(), jp.getEstoque()));

        System.out.println("\n--- Consoles em uso (locacoes atuais) ---");
        if (locacoesConsole.isEmpty()) System.out.println("Nenhum console em uso.");
        else locacoesConsole.forEach(lc -> System.out.println(lc));
    }

    /* ---------- Utilitários ---------- */

    private static int lerInt() {
        while (true) {
            try {
                int valor = entrada.nextInt();
                entrada.nextLine(); // limpa o ENTER deixado no buffer
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("Digite um número válido: ");
                entrada.nextLine(); // limpa entrada inválida
            }
        }
    }

    private static String lerString() {
        String texto = entrada.nextLine();
        while (texto.isBlank()) {
            System.out.print("Entrada vazia, digite novamente: ");
            texto = entrada.nextLine();
        }
        return texto;
    }

    private static double lerDouble() {
        while (true) {
            try {
                String s = entrada.nextLine();
                return Double.parseDouble(s.trim());
            } catch (Exception e) {
                System.out.print("Entrada invalida. Digite um numero: ");
            }
        }
    }

    private static String lerLinha() {
        return entrada.nextLine().trim();
    }

    private static boolean lerBoolean() {
        while (true) {
            String s = entrada.nextLine().trim().toLowerCase();
            if (s.equals("true") || s.equals("t") || s.equals("1")) return true;
            if (s.equals("false") || s.equals("f") || s.equals("0")) return false;
            System.out.print("Entrada invalida. Digite true/false: ");
        }
    }

    private static int gerarId() {
        return random.nextInt(100000) + 1;
    }

    private static int gerarIdUsuario() {
        return gerarId();
    }

    private static int gerarIdLocacao() {
        return gerarId();
    }

    /* ---------- Dados iniciais para testes ---------- */
    private static void seedDadosIniciais() {
        // Usuários
        usuarios.add(new Usuario(1, "Admin", "admin@locadora.com", "0000-0000", "admin", true));
        usuarios.add(new Usuario(2, "Cliente Teste", "cliente@locadora.com", "1111-1111", "cliente", false));

        // Plataformas
        Plataforma p1 = new Plataforma(1, "PS5", "PlayStation 5");
        Plataforma p2 = new Plataforma(2, "Xbox Series X", "Xbox");
        plataformas.add(p1);
        plataformas.add(p2);

        // Jogos
        Jogo j1 = new Jogo(1, "God of War", "Ação");
        Jogo j2 = new Jogo(2, "Forza Horizon", "Corrida");
        jogosPlataforma.add(new JogoPlataforma(j1, p1, 3, 12.0));
        jogosPlataforma.add(new JogoPlataforma(j2, p2, 2, 10.0));

        // Consoles
        consoles.add(new Console(1, "PlayStation 5 - Unidade 1", 15.0));
        consoles.add(new Console(2, "Xbox Series X - Unidade 1", 14.0));

        // Acessórios
        acessorios.add(new Acessorio(1, "Controle Extra"));
        acessorios.add(new Acessorio(2, "Fone"));
    }
}
