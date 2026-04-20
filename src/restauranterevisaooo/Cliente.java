package restauranterevisaooo;

/**
 * [CLASSE - Modelo de Dados do Cliente]
 * 
 * Demonstra:
 * 1. Encapsulamento: atributos privados com getters/setters
 * 2. Construtor: inicializa o objeto em estado válido
 * 3. Validação: verifica dados de entrada
 * 4. Associação: Cliente está associado a um endereço
 */
public class Cliente {
    
    // [ATRIBUTOS PRIVADOS - Encapsulamento]
    private String nome;
    private String telefone;
    private String endereco;

    // [CONSTRUTOR - Valida e inicializa]
    public Cliente(String nome, String telefone, String endereco) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser vazio");
        }
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço não pode ser vazio");
        }
        
        this.nome = nome.trim();
        this.telefone = telefone.trim();
        this.endereco = endereco.trim();
    }

    // [GETTERS]
    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    // [SETTERS - Permitem modificação controlada]
    public void setTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser vazio");
        }
        this.telefone = telefone.trim();
    }

    public void setEndereco(String endereco) {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço não pode ser vazio");
        }
        this.endereco = endereco.trim();
    }

    @Override
    public String toString() {
        return String.format("Cliente: %s\n   Telefone: %s\n   Endereço: %s", 
            nome, telefone, endereco);
    }
}
