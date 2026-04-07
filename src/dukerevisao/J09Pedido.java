package dukerevisao;

public class J09Pedido {
    private J07Carrinho carrinho; 
    private J08StatusPedido status;

    public J09Pedido(J07Carrinho carrinho) {
        this.carrinho = carrinho;
        this.status = J08StatusPedido.AGUARDANDO_PAGAMENTO;
    }

    // [POLIMORFISMO NA PRÁTICA]
    // O parâmetro é do tipo da Interface. Aceita CartaoCredito ou PagamentoPix.
    // --- O MOTOR DO SISTEMA (Onde o Polimorfismo acontece) ---
    public void fecharPedido(J02MetodoPagamento metodo, double total) {
        try {
            // O método processar é chamado, mas a implementação específica depende do objeto enviado (CartaoCredito ou PagamentoPix).
            // Aqui temo o polimorfismo em ação: o mesmo método processar() tem comportamentos diferentes dependendo da classe concreta que o implementa.
            metodo.processar(total); // Executa o método da classe concreta enviada
            this.status = J08StatusPedido.PAGO;
            System.out.println("✅ Status do Pedido: " + this.status);
        } catch (J01PagamentoException e) {
            System.err.println("❌ Falha no Pagamento: " + e.getMessage());
        }
 
    }
}
