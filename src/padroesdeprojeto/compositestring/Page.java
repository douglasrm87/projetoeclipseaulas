package padroesdeprojeto.compositestring;

class Page extends CompositeDocumentElement { 
	String str;
	public Page(String str){
		this.str=str;
	}
	// Retorna a quantidade de caracteres que a classe Page contribui
	@Override
	public int apresentarDocumento() {
		return 45;
	}
	@Override
	public String apresentarDocumentoString() {
		return str;
	}
}  







