package model;

public class Lab02ContaCorrente {
	private int numAge = 0; 
	private int numConta;
	private String nome;
	private double saldo; 
	
	public int sacar(double v) {
		if (this.saldo >= v) {
			this.saldo = this.saldo - v;
			return 1; // deu certo
		}
		return 0;// deu errado. Faltou din din :).
	}
	public void deposito (double pValor) {
		this.saldo = this.saldo + pValor;
	}
	public void imprimir () {
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
