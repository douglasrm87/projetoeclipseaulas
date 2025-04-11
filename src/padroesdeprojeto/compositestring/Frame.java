package padroesdeprojeto.compositestring;

class Frame extends CompositeDocumentElement {
	String str;
	public Frame(String str){
		this.str=str;
	}
	// Retorna a quantidade de caracteres que a classe Frame contribui
	@Override
	public int apresentarDocumento() {
		return 5;
	} 
	@Override
	public String apresentarDocumentoString() {
		return str;
	}
}  
