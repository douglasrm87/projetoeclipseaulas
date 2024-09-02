package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Lab03ContaCorrente {
	private int numAge = 0;
	private int numConta;
	private String nome;
	private double saldo;

	// Criar o método construtor - Pesquisa
	public Lab03ContaCorrente(int ag, int cta) {
		this.numAge = ag;
		this.numConta = cta;
		// Realizar a leitura dos dados de arquivo
		recuperar();

	}
	public Lab03ContaCorrente (int ag,int ca,String nome,double saldo) {
		this.numAge = ag;
		this.numConta = ca;
		this.nome = nome;
		this.saldo = saldo;
	}
	
	public boolean gravar() {
		FileWriter tArq1;
		PrintWriter tArq2;
		try {
			// Operação I - Abrir o aquivo
			tArq1 = new FileWriter(getNumAge() + "." + getNumConta() + ".dat");
			tArq2 = new PrintWriter(tArq1);
			tArq2.println(getNumAge());
			tArq2.println(getNumConta());
			tArq2.println(getNome());
			tArq2.println(getSaldo());
			// Operação II - Fechar o arquivo
			tArq2.close();
			return true;
		}
		catch (IOException tExcept) {
			tExcept.printStackTrace();
			return false;
		}
	}

	private void recuperar() {
		FileReader tArq1;
		BufferedReader tArq2;
		int tQtde = 4;
		try {
			// Operação I - Abrir o arquivo
			tArq1 = new FileReader(getNumAge() + "." + getNumConta() + ".dat");
			tArq2 = new BufferedReader(tArq1);
			// Operação II - Ler atributo/valor e colocar na matriz
			String[] tLinha = new String[tQtde];
			for (int i = 0; i < tQtde; i++) {
				tLinha[i] = tArq2.readLine();
			}
			// Operação III - Fechar o arquivo
			tArq2.close();
			setNumAge(Integer.parseInt(tLinha[0]));
			setNumConta(Integer.parseInt(tLinha[1]));
			setNome(tLinha[2]);
			setSaldo(Double.parseDouble(tLinha[3]));
		} catch (IOException tExcept) {
			System.out.println("Houve algum problema com a carga da sua conta corrente:");
			System.out.println("Mensagem:" + tExcept.getMessage());
			tExcept.printStackTrace();
		}
	}

	public int sacar(double v) {
		if (this.saldo >= v) {
			this.saldo = this.saldo - v;
			return 1; // deu certo
		}
		return 0;// deu errado. Faltou din din :).
	}

	public void deposito(double pValor) {
		this.saldo = this.saldo + pValor;
	}

	public void imprimir() {
		System.out.println("Imprimindo seu Extrato:");
		System.out.println("Agencia:" + this.numAge);
		System.out.println("Conta:" + this.numConta);
		System.out.println("Nome:" + this.nome);
		System.out.println("Saldo:" + this.saldo);
	}

	public int getNumAge() {
		return numAge;
	}

	public void setNumAge(int numAge) {
		this.numAge = numAge;
	}

	public int getNumConta() {
		return numConta;
	}

	public void setNumConta(int numConta) {
		this.numConta = numConta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

}
