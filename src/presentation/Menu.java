package presentation;

import application.AlunoServico;
import application.DisciplinaServico;
import application.ProfessorServico;
import domain.Aluno;
import domain.Disciplina;
import domain.Pessoa;
import domain.Professor;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private final AlunoServico alunoServico;
    private final ProfessorServico professorServico;
    private final DisciplinaServico disciplinaServico;
    private final Scanner scanner;

    public Menu(AlunoServico alunoServico, ProfessorServico professorServico, DisciplinaServico disciplinaServico) {
        this.alunoServico = alunoServico;
        this.professorServico = professorServico;
        this.disciplinaServico = disciplinaServico;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        exibirBoasVindas();
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opção");
            switch (opcao) {
                case 1 -> menuAlunos();
                case 2 -> menuProfessores();
                case 3 -> menuDisciplinas();
                case 4 -> exibirResumo();
                case 0 -> System.out.println("\n  Encerrando o sistema. Até logo!\n");
                default -> System.out.println("\n  [!] Opção inválida. Tente novamente.\n");
            }
        } while (opcao != 0);
    }

    // ===== MENUS PRINCIPAIS =====

    private void exibirMenuPrincipal() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║    SISTEMA DE GESTÃO ESCOLAR         ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Gerenciar Alunos                 ║");
        System.out.println("║  2. Gerenciar Professores            ║");
        System.out.println("║  3. Gerenciar Disciplinas            ║");
        System.out.println("║  4. Resumo Geral                     ║");
        System.out.println("║  0. Sair                             ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    private void menuAlunos() {
        int opcao;
        do {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║          GERENCIAR ALUNOS            ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1. Cadastrar Aluno                  ║");
            System.out.println("║  2. Listar Todos os Alunos           ║");
            System.out.println("║  3. Buscar Aluno por ID              ║");
            System.out.println("║  4. Buscar Aluno por Nome            ║");
            System.out.println("║  5. Remover Aluno                    ║");
            System.out.println("║  0. Voltar                           ║");
            System.out.println("╚══════════════════════════════════════╝");
            opcao = lerInteiro("Escolha uma opção");
            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> listarAlunos();
                case 3 -> buscarAlunoPorId();
                case 4 -> buscarAlunoPorNome();
                case 5 -> removerAluno();
                case 0 -> System.out.println("  Voltando ao menu principal...");
                default -> System.out.println("\n  [!] Opção inválida.\n");
            }
        } while (opcao != 0);
    }

    private void menuProfessores() {
        int opcao;
        do {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║        GERENCIAR PROFESSORES         ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1. Cadastrar Professor              ║");
            System.out.println("║  2. Listar Todos os Professores      ║");
            System.out.println("║  3. Buscar Professor por ID          ║");
            System.out.println("║  4. Buscar Professor por Nome        ║");
            System.out.println("║  5. Remover Professor                ║");
            System.out.println("║  0. Voltar                           ║");
            System.out.println("╚══════════════════════════════════════╝");
            opcao = lerInteiro("Escolha uma opção");
            switch (opcao) {
                case 1 -> cadastrarProfessor();
                case 2 -> listarProfessores();
                case 3 -> buscarProfessorPorId();
                case 4 -> buscarProfessorPorNome();
                case 5 -> removerProfessor();
                case 0 -> System.out.println("  Voltando ao menu principal...");
                default -> System.out.println("\n  [!] Opção inválida.\n");
            }
        } while (opcao != 0);
    }

    private void menuDisciplinas() {
        int opcao;
        do {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║        GERENCIAR DISCIPLINAS         ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1. Cadastrar Disciplina             ║");
            System.out.println("║  2. Listar Todas as Disciplinas      ║");
            System.out.println("║  3. Buscar Disciplina por ID         ║");
            System.out.println("║  4. Buscar Disciplina por Nome       ║");
            System.out.println("║  5. Remover Disciplina               ║");
            System.out.println("║  0. Voltar                           ║");
            System.out.println("╚══════════════════════════════════════╝");
            opcao = lerInteiro("Escolha uma opção");
            switch (opcao) {
                case 1 -> cadastrarDisciplina();
                case 2 -> listarDisciplinas();
                case 3 -> buscarDisciplinaPorId();
                case 4 -> buscarDisciplinaPorNome();
                case 5 -> removerDisciplina();
                case 0 -> System.out.println("  Voltando ao menu principal...");
                default -> System.out.println("\n  [!] Opção inválida.\n");
            }
        } while (opcao != 0);
    }

    // ===== OPERAÇÕES DE ALUNO =====

    private void cadastrarAluno() {
        System.out.println("\n--- CADASTRO DE ALUNO ---");
        try {
            String nome = lerTexto("Nome");
            String email = lerTexto("E-mail");
            String telefone = lerTexto("Telefone");
            String matricula = lerTexto("Matrícula");
            String curso = lerTexto("Curso");

            System.out.print("  Informar Coeficiente de Rendimento? (s/n): ");
            String resp = scanner.nextLine().trim();

            Aluno aluno;
            if (resp.equalsIgnoreCase("s")) {
                double cr = lerDecimal("Coeficiente de Rendimento (0.0 a 10.0)");
                aluno = alunoServico.cadastrar(nome, email, telefone, matricula, curso, cr);
            } else {
                aluno = alunoServico.cadastrar(nome, email, telefone, matricula, curso);
            }

            System.out.println("\n  [✓] Aluno cadastrado com sucesso!");
            // Demonstração de polimorfismo: chamada de apresentar() que usa a versão de Aluno
            System.out.println("  " + aluno.apresentar());

        } catch (IllegalArgumentException e) {
            System.out.println("\n  [!] Erro: " + e.getMessage());
        }
    }

    private void listarAlunos() {
        System.out.println("\n--- LISTA DE ALUNOS ---");
        List<Aluno> alunos = alunoServico.listarTodos();
        if (alunos.isEmpty()) {
            System.out.println("  Nenhum aluno cadastrado.");
            return;
        }
        // Demonstração de polimorfismo: uso de Pessoa como tipo base
        for (Pessoa p : alunos) {
            System.out.println("  " + p.apresentar());
        }
        System.out.println("  Total: " + alunos.size() + " aluno(s).");
    }

    private void buscarAlunoPorId() {
        System.out.println("\n--- BUSCAR ALUNO POR ID ---");
        try {
            int id = lerInteiro("ID do aluno");
            alunoServico.buscarPorId(id).ifPresentOrElse(
                    a -> System.out.println("\n  Encontrado: " + a),
                    () -> System.out.println("\n  [!] Aluno não encontrado.")
            );
        } catch (Exception e) {
            System.out.println("\n  [!] Erro: " + e.getMessage());
        }
    }

    private void buscarAlunoPorNome() {
        System.out.println("\n--- BUSCAR ALUNO POR NOME ---");
        try {
            String nome = lerTexto("Nome (parcial)");
            List<Aluno> resultado = alunoServico.buscarPorNome(nome);
            if (resultado.isEmpty()) {
                System.out.println("  Nenhum aluno encontrado para \"" + nome + "\".");
            } else {
                resultado.forEach(a -> System.out.println("  " + a.apresentar()));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n  [!] Erro: " + e.getMessage());
        }
    }

    private void removerAluno() {
        System.out.println("\n--- REMOVER ALUNO ---");
        try {
            int id = lerInteiro("ID do aluno a remover");
            alunoServico.buscarPorId(id).ifPresentOrElse(a -> {
                System.out.println("  Você deseja remover: " + a.getNome() + "?");
                System.out.print("  Confirmar? (s/n): ");
                if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
                    alunoServico.remover(id);
                    System.out.println("  [✓] Aluno removido com sucesso.");
                } else {
                    System.out.println("  Operação cancelada.");
                }
            }, () -> System.out.println("  [!] Aluno não encontrado."));
        } catch (Exception e) {
            System.out.println("\n  [!] Erro: " + e.getMessage());
        }
    }

    // ===== OPERAÇÕES DE PROFESSOR =====

    private void cadastrarProfessor() {
        System.out.println("\n--- CADASTRO DE PROFESSOR ---");
        try {
            String nome = lerTexto("Nome");
            String email = lerTexto("E-mail");
            String telefone = lerTexto("Telefone");
            String registro = lerTexto("Registro");
            String especialidade = lerTexto("Especialidade");

            System.out.print("  Informar Salário? (s/n): ");
            String resp = scanner.nextLine().trim();

            Professor professor;
            if (resp.equalsIgnoreCase("s")) {
                double salario = lerDecimal("Salário (R$)");
                professor = professorServico.cadastrar(nome, email, telefone, registro, especialidade, salario);
            } else {
                professor = professorServico.cadastrar(nome, email, telefone, registro, especialidade);
            }

            System.out.println("\n  [✓] Professor cadastrado com sucesso!");
            // Demonstração de polimorfismo: chamada de apresentar() que usa a versão de Professor
            System.out.println("  " + professor.apresentar());

        } catch (IllegalArgumentException e) {
            System.out.println("\n  [!] Erro: " + e.getMessage());
        }
    }

    private void listarProfessores() {
        System.out.println("\n--- LISTA DE PROFESSORES ---");
        List<Professor> professores = professorServico.listarTodos();
        if (professores.isEmpty()) {
            System.out.println("  Nenhum professor cadastrado.");
            return;
        }
        // Demonstração de polimorfismo: uso de Pessoa como tipo base
        for (Pessoa p : professores) {
            System.out.println("  " + p.apresentar());
        }
        System.out.println("  Total: " + professores.size() + " professor(es).");
    }

    private void buscarProfessorPorId() {
        System.out.println("\n--- BUSCAR PROFESSOR POR ID ---");
        try {
            int id = lerInteiro("ID do professor");
            professorServico.buscarPorId(id).ifPresentOrElse(
                    p -> System.out.println("\n  Encontrado: " + p),
                    () -> System.out.println("\n  [!] Professor não encontrado.")
            );
        } catch (Exception e) {
            System.out.println("\n  [!] Erro: " + e.getMessage());
        }
    }

    private void buscarProfessorPorNome() {
        System.out.println("\n--- BUSCAR PROFESSOR POR NOME ---");
        try {
            String nome = lerTexto("Nome (parcial)");
            List<Professor> resultado = professorServico.buscarPorNome(nome);
            if (resultado.isEmpty()) {
                System.out.println("  Nenhum professor encontrado para \"" + nome + "\".");
            } else {
                resultado.forEach(p -> System.out.println("  " + p.apresentar()));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n  [!] Erro: " + e.getMessage());
        }
    }

    private void removerProfessor() {
        System.out.println("\n--- REMOVER PROFESSOR ---");
        try {
            int id = lerInteiro("ID do professor a remover");
            professorServico.buscarPorId(id).ifPresentOrElse(p -> {
                System.out.println("  Você deseja remover: " + p.getNome() + "?");
                System.out.print("  Confirmar? (s/n): ");
                if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
                    professorServico.remover(id);
                    System.out.println("  [✓] Professor removido com sucesso.");
                } else {
                    System.out.println("  Operação cancelada.");
                }
            }, () -> System.out.println("  [!] Professor não encontrado."));
        } catch (Exception e) {
            System.out.println("\n  [!] Erro: " + e.getMessage());
        }
    }

    // ===== OPERAÇÕES DE DISCIPLINA =====

    private void cadastrarDisciplina() {
        System.out.println("\n--- CADASTRO DE DISCIPLINA ---");
        try {
            String nome = lerTexto("Nome da Disciplina");
            int cargaHoraria = lerInteiro("Carga Horária (horas)");

            System.out.print("  Vincular professor agora? (s/n): ");
            String resp = scanner.nextLine().trim();

            Disciplina disciplina;
            if (resp.equalsIgnoreCase("s")) {
                listarProfessores();
                int profId = lerInteiro("ID do Professor");
                disciplina = disciplinaServico.cadastrar(nome, cargaHoraria, profId);
            } else {
                disciplina = disciplinaServico.cadastrar(nome, cargaHoraria);
            }

            System.out.println("\n  [✓] Disciplina cadastrada com sucesso!");
            System.out.println("  " + disciplina);

        } catch (IllegalArgumentException e) {
            System.out.println("\n  [!] Erro: " + e.getMessage());
        }
    }

    private void listarDisciplinas() {
        System.out.println("\n--- LISTA DE DISCIPLINAS ---");
        List<Disciplina> disciplinas = disciplinaServico.listarTodos();
        if (disciplinas.isEmpty()) {
            System.out.println("  Nenhuma disciplina cadastrada.");
            return;
        }
        disciplinas.forEach(d -> System.out.println("  " + d));
        System.out.println("  Total: " + disciplinas.size() + " disciplina(s).");
    }

    private void buscarDisciplinaPorId() {
        System.out.println("\n--- BUSCAR DISCIPLINA POR ID ---");
        try {
            int id = lerInteiro("ID da disciplina");
            disciplinaServico.buscarPorId(id).ifPresentOrElse(
                    d -> System.out.println("\n  Encontrada: " + d),
                    () -> System.out.println("\n  [!] Disciplina não encontrada.")
            );
        } catch (Exception e) {
            System.out.println("\n  [!] Erro: " + e.getMessage());
        }
    }

    private void buscarDisciplinaPorNome() {
        System.out.println("\n--- BUSCAR DISCIPLINA POR NOME ---");
        try {
            String nome = lerTexto("Nome (parcial)");
            List<Disciplina> resultado = disciplinaServico.buscarPorNome(nome);
            if (resultado.isEmpty()) {
                System.out.println("  Nenhuma disciplina encontrada para \"" + nome + "\".");
            } else {
                resultado.forEach(d -> System.out.println("  " + d));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n  [!] Erro: " + e.getMessage());
        }
    }

    private void removerDisciplina() {
        System.out.println("\n--- REMOVER DISCIPLINA ---");
        try {
            int id = lerInteiro("ID da disciplina a remover");
            disciplinaServico.buscarPorId(id).ifPresentOrElse(d -> {
                System.out.println("  Você deseja remover: " + d.getNome() + "?");
                System.out.print("  Confirmar? (s/n): ");
                if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
                    disciplinaServico.remover(id);
                    System.out.println("  [✓] Disciplina removida com sucesso.");
                } else {
                    System.out.println("  Operação cancelada.");
                }
            }, () -> System.out.println("  [!] Disciplina não encontrada."));
        } catch (Exception e) {
            System.out.println("\n  [!] Erro: " + e.getMessage());
        }
    }

    // ===== RESUMO =====

    private void exibirResumo() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║           RESUMO DO SISTEMA          ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.printf("║  Alunos cadastrados:     %-12d║%n", alunoServico.total());
        System.out.printf("║  Professores cadastrados: %-11d║%n", professorServico.total());
        System.out.printf("║  Disciplinas cadastradas: %-11d║%n", disciplinaServico.total());
        System.out.println("╚══════════════════════════════════════╝");
    }

    // ===== UTILITÁRIOS DE LEITURA =====

    private String lerTexto(String campo) {
        String valor;
        do {
            System.out.print("  " + campo + ": ");
            valor = scanner.nextLine().trim();
            if (valor.isEmpty()) {
                System.out.println("  [!] Campo obrigatório. Tente novamente.");
            }
        } while (valor.isEmpty());
        return valor;
    }

    private int lerInteiro(String campo) {
        while (true) {
            try {
                System.out.print("  " + campo + ": ");
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Valor inválido. Informe um número inteiro.");
            }
        }
    }

    private double lerDecimal(String campo) {
        while (true) {
            try {
                System.out.print("  " + campo + ": ");
                double valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Valor inválido. Informe um número decimal (ex: 7.5).");
            }
        }
    }

    private void exibirBoasVindas() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   BEM-VINDO AO SISTEMA DE GESTÃO    ║");
        System.out.println("║            ESCOLAR v1.0              ║");
        System.out.println("╚══════════════════════════════════════╝\n");
    }
}
