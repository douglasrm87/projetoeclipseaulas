package bancodadossupabse.model;


public abstract class Pessoa {

    private int id;
    private String nome;
    private String email;
    private Endereco endereco;

    public abstract void exibirPerfil();

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }
}
