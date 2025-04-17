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
import model.Lab05ContaCorrenteEspecialBD;

public class Lab06istemaBancoDados	 {
	// criando o objeto myConta.

	public static void main(String[] args) {
		new Lab06istemaBancoDados().executarLab();
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
		// Sétimo conceito: Polimorfismo
		// Apenas uma referência para a classe
		Lab03ContaCorrenteBancoDados myContaRef = null;
	
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
					//Sétimo conceito: Polimorfismo
					// Primeira forma - polimorfismo - muitas formas
					myContaRef = new Lab05ContaCorrenteEspecialBD(agencia, conta, nome, saldo, limite);
					InsereDados ins = new InsereDados();
					ins.inserirDados(con, myContaRef);
					InsereDadosCCEspecial insE = new InsereDadosCCEspecial();
					// Foi necessário utilizar downcasting
					insE.inserirDados(con, (Lab05ContaCorrenteEspecialBD)myContaRef);
				}
				else {
					//Sétimo conceito: Polimorfismo
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
		System.out.println("Digite o Numero da Agencia: ");
		int agencia = leia.nextInt();
		System.out.println("Digite o Numero da Conta: ");
		int conta = leia.nextInt();
		System.out.println("Digite o valor do saque: ");
		double val = leia.nextDouble();
		System.out.println("Confirma saque(S/N):");
		String saq = leia.next();
		if (saq.equalsIgnoreCase("s")) {
			Lab03ContaCorrenteBancoDados myConta;
			if (agencia >= 5000)
				myConta = new Lab03ContaCorrenteBancoDados(agencia, conta);
			else
				myConta = new Lab05ContaCorrenteEspecialBD(agencia, conta);
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
		// Apenas uma referência para a classe
		Lab03ContaCorrenteBancoDados myContaRef;
				
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
		// Apenas uma referência para a classe
		Lab03ContaCorrenteBancoDados myContaRef;
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
