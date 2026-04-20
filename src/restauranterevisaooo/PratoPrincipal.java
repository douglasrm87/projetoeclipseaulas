package restauranterevisaooo;

// [Herança] - Especialização de Produto
public class PratoPrincipal extends Produto {
    public PratoPrincipal(String nome, double preco) { super(nome, preco); }
    
    @Override
    public void exibirDetalhes() {
        System.out.println("[Prato] " + getNome() + " - R$ " + getPreco());
    }
}
