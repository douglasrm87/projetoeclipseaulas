package dukerevisao;

public class J04PagamentoPix implements J02MetodoPagamento {
    private String chavePix;

    public J04PagamentoPix(String chave) {
        this.chavePix = chave;
    }
 
    @Override
    public void processar(double valor) throws J01PagamentoException {
        if (valor <= 0) 
            throw new J01PagamentoException("Valor inválido para Pix.");
        System.out.println("📱 Pagamento de R$" + valor + " autorizado via Pix com chave " + chavePix);
        System.out.println("📱 QR Code Pix gerado para o valor de R$" + valor);
    }
    
}
