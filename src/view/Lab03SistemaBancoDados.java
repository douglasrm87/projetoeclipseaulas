package view;
/*
git add .
git commit -m "lab05"
git push -u origin main

*/
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import bancodados.AtualizaDados;
import bancodados.ConexaoBancoDados;
import bancodados.InsereDados;
import bancodados.SelecionaDados;
// precisa importar pois esta em outro pacote.
import model.Lab03ContaCorrenteBancoDados;

public class Lab03SistemaBancoDados {
	// criando o objeto myConta.

	public static void main(String[] args) {
		new Lab03SistemaBancoDados().executarLab();
	}
	Scanner leia = new Scanner(System.in);
	private void executarLab() {
		int opcao = 0;
		
		while (opcao != 9) {
			
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
		if (cad.equalsIgnoreCase("s")) {
			Lab03ContaCorrenteBancoDados myConta = new Lab03ContaCorrenteBancoDados(agencia, conta, nome, saldo);
			// Gravar - Inserir
			ConexaoBancoDados conexPost = new ConexaoBancoDados();
			InsereDados ins = new InsereDados();
			Connection con = conexPost.conectarBanco();
			ins.inserirDados(con, myConta);
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Problemas ao encerrar a conexão.");
				e.printStackTrace();
			}

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
		if (saq.equalsIgnoreCase("s")) {
			Lab03ContaCorrenteBancoDados myConta = new Lab03ContaCorrenteBancoDados(agencia, conta);
			// Selecionar
			ConexaoBancoDados conexPost = new ConexaoBancoDados();
			SelecionaDados sel = new SelecionaDados();
			Connection con = conexPost.conectarBanco();
			// Faltando retornar os dados selecionados.
			sel.selecionarDados(con, myConta);

			System.out.println("Saldo atual: " + myConta.getSaldo());
			int ret = myConta.sacar(val);
			if (ret == 1) {
				AtualizaDados atu = new AtualizaDados();
				atu.atualizarDados(con, myConta);
				try {
					con.close();
				} catch (SQLException e) {
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
				e.printStackTrace();
			}
			System.out.println("Deposito realizado com sucesso.");
		}
	}

	public void execConsulta() {
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
	}
}
