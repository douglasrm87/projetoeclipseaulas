package padroesdeprojeto.abstractfactory.solucao4;

public class CriadorPizzaB  extends CriadorAbstrato  {
	public Pizza criarPizza(String tipo) {
		Pizza pq = null;
		if ("queijo".equals(tipo)) {
			pq = new PizzaQueijoB();
		} else if ("camarao".equals(tipo)) {
			pq = new PizzaCamaraoB();
		} else if ("chocolate".equals(tipo)) {
			pq = new PizzaChocolateB();
		} else if ("calabresa".equals(tipo)) {
			pq = new PizzaCalabresaB();
		}
		return pq;
	}

}
