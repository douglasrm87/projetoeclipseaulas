package model;

// Lab05ContaCorrenteEspecial é uma especialização de Lab03ContaCorrenteBancoDados

public class Lab05ContaCorrenteEspecial 
	extends Lab03ContaCorrenteBancoDados{

	private double limiteCredito = 0.0;
	
	public Lab05ContaCorrenteEspecial(int ag, int cta) {
		// Chamando o construtor da super classe.
		super(ag, cta);
	}
	


	public Lab05ContaCorrenteEspecial 
	(int ag, int ca, String nome, double saldo , 
			double limite) {
		super(ag, ca, nome, saldo);
		this.limiteCredito = limite;
		 
	}



	// Mesma assinatura da super classe.
	// Aqui temos override
	public int sacar(double v) {
		if (getSaldo() + this.limiteCredito  >= v) {
			setSaldo(getSaldo() - v);
			return 1; // deu certo
		}
		return 0;// deu errado. Faltou din din :).
	}
	

}
