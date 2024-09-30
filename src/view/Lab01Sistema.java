// Primeiro conceito foi o de Classe.
package view;
/*
git add .
git commit -m "Threads"
git push -u origin main

*/
import java.util.Scanner;
public class Lab01Sistema {
	public static void main(String[] args) {
		int opcao = 0;
		while (opcao != 9) {
			Scanner leia = new Scanner(System.in);
			System.out.println("1 - Cadastramento");
			System.out.println("2 - Saque");
			System.out.println("3 - Deposito");
			System.out.println("9 - Fim ");
			System.out.println("Digite sua opção: ");
			opcao = leia.nextInt();
			switch (opcao) {
			case 1:
				execCadastramento();
				break;
			case 2:
				execSaque();
				break;
			case 3:
				execDeposito();
				break;
			default:
				break;
			}
		}
	}
	public static void execCadastramento() {
		Scanner leia = new Scanner (System.in);
		System.out.println("Digite o Numero da Agencia"); 
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta");
		int conta = leia.nextInt();
		System.out.println("Digite o Nome do Cliente");
		String nome = leia.next();
		System.out.println("Digite o Saldo");
		double saldo = leia.nextDouble();
		System.out.println("Confirma cadastramento(S/N):");
		String cad = leia.next();
		if (cad.equalsIgnoreCase("s")){
			System.out.println("Cadastro realizado com sucesso.");
		}
	}
	public static void execSaque() {
		Scanner leia = new Scanner (System.in);
		System.out.println("Digite o Numero da Agencia: "); 
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		System.out.println("Digite o valor do saque: ");
		double saldo = leia.nextDouble();
		System.out.println("Confirma saque(S/N):");
		String saq = leia.next();
		if (saq.equalsIgnoreCase("s")){
			System.out.println("Saque realizado com sucesso.");
		}
	}
	public static void execDeposito() {
		Scanner leia = new Scanner (System.in);
		System.out.println("Digite o Numero da Agencia: "); 
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		System.out.println("Digite o valor de deposito: ");
		double saldo = leia.nextDouble();
		System.out.println("Confirma deposito(S/N):");
		String saq = leia.next();
		if (saq.equalsIgnoreCase("s")){
			System.out.println("Deposito realizado com sucesso.");
		}
	}
}
