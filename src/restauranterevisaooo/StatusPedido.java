package restauranterevisaooo;

/**
 * [ENUM - Tipo de Dado Especial]
 * Um Enum é um tipo de dado que define um conjunto FIXO de constantes.
 * Útil para representar estados, categorias ou opções limitadas.
 * 
 * VANTAGENS:
 * 1. Type-safe: Só permite valores definidos
 * 2. Fácil manutenção: Valores centralizados
 * 3. Legibilidade: StatusPedido.PRONTO é mais claro que string "PRONTO"
 */
public enum StatusPedido {
    PENDENTE("Aguardando preparação"),
    PREPARANDO("Em processamento na cozinha"),
    PRONTO("Pronto para entrega/retirada"),
    ENTREGUE("Pedido finalizado");

    private final String descricao;

    // [CONSTRUTOR de Enum]
    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
