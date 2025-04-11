package padroesdeprojeto.compositestring;
public class Caracter extends DocumentElementTopoDocumento {
	String str;
	public Caracter(String str){
		this.str=str;
	}
	// Retorna a quantidade de caracteres que a classe Character contribuir
	@Override
	public int apresentarDocumento() {
		return 1;
	}
	@Override
	public String apresentarDocumentoString() {
		return str;
	}
} 

