package restauranterevisaooo;

/**
 * [HERANÇA - Especialização de Produto]
 * 
 * PratoPrincipal também herda de Produto mas com características diferentes de Bebida.
 * Demonstra que múltiplas classes podem herdar da mesma superclasse.
 */
public class PratoPrincipal extends Produto {
    
    // [ATRIBUTO ESPECÍFICO]
    private String calorias;
    private boolean contemGluten;

    // [CONSTRUTOR - Chama o construtor da superclasse]
    public PratoPrincipal(String nome, double preco, String descricao, 
                          String calorias, boolean contemGluten) {
        super(nome, preco, descricao);
        this.calorias = calorias;
        this.contemGluten = contemGluten;
    }

    public String getCalorias() {
        return calorias;
    }

    public boolean isContemGluten() {
        return contemGluten;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("🍽️ [PRATO PRINCIPAL] " + getNome());
        System.out.println("   Preço: R$ " + String.format("%.2f", getPreco()));
        System.out.println("   Calorias: " + calorias);
        System.out.println("   Contém Glúten: " + (contemGluten ? "Sim" : "Não"));
        System.out.println("   " + getDescricao());
    }
}
