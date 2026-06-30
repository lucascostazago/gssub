package application;

import domain.Professor;
import infrastructure.ProfessorRepositorio;
import java.util.List;
import java.util.Optional;

public class ProfessorServico {

    private final ProfessorRepositorio repositorio;

    public ProfessorServico(ProfessorRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Professor cadastrar(String nome, String email, String telefone, String registro, String especialidade) {
        validarCamposObrigatorios(nome, email, registro, especialidade);
        if (repositorio.existeRegistro(registro)) {
            throw new IllegalArgumentException("Já existe um professor com o registro: " + registro);
        }
        Professor professor = new Professor(0, nome.trim(), email.trim(), telefone.trim(), registro.trim(), especialidade.trim());
        return repositorio.salvar(professor);
    }

    public Professor cadastrar(String nome, String email, String telefone, String registro, String especialidade, double salario) {
        validarCamposObrigatorios(nome, email, registro, especialidade);
        if (salario < 0) {
            throw new IllegalArgumentException("Salário não pode ser negativo.");
        }
        if (repositorio.existeRegistro(registro)) {
            throw new IllegalArgumentException("Já existe um professor com o registro: " + registro);
        }
        Professor professor = new Professor(0, nome.trim(), email.trim(), telefone.trim(), registro.trim(), especialidade.trim(), salario);
        return repositorio.salvar(professor);
    }

    public List<Professor> listarTodos() {
        return repositorio.listarTodos();
    }

    public Optional<Professor> buscarPorId(int id) {
        return repositorio.buscar(id);
    }

    public List<Professor> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Informe um nome para a busca.");
        }
        return repositorio.buscar(nome);
    }

    public boolean remover(int id) {
        if (repositorio.buscar(id).isEmpty()) {
            throw new IllegalArgumentException("Professor com ID " + id + " não encontrado.");
        }
        return repositorio.remover(id);
    }

    public int total() {
        return repositorio.totalProfessores();
    }

    private void validarCamposObrigatorios(String nome, String email, String registro, String especialidade) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório.");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("E-mail é obrigatório.");
        if (!email.contains("@")) throw new IllegalArgumentException("E-mail inválido.");
        if (registro == null || registro.isBlank()) throw new IllegalArgumentException("Registro é obrigatório.");
        if (especialidade == null || especialidade.isBlank()) throw new IllegalArgumentException("Especialidade é obrigatória.");
    }
}
