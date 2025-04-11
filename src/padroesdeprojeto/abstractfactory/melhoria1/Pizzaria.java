package padroesdeprojeto.abstractfactory.melhoria1;

public class Pizzaria {
	public void montarPizza(String tipo) {
		if ("queijo".equals(tipo)) {
			PizzaQueijo pq = new PizzaQueijo();
			pq.assar();
			pq.embalar();
		} else if ("camarao".equals(tipo)) {
			PizzaCamarao pq = new PizzaCamarao();
			pq.assar();
			pq.embalar();
		} else if ("chocolate".equals(tipo)) {
			PizzaChocolate pq = new PizzaChocolate();
			pq.assar();
			pq.embalar();
		} else if ("calabresa".equals(tipo)) {
			PizzaCalabresa pq = new PizzaCalabresa();
			
		}
	}
}
