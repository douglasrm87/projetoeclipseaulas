package bancodadossupabse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Classe para gerenciar conexão com Supabase (PostgreSQL)
 * Supabase fornece um banco PostgreSQL gerenciado na nuvem
 */
public class ConexaoSupaBase {
    
    // Configurações do Supabase
    // IMPORTANTE: Atualize estas credenciais com os dados do seu projeto Supabase!
    private static final String SUPABASE_HOST1 =
    System.getenv("SUPABASE_HOST") != null
        ? System.getenv("SUPABASE_HOST")
        : "db.pjqghjdocznjdilgymzr.supabase.co";

        private static final String SUPABASE_HOST = "aws-0-us-east-1.pooler.supabase.com";
private static final int SUPABASE_PORT = 6543;
private static final String SUPABASE_DATABASE = "postgres";
private static final String SUPABASE_USER =    "postgres.pjqghjdocznjdilgymzr";

private static final String SUPABASE_PASSWORD =
    "12EGG2##";
 

 private static final String JDBC_URL = "jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres?user=postgres.pjqghjdocznjdilgymzr&password=12EGG2##&sslmode=require";
 
 

    private static HikariDataSource dataSource;
    private static boolean inicializacaoFalhou = false;
    private static Exception erroInicializacao = null;
    
    /**
     * Inicializa o pool de conexões com HikariCP (Lazy Initialization)
     * Não lança exceção durante o carregamento da classe
     */
    private static synchronized void inicializarPool() {
        if (dataSource != null || inicializacaoFalhou) {
            return; // Já inicializado ou falhou anteriormente
        }
        
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("✓ Driver PostgreSQL carregado com sucesso");
            
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(JDBC_URL);
            config.setUsername(SUPABASE_USER);
            config.setPassword(SUPABASE_PASSWORD);
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setConnectionTimeout(15000); // 15 segundos
            config.setIdleTimeout(600000); // 10 minutos
            config.setMaxLifetime(1800000); // 30 minutos
            config.setLeakDetectionThreshold(15000); // Detectar leaks
            config.setAutoCommit(true);
            
            System.out.println("Conectando ao Supabase: " + SUPABASE_HOST);
            dataSource = new HikariDataSource(config);
            System.out.println("✓ Pool de conexões Supabase inicializado com sucesso!");
            
        } catch (ClassNotFoundException e) {
            inicializacaoFalhou = true;
            erroInicializacao = e;
            System.err.println("✗ ERRO: Driver PostgreSQL não encontrado!");
            System.err.println("  Adicione a dependência postgresql ao pom.xml");
            e.printStackTrace();
        } catch (Exception e) {
            inicializacaoFalhou = true;
            erroInicializacao = e;
            System.err.println("✗ ERRO ao inicializar pool de conexões: " + e.getMessage());
            System.err.println("\nDicas de solução:");
            System.err.println("1. Verifique se as credenciais estão corretas:");
            System.err.println("   - Host: " + SUPABASE_HOST);
            System.err.println("   - User: " + SUPABASE_USER);
            System.err.println("2. Verifique a conexão de rede (ping, DNS)");
            System.err.println("3. Atualize as variáveis de ambiente ou constantes em ConexaoSupaBase.java");
            System.err.println("4. Certifique-se de que o Supabase está acessível");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Testando Conexão com Supabase ===");
        if (conectarSupaBase()) {
            System.out.println("\n✓ Conexão estabelecida com sucesso!");
            testarConsulta();
        } else {
            System.out.println("\n✗ Falha ao conectar");
        }
    }
    
    /**
     * Conecta ao Supabase e testa a conexão
     * @return true se a conexão foi bem-sucedida, false caso contrário
     */
    public static boolean conectarSupaBase() {
        Connection conn = null;
        try {
            inicializarPool();
            
            if (inicializacaoFalhou) {
                System.err.println("✗ Falha anterior impede a conexão");
                System.err.println("  Erro: " + (erroInicializacao != null ? erroInicializacao.getMessage() : "desconhecido"));
                return false;
            }
            
            System.out.println("\nTentando conectar ao Supabase em: " + SUPABASE_HOST);
            
            conn = obterConexao();
            if (conn != null && !conn.isClosed()) {
                System.out.println("✓ Conexão com Supabase estabelecida com sucesso!");
                return true;
            }
            
        } catch (Exception e) {
            System.err.println("✗ Erro ao conectar com Supabase: " + e.getMessage());
            System.err.println("\nTroubleshooting:");
            System.err.println("1. Se recebeu 'connect timed out': Verifique a URL e firewall");
            System.err.println("2. Se recebeu 'authentication failed': Verifique user/password");
            System.err.println("3. Se é 'sslmode': Tente desabilitar SSL ou configurar corretamente");
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    
    /**
     * Obtém uma conexão do pool de conexões
     * @return Connection com o banco de dados
     * @throws Exception se não conseguir obter conexão
     */
    public static Connection obterConexao() throws Exception {
        if (dataSource == null) {
            inicializarPool();
        }
        
        if (dataSource == null || inicializacaoFalhou) {
            throw new Exception("Pool de conexões não foi inicializado. Erro: " + 
                (erroInicializacao != null ? erroInicializacao.getMessage() : "desconhecido"));
        }
        
        return dataSource.getConnection();
    }
    
    /**
     * Executa uma query de teste para validar a conexão
     */
    public static void testarConsulta() {
        inicializarPool();
        
        if (inicializacaoFalhou || dataSource == null) {
            System.err.println("✗ Não é possível executar query: pool de conexões não está disponível");
            return;
        }
        
        try (Connection conn = obterConexao();
             Statement stmt = conn.createStatement()) {
            
            System.out.println("\nExecutando query de teste...");
            ResultSet rs = stmt.executeQuery("SELECT NOW() as tempo_atual, USER as usuario_atual, version() as versao");
            
            if (rs.next()) {
                System.out.println("Tempo do servidor: " + rs.getTimestamp("tempo_atual"));
                System.out.println("Usuário: " + rs.getString("usuario_atual"));
                System.out.println("Versão PostgreSQL: " + rs.getString("versao"));
            }
            rs.close();
            
        } catch (Exception e) {
            System.err.println("Erro ao executar query de teste: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Obtém a URL JDBC do Supabase
     * @return String com a URL JDBC
     */
    public static String obterJdbcUrl() {
        return JDBC_URL;
    }
    
    /**
     * Fecha o pool de conexões (deve ser chamado no shutdown da aplicação)
     */
    public static void fecharPool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            System.out.println("Pool de conexões fechado");
        }
    }
}
