
# 📁 ESTRUTURA DO PROJETO - SISTEMA RESTAURANTE

```
src/restauranterevisaooo/
│
├── 🎓 CONCEITOS FUNDAMENTAIS
│   ├── Cliente.java                 [Classes, Construtores, Encapsulamento]
│   ├── Produto.java                 [Classe Abstrata, Métodos Abstratos]
│   ├── StatusPedido.java            [Enum - Conjunto de Constantes]
│   └── RestauranteException.java    [Exceção Customizada]
│
├── 🍽️ HIERARQUIA DE PRODUTOS (HERANÇA)
│   ├── Bebida.java                  [extends Produto]
│   ├── PratoPrincipal.java          [extends Produto]
│   └── Sobremesa.java               [extends Produto]
│
├── 🛒 SISTEMA DE PEDIDOS
│   ├── ItemPedido.java              [Composição: ItemPedido contém Produto]
│   ├── Pedido.java                  [Agregação: contém List<ItemPedido>]
│   └── Delivery.java                [extends Pedido - Herança]
│
├── 💳 SISTEMA DE PAGAMENTO (POLIMORFISMO)
│   ├── MetodoPagamento.java         [Interface - Contrato]
│   ├── PagamentoCartao.java         [implements MetodoPagamento]
│   └── PagamentoPix.java            [implements MetodoPagamento]
│
├── 🏪 GERENCIADOR CENTRAL
│   └── Restaurante.java             [Agregação: contém Pedidos e Produtos]
│
├── 🎮 INTERFACE DO USUÁRIO
│   ├── RestauranteMain.java         [Novo Main - Sequência Didática]
│   └── RestauranteApp.java          [Legado - Descontinuado]
│
├── 📚 DOCUMENTAÇÃO
│   ├── README.md                    [Guia Completo de Uso e Conceitos]
│   ├── ESTRUTURA.md                 [Este arquivo]
│   ├── DiagramaClasses.puml         [UML - Diagrama de Classes]
│   └── DiagramaSequencia.puml       [UML - Fluxo de Pedido]
│
└── 📝 REFERÊNCIA
    └── InformacaoAula.java          [Arquivo legado de informações]
```

---

## 📚 LEITURA RECOMENDADA

### Para Iniciantes em OOP:
1. Comece por: `Cliente.java` → `Produto.java` → `Bebida.java`
2. Depois: `Pedido.java` → `ItemPedido.java` → `Restaurante.java`
3. Por fim: `MetodoPagamento.java` → `PagamentoCartao.java` → `PagamentoPix.java`

### Para Intermediários:
1. Analise a herança em `Bebida.java`, `PratoPrincipal.java`, `Sobremesa.java`
2. Estude o polimorfismo em `MetodoPagamento` e suas implementações
3. Compreenda agregação em `Pedido.java` e composição em `ItemPedido.java`

### Para Avançados:
1. Examine como Stream API é usada em `Pedido.calcularTotal()`
2. Analise o fluxo completo em `RestauranteMain.java`
3. Estude os padrões de design (Factory em `inicializarCardapio()`)

---

## 🎯 MAPEAMENTO DE CONCEITOS POR ARQUIVO

| Conceito | Arquivo | Linha/Método |
|----------|---------|------|
| Classe Abstrata | Produto.java | class definition |
| Herança | Bebida.java | `extends Produto` |
| Polimorfismo | Bebida.java | `@Override exibirDetalhes()` |
| Interface | MetodoPagamento.java | `@FunctionalInterface` |
| Implementação | PagamentoCartao.java | `implements MetodoPagamento` |
| Encapsulamento | Cliente.java | `private` + getters/setters |
| Construtor | Cliente.java | `public Cliente(...)` |
| Composição | ItemPedido.java | `private Produto produto` |
| Agregação | Pedido.java | `protected List<ItemPedido> itens` |
| Enum | StatusPedido.java | `public enum StatusPedido` |
| Exceção | RestauranteException.java | `extends Exception` |
| Anotação | Qualquer override | `@Override` |
| Stream API | Pedido.java | `itens.stream().mapToDouble(...).sum()` |
| Thread | Pedido.java | `new Thread(() -> {...}).start()` |

---

## 🔄 FLUXO DE EXECUÇÃO PRINCIPAL

```
RestauranteMain.main()
    │
    ├─► exibirIntroducao()
    │
    └─► menuPrincipal()
        │
        ├─ Opção 1: restaurante.exibirCardapio()
        │           └─► Exibe todos os Produtos
        │
        ├─ Opção 2: criarNovoPedido()
        │           ├─► Cria Cliente
        │           ├─► Cria Pedido (ou Delivery)
        │           ├─► adicionarItensAoPedido()
        │           │   └─► Busca Produtos
        │           │   └─► Cria ItemPedido
        │           ├─► Escolhe MetodoPagamento
        │           └─► pedido.finalizarPedido()
        │               └─► Chama metodo.processarPagamento()
        │               └─► Inicia Thread de preparo
        │
        ├─ Opção 3: restaurante.exibirPedidos()
        │           └─► Lista todos os Pedidos criados
        │
        └─ Opção 4: demonstracaoConceitos()
                    ├─► demonstracao1_ClassesObjetos()
                    ├─► demonstracao2_Encapsulamento()
                    ├─► demonstracao3_Heranca()
                    ├─► demonstracao4_Polimorfismo()
                    ├─► demonstracao5_Interface()
                    ├─► demonstracao6_Enum()
                    ├─► demonstracao7_Associacao()
                    ├─► demonstracao8_Agregacao()
                    └─► demonstracao9_Composicao()
```

---

## 📊 RELACIONAMENTOS UML

### Herança (is-a)
```
Produto
  ▲
  │ extends
  ├── Bebida
  ├── PratoPrincipal
  └── Sobremesa

Pedido
  ▲
  │ extends
  └── Delivery
```

### Implementação (implements)
```
MetodoPagamento (interface)
  ▲
  │ implements
  ├── PagamentoCartao
  └── PagamentoPix
```

### Associação (knows-a)
```
Cliente ─────── Pedido
       1         *
    (um cliente pode ter múltiplos pedidos)
```

### Agregação (has-a - fraco)
```
Pedido ────○─── ItemPedido
      1      *
   (um pedido contém múltiplos itens)
   (itens podem existir sem pedido)
```

### Composição (has-a - forte)
```
ItemPedido ──●─── Produto
       1       1
  (um item é composto por um produto)
  (produto não faz sentido sem item)
```

---

## 🎓 ATIVIDADES PROPOSTAS

### Atividade 1: Executar e Entender
- [ ] Compilar o projeto
- [ ] Executar `RestauranteMain`
- [ ] Criar um pedido
- [ ] Visualizar os pedidos criados

### Atividade 2: Analisar Código
- [ ] Ler `Produto.java` e identificar classe abstrata
- [ ] Ler `Bebida.java` e identificar herança
- [ ] Ler `MetodoPagamento.java` e identificar interface
- [ ] Ler `RestauranteMain.java` e entender o fluxo

### Atividade 3: Modificar
- [ ] Adicionar novo atributo em `Cliente`
- [ ] Criar nova classe `Entrada extends Produto`
- [ ] Adicionar novo método de pagamento
- [ ] Adicionar novo status no enum

### Atividade 4: Desenhar
- [ ] Desenhar diagrama de classes
- [ ] Desenhar diagrama de sequência
- [ ] Identificar todos os relacionamentos

### Atividade 5: Expandir
- [ ] Adicionar persistência (arquivo/banco)
- [ ] Criar classe `Funcionario`
- [ ] Implementar sistema de avaliações
- [ ] Adicionar histórico de pedidos

---

## 🧪 COMO TESTAR

### Teste 1: Criar Pedido Local
```
Opção 1: Ver cardápio
Opção 2: Criar pedido
- Nome: João Silva
- Telefone: 11999999999
- Tipo: 1 (Local)
- Endereço: Rua A, 123
- Produto: Risoto de Java (quantidade: 1)
- Método de pagamento: 1 (Cartão/Visa)
```

### Teste 2: Criar Delivery
```
Opção 2: Criar pedido
- Nome: Maria Santos
- Telefone: 11988888888
- Tipo: 2 (Delivery)
- Endereço de entrega: Rua B, 456
- Produto: Suco de Melancia (quantidade: 2)
- Método de pagamento: 2 (Pix)
```

### Teste 3: Demonstração
```
Opção 4: Demonstração de Conceitos
- Executar e acompanhar cada conceito
```

---

## 🚀 PRÓXIMOS PASSOS

1. **Entender**: Leia todos os arquivos `.java` e `README.md`
2. **Executar**: Compile e rode o programa
3. **Modificar**: Faça pequenas alterações no código
4. **Expandir**: Adicione novas funcionalidades
5. **Ensinar**: Use como material didático

---

*Estrutura criada para fins educacionais*
*Versão 2.0 - Reorganizado e Didatizado*
