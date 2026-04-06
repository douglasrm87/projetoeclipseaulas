package dukerevisao;

public class J06Camisa extends J05Produto {
    private String tamanho;

    public J06Camisa(String nome, double preco, String tamanho) {
        super(nome, preco);
        this.tamanho = tamanho;
    }

    @Override // [Anotação] - Indica sobreposição de método
    public void exibirDetalhes() {
        System.out.println("Camisa: " + getNome() + " | Tamanho: " + tamanho);
    }
}
