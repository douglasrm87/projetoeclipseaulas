package restauranterevisaooo;

import java.util.Scanner;

public class RestauranteApp {
    private static int VALOR_MIN_ATEND = 10;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       
        // [Objetos] - Instanciando produtos
        Produto p1 = new PratoPrincipal("Risoto de Java", 45.0);
        Produto p2 = new PratoPrincipal("Filé de Python", 55.0);

        System.out.println("--- BEM VINDO AO DUKE GOURMET ---");
        System.out.println("1. Pedido Local\n2. Delivery");
        int opcao = scanner.nextInt();

        Pedido meuPedido = (opcao == 2) ? 
            new Delivery("Rua da Tecnologia, 101", VALOR_MIN_ATEND) 
        : 
            new Pedido(VALOR_MIN_ATEND);// taxa de atendimento minimo
        
        meuPedido.adicionarItem(p1, 1);
        meuPedido.adicionarItem(p2, 1);

        System.out.println("Total: R$ " + meuPedido.calcularTotal());
        System.out.println("Escolha o pagamento: 1. Cartão  | 2. Pix");
        int pag = scanner.nextInt();

        try {
            if (pag == 1) {
                // Usando classe anônima para mostrar polimorfismo clássico
                meuPedido.finalizarPedido(new PagamentoCartao("Visa"));
            } else {
                // [Lambda] - Polimorfismo moderno e conciso
                meuPedido.finalizarPedido(new PagamentoPix());
            }

        } catch (RestauranteException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Fim do menu principal. A cozinha continuará em background...");
    }
}
