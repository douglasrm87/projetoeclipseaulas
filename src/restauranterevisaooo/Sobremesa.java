package restauranterevisaooo;

/**
 * [HERANÇA - Terceira Especialização de Produto]
 * 
 * Demonstra que uma classe abstrata pode ter múltiplas subclasses,
 * cada uma com suas particularidades (POLIMORFISMO na prática).
 */
public class Sobremesa extends Produto {
    
    // [ATRIBUTO ESPECÍFICO]
    private String tempodeSabor; // Amargo, Doce, Azedo
    private boolean contemLactose;

    // [CONSTRUTOR]
    public Sobremesa(String nome, double preco, String descricao, 
                     String tempodeSabor, boolean contemLactose) {
        super(nome, preco, descricao);
        this.tempodeSabor = tempodeSabor;
        this.contemLactose = contemLactose;
    }

    public String getTempodeSabor() {
        return tempodeSabor;
    }

    public boolean isContemLactose() {
        return contemLactose;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("🍰 [SOBREMESA] " + getNome());
        System.out.println("   Preço: R$ " + String.format("%.2f", getPreco()));
        System.out.println("   Tipo de Sabor: " + tempodeSabor);
        System.out.println("   Contém Lactose: " + (contemLactose ? "Sim" : "Não"));
        System.out.println("   " + getDescricao());
    }
}
