package dukerevisao;

public class J03CartaoCredito implements J02MetodoPagamento {
    private String numeroCartao;

    public J03CartaoCredito(String numero) {
        this.numeroCartao = numero;
    }

    @Override
    public void processar(double valor) throws J01PagamentoException {
        if (valor <= 0) throw new J01PagamentoException("Valor inválido para cartão.");
        System.out.println("💳 Pagamento de R$" + valor + " autorizado no cartão " + numeroCartao);
    }
}
