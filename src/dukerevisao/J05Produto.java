package dukerevisao;

public abstract class J05Produto {
    private String nome; // [Atributo]
    private double preco;

    // [Construtor] - Inicializa o estado do objeto
    public J05Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    // [Encapsulamento] - Getters para acesso seguro
    public String getNome() { return nome; }
    public double getPreco() { return preco; }

    // [Método Abstrato] - Obriga as subclasses a implementarem sua própria lógica
    public abstract void exibirDetalhes();
}
