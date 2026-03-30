package pilha;

public class PilhaEncadeada {
    private No topo;
    private int tamanho;

    public PilhaEncadeada() {
        this.topo = null;
        this.tamanho = 0;
    }

    public void push(int valor) {
        No novoNo = new No(valor);
        // Aqui temos a efetivação do encadeamento para uma Pilha, 
        // o novo nó aponta para o topo atual que será o objeto de BAIXO do novo nó, 
        // e o topo passa a ser o novo nó.
        novoNo.setProximo(topo); 
        topo = novoNo;
        tamanho++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Pilha vazia");
        }
        int valor = topo.getValor();
        topo = topo.getProximo();
        tamanho--;
        return valor;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Pilha vazia");
        }
        return topo.getValor();
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public int size() {
        return tamanho;
    }
    
}
