# Guia de Configuração Supabase - Passo a Passo

## 🔴 Erro Recebido
```
java.net.SocketTimeoutException: connect timed out
```

Este erro significa que o programa está tentando conectar mas não consegue alcançar o servidor Supabase.

---

## ✅ Solução: 3 Opções

### **Opção 1: Usar Variáveis de Ambiente (RECOMENDADO)**

#### Linux/Mac - Terminal:
```bash
export SUPABASE_HOST="seu_host_aqui.supabase.co"
export SUPABASE_USER="postgres"
export SUPABASE_PASSWORD="sua_senha_aqui"

# Compile e execute
mvn clean compile
mvn exec:java -Dexec.mainClass="bancodadossupabse.ExemploConexaoSupaBase"
```

#### Windows - PowerShell:
```powershell
$env:SUPABASE_HOST = "seu_host_aqui.supabase.co"
$env:SUPABASE_USER = "postgres"
$env:SUPABASE_PASSWORD = "sua_senha_aqui"

# Compile e execute
mvn clean compile
mvn exec:java -Dexec.mainClass="bancodadossupabse.ExemploConexaoSupaBase"
```

---

### **Opção 2: Editar o Arquivo Java**

Abra `src/bancodadossupabse/ConexaoSupaBase.java` e procure por estas linhas (linhas 16-20):

```java
private static final String SUPABASE_HOST = System.getenv("SUPABASE_HOST") != null ? 
    System.getenv("SUPABASE_HOST") : "seu_host_aqui.supabase.co";
private static final String SUPABASE_DATABASE = "postgres";
private static final String SUPABASE_USER = System.getenv("SUPABASE_USER") != null ? 
    System.getenv("SUPABASE_USER") : "postgres";
private static final String SUPABASE_PASSWORD = System.getenv("SUPABASE_PASSWORD") != null ? 
    System.getenv("SUPABASE_PASSWORD") : "sua_senha_aqui";
```

Substitua os valores padrão com seus dados reais:
- `seu_host_aqui.supabase.co` → seu ID do projeto Supabase
- `sua_senha_aqui` → sua senha do banco de dados

---

### **Opção 3: Criar Arquivo `.env`**

1. Crie arquivo `.env` na raiz do projeto:
```
SUPABASE_HOST=seu_projeto.supabase.co
SUPABASE_USER=postgres
SUPABASE_PASSWORD=sua_senha
```

2. Modifique o início da classe para ler o `.env`:
```java
private static final String SUPABASE_HOST = getDotEnvValue("SUPABASE_HOST", "seu_projeto.supabase.co");
```

---

## 🔍 Como Obter Suas Credenciais Supabase

1. Acesse https://app.supabase.com
2. Clique no seu projeto
3. Vá em **Project Settings** → **Database**
4. Procure pela seção "Connection info"
5. Você verá:
   - **Host**: `[seu_projeto_id].supabase.co`
   - **Port**: `5432`
   - **Database**: `postgres`
   - **User**: `postgres`
   - **Password**: (a senha que você definiu)

### Exemplo de URL JDBC gerada:
```
jdbc:postgresql://seu_projeto.supabase.co:5432/postgres?sslmode=require&connectTimeout=10
```

---

## 🧪 Testando a Conexão

Após configurar, execute:

```bash
mvn exec:java -Dexec.mainClass="bancodadossupabse.ConexaoSupaBase"
```

### ✓ Sucesso:
```
✓ Pool de conexões Supabase inicializado com sucesso!
✓ Conexão com Supabase estabelecida com sucesso!
```

### ✗ Erro ainda ocorre?

#### Se recebeu `connect timed out`:
- [ ] Verifique o hostname/host (sem typos)
- [ ] Verifique se a URL é acessível: `ping seu_projeto.supabase.co`
- [ ] Verifique firewall/rede
- [ ] Tente desabilitar SSL: remova `?sslmode=require` da URL

#### Se recebeu `authentication failed`:
- [ ] Verifique user/password (case-sensitive)
- [ ] Verifique se a senha não contém caracteres especiais que precisam escape

#### Se recebeu erro de driver:
- [ ] Certifique-se de rodar `mvn clean compile` primeiro
- [ ] Verifique se o `pom.xml` contém `org.postgresql:postgresql`

---

## 📝 Melhorias Implementadas

O código foi corrigido com:
- ✓ Inicialização lazy (não trava na carga da classe)
- ✓ Melhor tratamento de erros com mensagens úteis
- ✓ Suporte a variáveis de ambiente
- ✓ Timeouts configuráveis
- ✓ Mensagens de troubleshooting automáticas

---

## 🆘 Precisa de Help?

Se ainda tiver problemas:
1. Verifique se consegue conectar pelo terminal: `psql -h seu_host.supabase.co -U postgres -d postgres`
2. Verifique o console de logs do Supabase em `app.supabase.com`
3. Tente criar um novo projeto Supabase com credenciais novas
