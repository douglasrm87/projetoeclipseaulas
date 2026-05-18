package bancodadossupabse.model;
 

public class Professor extends Pessoa implements Autenticavel {

    private double salario;

    public Professor() {
    }

    public Professor(String nome, String email, double salario) {
        this.setNome(nome);
        this.setEmail(email);
        this.salario = salario;
    }

    @Override
    public void exibirPerfil() {
        System.out.println("Professor: " + getNome());
    }

    @Override
    public boolean login(String usuario, String senha) {
        return usuario.equals("prof") && senha.equals("123");
    }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}

