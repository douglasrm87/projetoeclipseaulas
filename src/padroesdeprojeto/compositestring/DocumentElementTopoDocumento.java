package padroesdeprojeto.compositestring;

public abstract class DocumentElementTopoDocumento {
	CompositeDocumentElement parent; // this object's container

	//Retorna o objeto pai, ou nulo caso n�o tenha
	public CompositeDocumentElement getParent() {
		return parent;
	}
	// m�todo abstrato que dever� ser implementado em todas as sub-classes
 	public abstract int apresentarDocumento();
 	public abstract String apresentarDocumentoString();
}  
