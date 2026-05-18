package bancodadossupabse.model;



public class PagamentoCartao extends Pagamento {

    @Override
    public boolean realizarPagamento(double valor) {
        this.valor = valor;
        this.tipo = TipoPagamento.CARTAO;

        System.out.println("Pagamento via Cartão: R$ " + valor);
        return true;
    }
}
