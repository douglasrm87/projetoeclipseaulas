package restauranterevisaooo;

/**
 * [COMPOSIÇÃO - Relacionamento "tem um"]
 * 
 * ItemPedido CONTÉM um Produto. A composição significa que:
 * 1. ItemPedido não faz sentido existir sem um Produto
 * 2. Quando ItemPedido é destruído, a composição é quebrada
 * 3. Um Produto pode fazer parte de múltiplos ItemPedido (não é exclusivo)
 * 
 * Diferença entre COMPOSIÇÃO e AGREGAÇÃO:
 * - COMPOSIÇÃO: O todo "morre" se a parte morre
 * - AGREGAÇÃO: O todo pode continuar sem a parte
 * 
 * Neste caso, é COMPOSIÇÃO porque item = produto + quantidade juntos
 * 
 * [ENCAPSULAMENTO]
 * Atributos privados, acesso controlado por getters
 */
public class ItemPedido {
    
    // [ATRIBUTOS PRIVADOS - Encapsulamento]
    private Produto produto;        // [COMPOSIÇÃO] - Produto é parte essencial do Item
    private int quantidade;
    private double subtotal;        // Cache do subtotal para melhor performance

    // [CONSTRUTOR]
    public ItemPedido(Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = produto.getPreco() * quantidade;
    }

    // [GETTERS - Controlam o acesso aos dados]
    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    /**
     * [MÉTODO DEPRECIADO]
     * @deprecated Use getSubtotal() em vez disso
     * 
     * @Deprecated marca um método como obsoleto, sugerindo usar outra coisa
     */
    @Deprecated
    public double calcularSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return String.format("%d x %s = R$ %.2f", 
            quantidade, 
            produto.getNome(), 
            subtotal);
    }
}


