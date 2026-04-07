package dukerevisao;

public class J03PIX implements J02MetodoPagamento {
    private String numeroPIX;

    public J03PIX(String numero) {
        this.numeroPIX = numero;
    }

    @Override
    public void processar(double valor) throws J01PagamentoException {
        if (valor <= 0) throw new J01PagamentoException("Valor inválido para PIX.");
        System.out.println("💳 Pagamento de R$" + valor + " autorizado no PIX " + numeroPIX);
    }
}
