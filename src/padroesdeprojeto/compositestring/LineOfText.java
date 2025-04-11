package padroesdeprojeto.compositestring;

class LineOfText extends CompositeDocumentElement {
	String str;
	public LineOfText(String str){
		this.str=str;
	}
	// Retorna a quantidade de caracteres que a classe LineOfText contribui
	@Override
	public int apresentarDocumento() {
		return 54;
	} 
	@Override
	public String apresentarDocumentoString() {
		return str;
	}
} 