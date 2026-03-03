package gestaobiblioteca.model;
    //Construtor serve para criar um novo aluno com os dados forma
    //O construtor é um método especial que é chamado quando um objeto da classe é criado, e é usado para inicializar os atributos do objeto com os valores fornecidos como parâmetros. No caso da classe Aluno, o construtor recebe os parâmetros id, nome, email e senha, e inicializa os atributos correspondentes do objeto Aluno com esses valores. Além disso, o construtor também define o perfil do aluno como ALUNO, indicando que este objeto representa um aluno no sistema.
    //objeto da classe Aluno é criado, e é usado para inicializar os atributos do objeto com os valores fornecidos como parâmetros. No caso da classe Aluno, o construtor recebe os parâmetros id, nome, email e senha, e inicializa os atributos correspondentes do objeto Aluno com esses valores. Além disso, o construtor também define o perfil do aluno como ALUNO, indicando que este objeto representa um aluno no sistema.
    //Ele inicializa os atributos do objeto Aluno com os valores fornecidos como parâmetros, e define o perfil do aluno como ALUNO, indicando que este objeto representa um aluno no sistema.
    //fornecidos como parâmetros, e define o perfil do aluno como ALUNO, indicando que este objeto representa um aluno no sistema.    
public class Aluno extends Usuario {

	public Aluno(long id, String nome, String email, 
        String login, String senha) {
        super(id, nome, email, login, senha, Perfil.ALUNO);		 
	}
}
