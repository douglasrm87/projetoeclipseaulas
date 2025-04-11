package padroesdeprojeto.abstractfactory.solucao4;

import java.util.Scanner;

public class FrenteCaixa {
	public static void main(String[] args) {
		System.out.println("Digitar sua escolha de pizza");
		Scanner leia = new Scanner(System.in);
		String op = leia.next();
		Pizzaria p = new Pizzaria();
		p.montarPizza(op);
		
		leia.close();
		
	}

}
