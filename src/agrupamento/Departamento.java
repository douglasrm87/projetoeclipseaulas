package agrupamento;

public class Departamento {
    // Atributos
    private String nome;
    private Aluno aluno;

    // Métodos
    public Departamento(String nome) {
        this.nome = nome;
    }

    public void adicionarAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "nome='" + nome + '\'' +
                ", aluno=" + aluno +
                '}';
    }
    
}
