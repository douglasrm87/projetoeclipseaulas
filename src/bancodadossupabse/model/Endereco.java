package bancodadossupabse.model;

 

public class Endereco {
    private int id;
    private String rua;
    private String cidade;
    private String cep;

    public Endereco() {
    }

    public Endereco(String rua, String cidade, String cep) {
        this.rua = rua;
        this.cidade = cidade;
        this.cep = cep;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
}
