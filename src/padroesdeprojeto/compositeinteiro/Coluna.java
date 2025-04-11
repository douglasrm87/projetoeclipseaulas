package padroesdeprojeto.compositeinteiro;
class Coluna extends CompositeDocumentElement { 
	// Retorna a quantidade de caracteres que a classe Column contribui
	@Override
	public int apresentarDocumento() {
		System.out.println("Somando 12");
		return 12;
	}
} 

