package restauranterevisaooo;

// [Herança] - Especialização de Produto
public class Bebida extends Produto {
    public Bebida(String nome, double preco) { super(nome, preco); }
    
    @Override
    public void exibirDetalhes() {
        System.out.println("[Bebida] " + getNome() + " - R$ " + getPreco());
    }
}
