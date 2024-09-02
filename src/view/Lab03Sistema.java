package view;
import java.util.Scanner;
// precisa importar pois esta em outro pacote.
import model.Lab02ContaCorrente;
import model.Lab03ContaCorrente;
public class Lab03Sistema {
	// criando o objeto myConta. 
	
	
	public static void main(String[] args) {
		new Lab03Sistema().executarLab();
	}
	private void executarLab() {
		int opcao = 0;
		while (opcao != 9) {
			Scanner leia = new Scanner(System.in);
			System.out.println("1 - Cadastramento");
			System.out.println("2 - Saque");
			System.out.println("3 - Deposito");
			System.out.println("4 - Imprimir");
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
			case 4:
				execConsulta();
				break;
			default:
				break;
			}
		}
	}
	public void execCadastramento() {
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
			Lab03ContaCorrente myConta = 
					new Lab03ContaCorrente(agencia,conta,nome,saldo);
			myConta.gravar();
			System.out.println("Cadastro realizado com sucesso.");
		}
	}
	public void execSaque() {
		Scanner leia = new Scanner (System.in);
		System.out.println("Digite o Numero da Agencia: "); 
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		System.out.println("Digite o valor do saque: ");
		double val = leia.nextDouble();
		System.out.println("Confirma saque(S/N):");
		String saq = leia.next();
		if (saq.equalsIgnoreCase("s")){
			Lab03ContaCorrente myConta = 
					new Lab03ContaCorrente(agencia,conta);
			System.out.println("Saldo atual: " + myConta.getSaldo() );
			int ret = myConta.sacar (val);
			if (ret == 1) {
				System.out.println("Saque realizado com sucesso.");
				myConta.gravar();
			}
			else {
				System.out.println("Saldo insuficiente.");
			}
		}
	}
	public void execDeposito() {
		Scanner leia = new Scanner (System.in);
		System.out.println("Digite o Numero da Agencia: "); 
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		System.out.println("Digite o valor de deposito: ");
		double val = leia.nextDouble();
		System.out.println("Confirma deposito(S/N):");
		String saq = leia.next();
		if (saq.equalsIgnoreCase("s")){
			Lab03ContaCorrente myConta = 
					new Lab03ContaCorrente(agencia,conta);
			System.out.println("Saldo atual: " + myConta.getSaldo() );
			
			myConta.deposito(val);
			myConta.gravar();
			System.out.println("Deposito realizado com sucesso.");
		}
	}
	public void execConsulta() {
		Scanner leia = new Scanner (System.in);
		System.out.println("Digite o Numero da Agencia: "); 
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		Lab03ContaCorrente myConta = 
				new Lab03ContaCorrente(agencia,conta);
		myConta.imprimir();
	}
}
