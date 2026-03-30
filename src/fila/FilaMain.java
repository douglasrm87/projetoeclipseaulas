package fila;

public class FilaMain {
    public static void main(String[] args) {
        FilaEncadeada fila = new FilaEncadeada();
        
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);
        fila.inserir(40);
        fila.inserir(50);
        
        System.out.println("Elemento removido: " + fila.remover()); // 30
 
        while (!fila.isEmpty()) {
            System.out.println("Removendo elemento: " + fila.remover());
        }

        System.out.println("Fila vazia? " + fila.isEmpty()); // true
    }
    
}
