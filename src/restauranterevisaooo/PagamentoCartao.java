package restauranterevisaooo;

/**
 * [IMPLEMENTAÇÃO DE INTERFACE - Polimorfismo em Ação]
 * 
 * PagamentoCartao implementa a interface MetodoPagamento.
 * Isso significa que ela se compromete a fornecer o método processarPagamento.
 * 
 * POLIMORFISMO: O código que usa MetodoPagamento não precisa saber se é:
 * - PagamentoCartao
 * - PagamentoPix
 * - PagamentoDinheiro
 * - Qualquer outra implementação
 * 
 * Basta chamar: pagamento.processarPagamento(valor)
 */
public class PagamentoCartao implements MetodoPagamento {
    
    // [ATRIBUTOS PRIVADOS - Encapsulamento]
    private String operadora;
    private String numeroCartao;

    // [CONSTRUTOR - Inicializa os atributos]
    public PagamentoCartao(String operadora) {
        if (operadora == null || operadora.trim().isEmpty()) {
            throw new IllegalArgumentException("Operadora não pode ser vazia");
        }
        this.operadora = operadora;
        // Simula um número de cartão (em produção seria criptografado)
        this.numeroCartao = "**** **** **** " + (int)(Math.random() * 10000);
    }

    public String getOperadora() {
        return operadora;
    }

    /**
     * [IMPLEMENTAÇÃO DE MÉTODO DA INTERFACE]
     * @Override garante que estamos implementando um método da interface
     * Se o assinatura não bater, o compilador avisa
     */
    @Override
    public void processarPagamento(double valor) throws RestauranteException {
        if (valor <= 0) {
            throw new RestauranteException("Valor inválido: " + valor);
        }
        
        try {
            System.out.println("\n💳 [PROCESSANDO PAGAMENTO COM CARTÃO]");
            System.out.println("   Operadora: " + operadora.toUpperCase());
            System.out.println("   Cartão: " + numeroCartao);
            System.out.println("   Valor: R$ " + String.format("%.2f", valor));
            
            // Simula validação
            if (!validarCartao()) {
                throw new RestauranteException("Cartão recusado pela operadora");
            }
            
            System.out.println("   ✅ Transação autorizada com sucesso!");
            
        } catch (RestauranteException e) {
            throw e;
        } catch (Exception e) {
            throw new RestauranteException("Erro ao processar pagamento: " + e.getMessage());
        }
    }

    // [MÉTODO AUXILIAR PRIVADO]
    private boolean validarCartao() {
        // Simula validação (em produção seria mais complexo)
        return Math.random() > 0.1; // 90% de sucesso
    }
}


