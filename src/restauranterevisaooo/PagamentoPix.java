package restauranterevisaooo;

// Implementação 2: Pix (Lógica de QR Code)
public class PagamentoPix implements MetodoPagamento {
    @Override
    public void processarPagamento(double valor) {
        System.out.println("📱 [PIX]");
        System.out.println("Gerando chave aleatória para R$" + valor);
        System.out.println("QR Code: [00020126360014br.gov.bcb.pix...]");
    }
}