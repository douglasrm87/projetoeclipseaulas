package padroesdeprojeto.compositestring;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeDocumentElement extends DocumentElementTopoDocumento {
	// Collection de objetos filhos
	private List children = new ArrayList<>();
	// The cached value from the previous call to getCharLength or -1 to
	// indicate that charLength does not contain a cached value.
	// private int cachedCharLength = -1;

	public DocumentElementTopoDocumento getChild(int index) {
		return (DocumentElementTopoDocumento) children.get(index);
	}

	/**
	 * Make the given DocumentElement a child of this object.
	 */
	public synchronized void addChild(DocumentElementTopoDocumento child) {
		synchronized (child) {
			children.add(child);
			child.parent = this;
		} // synchronized
	}

	/**
	 * Make the given DocumentElement NOT a child of this object.
	 */
	public synchronized void removeChild(DocumentElementTopoDocumento child) {
		synchronized (child) {
			if (this == child.parent)
				child.parent = null;
			children.remove(child);
		} // synchronized
	}

	/**
	 * Return the number of characters that this object contains.
	 */
	public int apresentarDocumento() {
		int len = 0;
		for (int i = 0; i < children.size(); i++) {
			len += ((DocumentElementTopoDocumento) children.get(i)).apresentarDocumento();
		}
		return len;
	}
	public String apresentarDocumentoString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < children.size(); i++) {
			str.append(((DocumentElementTopoDocumento) children.get(i)).apresentarDocumentoString());
			str.append("\n");
		}
		return str.toString();
	}
}