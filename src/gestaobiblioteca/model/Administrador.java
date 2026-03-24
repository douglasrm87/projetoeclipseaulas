package gestaobiblioteca.model;

// A) Classe Administrador que herda de Usuario
// B) Esta classe pode ter atributos e métodos específicos para o administrador, como gerenciamento de usuários, controle de empréstimos, etc.
// C) extends é a palavra-chave usada para indicar 
// que a classe Administrador é uma subclasse de 
// Usuario, ou seja, ela herda as características 
// e comportamentos da classe Usuario.
public class Administrador extends Usuario {
    public Administrador (long id, String nome, String email, 
        String login, String senha) {
        super(id, nome, email, login, senha, Perfil.ADMIN);
         
    }
    public Administrador(String login, String senha, Perfil perfil) {
        super(login, senha, perfil);
    }
    public void exibirmenu (){
        System.out.println("Menu do Administrador");
        System.out.println("1. Gerenciar Usuários");
        System.out.println("2. Gerenciar Empréstimos");
        System.out.println("3. Gerenciar Materiais");
        System.out.println("4. Sair");
    }
    
}
