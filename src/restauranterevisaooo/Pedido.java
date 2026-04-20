package restauranterevisaooo;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * [CLASSE PEDIDO - Agregação e Associação]
 * 
 * AGREGAÇÃO:
 * Um Pedido CONTÉM uma lista de ItemPedido.
 * Diferença de COMPOSIÇÃO:
 * - Se o Pedido for destruído, os Itens podem continuar existindo
 * - Relacionamento mais fraco, coleção de partes
 * 
 * ASSOCIAÇÃO:
 * Pedido está ASSOCIADO a um Cliente e um MetodoPagamento
 * 
 * ENCAPSULAMENTO:
 * - Atributos privados
 * - Métodos públicos controlam o acesso
 * - Validações protegem a integridade
 */
public class Pedido {
    
    // [ATRIBUTOS PRIVADOS - Encapsulamento]
    protected List<ItemPedido> itens;           // [AGREGAÇÃO] - Lista de itens
    protected StatusPedido status;              // [ENUM] - Estado do pedido
    protected Cliente cliente;                  // [ASSOCIAÇÃO] - Ligação com Cliente
    protected LocalDateTime dataCriacao;
    protected static final double TAXA_MINIMA = 10.0;

    // [CONSTRUTOR]
    public Pedido(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.status = StatusPedido.PENDENTE;
        this.dataCriacao = LocalDateTime.now();
    }

    // [GETTERS]
    public Cliente getCliente() {
        return cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public List<ItemPedido> getItens() {
        return new ArrayList<>(itens);  // Retorna cópia para proteger dados internos
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    /**
     * [ASSOCIAÇÃO]
     * O Pedido se ASSOCIA a Produtos adicionando-os à lista.
     * Usa ItemPedido como intermediário (COMPOSIÇÃO).
     * 
     * @param produto O produto a ser adicionado
     * @param quantidade Quantidade desejada
     * @throws IllegalArgumentException Se quantidade for inválida
     */
    public void adicionarItem(Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        
        // Verifica se já existe este produto no pedido
        for (ItemPedido item : itens) {
            if (item.getProduto().getNome().equals(produto.getNome())) {
                throw new IllegalArgumentException("Produto já foi adicionado ao pedido");
            }
        }
        
        ItemPedido novoItem = new ItemPedido(produto, quantidade);
        itens.add(novoItem);
        System.out.println("✅ Adicionado: " + novoItem);
    }

    /**
     * [POLIMORFISMO]
     * Aceita qualquer implementação de MetodoPagamento:
     * - PagamentoCartao
     * - PagamentoPix
     * - Ou até uma Lambda
     * 
     * @param pagamento O método de pagamento a usar
     * @throws RestauranteException Se o pagamento falhar
     */
    public void finalizarPedido(MetodoPagamento pagamento) throws RestauranteException {
        if (itens.isEmpty()) {
            throw new RestauranteException("Erro: O pedido está vazio! Adicione itens primeiro.");
        }
        
        double total = calcularTotal();
        
        try {
            System.out.println("\n┌─ FINALIZANDO PEDIDO ─────────────────────┐");
            System.out.println("│ Cliente: " + cliente.getNome());
            System.out.println("│ Itens: " + itens.size());
            System.out.println("│ Total: R$ " + String.format("%.2f", total));
            System.out.println("└──────────────────────────────────────────┘");
            
            // Processa o pagamento (POLIMORFISMO)
            pagamento.processarPagamento(total);
            
            // Atualiza status
            this.status = StatusPedido.PREPARANDO;
            
            // Simula a cozinha preparando o pedido
            iniciarPreparoEmThread();
            
        } catch (RestauranteException e) {
            throw e;
        }
    }

    /**
     * [STREAM API - Calcular total usando programação funcional]
     * 
     * Alternativa moderna ao loop tradicional (for/while):
     * 1. itens.stream() - transforma a lista em um pipeline de dados
     * 2. .mapToDouble(...) - extrai o subtotal de cada item
     * 3. .sum() - soma todos os valores
     * 
     * @return O total do pedido
     */
    public double calcularTotal() {
        return itens.stream()
            .mapToDouble(ItemPedido::getSubtotal)
            .sum();
    }

    /**
     * [MÉTODO PRIVADO - Encapsulamento]
     * Simula a cozinha processando o pedido em background
     * Demonstra uso de Thread (Programação Paralela)
     */
    private void iniciarPreparoEmThread() {
        new Thread(() -> {
            try {
                System.out.println("\n🍳 [COZINHA] Iniciando preparo do pedido...");
                Thread.sleep(3000);
                
                this.status = StatusPedido.PRONTO;
                System.out.println("✅ [NOTIFICAÇÃO] Seu pedido está pronto!");
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    /**
     * [MÉTODO DE RELATÓRIO]
     * Exibe o resumo do pedido de forma formatada
     */
    public void exibirResumo() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          RESUMO DO PEDIDO              ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Cliente: " + String.format("%-29s ║", cliente.getNome()));
        System.out.println("║ Data: " + dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "                 ║");
        System.out.println("║ Status: " + String.format("%-28s ║", status.getDescricao()));
        System.out.println("╠════════════════════════════════════════╣");
        
        for (ItemPedido item : itens) {
            System.out.println("║ " + String.format("%-36s ║", item.toString()));
        }
        
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ TOTAL: R$ " + String.format("%-28.2f ║", calcularTotal()));
        System.out.println("╚════════════════════════════════════════╝");
    }
}
