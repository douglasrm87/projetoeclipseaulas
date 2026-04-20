package restauranterevisaooo;

// Implementação 1: Cartão de Crédito (Lógica de operadora)
public class PagamentoCartao implements MetodoPagamento {
    private String operadora;

    public PagamentoCartao(String operadora) {
        this.operadora = operadora;
    }

    @Override
    public void processarPagamento(double valor) {
        System.out.println("💳 [CARTÃO " + operadora.toUpperCase() + "]");
        System.out.println("Enviando transação de R$" + valor + " para a rede...");
        System.out.println("Transação autorizada com sucesso!");
    }
}


