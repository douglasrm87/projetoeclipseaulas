# ⚠️ Problema de Compatibilidade - SOLUÇÃO

## Problema
```
Apache Maven 4.x requires Java 17 or newer to run.
Currently installed: Java 11.0.14
```

## Soluções

### Opção 1: Usar Maven via Docker (Recomendado)

```bash
# Testar compilação
docker run --rm -v "$PWD":/workspace -w /workspace maven:3.8-openjdk-11 mvn clean compile

# Testar conexão  
docker run --rm \
  -e SUPABASE_HOST="seu_host.supabase.co" \
  -e SUPABASE_PASSWORD="sua_senha" \
  -v "$PWD":/workspace -w /workspace maven:3.8-openjdk-11 \
  mvn exec:java -Dexec.mainClass="bancodadossupabse.ConexaoSupaBase"
```

### Opção 2: Downgrade Maven para 3.8.x

```bash
# Remover Maven 4.x
sudo apt-get remove maven

# Instalar Maven 3.8.x
sudo apt-get install maven=3.8.*

# Verificar
mvn -v
```

### Opção 3: Instalar Java 17/21

```bash
# Adicionar PPA
sudo apt-get install software-properties-common
sudo add-apt-repository ppa:openjdk-r/ppa
sudo apt-get update

# Instalar Java 21
sudo apt-get install openjdk-21-jdk

# Verificar qual versão está como padrão
sudo update-alternatives --config java

# Usar Java 21 para Maven
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
mvn -v
mvn clean compile
```

### Opção 4: Usar mvnw (Maven Wrapper)

Se o projeto usar Maven Wrapper:
```bash
./mvnw clean compile
```

---

## 🎯 Recomendação Imediata

Para testar rápido **sem instalar nada novo**:

```bash
# Compilar manualmente com javac
javac -d target/classes -cp "target/dependency/*" src/bancodadossupabse/*.java

# Executar
java -cp target/classes:target/dependency/* \
  -DSUPABASE_HOST="seu_host.supabase.co" \
  -DSUPABASE_PASSWORD="sua_senha" \
  bancodadossupabse.ConexaoSupaBase
```

---

## Alternativa: Usar VS Code

Se estiver usando VS Code com Extension Pack for Java:
1. Abra o projeto no VS Code
2. Clique em "Run" no main method de ExemploConexaoSupaBase.java
3. Configure as variáveis de ambiente nas configurações de launch

O VS Code tem seus próprios compiladores e não depende da versão do Maven.

---

## Status das Correções

Todas as correções de código foram implementadas em:
- ✅ [ConexaoSupaBase.java](src/bancodadossupabse/ConexaoSupaBase.java)
  - Lazy initialization
  - Melhor tratamento de erros
  - Suporte a variáveis de ambiente
  
- ✅ [Guia de Configuração](SUPABASE_CONFIG_PASSO_A_PASSO.md)
- ✅ [Script de Configuração](configure-supabase.sh)

O código está correto, apenas há problema com a ferramenta de build!

