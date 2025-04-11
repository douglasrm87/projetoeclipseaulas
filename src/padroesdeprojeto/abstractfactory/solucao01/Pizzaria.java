package padroesdeprojeto.abstractfactory.solucao01;

public class Pizzaria {
	public void montarPizza(String tipo) {
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
		if (pq != null) {
			pq.assar();
			pq.embalar();
		}

	}
}
