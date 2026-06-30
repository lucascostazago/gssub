package domain;

public class Aluno extends Pessoa {

    private String matricula;
    private String curso;
    private double coeficienteRendimento;

    public Aluno() {
        super();
    }

    // Polimorfismo overload - construtor sem coeficiente
    public Aluno(int id, String nome, String email, String telefone, String matricula, String curso) {
        super(id, nome, email, telefone);
        this.matricula = matricula;
        this.curso = curso;
        this.coeficienteRendimento = 0.0;
    }

    // Polimorfismo overload - construtor com coeficiente
    public Aluno(int id, String nome, String email, String telefone, String matricula, String curso, double coeficienteRendimento) {
        super(id, nome, email, telefone);
        this.matricula = matricula;
        this.curso = curso;
        this.coeficienteRendimento = coeficienteRendimento;
    }

    // Polimorfismo override - sobrescreve apresentar() de Pessoa
    @Override
    public String apresentar() {
        return "ALUNO | ID: " + getId() + " | Nome: " + getNome()
                + " | Matrícula: " + matricula + " | Curso: " + curso
                + " | CR: " + String.format("%.1f", coeficienteRendimento);
    }

    // Polimorfismo override - sobrescreve toString() de Pessoa
    @Override
    public String toString() {
        return super.toString() + " | Matrícula: " + matricula
                + " | Curso: " + curso
                + " | CR: " + String.format("%.1f", coeficienteRendimento);
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public double getCoeficienteRendimento() { return coeficienteRendimento; }
    public void setCoeficienteRendimento(double coeficienteRendimento) { this.coeficienteRendimento = coeficienteRendimento; }
}
