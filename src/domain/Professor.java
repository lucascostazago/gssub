package domain;

public class Professor extends Pessoa {

    private String registro;
    private String especialidade;
    private double salario;

    public Professor() {
        super();
    }

    // Polimorfismo overload - construtor sem salário
    public Professor(int id, String nome, String email, String telefone, String registro, String especialidade) {
        super(id, nome, email, telefone);
        this.registro = registro;
        this.especialidade = especialidade;
        this.salario = 0.0;
    }

    // Polimorfismo overload - construtor com salário
    public Professor(int id, String nome, String email, String telefone, String registro, String especialidade, double salario) {
        super(id, nome, email, telefone);
        this.registro = registro;
        this.especialidade = especialidade;
        this.salario = salario;
    }

    // Polimorfismo override - sobrescreve apresentar() de Pessoa
    @Override
    public String apresentar() {
        return "PROFESSOR | ID: " + getId() + " | Nome: " + getNome()
                + " | Registro: " + registro + " | Especialidade: " + especialidade
                + " | Salário: R$ " + String.format("%.2f", salario);
    }

    // Polimorfismo override - sobrescreve toString() de Pessoa
    @Override
    public String toString() {
        return super.toString() + " | Registro: " + registro
                + " | Especialidade: " + especialidade
                + " | Salário: R$ " + String.format("%.2f", salario);
    }

    public String getRegistro() { return registro; }
    public void setRegistro(String registro) { this.registro = registro; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}
