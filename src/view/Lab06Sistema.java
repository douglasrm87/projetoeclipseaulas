package view;

import java.util.Scanner;

import model.Lab03ContaCorrente;
import model.Lab04Historico;
import model.Lab05ContaCorrenteEspecial;
public class Lab06Sistema {
	Scanner leia = new Scanner(System.in);
	public static void main(String[] args) {
		new Lab06Sistema().executarLab();
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
		// 2 - Polimorfismo
		Lab03ContaCorrente myContaRef = null;

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
			// 1 - Polimorfismo
            if (agencia >=5000){
                double limite = 0.0;
                System.out.println("Digite o Limite de Credito");
                limite = leia.nextDouble();
                myContaRef = new Lab05ContaCorrenteEspecial(agencia,conta,nome,saldo,limite);
                myContaRef.gravar();
                System.out.println("Cadastro realizado com sucesso.");

            }else{ 
                 // Cria o objeto conta corrente
				 myContaRef = new Lab03ContaCorrente(agencia,conta,nome,saldo);
				 myContaRef.gravar();
	    		System.out.println("Cadastro realizado com sucesso.");
            }
		}
	}
	public void execSaque() {
		/*
		 * You're right, but I think it's important to specify that the polymorphism it's not only related to inheritance but also, if not most, about interfaces.
		 */
		// 1 - Polimorfismo
		Lab03ContaCorrente myContaRef = null;

		System.out.println("Digite o Numero da Agencia: "); 
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		System.out.println("Digite o valor do saque: ");
		double val = leia.nextDouble();
		System.out.println("Confirma saque(S/N):");
		String saq = leia.next();
		if (saq.equalsIgnoreCase("s")){
			// 2 - Polimorfismo
            if (agencia >= 5000) {
                myContaRef = new Lab05ContaCorrenteEspecial(agencia, conta);
            } else {
                myContaRef = new Lab03ContaCorrente(agencia, conta);
            }
			System.out.println("Saldo atual: " + myContaRef.getSaldo() );
			int ret = myContaRef.sacar (val);
			if (ret == 1) {
				System.out.println("Saque realizado com sucesso.");
				myContaRef.gravar();
				Lab04Historico hist = new Lab04Historico(agencia,conta);
				hist.gravar(1, val);
			}
			else {
				System.out.println("Saldo insuficiente.");
			}
		}
	}
	public void execDeposito() {
		// 1 - Polimorfismo
		Lab03ContaCorrente myContaRef = null;

		System.out.println("Digite o Numero da Agencia: "); 
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		System.out.println("Digite o valor de deposito: ");
		double val = leia.nextDouble();
		System.out.println("Confirma deposito(S/N):");
		String saq = leia.next();
		if (saq.equalsIgnoreCase("s")){
			// 2 - Polimorfismo
			if (agencia >= 5000) {
                myContaRef = new Lab05ContaCorrenteEspecial(agencia, conta);
            } else {
                myContaRef = new Lab03ContaCorrente(agencia, conta);
            }
			System.out.println("Saldo atual: " + myContaRef.getSaldo() );
			
			myContaRef.deposito(val);
			myContaRef.gravar();
			System.out.println("Deposito realizado com sucesso.");
			Lab04Historico hist = new Lab04Historico(agencia,conta);
			hist.gravar(2, val);
		}
	}
	public void execConsulta() {
		// 1 - Polimorfismo
		Lab03ContaCorrente myContaRef = null;

		System.out.println("Digite o Numero da Agencia: "); 
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		if (agencia >= 5000) {
			// 2 - Polimorfismo
			myContaRef = new Lab05ContaCorrenteEspecial(agencia, conta);
		} else {
			// 2 - Polimorfismo
			myContaRef = new Lab03ContaCorrente(agencia, conta);
		}
		myContaRef.imprimir();

	}
}

