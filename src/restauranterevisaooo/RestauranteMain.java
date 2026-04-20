package restauranterevisaooo;

import java.util.Scanner;

/**
 * ═══════════════════════════════════════════════════════════════════════════
 * [SISTEMA RESTAURANTE - DEMONSTRAÇÃO DE CONCEITOS OOP]
 * 
 * Este programa demonstra os principais conceitos de Orientação a Objetos:
 * 
 * 1. CLASSES E OBJETOS - Criação de instâncias
 * 2. ATRIBUTOS E MÉTODOS - Dados e comportamentos
 * 3. ENCAPSULAMENTO - Proteção de dados
 * 4. CONSTRUTORES - Inicialização de objetos
 * 5. HERANÇA - Reutilização e especialização
 * 6. POLIMORFISMO - Comportamentos diferentes para a mesma interface
 * 7. INTERFACES - Contratos de comportamento
 * 8. ENUMS - Conjuntos de constantes
 * 9. ASSOCIAÇÃO/AGREGAÇÃO/COMPOSIÇÃO - Relacionamentos entre classes
 * 10. EXCEÇÕES CUSTOMIZADAS - Tratamento de erros específicos
 * 11. ANOTAÇÕES - @Override, @FunctionalInterface, @Deprecated
 * 12. STREAM API - Processamento funcional de coleções
 * ═══════════════════════════════════════════════════════════════════════════
 */
public class RestauranteMain {
    
    // [VARIÁVEIS ESTÁTICAS - Compartilhadas por todas as instâncias]
    private static Scanner scanner = new Scanner(System.in);
    private static Restaurante restaurante = new Restaurante("DUKE GOURMET");

    public static void main(String[] args) {
        try {
            exibirIntroducao();
            menuPrincipal();
        } catch (RestauranteException e) {
            System.err.println("❌ ERRO NA APLICAÇÃO: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("\n👋 Obrigado por usar o DUKE GOURMET!");
        }
    }

    /**
     * [SEQUÊNCIA DIDÁTICA]
     * Introdução interativa que demonstra os conceitos passo a passo
     */
    private static void exibirIntroducao() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║        🍽️  BEM-VINDO AO SISTEMA DUKE GOURMET  🍽️           ║");
        System.out.println("║                                                            ║");
        System.out.println("║  Sistema didático para ensinar Orientação a Objetos       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n[1] CONCEITOS DEMONSTRADOS NESTA APLICAÇÃO:");
        System.out.println("    ✓ Classes: Pedido, Cliente, Produto");
        System.out.println("    ✓ Objetos: Instâncias de cada classe");
        System.out.println("    ✓ Herança: Bebida, PratoPrincipal, Sobremesa herdam de Produto");
        System.out.println("    ✓ Polimorfismo: PagamentoCartao e PagamentoPix implementam MetodoPagamento");
        System.out.println("    ✓ Interfaces: MetodoPagamento define contrato");
        System.out.println("    ✓ Enums: StatusPedido (PENDENTE, PREPARANDO, PRONTO, ENTREGUE)");
        System.out.println("    ✓ Encapsulamento: Atributos privados com getters públicos");
        System.out.println("    ✓ Associação: Pedido associa-se a Cliente e ItemPedido");
        System.out.println("    ✓ Agregação: Pedido contém lista de ItemPedido");
        System.out.println("    ✓ Composição: ItemPedido contém Produto");
        System.out.println("    ✓ Exceções: RestauranteException customizada");
        System.out.println("    ✓ Stream API: .mapToDouble(...).sum()");
        System.out.println();
        
        pressionarEnter();
    }

    /**
     * [MENU PRINCIPAL]
     * Controla o fluxo da aplicação
     */
    private static void menuPrincipal() throws RestauranteException {
        boolean ativo = true;
        
        while (ativo) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║         MENU PRINCIPAL                 ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1. Visualizar Cardápio                 ║");
            System.out.println("║ 2. Criar Novo Pedido                   ║");
            System.out.println("║ 3. Visualizar Pedidos                  ║");
            System.out.println("║ 4. Demonstração de Conceitos OOP       ║");
            System.out.println("║ 5. Sair                                ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("Escolha uma opção: ");
            
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();  // Limpa o buffer
                
                switch (opcao) {
                    case 1:
                        restaurante.exibirCardapio();
                        break;
                    case 2:
                        criarNovoPedido();
                        break;
                    case 3:
                        restaurante.exibirPedidos();
                        break;
                    case 4:
                        demonstracaoConceitos();
                        break;
                    case 5:
                        ativo = false;
                        break;
                    default:
                        System.out.println("❌ Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("❌ Entrada inválida!");
                scanner.nextLine();  // Limpa o buffer
            }
        }
    }

    /**
     * [FLUXO CRIAÇÃO DE PEDIDO]
     * Demonstra a interação entre objetos
     */
    private static void criarNovoPedido() throws RestauranteException {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       CRIAR NOVO PEDIDO                ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        try {
            // [OBJETO] - Criação de Cliente
            System.out.print("\n📝 Nome do cliente: ");
            String nomeCliente = scanner.nextLine();
            
            System.out.print("📞 Telefone: ");
            String telefone = scanner.nextLine();
            
            // Tipo de pedido
            System.out.println("\n1. Pedido Local");
            System.out.println("2. Delivery");
            System.out.print("Escolha: ");
            int tipoPedido = scanner.nextInt();
            scanner.nextLine();
            
            // [OBJETO] - Criação de Pedido (Local ou Delivery)
            Pedido pedido;
            
            if (tipoPedido == 2) {
                System.out.print("📍 Endereço de entrega: ");
                String endereco = scanner.nextLine();
                
                // [HERANÇA] - Delivery estende Pedido
                Cliente cliente = new Cliente(nomeCliente, telefone, endereco);
                pedido = new Delivery(cliente, endereco, 10.0);
                
            } else {
                System.out.print("📍 Endereço do cliente: ");
                String endereco = scanner.nextLine();
                
                // [OBJETO] - Pedido local
                Cliente cliente = new Cliente(nomeCliente, telefone, endereco);
                pedido = new Pedido(cliente);
            }
            
            // [ADIÇÃO DE ITENS]
            adicionarItensAoPedido(pedido);
            
            // [EXIBIÇÃO DO PEDIDO]
            pedido.exibirResumo();
            
            // [SELEÇÃO DE PAGAMENTO - POLIMORFISMO]
            System.out.println("\n💳 ESCOLHA O MÉTODO DE PAGAMENTO:");
            System.out.println("1. Cartão de Crédito");
            System.out.println("2. Pix");
            System.out.print("Opção: ");
            int opcaoPagamento = scanner.nextInt();
            scanner.nextLine();
            
            // [POLIMORFISMO] - Diferentes implementações de MetodoPagamento
            MetodoPagamento metodo;
            if (opcaoPagamento == 1) {
                System.out.print("Operadora (Visa/Mastercard): ");
                String operadora = scanner.nextLine();
                metodo = new PagamentoCartao(operadora);
            } else {
                metodo = new PagamentoPix();
            }
            
            // [FINALIZAÇÃO] - Processa pagamento e pedido
            pedido.finalizarPedido(metodo);
            
            // [AGREGAÇÃO] - Adiciona pedido ao restaurante
            restaurante.adicionarPedido(pedido);
            
            System.out.println("✅ Pedido registrado com sucesso!");
            pressionarEnter();
            
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Dados inválidos: " + e.getMessage());
            pressionarEnter();
        }
    }

    /**
     * [ADIÇÃO DE ITENS]
     * Permite ao usuário adicionar produtos ao pedido
     */
    private static void adicionarItensAoPedido(Pedido pedido) {
        boolean adicionandoItens = true;
        
        while (adicionandoItens) {
            System.out.println("\n📋 ADICIONAR ITENS AO PEDIDO");
            restaurante.exibirCardapio();
            
            System.out.print("Digite o nome do produto (ou 'pronto' para finalizar): ");
            String nomeProduto = scanner.nextLine();
            
            if (nomeProduto.equalsIgnoreCase("pronto")) {
                adicionandoItens = false;
                continue;
            }
            
            Produto produto = restaurante.buscarProduto(nomeProduto);
            if (produto == null) {
                System.out.println("❌ Produto não encontrado!");
                continue;
            }
            
            System.out.print("Quantidade: ");
            try {
                int quantidade = scanner.nextInt();
                scanner.nextLine();
                
                pedido.adicionarItem(produto, quantidade);
                
            } catch (Exception e) {
                System.out.println("❌ Quantidade inválida!");
                scanner.nextLine();
            }
        }
        
        if (pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("Pedido precisa ter pelo menos 1 item!");
        }
    }

    /**
     * [DEMONSTRAÇÃO DIDÁTICA]
     * Explica e demonstra os conceitos OOP na prática
     */
    private static void demonstracaoConceitos() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     DEMONSTRAÇÃO DE CONCEITOS OOP NA PRÁTICA              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        demonstracao1_ClassesObjetos();
        demonstracao2_Encapsulamento();
        demonstracao3_Heranca();
        demonstracao4_Polimorfismo();
        demonstracao5_Interface();
        demonstracao6_Enum();
        demonstracao7_Associacao();
        demonstracao8_Agregacao();
        demonstracao9_Composicao();
    }

    private static void demonstracao1_ClassesObjetos() {
        System.out.println("\n[1] CLASSES E OBJETOS");
        System.out.println("════════════════════");
        System.out.println("• Classe: Projeto ou molde (exemplo: classe Produto)");
        System.out.println("• Objeto: Instância concreta (exemplo: PratoPrincipal 'Risoto de Java')");
        System.out.println();
        
        PratoPrincipal pratoPrincipal = new PratoPrincipal(
            "Exemplo", 50.0, "Um prato de exemplo", "500 cal", false
        );
        System.out.println("Criamos um OBJETO pratoPrincipal da classe PratoPrincipal:");
        pratoPrincipal.exibirDetalhes();
        
        pressionarEnter();
    }

    private static void demonstracao2_Encapsulamento() {
        System.out.println("\n[2] ENCAPSULAMENTO");
        System.out.println("═════════════════");
        System.out.println("• Atributos privados: Protegem os dados internos");
        System.out.println("• Getters: Permitem ler os dados de forma controlada");
        System.out.println("• Setters: Permitem modificar os dados com validação");
        System.out.println();
        
        Cliente cliente = new Cliente("João Silva", "11999999999", "Rua A, 123");
        System.out.println("Cliente criado:");
        System.out.println(cliente);
        
        System.out.println("\n❌ Não podemos acessar 'cliente.nome' diretamente (é privado)");
        System.out.println("✅ Acessamos via getter: cliente.getNome() = " + cliente.getNome());
        
        pressionarEnter();
    }

    private static void demonstracao3_Heranca() {
        System.out.println("\n[3] HERANÇA");
        System.out.println("══════════");
        System.out.println("• Bebida HERDA de Produto");
        System.out.println("• PratoPrincipal HERDA de Produto");
        System.out.println("• Sobremesa HERDA de Produto");
        System.out.println();
        
        System.out.println("Todos herdam: nome, preço, getters, e o método abstrato exibirDetalhes()");
        System.out.println("\nMas cada uma implementa 'exibirDetalhes()' de forma diferente:");
        System.out.println();
        
        Produto bebida = new Bebida("Suco", 8.0, "Suco natural", "Gelada");
        bebida.exibirDetalhes();
        
        System.out.println();
        
        Produto prato = new PratoPrincipal("Filé", 55.0, "Filé macio", "720 cal", true);
        prato.exibirDetalhes();
        
        pressionarEnter();
    }

    private static void demonstracao4_Polimorfismo() {
        System.out.println("\n[4] POLIMORFISMO");
        System.out.println("═══════════════");
        System.out.println("• Múltiplas formas do mesmo objeto");
        System.out.println("• Mesma interface (MetodoPagamento)");
        System.out.println("• Comportamentos diferentes (Cartão vs Pix)");
        System.out.println();
        
        System.out.println("Uma referência MetodoPagamento pode apontar para:");
        System.out.println("  - PagamentoCartao");
        System.out.println("  - PagamentoPix");
        System.out.println("  - Qualquer outra implementação de MetodoPagamento");
        System.out.println();
        
        try {
            MetodoPagamento[] pagamentos = {
                new PagamentoCartao("Visa"),
                new PagamentoPix()
            };
            
            System.out.println("Polimorfismo em ação:");
            for (MetodoPagamento pag : pagamentos) {
                pag.processarPagamento(100.0);
                System.out.println();
            }
        } catch (RestauranteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
        pressionarEnter();
    }

    private static void demonstracao5_Interface() {
        System.out.println("\n[5] INTERFACE");
        System.out.println("═════════════");
        System.out.println("• Contrato que define o que fazer (não o como)");
        System.out.println("• Classe que implementa deve fornecer o comportamento");
        System.out.println("• MetodoPagamento é uma interface");
        System.out.println();
        
        System.out.println("interface MetodoPagamento {");
        System.out.println("    void processarPagamento(double valor) throws RestauranteException;");
        System.out.println("}");
        System.out.println();
        System.out.println("✓ PagamentoCartao implements MetodoPagamento");
        System.out.println("✓ PagamentoPix implements MetodoPagamento");
        
        pressionarEnter();
    }

    private static void demonstracao6_Enum() {
        System.out.println("\n[6] ENUM (Tipo de dado especial)");
        System.out.println("═════════════════════════════════");
        System.out.println("• Define um conjunto FIXO de constantes");
        System.out.println("• Type-safe: Só permite valores definidos");
        System.out.println();
        
        System.out.println("StatusPedido possui 4 valores:");
        for (StatusPedido status : StatusPedido.values()) {
            System.out.println("  • " + status + " -> " + status.getDescricao());
        }
        
        pressionarEnter();
    }

    private static void demonstracao7_Associacao() {
        System.out.println("\n[7] ASSOCIAÇÃO");
        System.out.println("═════════════");
        System.out.println("• Relacionamento entre duas classes");
        System.out.println("• Pedido ESTÁ ASSOCIADO A Cliente");
        System.out.println("• Pedido ESTÁ ASSOCIADO A MetodoPagamento");
        System.out.println();
        
        System.out.println("Exemplo:");
        Cliente cli = new Cliente("Maria", "11988888888", "Rua B, 456");
        System.out.println(cli);
        
        pressionarEnter();
    }

    private static void demonstracao8_Agregacao() {
        System.out.println("\n[8] AGREGAÇÃO");
        System.out.println("═════════════");
        System.out.println("• Pedido CONTÉM uma lista de ItemPedido");
        System.out.println("• Relacionamento fraco: se Pedido morre, Itens podem continuar");
        System.out.println("• Exemplo de 'tem um' (hasA) relationship");
        System.out.println();
        
        System.out.println("List<ItemPedido> itens = new ArrayList<>();");
        System.out.println("Um Pedido pode ter múltiplos ItemPedido agregados");
        
        pressionarEnter();
    }

    private static void demonstracao9_Composicao() {
        System.out.println("\n[9] COMPOSIÇÃO");
        System.out.println("══════════════");
        System.out.println("• ItemPedido CONTÉM um Produto");
        System.out.println("• Relacionamento forte: sem Produto não há ItemPedido");
        System.out.println("• Partes essenciais da composição");
        System.out.println();
        
        System.out.println("class ItemPedido {");
        System.out.println("    private Produto produto;  // Composição");
        System.out.println("    private int quantidade;");
        System.out.println("}");
        
        pressionarEnter();
    }

    /**
     * [MÉTODO AUXILIAR]
     * Aguarda o usuário pressionar Enter
     */
    private static void pressionarEnter() {
        System.out.print("\n[Pressione ENTER para continuar...]");
        scanner.nextLine();
    }
}
