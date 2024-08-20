package view;
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
			if (opcao == 1) {
				execCadastramento();
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
}
