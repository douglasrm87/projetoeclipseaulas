
# 🍽️ SISTEMA RESTAURANTE DUKE GOURMET - DIDÁTICO

## 📚 PROPÓSITO EDUCACIONAL

Este sistema foi **reconstruído e reorganizado** para fins didáticos, demonstrando os principais conceitos de **Orientação a Objetos em Java** de forma prática e interativa.

É uma ferramenta excelente para ensinar Java OOP a alunos de tecnologia.

---

## 🎓 CONCEITOS DE OOP DEMONSTRADOS

### 1. **CLASSES E OBJETOS**
- Definição de classes como moldes
- Criação de instâncias (objetos)
- Exemplo: `Cliente cliente = new Cliente("João", "11999999999", "Rua A")`

### 2. **ATRIBUTOS E MÉTODOS**
- Dados (atributos) que caracterizam um objeto
- Comportamentos (métodos) que um objeto realiza
- Exemplo: `cliente.getNome()`, `pedido.calcularTotal()`

### 3. **CONSTRUTORES**
- Inicializam objetos em estado válido
- Executados automaticamente ao criar novo objeto
- Com validação de dados
```java
public Cliente(String nome, String telefone, String endereco) {
    if (nome == null || nome.trim().isEmpty()) {
        throw new IllegalArgumentException("Nome não pode ser vazio");
    }
    this.nome = nome;
    this.telefone = telefone;
    this.endereco = endereco;
}
```

### 4. **ENCAPSULAMENTO**
- Atributos privados (proteção de dados)
- Getters e setters públicos (acesso controlado)
- Exemplo:
```java
private String nome;        // Privado - não acessa direto
public String getNome() {   // Público - acesso controlado
    return nome;
}
```

### 5. **HERANÇA**
- Reutilização de código através de especialização
- Classe filha herda de classe pai: `extends`
- Exemplos:
  - `Bebida extends Produto`
  - `PratoPrincipal extends Produto`
  - `Sobremesa extends Produto`
  - `Delivery extends Pedido`

### 6. **CLASSE ABSTRATA**
- Molde que não pode ser instanciada diretamente
- Define contrato que subclasses devem seguir
- Exemplo: `abstract class Produto { abstract void exibirDetalhes(); }`

### 7. **POLIMORFISMO**
- Múltiplas formas do mesmo objeto
- Mesma interface, comportamentos diferentes
- Exemplo:
```java
Produto p1 = new Bebida(...);          // Bebida é um Produto
Produto p2 = new PratoPrincipal(...);  // PratoPrincipal é um Produto
p1.exibirDetalhes();  // Comportamento de Bebida
p2.exibirDetalhes();  // Comportamento diferente de Prato
```

### 8. **INTERFACE**
- Contrato que define o QUE fazer (não o COMO)
- Classes implementam a interface: `implements`
- Exemplo:
```java
interface MetodoPagamento {
    void processarPagamento(double valor) throws RestauranteException;
}

class PagamentoCartao implements MetodoPagamento { ... }
class PagamentoPix implements MetodoPagamento { ... }
```

### 9. **ENUM**
- Tipo de dado que define conjunto fixo de constantes
- Type-safe: só permite valores definidos
- Exemplo:
```java
public enum StatusPedido {
    PENDENTE("Aguardando preparação"),
    PREPARANDO("Em processamento na cozinha"),
    PRONTO("Pronto para entrega"),
    ENTREGUE("Pedido finalizado")
}
```

### 10. **ASSOCIAÇÃO**
- Relacionamento entre duas classes
- Uma classe "conhece" a outra
- Exemplo: `Pedido está associado a Cliente`

### 11. **AGREGAÇÃO** (Relacionamento Fraco)
- Uma classe contém coleção de outra
- As partes podem existir sem o todo
- Exemplo: `Pedido contém List<ItemPedido>`
- Se o Pedido for deletado, os ItemPedido podem continuar existindo (conceitualmente)

### 12. **COMPOSIÇÃO** (Relacionamento Forte)
- Uma classe contém parte que é essencial
- As partes não existem sem o todo
- Exemplo: `ItemPedido contém Produto`
- Se ItemPedido não tiver Produto, não faz sentido

### 13. **EXCEÇÕES CUSTOMIZADAS**
- Exceções específicas do domínio
- Melhor tratamento de erros
- Exemplo:
```java
public class RestauranteException extends Exception {
    public RestauranteException(String mensagem) {
        super(mensagem);
    }
}

try {
    pedido.finalizarPedido(metodo);
} catch (RestauranteException e) {
    System.err.println("ERRO: " + e.getMessage());
}
```

### 14. **ANOTAÇÕES**
- Metadados sobre o programa (não diretamente executadas)
- Exemplos usados:
  - `@Override`: Indica sobrescrita de método da superclasse
  - `@Deprecated`: Marca método como obsoleto
  - `@FunctionalInterface`: Marca interface funcional

### 15. **STREAM API**
- Processamento funcional de coleções (Java 8+)
- Alternativa moderna aos loops tradicionais
- Exemplo:
```java
public double calcularTotal() {
    return itens.stream()
        .mapToDouble(ItemPedido::getSubtotal)
        .sum();
}
```

---

## 📁 ESTRUTURA DE CLASSES

### Hierarquia de Classes

```
Produto (abstrata)
├── Bebida (herança)
├── PratoPrincipal (herança)
└── Sobremesa (herança)

Pedido
└── Delivery (herança)

ItemPedido (composição com Produto)

Cliente

Restaurante (agregação de Pedidos e Produtos)

MetodoPagamento (interface)
├── PagamentoCartao (implementação)
└── PagamentoPix (implementação)

StatusPedido (enum)

RestauranteException (exceção customizada)
```

### Relacionamentos

```
Cliente ──────────────┐
                      │
                      ▼
                  Pedido ◄── ItemPedido ◄── Produto
                   │            (composição)   ▲
                   │                          │
                   ├─────────────────────────┤
                   │ (agregação)    (herança)│
                   │                         │
                   └─ Delivery     Bebida   ▼
                                  PratoPrincipal
                                  Sobremesa

Pedido ──► MetodoPagamento ◄─ PagamentoCartao
                                PagamentoPix

Restaurante ◄─ Pedidos (agregação)
            ◄─ Produtos (agregação)
```

---

## 🚀 COMO EXECUTAR

### Compilação
```bash
cd /workspaces/projetoeclipseaulas
javac -d target/classes -sourcepath src src/restauranterevisaooo/*.java
```

### Execução
```bash
java -cp target/classes restauranterevisaooo.RestauranteMain
```

---

## 📖 GUIA DIDÁTICO PARA AULAS

### AULA 1: Introdução - O que é POO?
1. Execute o programa
2. Escolha opção 4 (Demonstração de Conceitos)
3. Estude cada conceito demonstrado:
   - Classes e Objetos
   - Encapsulamento
   - Herança

### AULA 2: Hierarquias e Polimorfismo
1. Analise a hierarquia de `Produto`
2. Veja como `Bebida`, `PratoPrincipal` e `Sobremesa` herdam
3. Observe o polimorfismo em `exibirDetalhes()`

### AULA 3: Interfaces e Contrato
1. Estude `MetodoPagamento` (interface)
2. Compare `PagamentoCartao` e `PagamentoPix` (implementações)
3. Veja polimorfismo em ação durante pagamento

### AULA 4: Relacionamentos (Associação, Agregação, Composição)
1. `Cliente` e `Pedido` → Associação
2. `Pedido` e `ItemPedido` → Agregação
3. `ItemPedido` e `Produto` → Composição

### AULA 5: Fluxo Completo
1. Criar um pedido
2. Adicionar itens
3. Processar pagamento
4. Observe como todos os conceitos trabalham juntos

---

## 🎯 EXEMPLOS DE CÓDIGO PARA ESTUDAR

### Herança
```java
public class Bebida extends Produto {
    private String temperatura;
    
    public Bebida(String nome, double preco, String descricao, String temperatura) {
        super(nome, preco, descricao);  // Chama construtor da superclasse
        this.temperatura = temperatura;
    }
    
    @Override
    public void exibirDetalhes() {
        // Implementação específica de Bebida
    }
}
```

### Polimorfismo
```java
public void finalizarPedido(MetodoPagamento pagamento) throws RestauranteException {
    double total = calcularTotal();
    
    // 'pagamento' pode ser PagamentoCartao, PagamentoPix ou qualquer outra
    // implementação de MetodoPagamento
    pagamento.processarPagamento(total);
    
    this.status = StatusPedido.PREPARANDO;
}
```

### Stream API
```java
public double calcularTotal() {
    return itens.stream()
        .mapToDouble(ItemPedido::getSubtotal)  // Extrai subtotal de cada item
        .sum();  // Soma todos
}
```

### Validação e Exceções
```java
public void adicionarItem(Produto produto, int quantidade) {
    if (produto == null) {
        throw new IllegalArgumentException("Produto não pode ser nulo");
    }
    if (quantidade <= 0) {
        throw new IllegalArgumentException("Quantidade deve ser maior que zero");
    }
    
    ItemPedido novoItem = new ItemPedido(produto, quantidade);
    itens.add(novoItem);
}
```

---

## 📊 DIAGRAMAS UML

O projeto inclui dois diagramas PlantUML:

### 1. **DiagramaClasses.puml**
- Estrutura completa de classes
- Relacionamentos (herança, interfaces, agregação, composição)
- Atributos e métodos de cada classe

### 2. **DiagramaSequencia.puml**
- Fluxo de um pedido do início ao fim
- Interação entre objetos
- Passagem de mensagens (chamadas de métodos)

Para visualizar os diagramas, use:
- [PlantUML Online Editor](http://www.plantuml.com/plantuml/uml/)
- VS Code extension "PlantUML"
- Ou use: `plantuml -Tpng DiagramaClasses.puml`

---

## 🎓 SEQUÊNCIA RECOMENDADA DE ESTUDO

1. **Ler esta documentação** para entender conceitos
2. **Compilar e executar** o programa
3. **Analisar o código-fonte** de cada classe
4. **Escolha opção 4** no menu para ver demonstrações
5. **Criar um pedido** e acompanhar o fluxo
6. **Modificar o código** e ver o que acontece
7. **Desenhar os diagramas** em papel ou ferramenta UML
8. **Discutir** como cada conceito foi aplicado

---

## 🔧 MODIFICAÇÕES EDUCACIONAIS

Sugestões de atividades para alunos:

1. **Adicionar nova classe de Produto**
   - Crie `Entrada extends Produto`
   - Implemente seus próprios atributos
   - Override o método `exibirDetalhes()`

2. **Adicionar novo método de pagamento**
   - Crie `PagamentoDinheiro implements MetodoPagamento`
   - Implemente `processarPagamento()`

3. **Adicionar novo status de pedido**
   - Adicione `CANCELADO` ao enum `StatusPedido`

4. **Criar classe de Funcionário**
   - Associe ao Restaurante
   - Implemente sistema de funcionários

5. **Adicionar persistência de dados**
   - Use arquivo ou banco de dados
   - Salve e carregue pedidos

---

## 📝 ARQUIVO DE INFORMAÇÕES ANTIGAS

O arquivo `InformacaoAula.java` contém informações de aulas anteriores. Ele foi mantido apenas para referência.

---

## ✅ CHECKLIST DE CONCEITOS

Após estudar o sistema, verifique seu entendimento:

- [ ] Entendo a diferença entre Classe e Objeto
- [ ] Consigo identificar Herança no código
- [ ] Entendo o que é Polimorfismo
- [ ] Sei diferenciar Classe Abstrata de Interface
- [ ] Consigo identificar Agregação e Composição
- [ ] Entendo como Enums funcionam
- [ ] Consigo usar Exception Customizada
- [ ] Entendo o fluxo de criação de um Pedido
- [ ] Consigo modificar o código e adicionar novas classes
- [ ] Consigo explicar os relacionamentos entre classes

---

## 📞 DÚVIDAS FREQUENTES

**P: Por que Produto é abstrata e não interface?**
R: Porque Produto tem atributos comuns (nome, preco) e métodos concretos. Uma interface não pode ter implementação (em Java 8+).

**P: Por que Delivery extends Pedido?**
R: Porque Delivery É UM Pedido especial com taxa de entrega. É um caso clássico de herança.

**P: Qual a diferença entre Agregação e Composição?**
R: Agregação é fraca (partes podem existir sem o todo), Composição é forte (partes não fazem sentido sem o todo).

**P: Por que usar Stream API em vez de for loop?**
R: Stream API é mais concisa, legível e permite processamento paralelo.

---

## 🎉 CONCLUSÃO

Este sistema demonstra como todos os conceitos de Orientação a Objetos trabalham juntos em uma aplicação real. Use-o como base para aprender, ensinar ou expandir com novas funcionalidades.

**Bom estudo! 📚**

---

*Última atualização: 2026-04-20*
*Versão: 2.0 (Reconstruído para fins didáticos)*
