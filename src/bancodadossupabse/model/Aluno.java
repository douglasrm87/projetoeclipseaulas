package bancodadossupabse.model;
 

public class Aluno extends Pessoa implements Autenticavel {

    private String matricula;

    public Aluno() {
    }

    public Aluno(String nome, String email, String matricula) {
        this.setNome(nome);
        this.setEmail(email);
        this.matricula = matricula;
    }

    @Override
    public void exibirPerfil() {
        System.out.println("Aluno: " + getNome());
    }

    @Override
    public boolean login(String usuario, String senha) {
        return usuario.equals("aluno") && senha.equals("123");
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
}
 
