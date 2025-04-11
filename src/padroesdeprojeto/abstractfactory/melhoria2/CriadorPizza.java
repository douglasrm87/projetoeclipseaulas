package padroesdeprojeto.abstractfactory.melhoria2;

public class CriadorPizza {
	public Pizza criarPizza(String tipo) {
		Pizza pq = null;
		if ("queijo".equals(tipo)) {
			pq = new PizzaQueijo();
		} else if ("camarao".equals(tipo)) {
			pq = new PizzaCamarao();
		} else if ("chocolate".equals(tipo)) {
			pq = new PizzaChocolate();
		} else if ("calabresa".equals(tipo)) {
			pq = new PizzaCalabresa();
		}
		return pq;
	}

}
