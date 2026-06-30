package infrastructure;

import domain.Professor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfessorRepositorio {

    private final ArrayList<Professor> professores = new ArrayList<>();
    private int proximoId = 1;

    public Professor salvar(Professor professor) {
        professor.setId(proximoId++);
        professores.add(professor);
        return professor;
    }

    public List<Professor> listarTodos() {
        return new ArrayList<>(professores);
    }

    // Polimorfismo overload - busca por ID
    public Optional<Professor> buscar(int id) {
        return professores.stream().filter(p -> p.getId() == id).findFirst();
    }

    // Polimorfismo overload - busca por nome (parcial, ignora maiúsculas)
    public List<Professor> buscar(String nome) {
        List<Professor> resultado = new ArrayList<>();
        for (Professor p : professores) {
            if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public boolean remover(int id) {
        return professores.removeIf(p -> p.getId() == id);
    }

    public boolean existeRegistro(String registro) {
        return professores.stream().anyMatch(p -> p.getRegistro().equalsIgnoreCase(registro));
    }

    public int totalProfessores() {
        return professores.size();
    }
}
