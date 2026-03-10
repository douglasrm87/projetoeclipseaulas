package gestaobiblioteca.model;

// Classe usuario que é a superclasse de Aluno, Professor e Administrador
//A classe Usuario é a superclasse de Aluno, Professor e Administrador, o que significa que Aluno, Professor e Administrador herdam os atributos e métodos da classe Usuario. Isso              
//a palavra-chave abstract é usada para indicar que a classe Usuario é uma classe abstrata, ou seja, ela não pode ser instanciada diretamente, mas pode ser estendida por outras classes.
//indica que a classe Usuario é uma classe abstrata, o que significa que ela não pode ser instanciada diretamente, mas pode ser estendida por outras classes. Isso é útil quando queremos criar uma hierarquia de classes onde a classe base (Usuario) define atributos e métodos comuns, mas não faz sentido criar objetos diretamente dessa classe. Em vez disso, criamos subclasses (Aluno, Professor, Administrador) que herdam os atributos e métodos da classe Usuario e podem ter suas próprias características específicas.
//A classe Usuario tem um atributo id, que é um número inteiro que identifica unicamente cada usuário. O método setId(int id) é usado para definir o valor do id, e ele lança uma exceção IllegalArgumentException se o id for um número inteiro negativo. O método getId() retorna o valor do id.

public abstract class Usuario implements Autenticavel {
    public class Aluno extends Usuario {
    private long id;
    private String nome;
    private String email;
    private String login;
    private String senha;   
    private boolean primeiroAcesso = true;
    private Perfil perfil; // ADMIN, PROFESSOR, ALUNO
    
// Construtor
// Construtor é um método especial usado para criar e inicializar objetos. Ele tem o mesmo nome da classe e não possui um tipo de retorno. O construtor é chamado automaticamente quando um objeto é criado usando a palavra-chave "new". Ele pode ser usado para definir valores iniciais para os atributos do objeto ou para executar qualquer configuração necessária durante a criação do objeto.
// Objeto de classe é criado usando o construtor, que é um método especial que tem o mesmo nome da classe e é usado para inicializar os objetos. O construtor é chamado automaticamente quando um objeto é criado usando a palavra-chave "new". Ele pode ser usado para definir valores iniciais para os atributos do objeto ou para executar qualquer configuração necessária durante a criação do objeto.
// Ele inicializa os atributos do objeto com os valores fornecidos como parâmetros. O construtor é essencial para garantir que os objetos sejam criados com um estado válido e consistente. Ele pode ser sobrecarregado, permitindo a criação de objetos de diferentes maneiras, dependendo dos parâmetros fornecidos.
// fornecidos no ato da criação do objeto. Ele é fundamental para garantir que os objetos sejam criados com um estado válido e consistente, e pode ser sobrecarregado para permitir a criação de objetos de diferentes maneiras, dependendo dos parâmetros fornecidos.

    public Aluno(long id, String nome, String email, String login, String senha, Perfil perfil) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }
    
    public void setId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser um número inteiro positivo.");
        }
        this.id = id;
    }
}
