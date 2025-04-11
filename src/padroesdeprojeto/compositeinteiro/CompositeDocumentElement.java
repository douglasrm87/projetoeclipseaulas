package padroesdeprojeto.compositeinteiro;
import java.util.ArrayList;
import java.util.List;
public abstract class CompositeDocumentElement extends DocumentElementTopoDocumento {
	// Collection de objetos filhos
	private List <DocumentElementTopoDocumento> children = new ArrayList<>();
	public DocumentElementTopoDocumento getChild(int index) {
		return (DocumentElementTopoDocumento) children.get(index);
	}
	// Adicion aum item filho e faz com que o filho esteja conectado ao pai.
	public synchronized void addChild(DocumentElementTopoDocumento child) {
		children.add(child);
	}
	// Retorna o n�mero de caracters que comp�e o documento
	static int lenPArte = 0;
	public int apresentarDocumento() {
		int len = 0;
		for (int i = 0; i < children.size(); i++) {
			len += ((DocumentElementTopoDocumento) children.get(i)).apresentarDocumento();
		}
		lenPArte += len;
		return len;
	}
}
