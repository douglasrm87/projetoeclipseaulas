package padroesdeprojeto.compositeinteiro;
public class Principal2 {
	public static void main(String[] args) {
		CompositeDocumentElement meuDocumento = new Document();
		
		Frame quadro1 = new Frame();
		Frame quadro2 = new Frame();
		
		meuDocumento.addChild(quadro2);
		
		Coluna col1 = new Coluna();
		Coluna col2 = new Coluna();
		Coluna col3 = new Coluna();
		
		quadro1.addChild(col1);
		quadro1.addChild(col2);
		quadro1.addChild(col3);
		meuDocumento.addChild(quadro1);
		int qdadeCaracteres = meuDocumento.apresentarDocumento();
		System.out.println(qdadeCaracteres);
		System.out.println(meuDocumento.lenPArte);
	}

}
