package padroesdeprojeto.compositeinteiro;
class Page extends CompositeDocumentElement { 
	// Retorna a quantidade de caracteres que a classe Page contribui
	@Override
	public int apresentarDocumento() {
		System.out.println("Somando 45");
		return 45;
	}
}  







