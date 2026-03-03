package aula01;

import java.util.Scanner;

// Nome da classe. Também no do programa.
public class Aluno {
	private String nome;
	public void inserirNome(String n) {
		// código fonte para gravar o nome no BD.
		this.nome = n;
	}
	public String recuperarNome() {
		// código fonte para ler o nome no BD.
		return this.nome;
	}
	// Tela gráfica simulando a interface do usuário.
	public static void main(String[] args) {
		Aluno obj1 = new Aluno();
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("\nDigite o nome do aluno: ");
			String nome = sc.nextLine();
			obj1.inserirNome(nome);
		}
		System.out.println("Bem Vindo "+obj1.recuperarNome()+" Ao curso de Java!");

		/*Aluno obj2 = new Aluno();
		obj2.inserirNome("Lucas");
		System.out.println("Saida: "+obj2.recuperarNome());*/
	}
}
