package padroesdeprojeto.compositestring;

class Image extends DocumentElementTopoDocumento {
	String str;
	public Image(String str){
		this.str=str;
	}
	// Retorna a quantidade de caracteres que a classe Image contribui
	@Override
	public int apresentarDocumento() {
		return 5;
	} 
	@Override
	public String apresentarDocumentoString() {
		return str;
	}
}
