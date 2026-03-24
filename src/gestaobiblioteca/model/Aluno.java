package gestaobiblioteca.model;

public class Aluno extends Usuario {
	public Aluno(long id, String nome, String email, 
        String login, String senha) {
        super(id, nome, email, login, senha, Perfil.ALUNO);
		 
	}
        public Aluno(String login, String senha, Perfil perfil) {
                super(login, senha, perfil);
        }   
        public void exibirmenu (){
                System.out.println("Menu do Aluno");
                System.out.println("1. Visualizar Materiais Disponíveis");
                System.out.println("2. Submeter trabalho");
                System.out.println("3. Visualizar Empréstimos Ativos");
                System.out.println("4. Sair");
        }
      
}
