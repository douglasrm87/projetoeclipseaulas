package padroesdeprojeto.abstractfactory.solucao4;

public class Pizzaria {
	CriadorAbstrato criador = null;

	public void montarPizza(String tipo) {

		Pizza pizza = this.criador.criarPizza(tipo);
		if (pizza != null) {
			pizza.assar();
			pizza.embalar();
			pizza.rechear();
			pizza.adicionarBorda();
			pizza.cortarPedacos();

		}
	}

	public Pizzaria() {
		super();
		this.criador = new CriadorPizzaBReflection();
	}

}
