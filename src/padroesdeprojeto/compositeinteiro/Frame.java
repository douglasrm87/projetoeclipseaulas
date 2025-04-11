package padroesdeprojeto.compositeinteiro;
public class Frame extends CompositeDocumentElement {
	// Retorna a quantidade de caracteres que a classe Frame contribui
	@Override
	public int apresentarDocumento() {
		System.out.println("Somando 5");
		super.apresentarDocumento();
		return 5;
	} 
}  
