package gestaobiblioteca.model;

// A) Classe Usuario que é a superclasse para 
// Administrador, Professor e Aluno
// B) Esta classe pode ter atributos e métodos 
// comuns a todos os tipos de usuários, 
// como nome, email, senha, etc.
// C) A palavra-chave abstract é usada para 
// indicar que esta classe é abstrata, ou seja,
// não pode ser instanciada diretamente, mas serve
// como base para as subclasses que a estendem.
public abstract class Usuario implements Autenticavel {
    private long id; 
    private String nome; 
    private String email; 
    private String login; 
    private String senha; 
    private boolean primeiroAcesso = true; 
    private Perfil perfil; // ADMIN, PROFESSOR, ALUNO 

    // Construtor, serve para criar um novo aluno com os dados fornecidos
    // O construtor é um método especial que é chamado quando um 
    // objeto da classe é criado. 
    // Ele inicializa os atributos do objeto com os valores 
    // fornecidos no ato da criação.
    public Usuario(long id, String nome, String email, 
        String login, String senha , Perfil perfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }
    public void setId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser positivo.");
        }
        this.id = id;
    }
}