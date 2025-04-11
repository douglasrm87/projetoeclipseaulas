package padroesdeprojeto.compositestring;

public class Principal {

	public static void main(String[] args) {
		Document meuDocumento = new Document();
		
		Frame quadro1 = new Frame("Quadro 01");
		Frame quadro2 = new Frame("Quadro 02");
		meuDocumento.addChild(quadro1);
		meuDocumento.addChild(quadro2);
		
		Coluna col1 = new Coluna("Coluna 01");
		Coluna col2 = new Coluna("Coluna 02");
		Coluna col3 = new Coluna("Coluna 03");
		
		meuDocumento.addChild(col1);
		meuDocumento.addChild(col2);
		meuDocumento.addChild(col3);
		
		String conteudoDocumento = meuDocumento.apresentarDocumentoString();
		System.out.println(conteudoDocumento);
		

	}

}
