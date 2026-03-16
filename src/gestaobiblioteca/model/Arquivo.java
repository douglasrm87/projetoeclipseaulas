package gestaobiblioteca.model;

public class Arquivo {
    private long id;
    private String nome;
    private TipoArquivo tipo;
    private String caminho;

    public Arquivo(String nome, TipoArquivo tipo, String caminho) {
        this.nome = nome;
        this.tipo = tipo;
        this.caminho = caminho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoArquivo getTipo() {
        return tipo;
    }

    public void setTipo(TipoArquivo tipo) {
        this.tipo = tipo;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
    
}
