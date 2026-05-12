# Configuração de Conexão com Supabase

## Instruções de Configuração

### 1. Obter Credenciais do Supabase

1. Acesse [https://supabase.com](https://supabase.com) e faça login
2. Crie um novo projeto ou selecione um existente
3. Na seção "Project Settings" → "Database", encontre:
   - **Host**: Será mostrado como `[PROJECT_ID].supabase.co`
   - **Database Name**: `postgres` (padrão)
   - **User**: `postgres` (padrão)
   - **Password**: Configurado durante a criação do projeto
   - **Port**: `5432` (padrão)

### 2. Configurar a Classe ConexaoSupaBase.java

Abra o arquivo [src/bancodadossupabse/ConexaoSupaBase.java](src/bancodadossupabse/ConexaoSupaBase.java) e atualize as constantes:

```java
private static final String SUPABASE_HOST = "seu_projeto_id.supabase.co";
private static final String SUPABASE_DATABASE = "postgres";
private static final String SUPABASE_USER = "postgres";
private static final String SUPABASE_PASSWORD = "sua_senha_aqui";
private static final int SUPABASE_PORT = 5432;
```

### 3. Verificar Dependências no pom.xml

O arquivo [pom.xml](pom.xml) já contém:
- ✓ PostgreSQL JDBC Driver (42.7.3)
- ✓ HikariCP para pool de conexões (5.1.0)
- ✓ SLF4J para logging

### 4. Compilar e Testar

```bash
# Compilar o projeto
mvn clean compile

# Executar a classe de teste
mvn exec:java -Dexec.mainClass="bancodadossupabse.ConexaoSupaBase"
```

## Recursos da Classe ConexaoSupaBase

### Métodos Disponíveis

#### `obterConexao()`
Obtém uma conexão do pool de conexões HikariCP

```java
try (Connection conn = ConexaoSupaBase.obterConexao()) {
    // Usar a conexão
} catch (Exception e) {
    e.printStackTrace();
}
```

#### `conectarSupaBase()`
Testa a conexão com o Supabase

```java
if (ConexaoSupaBase.conectarSupaBase()) {
    System.out.println("Conectado com sucesso!");
}
```

#### `testarConsulta()`
Executa uma query de teste para validar a conexão

```java
ConexaoSupaBase.testarConsulta();
```

#### `fecharPool()`
Fecha o pool de conexões (deve ser chamado no shutdown)

```java
ConexaoSupaBase.fecharPool();
```

#### `obterJdbcUrl()`
Retorna a URL JDBC completa

```java
String url = ConexaoSupaBase.obterJdbcUrl();
System.out.println(url);
```

## Exemplo de Uso Completo

```java
package bancodadossupabse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExemploUso {
    public static void main(String[] args) {
        try {
            // Conectar
            if (ConexaoSupaBase.conectarSupaBase()) {
                // Obter conexão
                try (Connection conn = ConexaoSupaBase.obterConexao();
                     Statement stmt = conn.createStatement()) {
                    
                    // Executar query
                    ResultSet rs = stmt.executeQuery("SELECT * FROM sua_tabela LIMIT 10");
                    
                    while (rs.next()) {
                        System.out.println(rs.getInt("id") + " - " + rs.getString("nome"));
                    }
                    rs.close();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } finally {
            // Fechar pool ao encerrar
            ConexaoSupaBase.fecharPool();
        }
    }
}
```

## Configuração Avançada - Pool de Conexões

O arquivo utiliza HikariCP com as seguintes configurações padrão:

- **Maximum Pool Size**: 10 conexões
- **Minimum Idle**: 2 conexões
- **Connection Timeout**: 30 segundos
- **Idle Timeout**: 10 minutos
- **Max Lifetime**: 30 minutos

Para personalizar, edite o bloco `static {}` na classe ConexaoSupaBase.

## Solução de Problemas

### Erro: "Falha na autenticação"
- Verifique as credenciais (usuário, senha)
- Verifique se o IP está autorizado no Supabase (Database > Logs → verificar IP)

### Erro: "Conexão recusada"
- Verifique o host e porta
- Verifique se o projeto Supabase está online
- Verifique a conectividade de rede

### Erro: "Pool de conexões não foi inicializado"
- Verifique se o Driver PostgreSQL foi carregado
- Verifique o pom.xml contém as dependências

## Recursos Úteis

- [Documentação Supabase](https://supabase.com/docs)
- [Documentação PostgreSQL JDBC](https://jdbc.postgresql.org/)
- [Documentação HikariCP](https://github.com/brettwooldridge/HikariCP)
