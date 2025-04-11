package padroesdeprojeto.bridge.livraria;

public class Principal {
	public static void main(String[] args) {
	    LivrariaAbstrata textoPlano = new Livro(new FormatoPlain());
	    textoPlano.persistir();
	    
	    LivrariaAbstrata xml = new Livro(new FormatoXML());
	    xml.persistir();

	}
}
