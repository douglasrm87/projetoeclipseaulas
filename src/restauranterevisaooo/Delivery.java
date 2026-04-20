package restauranterevisaooo;

/**
 * [HERANÇA - Especialização de Pedido]
 * 
 * Delivery é um tipo específico de Pedido com características extras:
 * - Taxa de entrega
 * - Endereço de entrega
 * - Status de entrega
 * 
 * Demonstra:
 * 1. Herança: extends Pedido
 * 2. Polimorfismo: sobrescreve o método calcularTotal()
 * 3. Extensão: adiciona novos atributos e métodos
 * 4. Super: chama o construtor da superclasse
 */
public class Delivery extends Pedido {
    
    // [ATRIBUTOS ESPECÍFICOS DE DELIVERY]
    private String enderecoEntrega;
    private double taxaEntrega;
    private String statusEntrega;

    // [CONSTRUTOR - Chama construtor da superclasse com super()]
    public Delivery(Cliente cliente, String enderecoEntrega, double taxaEntrega) {
        super(cliente);  // Chama construtor de Pedido
        
        if (enderecoEntrega == null || enderecoEntrega.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço de entrega não pode ser vazio");
        }
        if (taxaEntrega < 0) {
            throw new IllegalArgumentException("Taxa de entrega não pode ser negativa");
        }
        
        this.enderecoEntrega = enderecoEntrega.trim();
        this.taxaEntrega = taxaEntrega;
        this.statusEntrega = "Aguardando coleta";
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public double getTaxaEntrega() {
        return taxaEntrega;
    }

    public String getStatusEntrega() {
        return statusEntrega;
    }

    public void setStatusEntrega(String statusEntrega) {
        this.statusEntrega = statusEntrega;
    }

    /**
     * [POLIMORFISMO - OVERRIDE]
     * Sobrescreve o método calcularTotal() de Pedido.
     * Agora inclui a taxa de entrega.
     * 
     * super.calcularTotal() chama o método da superclasse
     */
    @Override
    public double calcularTotal() {
        return super.calcularTotal() + taxaEntrega;
    }

    /**
     * [SOBRESCRITA]
     * Sobrescreve o resumo para mostrar informações de entrega
     */
    @Override
    public void exibirResumo() {
        super.exibirResumo();  // Chama o resumo da classe pai
        
        System.out.println("\n┌──── INFORMAÇÕES DE ENTREGA ──────────┐");
        System.out.println("│ Endereço: " + String.format("%-28s │", enderecoEntrega));
        System.out.println("│ Taxa: R$ " + String.format("%-29.2f │", taxaEntrega));
        System.out.println("│ Status: " + String.format("%-28s │", statusEntrega));
        System.out.println("└──────────────────────────────────────┘");
    }
}
