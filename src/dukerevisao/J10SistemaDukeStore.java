package dukerevisao;

/*
Por que essa abordagem é importante?
Desacoplamento: Mostra que a classe Pedido não "quebra" se você decidir adicionar um novo método de pagamento (ex: Cripto) amanhã. Basta criar a classe e implementar a interface.

Tratamento de Erros: O uso de throws PagamentoException na interface obriga o aluno a pensar em cenários de falha desde a arquitetura.

Encapsulamento: Note que os detalhes do cartão (numeroCartao) ficam escondidos dentro da classe CartaoCredito, e o Pedido nunca tem acesso a esse dado sensível, apenas ao resultado do processamento.
*/
public class J10SistemaDukeStore {
   public static void main(String[] args) {
       J09Pedido meuPedido = new J09Pedido(null);
        double valorTotal = 150.00;

        // 1. Usando a CLASSE CONCRETA (Objeto da classe CartaoCredito)
        // Útil quando a lógica é complexa e precisa ser reutilizada.
        J02MetodoPagamento cartao = new J03CartaoCredito("1234-5678-9012-3456");
        meuPedido.fecharPedido(cartao, valorTotal);

        System.out.println("---");

        // 2. Usando LAMBDA (Implementação "on-the-fly")
        // Útil para lógicas rápidas ou quando não queremos criar um novo arquivo .java
        // O Java entende que este trecho SUBSTITUI a criação de uma classe 'Pix'.
        meuPedido.fecharPedido(valor -> {
            System.out.println("📱 Gerando QR Code Pix para R$" + valor);
            System.out.println("⏳ Aguardando confirmação do Banco...");
        }, valorTotal);
   }
  
}
