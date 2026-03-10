package gestaobiblioteca.model;

// Classe Administrador que herda de Usuario
//Esta classe pode ter atributos e métodos específicos para o administrador, além dos herdados de Usuario.
//extends é a palavra-chave usada para indicar que a classe Administrador é uma subclasse de Usuario, ou seja, ela herda os atributos e métodos da classe Usuario.
//A classe Administrador pode ter seus próprios atributos e métodos, além dos herdados de Usuario. Por exemplo, ela pode ter um método para gerenciar usuários ou para acessar relatórios administrativos.

public class Administrador extends Usuario {

    public Administrador(long id, String nome, String email, String login, String senha) {
        super(id, nome, email, login, senha, Perfil.ADMIN);
        
    }
    
}
