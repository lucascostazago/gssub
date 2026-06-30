package infrastructure;

import domain.Aluno;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoRepositorio {

    private final ArrayList<Aluno> alunos = new ArrayList<>();
    private int proximoId = 1;

    public Aluno salvar(Aluno aluno) {
        aluno.setId(proximoId++);
        alunos.add(aluno);
        return aluno;
    }

    public List<Aluno> listarTodos() {
        return new ArrayList<>(alunos);
    }

    // Polimorfismo overload - busca por ID
    public Optional<Aluno> buscar(int id) {
        return alunos.stream().filter(a -> a.getId() == id).findFirst();
    }

    // Polimorfismo overload - busca por nome (parcial, ignora maiúsculas)
    public List<Aluno> buscar(String nome) {
        List<Aluno> resultado = new ArrayList<>();
        for (Aluno a : alunos) {
            if (a.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    public boolean remover(int id) {
        return alunos.removeIf(a -> a.getId() == id);
    }

    public boolean existeMatricula(String matricula) {
        return alunos.stream().anyMatch(a -> a.getMatricula().equalsIgnoreCase(matricula));
    }

    public int totalAlunos() {
        return alunos.size();
    }
}
