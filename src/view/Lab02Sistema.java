package view;
import java.util.Scanner;
// precisa importar pois esta em outro pacote.
import model.Lab02ContaCorrente;
public class Lab02Sistema {
	// criando o objeto myConta. 
	// Segundo conceito abordado: Objeto.
	Lab02ContaCorrente myConta = new Lab02ContaCorrente();
	Scanner leia = new Scanner(System.in);
	public static void main(String[] args) {
		new Lab02Sistema().executarLab();
	}
	private void executarLab() {
		int opcao = 0;
		
		while (opcao != 9) {
			
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
		leia.close();
	}
	public void execCadastramento() {
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
			this.myConta.setNumAge (agencia);
			this.myConta.setNumConta(conta);
			this.myConta.setNome(nome);
			this.myConta.setSaldo(saldo);
			System.out.println("Cadastro realizado com sucesso.");
		}
	}
	public void execSaque() {
		System.out.println("Digite o Numero da Agencia: "); 
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		System.out.println("Digite o valor do saque: ");
		double val = leia.nextDouble();
		System.out.println("Confirma saque(S/N):");
		String saq = leia.next();
		if (saq.equalsIgnoreCase("s")){
			int ret = this.myConta.sacar (val);
			if (ret == 1) {
				System.out.println("Saque realizado com sucesso.");
			}
			else {
				System.out.println("Saldo insuficiente.");
			}
		}
	}
	public void execDeposito() {
		System.out.println("Digite o Numero da Agencia: "); 
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		System.out.println("Digite o valor de deposito: ");
		double val = leia.nextDouble();
		System.out.println("Confirma deposito(S/N):");
		String saq = leia.next();
		if (saq.equalsIgnoreCase("s")){
			this.myConta.deposito(val);
			System.out.println("Deposito realizado com sucesso.");
		}
	}
	public void execConsulta() {
		this.myConta.imprimir();
	}
}
