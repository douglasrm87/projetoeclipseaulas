package padroesdeprojeto.abstractfactory.melhoria2;

public class Pizzaria {
	public void montarPizza(String tipo) {
		CriadorPizza cp = new CriadorPizza();
		Pizza pizza = cp.criarPizza(tipo);
		if (pizza != null) {
			pizza.assar();
			pizza.embalar();
			pizza.rechear();
			pizza.adicionarBorda();
			pizza.cortarPedacos();
			
		}
	}
}
