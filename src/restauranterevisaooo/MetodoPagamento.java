package restauranterevisaooo;

@FunctionalInterface
public interface MetodoPagamento {
    // [Polimorfismo] Todas as formas de pagamento devem implementar este método
    void processarPagamento(double valor) throws RestauranteException;
}
