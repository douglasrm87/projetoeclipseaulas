package restauranterevisaooo;

/**
 * [INTERFACE - Contrato de Comportamento]
 * 
 * Uma interface define o QUE fazer, não o COMO fazer.
 * Características:
 * 
 * 1. Define métodos abstratos que DEVEM ser implementados
 * 2. Todas as classes que implementam a interface precisam fornecer o comportamento
 * 3. Permite POLIMORFISMO: múltiplas implementações da mesma interface
 * 4. Facilita testes (pode-se criar mocks/stubs)
 * 
 * @FunctionalInterface indica que a interface tem apenas um método abstrato
 * Isso permite uso com expressões lambda no Java 8+
 * 
 * EXEMPLO:
 * MetodoPagamento pagamento = (valor) -> System.out.println("Pago: " + valor);
 */
@FunctionalInterface
public interface MetodoPagamento {
    /**
     * [MÉTODO ABSTRATO]
     * Qualquer classe que implemente MetodoPagamento PRECISA ter este método.
     * 
     * @param valor O montante a ser processado
     * @throws RestauranteException Se algo der errado no pagamento
     */
    void processarPagamento(double valor) throws RestauranteException;
}
