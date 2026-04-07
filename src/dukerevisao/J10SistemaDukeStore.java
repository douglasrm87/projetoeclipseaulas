package dukerevisao;

/*
Por que essa abordagem é importante?
Desacoplamento: Mostra que a classe Pedido não "quebra" se você decidir adicionar um novo método de pagamento (ex: Cripto) amanhã. Basta criar a classe e implementar a interface.

Tratamento de Erros: O uso de throws PagamentoException na interface obriga o aluno a pensar em cenários de falha desde a arquitetura.

Encapsulamento: Note que os detalhes do cartão (numeroCartao) ficam escondidos dentro da classe CartaoCredito, e o Pedido nunca tem acesso a esse dado sensível, apenas ao resultado do processamento.
*/
public class J10SistemaDukeStore {
   public static void main(String[] args) {
        J07Carrinho carrinho = new J07Carrinho();
        carrinho.adicionarItem(new J06Camisa("Java 25 Anos", 120.0, "M"));
        // 1. Setup de Produtos (Herança e Abstração)
        J05Produto camisaVermelha = new J06Camisa("Camisa Duke Java", 89.90, "G");
        carrinho.adicionarItem(camisaVermelha);

        J09Pedido pedido = new J09Pedido(carrinho);

        // 2. Teste com Cartão de Crédito (Polimorfismo)
        J03CartaoCredito cartao = new J03CartaoCredito("1234-5678-9012-3456");
        // carrinho contem o produto, o pedido tem o carrinho, e o pedido fecha usando o cartao. O pedido não sabe os detalhes do cartao, apenas chama o metodo processar() da interface.
        pedido.fecharPedido(cartao, carrinho.calcularTotal());

         // Uso de classe anônima ou Lambda para simular o Polimorfismo da Interface  
   
        pedido.fecharPedido(valor -> {  
            System.out.println("--- LOG DE OPERAÇÃO ---");
            System.out.println("Pagamento em dinheiro de R$" + valor);
        }, carrinho.calcularTotal());
 
        // 3. Teste com PIX (Polimorfismo)
        J03PIX pix = new J03PIX("pix@duke.com");
        pedido.fecharPedido(pix, carrinho.calcularTotal());

   }
  
}
