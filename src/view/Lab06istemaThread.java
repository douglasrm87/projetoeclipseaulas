package view;

/*
git add .
git commit -m "lab06"
git push -u origin main

*/
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import bancodados.AtualizaDados;
import bancodados.ConexaoBancoDados;
import bancodados.InsereDados;
import bancodados.InsereDadosCCEspecial;
import bancodados.SelecionaDados;
import bancodados.SelecionaDadosCCEspecial;
// precisa importar pois esta em outro pacote.
import model.Lab03ContaCorrenteBancoDados;
import model.Lab05ContaCorrenteEspecial;
import progparalela.VarrerContaNegativa;

public class Lab06istemaThread {
	// criando o objeto myConta.

	public static void main(String[] args) {
		new Lab06istemaThread().executarLab();
	}

	private void executarLab() {
		VarrerContaNegativa scanConta = new VarrerContaNegativa();
		scanConta.start();
		
		int opcao = 0;
		while (opcao != 9) {
			Scanner leia = new Scanner(System.in);
			System.out.println("1 - Cadastramento");
			System.out.println("2 - Saque");
			System.out.println("3 - Deposito");
			System.out.println("4 - Imprimir");
			System.out.println("5 - Interromper Varredura");
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
				scanConta.interrupt();
				break;
			case 9:
				scanConta.interrupt();
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}

	public void execCadastramento() {
		// Apenas uma referência para a classe
		Lab03ContaCorrenteBancoDados myContaRef = null;

		Scanner leia = new Scanner(System.in);
		System.out.println("Digite o Numero da Agencia: ");
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		System.out.println("Digite o Nome do Cliente: ");
		String nome = leia.next();
		System.out.println("Digite o Saldo: ");
		double saldo = leia.nextDouble();

		double limite = 0.0;
		if (agencia >= 5000) {
			System.out.println("Digite o limite: ");
			limite = leia.nextDouble();
		}
		System.out.println("Confirma cadastramento(S/N):");
		String cad = leia.next();
		if (cad.equalsIgnoreCase("s")) {
			try {
				ConexaoBancoDados conexPost = new ConexaoBancoDados();
				Connection con = conexPost.conectarBanco();
				if (agencia >= 5000) {
					// Primeira forma - polimorfismo - muitas formas
					myContaRef = new Lab05ContaCorrenteEspecial(agencia, conta, nome, saldo, limite);
					InsereDados ins = new InsereDados();
					ins.inserirDados(con, myContaRef);
					InsereDadosCCEspecial insE = new InsereDadosCCEspecial();
					// Foi necessário utilizar downcasting
					insE.inserirDados(con, (Lab05ContaCorrenteEspecial) myContaRef);
				} else {
					// Segunda forma
					myContaRef = new Lab03ContaCorrenteBancoDados(agencia, conta, nome, saldo);
					InsereDados ins = new InsereDados();
					ins.inserirDados(con, myContaRef);
				}
				System.out.println("Cadastro realizado com sucesso.");
			} catch (Exception e) {
				System.out.println("Problemas para gravar os dados. ");
				System.out.println("Mensagem: " + e.getMessage());
			}
		}
	}

	public void execSaque() {
		// Apenas uma referência para a classe
		Lab03ContaCorrenteBancoDados myContaRef;

		Scanner leia = new Scanner(System.in);
		System.out.println("Digite o Numero da Agencia: ");
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		System.out.println("Digite o valor do saque: ");
		double val = leia.nextDouble();
		System.out.println("Confirma saque(S/N):");
		String saq = leia.next();
		if (saq.equalsIgnoreCase("s")) {
			ConexaoBancoDados conexPost = new ConexaoBancoDados();
			SelecionaDados sel = new SelecionaDados();
			Connection con = conexPost.conectarBanco();

			if (agencia >= 5000) {
				myContaRef = new Lab05ContaCorrenteEspecial(agencia, conta);
				sel.selecionarLimiteSaldo(con, (Lab05ContaCorrenteEspecial)myContaRef);
				System.out.println("Limite atual: " + ((Lab05ContaCorrenteEspecial)myContaRef).getLimiteCredito());
			} else {
				myContaRef = new Lab03ContaCorrenteBancoDados(agencia, conta);
				sel.selecionarDados(con, myContaRef);
			}
			System.out.println("Saldo atual: " + myContaRef.getSaldo());

			int ret = myContaRef.sacar(val);

			if (ret == 1) {
				AtualizaDados atu = new AtualizaDados();
				atu.atualizarDados(con, myContaRef);
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Saque realizado com sucesso.");
				// Atualizar - Update
			} else {
				System.out.println("Saldo insuficiente.");
			}
		}
	}

	public void execDeposito() {
		// Apenas uma referência para a classe
		Lab03ContaCorrenteBancoDados myContaRef;

		Scanner leia = new Scanner(System.in);
		System.out.println("Digite o Numero da Agencia: ");
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		System.out.println("Digite o valor de deposito: ");
		double val = leia.nextDouble();
		System.out.println("Confirma deposito(S/N):");
		String saq = leia.next();
		if (saq.equalsIgnoreCase("s")) {
			Lab03ContaCorrenteBancoDados myConta = new Lab03ContaCorrenteBancoDados(agencia, conta);
			// Selecionar
			ConexaoBancoDados conexPost = new ConexaoBancoDados();
			SelecionaDados sel = new SelecionaDados();
			Connection con = conexPost.conectarBanco();
			// Faltando retornar os dados selecionados.
			sel.selecionarDados(con, myConta);
			System.out.println("Saldo atual: " + myConta.getSaldo());

			myConta.deposito(val);
			// Atualizar - Update
			AtualizaDados atu = new AtualizaDados();
			atu.atualizarDados(con, myConta);
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Deposito realizado com sucesso.");
		}
	}

	public void execConsulta() {
		// Apenas uma referência para a classe
		Lab03ContaCorrenteBancoDados myContaRef;

		Scanner leia = new Scanner(System.in);
		System.out.println("Digite o Numero da Agencia: ");
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		Lab03ContaCorrenteBancoDados myConta = new Lab03ContaCorrenteBancoDados(agencia, conta);
		// Selecionar
		ConexaoBancoDados conexPost = new ConexaoBancoDados();
		SelecionaDados sel = new SelecionaDados();
		Connection con = conexPost.conectarBanco();
		// Faltando retornar os dados selecionados.
		sel.selecionarDados(con, myConta);

		SelecionaDadosCCEspecial selE = new SelecionaDadosCCEspecial();
		selE.selecionarDados(con, new Lab05ContaCorrenteEspecial(agencia, conta));

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Consulta realizada com sucesso.");
	}
}
