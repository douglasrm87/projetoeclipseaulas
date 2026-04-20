package restauranterevisaooo;

// [Herança] - Delivery é um tipo específico de Pedido
public class Delivery extends Pedido {
    private String endereco;

    public Delivery(String endereco, double total) {
        super(total);
        this.endereco = endereco;
    }

    @Override 
    public double calcularTotal() {
        return super.calcularTotal() + 10.0; // Adiciona taxa de entrega fixa
    }
}
