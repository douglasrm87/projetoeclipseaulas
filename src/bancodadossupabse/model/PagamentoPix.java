package bancodadossupabse.model;

 

public class PagamentoPix extends Pagamento {

    @Override
    public boolean realizarPagamento(double valor) {
        this.valor = valor;
        this.tipo = TipoPagamento.PIX;

        System.out.println("Pagamento via PIX: R$ " + valor);
        return true;
    }
}
