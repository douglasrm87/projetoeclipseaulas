package restauranterevisaooo;

// [Classe Abstrata] - Molde para produtos, não pode ser instanciada diretamente
abstract class Produto {
    private String nome; // [Encapsulamento] Atributos privados
    private double preco;

    public Produto(String nome, double preco) { // [Construtor]
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }

    public abstract void exibirDetalhes(); // [Método Abstrato]
}


