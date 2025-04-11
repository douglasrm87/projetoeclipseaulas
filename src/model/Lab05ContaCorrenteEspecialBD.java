package model;

import java.io.File;

// Lab05ContaCorrenteEspecial é uma especialização de Lab03ContaCorrenteBancoDados
// Sexto conceito: Herança.
public class Lab05ContaCorrenteEspecialBD extends Lab03ContaCorrenteBancoDados {

	private double limiteCredito = 0.0;

	public Lab05ContaCorrenteEspecialBD(int ag, int cta) {
		// Chamando o construtor da super classe.
		super(ag, cta);
	}

	@Override
	public String toString() {
		System.out.println(super.toString());
		return "Lab05ContaCorrenteEspecial [limiteCredito=" + limiteCredito + "]";
	}

	public void imprimir() {
		super.imprimir();
		System.out.println("Limite de Crédito: " + this.limiteCredito);
	}

	public Lab05ContaCorrenteEspecialBD(int ag, int ca, String nome, double saldo,
			double limite) {
		super(ag, ca, nome, saldo);
		this.limiteCredito = limite;

	}

	// Mesma assinatura da super classe.
	// Aqui temos override
	public int sacar(double v) {
		if (getSaldo() + this.limiteCredito >= v) {
			setSaldo(getSaldo() - v);
			return 1; // deu certo
		}
		return 0;// deu errado. Faltou din din :).
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public boolean removerArquivo() {
		super.removerArquivo();
		File tArq1;
		tArq1 = new File(this.getNumAge() + "." + this.getNumConta() + ".esp");
		tArq1.delete();
		return true;
	}

}
