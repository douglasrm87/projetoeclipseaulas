package fila;

public class FilaEncadeada {
    private No topo; // Sempre aponta para o primeiro elemento da fila.
    private int tamanho;

    public FilaEncadeada() {
        this.topo = null;
        this.tamanho = 0;
    }

    public void inserir(int valor) {
        No novoNo = new No(valor);
        if (isEmpty()) {
            topo = novoNo; // Se a fila estiver vazia, o novo nó se torna o topo.
        } else {
            // Salva o valor do topo para navegar até o final da fila.
            No temp = topo;  
            // Navega na fila até o último nó.
            while (temp.getProximo() != null) {
                temp = temp.getProximo();
            }
            // Efetiva o encadeamento para um novo elemento a frente.
            temp.setProximo(novoNo);
        }
        tamanho++;
    }
    private No getUltimoNo() {
        No temp = topo;
        while (temp.getProximo() != null) {
            temp = temp.getProximo();
        }
        return temp;
    }
    public int remover() {
        if (isEmpty()) {
            throw new RuntimeException("Fila vazia!");
        }
        int valor = topo.getValor();
        topo = topo.getProximo();
        tamanho--;
        return valor;
    }
    private No getPenultimoNo() {
        No temp = topo;
        while (temp.getProximo() != null && temp.getProximo().getProximo() != null) {
            temp = temp.getProximo();
        }
        return temp;
    }
    public boolean isEmpty() {
        return tamanho == 0;
    }
    
}
