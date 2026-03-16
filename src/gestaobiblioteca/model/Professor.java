package gestaobiblioteca.model;

import java.util.List;
public class Professor extends Usuario {
    // Aula 04
    private long id;
    private String nome;
    private List<Disciplina> disciplinas;

    // Aula 03
	public Professor (long id, String nome, String email, 
        String login, String senha) {
        super(id, nome, email, login, senha, Perfil.PROFESSOR);
         
    } 
}
