package restauranterevisaooo;

/**
 * [CLASSE ABSTRATA - Molde para Produtos]
 * 
 * Uma classe abstrata define a INTERFACE e o CONTRATO que as subclasses devem seguir.
 * Características:
 * 
 * 1. NÃO pode ser instanciada diretamente: new Produto() -> ERRO
 * 2. Pode ter métodos concretos (implementados) e abstratos (não implementados)
 * 3. As subclasses OBRIGATORIAMENTE devem implementar métodos abstratos
 * 4. Usa herança: class Bebida extends Produto
 * 
 * [ENCAPSULAMENTO] - Atributos privados com getters públicos
 * - Protege os dados
 * - Permite controlar o acesso
 * - Facilita mudanças futuras
 * 
 * [CONSTRUTOR] - Inicializa os atributos da classe
 * - Sempre executado quando um objeto é criado
 * - Garante que o objeto saia em estado válido
 */
public abstract class Produto {
    // [ATRIBUTOS PRIVADOS - Encapsulamento]
    private String nome;
    private double preco;
    private String descricao;

    // [CONSTRUTOR]
    public Produto(String nome, double preco, String descricao) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }

    // [GETTERS - Controlam o acesso aos atributos]
    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * [MÉTODO ABSTRATO]
     * Define que toda subclasse DEVE implementar este método.
     * Cada tipo de produto (Bebida, Prato, Sobremesa) tem sua forma própria
     * de exibir detalhes (POLIMORFISMO)
     */
    public abstract void exibirDetalhes();

    /**
     * [MÉTODO CONCRETO]
     * Pode ser herdado pelas subclasses sem obrigação de modificação
     */
    public void exibirInformacoes() {
        System.out.println(nome + " - R$ " + String.format("%.2f", preco));
    }
}


