package padroesdeprojeto.compositeinteiro;
public class Principal {
	public static void main(String[] args) {
		CompositeDocumentElement meuDocumento = new Document();
		
		Frame quadro1 = new Frame();
		Frame quadro2 = new Frame();
		meuDocumento.addChild(quadro1);
		meuDocumento.addChild(quadro2);
		
		Coluna col1 = new Coluna();
		Coluna col2 = new Coluna();
		Coluna col3 = new Coluna();
		
		meuDocumento.addChild(col1);
		meuDocumento.addChild(col2);
		meuDocumento.addChild(col3);

		Image img1 = new Image();
		meuDocumento.addChild(img1);

		
		int qdadeCaracteres = meuDocumento.apresentarDocumento();
		System.out.println(qdadeCaracteres);
	}

}
