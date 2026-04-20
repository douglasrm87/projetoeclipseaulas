package restauranterevisaooo;

/**
 * [HERANÇA - Especialização de Produto]
 * 
 * Bebida herda os atributos e métodos de Produto.
 * Adiciona comportamento específico: temperatura de servimento.
 * 
 * Benefícios da Herança:
 * 1. Reutilização de código: não precisa redefinir nome e preço
 * 2. Polimorfismo: Bebida pode ser tratada como Produto
 * 3. Organização: agrupa comportamentos similares
 */
public class Bebida extends Produto {
    
    // [ATRIBUTO ESPECÍFICO]
    private String temperatura; // Quente, Gelada, Ambiente

    // [CONSTRUTOR - Chama o construtor da superclasse com super()]
    public Bebida(String nome, double preco, String descricao, String temperatura) {
        super(nome, preco, descricao);
        this.temperatura = temperatura;
    }

    public String getTemperatura() {
        return temperatura;
    }

    /**
     * [POLIMORFISMO - Override]
     * 
     * @Override garante que estamos implementando um método da superclasse.
     * Se o método não existir na superclasse, o compilador avisa.
     * 
     * Cada tipo de Produto exibe seus detalhes de forma diferente.
     * Isso é polimorfismo: mesma interface, comportamentos diferentes.
     */
    @Override
    public void exibirDetalhes() {
        System.out.println("🍹 [BEBIDA] " + getNome());
        System.out.println("   Preço: R$ " + String.format("%.2f", getPreco()));
        System.out.println("   Temperatura: " + temperatura);
        System.out.println("   " + getDescricao());
    }
}
