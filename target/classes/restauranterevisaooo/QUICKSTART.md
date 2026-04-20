
# ⚡ QUICK START - COMEÇAR RÁPIDO

## 🚀 5 Passos para Executar

### Passo 1: Compilar
```bash
cd /workspaces/projetoeclipseaulas
javac -d target/classes -sourcepath src src/restauranterevisaooo/*.java
```

### Passo 2: Executar
```bash
java -cp target/classes restauranterevisaooo.RestauranteMain
```

### Passo 3: Escolher Opção
```
╔════════════════════════════════════════╗
║         MENU PRINCIPAL                 ║
╠════════════════════════════════════════╣
║ 1. Visualizar Cardápio                 ║
║ 2. Criar Novo Pedido                   ║
║ 3. Visualizar Pedidos                  ║
║ 4. Demonstração de Conceitos OOP       ║
║ 5. Sair                                ║
╚════════════════════════════════════════╝
```

### Passo 4: Interagir
- **Opção 1**: Veja os produtos disponíveis
- **Opção 2**: Crie um pedido completo
- **Opção 4**: Aprenda sobre OOP

### Passo 5: Entender
- Leia o código-fonte
- Analyze os diagramas
- Modifique e experimente

---

## 📚 PRINCIPAIS ARQUIVOS

| Arquivo | Descrição |
|---------|-----------|
| `RestauranteMain.java` | **Início** - Menu principal interativo |
| `README.md` | **Guia Completo** - Tudo explicado |
| `ESTRUTURA.md` | **Mapa** - Organização do projeto |
| `Produto.java` | **Classe Abstrata** - Base da hierarquia |
| `Bebida.java` | **Herança** - Exemplo de especialização |
| `Pedido.java` | **Agregação** - Contém itens |
| `ItemPedido.java` | **Composição** - Composto de Produto |
| `MetodoPagamento.java` | **Interface** - Contrato de pagamento |
| `PagamentoCartao.java` | **Implementação** - Exemplo 1 |
| `PagamentoPix.java` | **Implementação** - Exemplo 2 |

---

## 🎯 CENÁRIOS DE USO

### Cenário 1: Entender Herança
```
1. Abra Produto.java
2. Veja @AbstractMethod exibirDetalhes()
3. Abra Bebida.java
4. Note: class Bebida extends Produto
5. Veja @Override exibirDetalhes()
```

### Cenário 2: Entender Polimorfismo
```
1. Abra MetodoPagamento.java (interface)
2. Abra PagamentoCartao.java (implements)
3. Abra PagamentoPix.java (implements)
4. Veja como ambas implementam processarPagamento()
5. Execute: Opção 2 do menu → Escolha Pagamento
```

### Cenário 3: Entender Agregação/Composição
```
1. Abra Pedido.java → veja List<ItemPedido> itens
2. Abra ItemPedido.java → veja Produto produto
3. Leia documentação no README.md
4. Execute: Opção 2 → Crie um pedido com múltiplos itens
```

### Cenário 4: Estudar Fluxo Completo
```
1. Leia RestauranteMain.java
2. Acompanhe método criarNovoPedido()
3. Veja como todos os objetos interagem
4. Execute e crie um pedido do início ao fim
```

---

## 💡 DICAS RÁPIDAS

**Quero aprender:**

- ✅ O que é uma classe? → Leia `Cliente.java`
- ✅ O que é herança? → Leia `Bebida.java` vs `Produto.java`
- ✅ O que é interface? → Leia `MetodoPagamento.java`
- ✅ O que é polimorfismo? → Compare `PagamentoCartao.java` e `PagamentoPix.java`
- ✅ O que é Enum? → Leia `StatusPedido.java`
- ✅ O que é agregação? → Veja `Pedido.java` com `List<ItemPedido>`
- ✅ O que é composição? → Veja `ItemPedido.java` com `Produto`
- ✅ Tudo junto? → Execute e crie um pedido

---

## 📋 ATIVIDADES RÁPIDAS

### Atividade 1: Criar Seu Primeiro Pedido (5 min)
```
1. Compile: javac -d target/classes -sourcepath src src/restauranterevisaooo/*.java
2. Execute: java -cp target/classes restauranterevisaooo.RestauranteMain
3. Opção: 2 (Criar Novo Pedido)
4. Preencha dados: Nome, Telefone, Endereço
5. Tipo: 1 (Local)
6. Adicione produtos
7. Escolha pagamento: 1 (Cartão) ou 2 (Pix)
```

### Atividade 2: Ver Demonstração de Conceitos (10 min)
```
1. Execute o programa
2. Opção: 4 (Demonstração de Conceitos)
3. Leia cada conceito explicado
4. Pressione Enter entre conceitos
```

### Atividade 3: Modificar Código (30 min)
```
1. Abra Bebida.java
2. Adicione novo atributo: private String marca;
3. Atualize construtor
4. Atualize exibirDetalhes()
5. Recompile
6. Teste criando um pedido
```

### Atividade 4: Criar Nova Classe (60 min)
```
1. Crie Entrada.java extends Produto
2. Adicione atributos específicos
3. Implemente exibirDetalhes()
4. Adicione ao cardápio em Restaurante.java
5. Recompile
6. Teste criando pedido com nova entrada
```

---

## 🐛 TROUBLESHOOTING

**Problema: "No such file or directory"**
```
Solução: Certifique-se de estar na pasta correta
$ cd /workspaces/projetoeclipseaulas
```

**Problema: "cannot find symbol: class RestauranteMain"**
```
Solução: Recompile
$ javac -d target/classes -sourcepath src src/restauranterevisaooo/*.java
```

**Problema: "java.util.Scanner.nextLine() does not exist"**
```
Solução: Compile com target Java 8+
$ javac -source 1.8 -target 1.8 ...
```

---

## 📈 PRÓXIMO NÍVEL

Depois de dominar o básico:

1. **Adicione persistência**: Salve pedidos em arquivo
2. **Crie banco de dados**: Use JDBC para armazenar dados
3. **Padrões de design**: Implemente Factory, Singleton
4. **Testes unitários**: Use JUnit para testar classes
5. **Interface gráfica**: Crie GUI com Swing
6. **APIs REST**: Exponha como API com Spring Boot

---

## 📞 PRECISA DE AJUDA?

1. Leia `README.md` - Explicação completa
2. Leia `ESTRUTURA.md` - Organização e mapa
3. Leia comentários no código-fonte
4. Abra DiagramaClasses.puml - Veja estrutura
5. Abra DiagramaSequencia.puml - Veja fluxo

---

**Boa sorte! 🚀**

*Dúvidas? Releia o README.md*
