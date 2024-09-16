package model;

public class Lab03ContaCorrenteBancoDados {
	private int numAge = 0;
	private int numConta;
	private String nome;
	private double saldo;

	// Criar o método construtor - Pesquisa
	public Lab03ContaCorrenteBancoDados(int ag, int cta) {
		this.numAge = ag;
		this.numConta = cta;
	}
	public Lab03ContaCorrenteBancoDados (int ag,int ca,String nome,double saldo) {
		this.numAge = ag;
		this.numConta = ca;
		this.nome = nome;
		this.saldo = saldo;
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
