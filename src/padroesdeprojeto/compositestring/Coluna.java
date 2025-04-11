package padroesdeprojeto.compositestring;

class Coluna extends CompositeDocumentElement {
	String str;
	public Coluna(String str){
		this.str=str;
	}
	// Retorna a quantidade de caracteres que a classe Column contribui
	@Override
	public int apresentarDocumento() {
		return 12;
	}
	@Override
	public String apresentarDocumentoString() {
		return str;
	}
} 

