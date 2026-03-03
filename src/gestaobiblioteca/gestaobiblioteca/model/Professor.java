// A) Classe Usuario que é a superclasse para 
// Administrador, Professor e Aluno
// B) Esta classe pode ter atributos e métodos 
// comuns a todos os tipos de usuários, 
// como nome, email, senha, etc.
// C) A palavra-chave abstract é usada para 
// indicar que esta classe é abstrata, ou seja,
// não pode ser instanciada diretamente, mas serve
// como base para as subclasses que a estendem.

package gestaobiblioteca.model;

public class Professor extends Usuario {
	public Professor (long id, String nome, String email, 
        String login, String senha) {
        super(id, nome, email, login, senha, Perfil.PROFESSOR);
         
    } 
}