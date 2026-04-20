package restauranterevisaooo;

/**
 * [SEGUNDA IMPLEMENTAÇÃO DA INTERFACE MetodoPagamento]
 * 
 * Demonstra polimorfismo: diferentes formas de pagar, mesma interface.
 * PagamentoPix tem lógica completamente diferente de PagamentoCartao,
 * mas ambas implementam MetodoPagamento.
 */
public class PagamentoPix implements MetodoPagamento {
    
    // [ATRIBUTO]
    private String chaveAleatoria;

    // [CONSTRUTOR]
    public PagamentoPix() {
        // Gera uma chave PIX aleatória
        this.chaveAleatoria = gerarChavePix();
    }

    @Override
    public void processarPagamento(double valor) throws RestauranteException {
        if (valor <= 0) {
            throw new RestauranteException("Valor inválido: " + valor);
        }
        
        try {
            System.out.println("\n📱 [PROCESSANDO PAGAMENTO COM PIX]");
            System.out.println("   Valor: R$ " + String.format("%.2f", valor));
            System.out.println("   Chave PIX: " + chaveAleatoria);
            System.out.println("   QR Code: ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
            System.out.println("   Status: Aguardando confirmação...");
            
            // Simula confirmação
            Thread.sleep(1500);
            
            System.out.println("   ✅ Pix confirmado! Transação concluída!");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RestauranteException("Processamento interrompido");
        } catch (Exception e) {
            throw new RestauranteException("Erro ao processar PIX: " + e.getMessage());
        }
    }

    // [MÉTODO PRIVADO - Encapsulamento]
    private String gerarChavePix() {
        // Simula uma chave PIX aleatória
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder chave = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            chave.append(caracteres.charAt((int)(Math.random() * caracteres.length())));
        }
        return chave.toString();
    }
}