package application;

import domain.Disciplina;
import domain.Professor;
import infrastructure.DisciplinaRepositorio;
import infrastructure.ProfessorRepositorio;
import java.util.List;
import java.util.Optional;

public class DisciplinaServico {

    private final DisciplinaRepositorio repositorio;
    private final ProfessorRepositorio professorRepositorio;

    public DisciplinaServico(DisciplinaRepositorio repositorio, ProfessorRepositorio professorRepositorio) {
        this.repositorio = repositorio;
        this.professorRepositorio = professorRepositorio;
    }

    public Disciplina cadastrar(String nome, int cargaHoraria) {
        validarCampos(nome, cargaHoraria);
        return repositorio.salvar(new Disciplina(0, nome.trim(), cargaHoraria));
    }

    public Disciplina cadastrar(String nome, int cargaHoraria, int professorId) {
        validarCampos(nome, cargaHoraria);
        Professor professor = professorRepositorio.buscar(professorId)
                .orElseThrow(() -> new IllegalArgumentException("Professor com ID " + professorId + " não encontrado."));
        return repositorio.salvar(new Disciplina(0, nome.trim(), cargaHoraria, professor));
    }

    public List<Disciplina> listarTodos() {
        return repositorio.listarTodos();
    }

    public Optional<Disciplina> buscarPorId(int id) {
        return repositorio.buscar(id);
    }

    public List<Disciplina> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Informe um nome para a busca.");
        }
        return repositorio.buscar(nome);
    }

    public boolean remover(int id) {
        if (repositorio.buscar(id).isEmpty()) {
            throw new IllegalArgumentException("Disciplina com ID " + id + " não encontrada.");
        }
        return repositorio.remover(id);
    }

    public int total() {
        return repositorio.totalDisciplinas();
    }

    private void validarCampos(String nome, int cargaHoraria) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome da disciplina é obrigatório.");
        if (cargaHoraria <= 0) throw new IllegalArgumentException("Carga horária deve ser maior que zero.");
    }
}
