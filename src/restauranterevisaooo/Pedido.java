package restauranterevisaooo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    protected List<ItemPedido> itens = new ArrayList<>();
    protected StatusPedido status = StatusPedido.PENDENTE;

    private double total;

    public Pedido(double total) { this.total = total; }

    // [POLIMORFISMO NA PRÁTICA]
    // O parâmetro 'pagamento' é do tipo MetodoPagamento (Interface).
    // Ele pode RECEBER um objeto PagamentoCartao, PagamentoPix ou uma Lambda.
    public void finalizarPedido(MetodoPagamento pagamento) throws RestauranteException {
        try {
            System.out.println("\n--- PROCESSANDO CHECKOUT ---");
            pagamento.processarPagamento(this.total);
            System.out.println("Pedido finalizado com sucesso!");
        } catch (RestauranteException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    // [Associação] - O pedido se associa a produtos via lista
    public void adicionarItem(Produto p, int qtd) {
        itens.add(new ItemPedido(p, qtd));
    }
/*

Essa linha de código é um excelente exemplo de como o Java moderno utiliza Programação Funcional para substituir loops tradicionais (for ou while), tornando o código mais limpo e menos propenso a erros de lógica.
Podemos documentar essa linha focando no conceito de Pipeline de Dados:
/**
 * [CONCEITO: STREAM API E METHOD REFERENCE]
 * Esta linha substitui um loop 'for' manual para calcular o total do pedido.
 * * 1. itens.stream(): 
 * - Transforma a lista de itens em um "fluxo" de dados (Pipeline). 
 * - Permite processar os elementos de forma sequencial ou paralela.
 * * 2. .mapToDouble(ItemPedido::getSubtotal): 
 * - [MAPEAMENTO]: Transforma cada objeto 'ItemPedido' em um valor 'double'.
 * - [METHOD REFERENCE]: 'ItemPedido::getSubtotal' é um atalho para (item -> item.getSubtotal()).
 * - O resultado aqui é um fluxo apenas de números decimais.
 * * 3. .sum():
 * - [OPERAÇÃO TERMINAL]: Percorre todo o fluxo, soma os valores e encerra a Stream.
 * * POR QUE USAR?
 * Melhora a legibilidade (Código Declarativo: você diz O QUE quer, não COMO fazer) 
 * e facilita a manutenção do sistema.
    return itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
    
*/
    public double calcularTotal() {
        return itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
    }

    // [Polimorfismo] - Aceita qualquer MetodoPagamento (Classe ou Lambda)
    public void fecharPedido(MetodoPagamento metodo) throws RestauranteException {
        double total = calcularTotal();
        if (total <= 0) throw new RestauranteException("Pedido vazio!");
        
        metodo.processarPagamento(total);
        this.status = StatusPedido.PREPARANDO;
        
        // [Threads] - Simula a cozinha processando em background
        new Thread(() -> {
            try {
                System.out.println("\n[COZINHA] Iniciando preparo do pedido...");
                Thread.sleep(5000); // Simula tempo de preparo
                this.status = StatusPedido.PRONTO;
                System.out.println("\n[NOTIFICAÇÃO] Pedido pronto para servir/entregar!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
