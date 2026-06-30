import application.AlunoServico;
import application.DisciplinaServico;
import application.ProfessorServico;
import infrastructure.AlunoRepositorio;
import infrastructure.DisciplinaRepositorio;
import infrastructure.ProfessorRepositorio;
import presentation.Menu;

public class Main {

    public static void main(String[] args) {
        AlunoRepositorio alunoRepositorio = new AlunoRepositorio();
        ProfessorRepositorio professorRepositorio = new ProfessorRepositorio();
        DisciplinaRepositorio disciplinaRepositorio = new DisciplinaRepositorio();

        AlunoServico alunoServico = new AlunoServico(alunoRepositorio);
        ProfessorServico professorServico = new ProfessorServico(professorRepositorio);
        DisciplinaServico disciplinaServico = new DisciplinaServico(disciplinaRepositorio, professorRepositorio);

        Menu menu = new Menu(alunoServico, professorServico, disciplinaServico);
        menu.iniciar();
    }
}
