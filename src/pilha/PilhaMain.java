package pilha;

public class PilhaMain {
    public static void main(String[] args) {
        PilhaEncadeada pilha = new PilhaEncadeada();
        
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        pilha.push(40);
        pilha.push(50);

        
        System.out.println("Topo da pilha: " + pilha.peek()); // 30
        System.out.println("Tamanho da pilha: " + pilha.size()); // 3
        
        System.out.println("Elemento removido: " + pilha.pop()); // 30
        System.out.println("Topo da pilha após pop: " + pilha.peek()); // 20
        System.out.println("Tamanho da pilha após pop: " + pilha.size()); // 2
        
        while (!pilha.isEmpty()) {
            System.out.println("Removendo elemento: " + pilha.pop());
        }
        
        System.out.println("Pilha vazia? " + pilha.isEmpty()); // true
    }
    
}
