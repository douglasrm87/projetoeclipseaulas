package padroesdeprojeto.abstractfactory.solucao4;

public class CriadorPizzaBReflection  extends CriadorAbstrato  {
	public Pizza criarPizza(String tipo) {
		tipo = "padraoprojeto.aula02.abstractfactory.solucao4." + tipo;
		Class meuObj;
		try {
			meuObj = Class.forName(tipo);
			Pizza pq = (Pizza) meuObj.newInstance();
			return pq;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

}
