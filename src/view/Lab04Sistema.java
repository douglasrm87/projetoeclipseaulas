package view;
import java.util.Scanner;

import model.Lab03ContaCorrente;
import model.Lab04Historico;
public class Lab04Sistema {
	Scanner leia = new Scanner(System.in);
	public static void main(String[] args) {
		new Lab04Sistema().executarLab();
	}
	private void executarLab() {
		int opcao = 0;
		
		while (opcao != 9) {
			
			System.out.println("1 - Cadastramento");
			System.out.println("2 - Saque");
			System.out.println("3 - Deposito");
			System.out.println("4 - Imprimir");
			System.out.println("5 - Extrato");
			System.out.println("8 - Remover Conta Corrente ");
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
			case 5:
				execExtrato();
				break;
			case 8:
				execRemoverContaCorrente ();
				break;
			default:
				break;
			}
			
		}
		leia.close();
	}
	public void execRemoverContaCorrente() {
		System.out.println("Digite o Numero da Agencia"); 
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta");
		int conta = leia.nextInt();
		Lab03ContaCorrente cc = new Lab03ContaCorrente(agencia, conta);
		cc.removerArquivo();
		
	}
	public void execExtrato() {
		System.out.println("Digite o Numero da Agencia"); 
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta");
		int conta = leia.nextInt();
		Lab04Historico hist = new Lab04Historico(agencia,conta);
		hist.imprimir();
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
			Lab03ContaCorrente myConta = 
					new Lab03ContaCorrente(agencia,conta,nome,saldo);
			myConta.gravar();
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
			Lab03ContaCorrente myConta = 
					new Lab03ContaCorrente(agencia,conta);
			System.out.println("Saldo atual: " + myConta.getSaldo() );
			int ret = myConta.sacar (val);
			if (ret == 1) {
				System.out.println("Saque realizado com sucesso.");
				myConta.gravar();
				Lab04Historico hist = new Lab04Historico(agencia,conta);
				hist.gravar(1, val);
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
			Lab03ContaCorrente myConta = 
					new Lab03ContaCorrente(agencia,conta);
			System.out.println("Saldo atual: " + myConta.getSaldo() );
			
			myConta.deposito(val);
			myConta.gravar();
			System.out.println("Deposito realizado com sucesso.");
			Lab04Historico hist = new Lab04Historico(agencia,conta);
			hist.gravar(2, val);
		}
	}
	public void execConsulta() {

		System.out.println("Digite o Numero da Agencia: "); 
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		Lab03ContaCorrente myConta = 
				new Lab03ContaCorrente(agencia,conta);
		myConta.imprimir();

	}
}
