package restauranterevisaooo;

 

// [Composição] - ItemPedido não faz sentido existir sem um Pedido
class ItemPedido {
    private Produto produto; // [Agregação] O produto existe independente do item
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }
}


