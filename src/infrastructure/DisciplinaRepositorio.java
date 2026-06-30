package infrastructure;

import domain.Disciplina;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DisciplinaRepositorio {

    private final ArrayList<Disciplina> disciplinas = new ArrayList<>();
    private int proximoId = 1;

    public Disciplina salvar(Disciplina disciplina) {
        disciplina.setId(proximoId++);
        disciplinas.add(disciplina);
        return disciplina;
    }

    public List<Disciplina> listarTodos() {
        return new ArrayList<>(disciplinas);
    }

    // Polimorfismo overload - busca por ID
    public Optional<Disciplina> buscar(int id) {
        return disciplinas.stream().filter(d -> d.getId() == id).findFirst();
    }

    // Polimorfismo overload - busca por nome (parcial, ignora maiúsculas)
    public List<Disciplina> buscar(String nome) {
        List<Disciplina> resultado = new ArrayList<>();
        for (Disciplina d : disciplinas) {
            if (d.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(d);
            }
        }
        return resultado;
    }

    public boolean remover(int id) {
        return disciplinas.removeIf(d -> d.getId() == id);
    }

    public int totalDisciplinas() {
        return disciplinas.size();
    }
}
