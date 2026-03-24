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
    public Professor(String login, String senha, Perfil perfil) {
        super(login, senha, perfil);
    }
    public void exibirmenu (){
        System.out.println("Menu do Professor");
        System.out.println("1. Gerenciar Disciplinas");
        System.out.println("2. Gerenciar Materiais");
        System.out.println("3. Gerenciar Empréstimos");
        System.out.println("4. Sair");
    }
}
