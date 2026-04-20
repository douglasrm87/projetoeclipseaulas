package restauranterevisaooo;

import java.util.ArrayList;
import java.util.List;

/**
 * [CLASSE GERENCIADORA - Padrão de Agregação]
 * 
 * A classe Restaurante atua como:
 * 1. Gerenciador central da aplicação
 * 2. Agregador de pedidos
 * 3. Controladora de fluxo
 * 
 * Demonstra:
 * - Agregação: contém uma lista de pedidos
 * - Encapsulamento: métodos privados e públicos bem definidos
 * - Validação: verifica dados de entrada
 */
public class Restaurante {
    
    // [ATRIBUTOS PRIVADOS]
    private String nome;
    private List<Pedido> pedidos;
    private List<Produto> cardapio;

    // [CONSTRUTOR]
    public Restaurante(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do restaurante não pode ser vazio");
        }
        this.nome = nome.trim();
        this.pedidos = new ArrayList<>();
        this.cardapio = new ArrayList<>();
        inicializarCardapio();
    }

    public String getNome() {
        return nome;
    }

    public List<Pedido> getPedidos() {
        return new ArrayList<>(pedidos);
    }

    public List<Produto> getCardapio() {
        return new ArrayList<>(cardapio);
    }

    /**
     * [MÉTODO PRIVADO]
     * Inicializa o cardápio do restaurante
     * Encapsulado porque não precisa ser público
     */
    private void inicializarCardapio() {
        // Pratos Principais
        cardapio.add(new PratoPrincipal(
            "Risoto de Java", 45.0,
            "Risoto cremoso preparado com grãos de Java",
            "650 cal", false
        ));
        
        cardapio.add(new PratoPrincipal(
            "Filé de Python", 55.0,
            "Filé macio com molho de programação",
            "720 cal", true
        ));

        // Bebidas
        cardapio.add(new Bebida(
            "Café Expresso", 5.0,
            "Café espresso coado na hora",
            "Quente"
        ));

        cardapio.add(new Bebida(
            "Suco de Melancia", 8.0,
            "Suco natural de melancia gelada",
            "Gelada"
        ));

        // Sobremesas
        cardapio.add(new Sobremesa(
            "Pudim de Leite", 12.0,
            "Pudim tradicional com calda de açúcar",
            "Doce", true
        ));

        cardapio.add(new Sobremesa(
            "Chocolate Gelado", 10.0,
            "Chocolate quente derretendo no sorvete",
            "Doce", false
        ));
    }

    /**
     * [MÉTODO PÚBLICO]
     * Exibe o cardápio de forma formatada
     */
    public void exibirCardapio() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          CARDÁPIO - " + nome + "   ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        for (Produto produto : cardapio) {
            produto.exibirDetalhes();
            System.out.println();
        }
    }

    /**
     * [MÉTODO PUBLIC]
     * Adiciona um novo pedido ao restaurante
     */
    public void adicionarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        pedidos.add(pedido);
    }

    /**
     * [MÉTODO PUBLIC]
     * Busca um produto no cardápio pelo nome
     */
    public Produto buscarProduto(String nome) {
        for (Produto produto : cardapio) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        return null;
    }

    /**
     * [MÉTODO PUBLIC]
     * Exibe o resumo de todos os pedidos
     */
    public void exibirPedidos() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        PEDIDOS DO RESTAURANTE          ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
            return;
        }
        
        for (int i = 0; i < pedidos.size(); i++) {
            System.out.println("--- PEDIDO #" + (i + 1) + " ---");
            pedidos.get(i).exibirResumo();
        }
    }
}
