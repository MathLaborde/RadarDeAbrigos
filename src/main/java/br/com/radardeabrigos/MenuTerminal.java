package br.com.radardeabrigos;

import br.com.radardeabrigos.service.*;
import br.com.radardeabrigos.model.*;
import br.com.radardeabrigos.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MenuTerminal implements CommandLineRunner {

    @Autowired
    private TriagemService triagemService;

    @Autowired
    private AbrigoService abrigoService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private RelatorioService relatorioService;

    @Autowired
    private NotificacaoService notificacaoService;

    private Scanner scanner = new Scanner(System.in);
    private String breadcrumb = "";

    // Códigos ANSI para cores
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BOLD = "\u001B[1m";

    @Override
    public void run(String... args) throws Exception {
        mostrarBoasVindas();
        menuPrincipal();
    }

    private void mostrarBoasVindas() {
        limparTela();
        System.out.println(CYAN + BOLD + "========================================");
        System.out.println("🏠 SISTEMA RADAR DE ABRIGOS v1.0");
        System.out.println("========================================" + RESET);
        System.out.println(YELLOW + "Sistema iniciado com sucesso!" + RESET);
        System.out.println();
        pausar();
    }

    private void menuPrincipal() {
        while (true) {
            limparTela();
            breadcrumb = "Menu Principal";
            mostrarCabecalho();

            System.out.println(WHITE + BOLD + "MENU PRINCIPAL:" + RESET);
            System.out.println("1. 👥 " + BLUE + "Gestão de Pessoas" + RESET);
            System.out.println("2. 🏢 " + GREEN + "Gestão de Abrigos" + RESET);
            System.out.println("3. 🔄 " + PURPLE + "Sistema de Triagem" + RESET);
            System.out.println("4. 📊 " + CYAN + "Relatórios" + RESET);
            System.out.println("5. ⚙️  " + YELLOW + "Utilitários" + RESET);
            System.out.println("0. ❌ " + RED + "Sair" + RESET);
            System.out.println();

            int opcao = lerOpcao("Digite sua opção: ");

            switch (opcao) {
                case 1 -> menuGestaoPessoas();
                case 2 -> menuGestaoAbrigos();
                case 3 -> menuTriagem();
                case 4 -> menuRelatorios();
                case 5 -> menuUtilitarios();
                case 0 -> {
                    mostrarMensagem("👋 Obrigado por usar o Sistema Radar de Abrigos!", GREEN);
                    System.exit(0);
                }
                default -> mostrarMensagem("❌ Opção inválida! Tente novamente.", RED);
            }
        }
    }

    private void menuGestaoPessoas() {
        while (true) {
            limparTela();
            breadcrumb = "Menu Principal > Gestão de Pessoas";
            mostrarCabecalho();

            System.out.println(BLUE + BOLD + "👥 GESTÃO DE PESSOAS" + RESET);
            System.out.println("1. ➕ Cadastrar nova pessoa");
            System.out.println("2. 📋 Listar pessoas por status");
            System.out.println("3. 🔍 Buscar pessoa por ID");
            System.out.println("4. 🚪 Marcar saída de pessoa");
            System.out.println("5. ♿ Listar pessoas com deficiência");
            System.out.println("6. 👁️  Ver detalhes de pessoa");
            System.out.println("0. ⬅️  Voltar ao menu principal");
            System.out.println();

            int opcao = lerOpcao("Digite sua opção: ");

            switch (opcao) {
                case 1 -> cadastrarPessoa();
                case 2 -> listarPessoasPorStatus();
                case 3 -> buscarPessoaPorId();
                case 4 -> marcarSaidaPessoa();
                case 5 -> listarPessoasComDeficiencia();
                case 6 -> verDetalhesPessoa();
                case 0 -> { return; }
                default -> mostrarMensagem("❌ Opção inválida!", RED);
            }
        }
    }

    private void menuGestaoAbrigos() {
        while (true) {
            limparTela();
            breadcrumb = "Menu Principal > Gestão de Abrigos";
            mostrarCabecalho();

            System.out.println(GREEN + BOLD + "🏢 GESTÃO DE ABRIGOS" + RESET);
            System.out.println("1. ➕ Cadastrar novo abrigo");
            System.out.println("2. 📋 Listar todos os abrigos");
            System.out.println("3. 🌍 Buscar abrigos por cidade/bairro");
            System.out.println("4. 🆓 Listar abrigos com vagas");
            System.out.println("5. ♿ Listar abrigos acessíveis");
            System.out.println("6. ✏️  Atualizar informações do abrigo");
            System.out.println("7. 👁️  Ver detalhes do abrigo");
            System.out.println("0. ⬅️  Voltar ao menu principal");
            System.out.println();

            int opcao = lerOpcao("Digite sua opção: ");

            switch (opcao) {
                case 1 -> cadastrarAbrigo();
                case 2 -> listarTodosAbrigos();
                case 3 -> buscarAbrigosPorLocalizacao();
                case 4 -> listarAbrigosComVagas();
                case 5 -> listarAbrigosAcessiveis();
                case 6 -> atualizarAbrigo();
                case 7 -> verDetalhesAbrigo();
                case 0 -> { return; }
                default -> mostrarMensagem("❌ Opção inválida!", RED);
            }
        }
    }

    private void menuTriagem() {
        while (true) {
            limparTela();
            breadcrumb = "Menu Principal > Sistema de Triagem";
            mostrarCabecalho();

            System.out.println(PURPLE + BOLD + "🔄 SISTEMA DE TRIAGEM" + RESET);
            System.out.println("1. 📊 Ver fila de prioridade");
            System.out.println("2. 👤 Alocar pessoa manualmente");
            System.out.println("3. 🤖 Executar triagem automática");
            System.out.println("4. 🔍 Buscar abrigos para pessoa");
            System.out.println("5. 📈 Status da triagem");
            System.out.println("0. ⬅️  Voltar ao menu principal");
            System.out.println();

            int opcao = lerOpcao("Digite sua opção: ");

            switch (opcao) {
                case 1 -> verFilaPrioridade();
                case 2 -> alocarPessoaManualmente();
                case 3 -> executarTriagemAutomatica();
                case 4 -> buscarAbrigosParaPessoa();
                case 5 -> statusTriagem();
                case 0 -> { return; }
                default -> mostrarMensagem("❌ Opção inválida!", RED);
            }
        }
    }

    private void menuRelatorios() {
        while (true) {
            limparTela();
            breadcrumb = "Menu Principal > Relatórios";
            mostrarCabecalho();

            System.out.println(CYAN + BOLD + "📊 RELATÓRIOS" + RESET);
            System.out.println("1. 📈 Relatório geral");
            System.out.println("2. 📊 Contadores por status");
            System.out.println("3. 🏢 Ocupação de abrigos");
            System.out.println("4. 📋 Relatório de pessoas");
            System.out.println("0. ⬅️  Voltar ao menu principal");
            System.out.println();

            int opcao = lerOpcao("Digite sua opção: ");

            switch (opcao) {
                case 1 -> relatorioGeral();
                case 2 -> contadoresPorStatus();
                case 3 -> ocupacaoAbrigos();
                case 4 -> relatorioPessoas();
                case 0 -> { return; }
                default -> mostrarMensagem("❌ Opção inválida!", RED);
            }
        }
    }

    private void menuUtilitarios() {
        while (true) {
            limparTela();
            breadcrumb = "Menu Principal > Utilitários";
            mostrarCabecalho();

            System.out.println(YELLOW + BOLD + "⚙️ UTILITÁRIOS" + RESET);
            System.out.println("1. 🧹 Limpar tela");
            System.out.println("2. 📊 Status do sistema");
            System.out.println("3. 🔔 Ver notificações");
            System.out.println("4. 🆘 Ajuda");
            System.out.println("0. ⬅️  Voltar ao menu principal");
            System.out.println();

            int opcao = lerOpcao("Digite sua opção: ");

            switch (opcao) {
                case 1 -> {
                    limparTela();
                    mostrarMensagem("✅ Tela limpa!", GREEN);
                }
                case 2 -> statusSistema();
                case 3 -> verNotificacoes();
                case 4 -> mostrarAjuda();
                case 0 -> { return; }
                default -> mostrarMensagem("❌ Opção inválida!", RED);
            }
        }
    }

    // Métodos de Gestão de Pessoas
    private void cadastrarPessoa() {
        try {
            mostrarSubtitulo("➕ CADASTRAR NOVA PESSOA");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

            System.out.print("Possui deficiência? (s/n): ");
            boolean possuiDeficiencia = scanner.nextLine().toLowerCase().startsWith("s");

            // Criar objeto Pessoa e salvar
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome);
            pessoa.setCpf(cpf);
            pessoa.setTelefone(telefone);
            pessoa.setIdade(idade);
            pessoa.setPossuiDeficiencia(possuiDeficiencia);
            pessoa.setStatus(StatusPessoa.AGUARDANDO_ABRIGO);

            pessoa = pessoaService.cadastrarPessoa(pessoa);
            mostrarMensagem("✅ Pessoa cadastrada com sucesso! ID: " + pessoa.getId(), GREEN);

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao cadastrar pessoa: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void listarPessoasPorStatus() {
        try {
            mostrarSubtitulo("📋 LISTAR PESSOAS POR STATUS");

            System.out.println("Selecione o status:");
            System.out.println("1. Aguardando abrigo");
            System.out.println("2. Abrigada");
            System.out.println("3. Saiu do abrigo");
            System.out.println("4. Transferida");

            int opcao = lerOpcao("Status: ");
            StatusPessoa status = switch (opcao) {
                case 1 -> StatusPessoa.AGUARDANDO_ABRIGO;
                case 2 -> StatusPessoa.ABRIGADA;
                case 3 -> StatusPessoa.SAIU_DO_ABRIGO;
                case 4 -> StatusPessoa.TRANSFERIDA;
                default -> null;
            };

            if (status == null) {
                mostrarMensagem("❌ Status inválido!", RED);
                return;
            }

            List<Pessoa> pessoas = pessoaService.buscarPorStatus(status);

            if (pessoas.isEmpty()) {
                mostrarMensagem("ℹ️ Nenhuma pessoa encontrada com o status selecionado.", YELLOW);
            } else {
                System.out.println("\n" + GREEN + "📋 PESSOAS COM STATUS: " + status + RESET);
                System.out.println("─".repeat(80));
                for (int i = 0; i < pessoas.size(); i++) {
                    Pessoa p = pessoas.get(i);
                    System.out.printf("%d. %s (ID: %d) - %d anos - %s%n",
                            i + 1, p.getNome(), p.getId(), p.getIdade(),
                            p.isPossuiDeficiencia() ? "♿ PcD" : "");
                }
            }

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao listar pessoas: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void buscarPessoaPorId() {
        try {
            mostrarSubtitulo("🔍 BUSCAR PESSOA POR ID");

            Long id = lerLong("Digite o ID da pessoa: ");

            Optional<Pessoa> pessoaOptional = pessoaService.buscarPorId(id);

            if (pessoaOptional.isEmpty()) {
                mostrarMensagem("❌ Pessoa não encontrada!", RED);
                return;

            }

            Pessoa pessoa = pessoaOptional.get();

            mostrarDetalhesPessoa(pessoa);

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao buscar pessoa: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void marcarSaidaPessoa() {
        try {
            mostrarSubtitulo("🚪 MARCAR SAÍDA DE PESSOA");

            Long id = lerLong("Digite o ID da pessoa: ");

            Optional<Pessoa> pessoaOptional = pessoaService.buscarPorId(id);

            if (pessoaOptional.isEmpty()) {
                mostrarMensagem("❌ Pessoa não encontrada!", RED);
                return;
            }

            Pessoa pessoa = pessoaOptional.get();

            if (pessoa.getStatus() != StatusPessoa.ABRIGADA) {
                mostrarMensagem("❌ Pessoa não está abrigada!", RED);
                return;
            }

            System.out.println("Confirmar saída de: " + pessoa.getNome() + " (s/n)? ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.toLowerCase().startsWith("s")) {
                pessoaService.marcarSaidaDoAbrigo(id);
                mostrarMensagem("✅ Saída registrada com sucesso!", GREEN);
            } else {
                mostrarMensagem("ℹ️ Operação cancelada.", YELLOW);
            }

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao marcar saída: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void listarPessoasComDeficiencia() {
        try {
            mostrarSubtitulo("♿ PESSOAS COM DEFICIÊNCIA");

            List<Pessoa> pessoas = pessoaService.buscarPessoasComDeficiencia();

            if (pessoas.isEmpty()) {
                mostrarMensagem("ℹ️ Nenhuma pessoa com deficiência cadastrada.", YELLOW);
            } else {
                System.out.println("\n" + PURPLE + "♿ PESSOAS COM DEFICIÊNCIA" + RESET);
                System.out.println("─".repeat(80));
                for (int i = 0; i < pessoas.size(); i++) {
                    Pessoa p = pessoas.get(i);
                    System.out.printf("%d. %s (ID: %d) - %d anos - Status: %s%n",
                            i + 1, p.getNome(), p.getId(), p.getIdade(), p.getStatus());
                }
            }

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao listar pessoas: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void verDetalhesPessoa() {
        try {
            mostrarSubtitulo("👁️ DETALHES DA PESSOA");

            Long id = lerLong("Digite o ID da pessoa: ");

            Optional<Pessoa> pessoaOptional = pessoaService.buscarPorId(id);

            if (pessoaOptional.isEmpty()) {
                mostrarMensagem("❌ Pessoa não encontrada!", RED);
                return;
            }

            Pessoa pessoa = pessoaOptional.get();

            mostrarDetalhesPessoa(pessoa);


        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao buscar pessoa: " + e.getMessage(), RED);
        }
        pausar();
    }

    // Métodos de Gestão de Abrigos
    private void cadastrarAbrigo() {
        try {
            mostrarSubtitulo("➕ CADASTRAR NOVO ABRIGO");

            System.out.print("Nome do abrigo: ");
            String nome = scanner.nextLine();

            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();

            System.out.print("Cidade: ");
            String cidade = scanner.nextLine();

            System.out.print("Bairro: ");
            String bairro = scanner.nextLine();

            System.out.print("Capacidade total: ");
            int capacidade = scanner.nextInt();
            scanner.nextLine();

            System.out.print("É acessível? (s/n): ");
            boolean acessivel = scanner.nextLine().toLowerCase().startsWith("s");

            System.out.println("Tipo do abrigo:");
            System.out.println("1. Público");
            System.out.println("2. Voluntário");
            System.out.println("3. ONG");
            System.out.println("4. Igreja");
            System.out.println("5. Escola");
            System.out.println("6. Ginásio");

            int tipoOpcao = lerOpcao("Tipo: ");
            TipoAbrigo tipo = switch (tipoOpcao) {
                case 1 -> TipoAbrigo.PUBLICO;
                case 2 -> TipoAbrigo.VOLUNTARIO;
                case 3 -> TipoAbrigo.ONG;
                case 4 -> TipoAbrigo.IGREJA;
                case 5 -> TipoAbrigo.ESCOLA;
                case 6 -> TipoAbrigo.GINASIO;
                default -> TipoAbrigo.PUBLICO;
            };

            Abrigo abrigo = new Abrigo();
            abrigo.setNome(nome);
            abrigo.setEndereco(endereco);
            abrigo.setCidade(cidade);
            abrigo.setBairro(bairro);
            abrigo.setCapacidadeTotal(capacidade);
            abrigo.setAcessivel(acessivel);
            abrigo.setTipo(tipo);
            abrigo.setStatus(StatusAbrigo.ATIVO);

            abrigo = abrigoService.cadastrarAbrigo(abrigo);
            mostrarMensagem("✅ Abrigo cadastrado com sucesso! ID: " + abrigo.getId(), GREEN);

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao cadastrar abrigo: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void listarTodosAbrigos() {
        try {
            mostrarSubtitulo("📋 TODOS OS ABRIGOS");

            List<Abrigo> abrigos = abrigoService.listarTodos();

            if (abrigos.isEmpty()) {
                mostrarMensagem("ℹ️ Nenhum abrigo cadastrado.", YELLOW);
            } else {
                System.out.println("\n" + GREEN + "🏢 ABRIGOS CADASTRADOS" + RESET);
                System.out.println("─".repeat(100));
                for (int i = 0; i < abrigos.size(); i++) {
                    Abrigo a = abrigos.get(i);
                    System.out.printf("%d. %s - %s/%s - Cap: %d - %s %s%n",
                            i + 1, a.getNome(), a.getCidade(), a.getBairro(),
                            a.getCapacidadeTotal(), a.getStatus(),
                            a.isAcessivel() ? "♿" : "");
                }
            }

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao listar abrigos: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void buscarAbrigosPorLocalizacao() {
        try {
            mostrarSubtitulo("🌍 BUSCAR ABRIGOS POR LOCALIZAÇÃO");

            System.out.println("Buscar por:");
            System.out.println("1. Cidade");
            System.out.println("2. Bairro");

            int opcao = lerOpcao("Opção: ");

            List<Abrigo> abrigos;

            if (opcao == 1) {
                System.out.print("Digite a cidade: ");
                String cidade = scanner.nextLine();
                abrigos = abrigoService.buscarPorCidade(cidade);
            } else if (opcao == 2) {
                System.out.print("Digite o bairro: ");
                String bairro = scanner.nextLine();
                abrigos = abrigoService.buscarPorBairro(bairro);
            } else {
                mostrarMensagem("❌ Opção inválida!", RED);
                return;
            }

            if (abrigos.isEmpty()) {
                mostrarMensagem("ℹ️ Nenhum abrigo encontrado.", YELLOW);
            } else {
                System.out.println("\n" + GREEN + "🏢 ABRIGOS ENCONTRADOS" + RESET);
                System.out.println("─".repeat(80));
                for (int i = 0; i < abrigos.size(); i++) {
                    Abrigo a = abrigos.get(i);
                    System.out.printf("%d. %s - %s - Cap: %d %s%n",
                            i + 1, a.getNome(), a.getEndereco(),
                            a.getCapacidadeTotal(), a.isAcessivel() ? "♿" : "");
                }
            }

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao buscar abrigos: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void listarAbrigosComVagas() {
        try {
            mostrarSubtitulo("🆓 ABRIGOS COM VAGAS DISPONÍVEIS");

            List<Abrigo> abrigos = abrigoService.buscarComVagas();

            if (abrigos.isEmpty()) {
                mostrarMensagem("ℹ️ Nenhum abrigo com vagas disponíveis.", YELLOW);
            } else {
                System.out.println("\n" + GREEN + "🆓 ABRIGOS COM VAGAS" + RESET);
                System.out.println("─".repeat(80));
                for (int i = 0; i < abrigos.size(); i++) {
                    Abrigo a = abrigos.get(i);
                    int vagasDisponiveis = a.getVagasDisponiveis();
                    System.out.printf("%d. %s - Vagas: %d/%d %s%n",
                            i + 1, a.getNome(), vagasDisponiveis, a.getCapacidadeTotal(),
                            a.isAcessivel() ? "♿" : "");
                }
            }

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao listar abrigos: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void listarAbrigosAcessiveis() {
        try {
            mostrarSubtitulo("♿ ABRIGOS ACESSÍVEIS");

            List<Abrigo> abrigos = abrigoService.buscarAcessiveis();

            if (abrigos.isEmpty()) {
                mostrarMensagem("ℹ️ Nenhum abrigo acessível cadastrado.", YELLOW);
            } else {
                System.out.println("\n" + PURPLE + "♿ ABRIGOS ACESSÍVEIS" + RESET);
                System.out.println("─".repeat(80));
                for (int i = 0; i < abrigos.size(); i++) {
                    Abrigo a = abrigos.get(i);
                    System.out.printf("%d. %s - %s/%s - Cap: %d%n",
                            i + 1, a.getNome(), a.getCidade(), a.getBairro(),
                            a.getCapacidadeTotal());
                }
            }

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao listar abrigos: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void atualizarAbrigo() {
        try {
            mostrarSubtitulo("✏️ ATUALIZAR ABRIGO");

            Long id = lerLong("Digite o ID do abrigo: ");

            Optional<Abrigo> abrigoOptional = abrigoService.buscarPorId(id);

            if (abrigoOptional.isEmpty()) {
                mostrarMensagem("❌ Abrigo não encontrado!", RED);
                return;
            }

            Abrigo abrigo = abrigoOptional.get();

            System.out.println("Abrigo atual: " + abrigo.getNome());
            System.out.println("Deixe em branco para manter o valor atual.");

            System.out.print("Novo nome [" + abrigo.getNome() + "]: ");
            String nome = scanner.nextLine();
            if (!nome.trim().isEmpty()) {
                abrigo.setNome(nome);
            }

            System.out.print("Nova capacidade [" + abrigo.getCapacidadeTotal() + "]: ");
            String capacidadeStr = scanner.nextLine();
            if (!capacidadeStr.trim().isEmpty()) {
                abrigo.setCapacidadeTotal(Integer.parseInt(capacidadeStr));
            }

            abrigoService.atualizarAbrigo(abrigo);
            mostrarMensagem("✅ Abrigo atualizado com sucesso!", GREEN);

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao atualizar abrigo: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void verDetalhesAbrigo() {
        try {
            mostrarSubtitulo("👁️ DETALHES DO ABRIGO");

            Long id = lerLong("Digite o ID do abrigo: ");

            Optional<Abrigo> abrigoOptional = abrigoService.buscarPorId(id);

            if (abrigoOptional.isEmpty()) {
                mostrarMensagem("❌ Abrigo não encontrado!", RED);
                return;
            }

            Abrigo abrigo = abrigoOptional.get();

            mostrarDetalhesAbrigo(abrigo);

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao buscar abrigo: " + e.getMessage(), RED);
        }
        pausar();
    }

    // Métodos de Triagem
    private void verFilaPrioridade() {
        try {
            mostrarSubtitulo("📊 FILA DE PRIORIDADE");

            List<Pessoa> fila = triagemService.obterFilaPrioridade();

            if (fila.isEmpty()) {
                mostrarMensagem("ℹ️ Nenhuma pessoa na fila de prioridade.", YELLOW);
            } else {
                System.out.println("\n" + PURPLE + "📊 FILA DE PRIORIDADE" + RESET);
                System.out.println("─".repeat(80));
                for (int i = 0; i < fila.size(); i++) {
                    Pessoa p = fila.get(i);
                    String prioridade = obterIndicadorPrioridade(p);
                    System.out.printf("%d. %s %s - %d anos%n",
                            i + 1, p.getNome(), prioridade, p.getIdade());
                }
            }

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao obter fila: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void alocarPessoaManualmente() {
        try {
            mostrarSubtitulo("👤 ALOCAÇÃO MANUAL");

            Long pessoaId = lerLong("Digite o ID da pessoa: ");
            Long abrigoId = lerLong("Digite o ID do abrigo: ");

            boolean sucesso = triagemService.alocarPessoaEmAbrigo(pessoaId, abrigoId);

            if (sucesso) {
                mostrarMensagem("✅ Pessoa alocada com sucesso!", GREEN);
            } else {
                mostrarMensagem("❌ Não foi possível alocar a pessoa.", RED);
            }

        } catch (Exception e) {
            mostrarMensagem("❌ Erro na alocação: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void executarTriagemAutomatica() {
        try {
            mostrarSubtitulo("🤖 TRIAGEM AUTOMÁTICA");

            System.out.print("Confirma execução da triagem automática? (s/n): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.toLowerCase().startsWith("s")) {
                mostrarMensagem("⏳ Executando triagem...", YELLOW);

                triagemService.processarTriagemAutomatica();

                mostrarMensagem("✅ Triagem concluída! Pessoas alocadas.", GREEN);
            } else {
                mostrarMensagem("ℹ️ Triagem cancelada.", YELLOW);
            }

        } catch (Exception e) {
            mostrarMensagem("❌ Erro na triagem: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void buscarAbrigosParaPessoa() {
        try {
            mostrarSubtitulo("🔍 BUSCAR ABRIGOS PARA PESSOA");

            Long pessoaId = lerLong("Digite o ID da pessoa: ");

            Optional<Pessoa> pessoaOptional = pessoaService.buscarPorId(pessoaId);

            if (pessoaOptional.isEmpty()) {
                mostrarMensagem("❌ Pessoa não encontrada!", RED);
                return;
            }

            Pessoa pessoa = pessoaOptional.get();

            List<Abrigo> abrigos = triagemService.buscarAbrigosApropriados(pessoa);

            if (abrigos.isEmpty()) {
                mostrarMensagem("ℹ️ Nenhum abrigo apropriado encontrado.", YELLOW);
            } else {
                System.out.println("\n" + GREEN + "🏢 ABRIGOS APROPRIADOS" + RESET);
                System.out.println("─".repeat(80));
                for (int i = 0; i < abrigos.size(); i++) {
                    Abrigo a = abrigos.get(i);

                    List<Pessoa> pessoas = pessoaService.buscarPorAbrigo(a);

                    a.setPessoasAbrigadas(pessoas);

                    int vagas = a.getVagasDisponiveis();
                    System.out.printf("%d. %s - Vagas: %d %s%n",
                            i + 1, a.getNome(), vagas, a.isAcessivel() ? "♿" : "");
                }
            }

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao buscar abrigos: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void statusTriagem() {
        try {
            mostrarSubtitulo("📈 STATUS DA TRIAGEM");

            // Aqui você implementaria a lógica para obter estatísticas da triagem
            System.out.println(CYAN + "📈 ESTATÍSTICAS DA TRIAGEM" + RESET);
            System.out.println("─".repeat(50));
            System.out.println("• Pessoas na fila: " + pessoaService.contarPorStatus(StatusPessoa.AGUARDANDO_ABRIGO));
            System.out.println("• Pessoas abrigadas: " + pessoaService.contarPorStatus(StatusPessoa.ABRIGADA));
            System.out.println("• Abrigos com vagas: " + abrigoService.buscarComVagas().size());
            System.out.printf("• Taxa de ocupação: %3.1f%%%n", calcularTaxaOcupacao());

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao obter status: " + e.getMessage(), RED);
        }
        pausar();
    }

    // Métodos de Relatórios
    private void relatorioGeral() {
        try {

            Map<String, Object> relatorio = relatorioService.gerarRelatorioGeral();

            mostrarSubtitulo("📈 RELATÓRIO GERAL");

            System.out.println(CYAN + BOLD + "📊 RELATÓRIO GERAL DO SISTEMA" + RESET);
            System.out.println("═".repeat(60));

            // Estatísticas de pessoas
            System.out.println("\n" + BLUE + "👥 PESSOAS:" + RESET);
            System.out.println("• Total cadastradas: " + relatorio.get("totalPessoas"));
            System.out.println("• Aguardando abrigo: " + relatorio.get("pessoasAguardando"));
            System.out.println("• Abrigadas: " + relatorio.get("pessoasAbrigadas"));
            System.out.println("• Com deficiência: " + relatorio.get("pessoasComDeficiencia"));

            // Estatísticas de abrigos
            System.out.println("\n" + GREEN + "🏢 ABRIGOS:" + RESET);
            System.out.println("• Total cadastrados: " + relatorio.get("totalAbrigos"));
            System.out.println("• Ativos: " + relatorio.get("abrigosAtivos"));
            System.out.println("• Vagas Disponiveis: " + relatorio.get("vagasDisponiveis"));
            System.out.println("• Acessíveis disponiveis: " + relatorio.get("abrigosAcessiveis"));

            // Capacidade total
            System.out.println("\n" + PURPLE + "📊 CAPACIDADE:" + RESET);
            System.out.println("• Capacidade total: " + relatorio.get("capacidadeTotal"));
            System.out.println("• Ocupação atual: " + relatorio.get("vagasOcupadas"));
            System.out.printf("• Taxa de ocupação: %3.1f%%%n", calcularTaxaOcupacao());

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao gerar relatório: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void contadoresPorStatus() {
        try {
            mostrarSubtitulo("📊 CONTADORES POR STATUS");

            System.out.println(YELLOW + BOLD + "📊 CONTADORES POR STATUS" + RESET);
            System.out.println("═".repeat(50));

            System.out.println("\n" + BLUE + "👥 STATUS DAS PESSOAS:" + RESET);
            for (StatusPessoa status : StatusPessoa.values()) {
                long count = pessoaService.contarPorStatus(status);
                System.out.printf("• %-20s: %d%n", status.toString(), count);
            }

            System.out.println("\n" + GREEN + "🏢 STATUS DOS ABRIGOS:" + RESET);
            for (StatusAbrigo status : StatusAbrigo.values()) {
                long count = abrigoService.ContarPorStatus(status);
                System.out.printf("• %-20s: %d%n", status.toString(), count);
            }

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao gerar contadores: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void ocupacaoAbrigos() {
        try {
            mostrarSubtitulo("🏢 OCUPAÇÃO DOS ABRIGOS");

            List<Abrigo> abrigos = abrigoService.listarTodos();

            if (abrigos.isEmpty()) {
                mostrarMensagem("ℹ️ Nenhum abrigo cadastrado.", YELLOW);
            } else {
                System.out.println(GREEN + BOLD + "🏢 OCUPAÇÃO DOS ABRIGOS" + RESET);
                System.out.println("═".repeat(80));
                System.out.printf("%-30s %-15s %-10s %-10s%n", "NOME", "CIDADE", "OCUPAÇÃO", "TAXA");
                System.out.println("─".repeat(80));

                for (Abrigo a : abrigos) {
                    List<Pessoa> pessoas = pessoaService.buscarPorAbrigo(a);

                    a.setPessoasAbrigadas(pessoas);

                    int ocupacao = a.getPessoasAbrigadas().size();
                    int capacidade = a.getCapacidadeTotal();
                    double taxa = capacidade > 0 ? (ocupacao * 100.0 / capacidade) : 0;

                    String status = taxa >= 100 ? "🔴" : taxa >= 80 ? "🟡" : "🟢";

                    System.out.printf("%-30s %-15s %s %d/%d %8.1f%%%n",
                            a.getNome(), a.getCidade(), status, ocupacao, capacidade, taxa);
                }
            }

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao gerar relatório: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void relatorioPessoas() {
        try {
            mostrarSubtitulo("📋 RELATÓRIO DE PESSOAS");

            System.out.println("Relatório por:");
            System.out.println("1. Deficiência");
            System.out.println("2. Status");

            int opcao = lerOpcao("Opção: ");

            switch (opcao) {
                case 1 -> relatorioPorDeficiencia();
                case 2 -> relatorioPorStatusPessoa();
                default -> mostrarMensagem("❌ Opção inválida!", RED);
            }

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao gerar relatório: " + e.getMessage(), RED);
        }
        pausar();
    }

    // Métodos de Utilitários
    private void statusSistema() {
        try {
            mostrarSubtitulo("📊 STATUS DO SISTEMA");

            System.out.println(CYAN + BOLD + "📊 STATUS DO SISTEMA" + RESET);
            System.out.println("═".repeat(50));

            System.out.println("\n" + GREEN + "✅ SERVIÇOS ATIVOS:" + RESET);
            System.out.println("• Serviço de Pessoas: OK");
            System.out.println("• Serviço de Abrigos: OK");
            System.out.println("• Serviço de Triagem: OK");
            System.out.println("• Serviço de Relatórios: OK");
            System.out.println("• Serviço de Notificações: OK");

            System.out.println("\n" + BLUE + "📈 ESTATÍSTICAS RÁPIDAS:" + RESET);
            System.out.println("• Uptime: Sistema iniciado");

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao obter status: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void verNotificacoes() {
        try {
            mostrarSubtitulo("🔔 NOTIFICAÇÕES");

            // Aqui você implementaria a lógica para obter notificações
            System.out.println(YELLOW + BOLD + "🔔 NOTIFICAÇÕES DO SISTEMA" + RESET);
            System.out.println("═".repeat(50));

            // Exemplo de notificações
            System.out.println("• " + YELLOW + "⚠️ Abrigo E. E. João Paulo II próximo da capacidade máxima" + RESET);
            System.out.println("• " + GREEN + "✅ 2 pessoas foram alocadas na última triagem" + RESET);
            System.out.println("• " + RED + "❌ Abrigo Casa Dona Joana reportou problema de infraestrutura" + RESET);

        } catch (Exception e) {
            mostrarMensagem("❌ Erro ao obter notificações: " + e.getMessage(), RED);
        }
        pausar();
    }

    private void mostrarAjuda() {
        mostrarSubtitulo("🆘 AJUDA");

        System.out.println(CYAN + BOLD + "🆘 SISTEMA DE AJUDA" + RESET);
        System.out.println("═".repeat(60));

        System.out.println("\n" + BLUE + "👥 GESTÃO DE PESSOAS:" + RESET);
        System.out.println("• Cadastre pessoas que precisam de abrigo");
        System.out.println("• Monitore o status de cada pessoa");
        System.out.println("• Gerencie saídas e transferências");

        System.out.println("\n" + GREEN + "🏢 GESTÃO DE ABRIGOS:" + RESET);
        System.out.println("• Cadastre locais disponíveis para abrigo");
        System.out.println("• Monitore capacidade e acessibilidade");
        System.out.println("• Atualize informações conforme necessário");

        System.out.println("\n" + PURPLE + "🔄 SISTEMA DE TRIAGEM:" + RESET);
        System.out.println("• Execute triagem automática baseada em prioridades");
        System.out.println("• Aloque pessoas manualmente quando necessário");
        System.out.println("• Monitore fila de espera e disponibilidade");

        System.out.println("\n" + YELLOW + "💡 DICAS:" + RESET);
        System.out.println("• Use 0 para voltar ao menu anterior");
        System.out.println("• Pessoas com deficiência têm prioridade na triagem");
        System.out.println("• Verifique relatórios regularmente para acompanhar o sistema");

        pausar();
    }

    // Métodos auxiliares
    private void mostrarCabecalho() {
        System.out.println(CYAN + BOLD + "========================================");
        System.out.println("🏠 SISTEMA RADAR DE ABRIGOS v1.0");
        System.out.println("========================================" + RESET);
        System.out.println(YELLOW + "📍 " + breadcrumb + RESET);
        System.out.println();
    }

    private void mostrarSubtitulo(String titulo) {
        System.out.println("\n" + BOLD + titulo + RESET);
        System.out.println("─".repeat(titulo.length()));
    }

    private void mostrarMensagem(String mensagem, String cor) {
        System.out.println("\n" + cor + mensagem + RESET);
    }

    private void pausar() {
        System.out.println("\n" + YELLOW + "Pressione ENTER para continuar..." + RESET);
        scanner.nextLine();
    }

    private void limparTela() {
            try {
                String os = System.getProperty("os.name").toLowerCase();

                if (os.contains("win")) {
                    // Windows
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    // Linux/Mac/Unix
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                }
            } catch (Exception e) {
                // Fallback: imprime várias linhas em branco
                for (int i = 0; i < 50; i++) {
                    System.out.println();
                }
            }

    }

    private int lerOpcao(String prompt) {
        while (true) {
            try {
                System.out.print(BOLD + prompt + RESET);
                int opcao = scanner.nextInt();
                scanner.nextLine(); // consumir quebra de linha
                return opcao;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // limpar buffer
                mostrarMensagem("❌ Por favor, digite um número válido!", RED);
            }
        }
    }

    private Long lerLong(String prompt) {
        while (true) {
            try {
                System.out.print(BOLD + prompt + RESET);
                Long valor = scanner.nextLong();
                scanner.nextLine(); // consumir quebra de linha
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // limpar buffer
                mostrarMensagem("❌ Por favor, digite um número válido!", RED);
            }
        }
    }

    private void mostrarDetalhesPessoa(Pessoa pessoa) {
        System.out.println("\n" + BLUE + BOLD + "👤 DETALHES DA PESSOA" + RESET);
        System.out.println("═".repeat(50));
        System.out.println("ID: " + pessoa.getId());
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("CPF: " + pessoa.getCpf());
        System.out.println("Telefone: " + pessoa.getTelefone());
        System.out.println("Idade: " + pessoa.getIdade() + " anos");
        System.out.println("Deficiência: " + (pessoa.isPossuiDeficiencia() ? "Sim ♿" : "Não"));
        System.out.println("Status: " + pessoa.getStatus());

        if (pessoa.getAbrigoAtual() != null) {
            System.out.println("Abrigo atual: " + pessoa.getAbrigoAtual().getNome());
        }
    }

    private void mostrarDetalhesAbrigo(Abrigo abrigo) {
        System.out.println("\n" + GREEN + BOLD + "🏢 DETALHES DO ABRIGO" + RESET);
        System.out.println("═".repeat(50));
        System.out.println("ID: " + abrigo.getId());
        System.out.println("Nome: " + abrigo.getNome());
        System.out.println("Endereço: " + abrigo.getEndereco());
        System.out.println("Cidade: " + abrigo.getCidade());
        System.out.println("Bairro: " + abrigo.getBairro());
        System.out.println("Capacidade: " + abrigo.getCapacidadeTotal() + "/" + abrigo.getCapacidadeTotal());
        System.out.println("Acessível: " + (abrigo.isAcessivel() ? "Sim ♿" : "Não"));
        System.out.println("Tipo: " + abrigo.getTipo());
        System.out.println("Status: " + abrigo.getStatus());

        List<Pessoa> pessoas = pessoaService.buscarPorAbrigo(abrigo);

        abrigo.setPessoasAbrigadas(pessoas);

        double taxa = abrigo.getCapacidadeTotal() > 0 ?
                (abrigo.getPessoasAbrigadas().size() * 100.0 / abrigo.getCapacidadeTotal()) : 0;
        System.out.printf("Taxa de ocupação: %.1f%%%n", taxa);
    }

    private String obterIndicadorPrioridade(Pessoa pessoa) {
        if (pessoa.isPossuiDeficiencia()) {
            return "🔴 ALTA";
        } else if (pessoa.getIdade() >= 65) {
            return "🟡 MÉDIA";
        } else {
            return "🟢 NORMAL";
        }
    }

    private double calcularTaxaOcupacao() {
        try {
            Map<String, Object> relatorio = relatorioService.gerarRelatorioGeral();

            long capacidadeTotal = (long) relatorio.get("capacidadeTotal");
            long ocupacaoTotal = (long) relatorio.get("vagasOcupadas");

            return capacidadeTotal > 0 ? (ocupacaoTotal * 100.0 / capacidadeTotal) : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private void relatorioPorDeficiencia() {
        System.out.println("\n" + PURPLE + "♿ RELATÓRIO POR DEFICIÊNCIA" + RESET);
        System.out.println("─".repeat(50));
        System.out.println("• Com deficiência: " + pessoaService.buscarPessoasComDeficiencia().size());
        System.out.println("• Sem deficiência: " + (pessoaService.listarTodas().size() - pessoaService.buscarPessoasComDeficiencia().size()));
    }

    private void relatorioPorStatusPessoa() {
        System.out.println("\n" + PURPLE + "📈 RELATÓRIO POR STATUS" + RESET);
        System.out.println("─".repeat(50));
        for (StatusPessoa status : StatusPessoa.values()) {
            System.out.println("• " + status + ": " + pessoaService.contarPorStatus(status));
        }
    }
}