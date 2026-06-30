package application;

import domain.Aluno;
import infrastructure.AlunoRepositorio;
import java.util.List;
import java.util.Optional;

public class AlunoServico {

    private final AlunoRepositorio repositorio;

    public AlunoServico(AlunoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Aluno cadastrar(String nome, String email, String telefone, String matricula, String curso) {
        validarCamposObrigatorios(nome, email, matricula, curso);
        if (repositorio.existeMatricula(matricula)) {
            throw new IllegalArgumentException("Já existe um aluno com a matrícula: " + matricula);
        }
        Aluno aluno = new Aluno(0, nome.trim(), email.trim(), telefone.trim(), matricula.trim(), curso.trim());
        return repositorio.salvar(aluno);
    }

    public Aluno cadastrar(String nome, String email, String telefone, String matricula, String curso, double cr) {
        validarCamposObrigatorios(nome, email, matricula, curso);
        if (cr < 0 || cr > 10) {
            throw new IllegalArgumentException("Coeficiente de Rendimento deve estar entre 0 e 10.");
        }
        if (repositorio.existeMatricula(matricula)) {
            throw new IllegalArgumentException("Já existe um aluno com a matrícula: " + matricula);
        }
        Aluno aluno = new Aluno(0, nome.trim(), email.trim(), telefone.trim(), matricula.trim(), curso.trim(), cr);
        return repositorio.salvar(aluno);
    }

    public List<Aluno> listarTodos() {
        return repositorio.listarTodos();
    }

    public Optional<Aluno> buscarPorId(int id) {
        return repositorio.buscar(id);
    }

    public List<Aluno> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Informe um nome para a busca.");
        }
        return repositorio.buscar(nome);
    }

    public boolean remover(int id) {
        if (repositorio.buscar(id).isEmpty()) {
            throw new IllegalArgumentException("Aluno com ID " + id + " não encontrado.");
        }
        return repositorio.remover(id);
    }

    public int total() {
        return repositorio.totalAlunos();
    }

    private void validarCamposObrigatorios(String nome, String email, String matricula, String curso) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório.");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("E-mail é obrigatório.");
        if (!email.contains("@")) throw new IllegalArgumentException("E-mail inválido.");
        if (matricula == null || matricula.isBlank()) throw new IllegalArgumentException("Matrícula é obrigatória.");
        if (curso == null || curso.isBlank()) throw new IllegalArgumentException("Curso é obrigatório.");
    }
}
