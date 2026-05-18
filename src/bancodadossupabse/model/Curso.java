package bancodadossupabse.model;
 

public class Curso {
    private int id;
    private String codigo;
    private String nome;

    public Curso() {
    }

    public Curso(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}

