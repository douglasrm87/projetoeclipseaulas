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
    private static final String SUPABASE_HOST = "pjqghj mzr.supabase.co";
    private static final String SUPABASE_DATABASE = "postgres";
    private static final String SUPABASE_USER = "postgres";
    private static final String SUPABASE_PASSWORD = "1212##"; // Configurar com a senha correta
    private static final int SUPABASE_PORT = 5432;
    // nslookup pjqghjdocznjdilgymzr.supabase.co 
    //String url = "https://pjqghjdocznjdilgymzr.supabase.co/rest/v1/sua_tabela";
    // URL JDBC para conexão PostgreSQL
    // "jdbc:postgresql://%s:%d/%s?sslmode=require",
    //postgresql://postgres:[YOUR-PASSWORD]@db.pjqghjdocznjdilgymzr.supabase.co:5432/postgres
    private static final String JDBC_URL = String.format(
        "jdbc:postgresql://%s:%d/%s?sslmode=require",
        SUPABASE_HOST,
        SUPABASE_PORT,
        SUPABASE_DATABASE
    );
    
    private static HikariDataSource dataSource;
    
    /**
     * Inicializa o pool de conexões com HikariCP
     */
    static {
        try {
            Class.forName("org.postgresql.Driver");
            
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(JDBC_URL);
            config.setUsername(SUPABASE_USER);
            config.setPassword(SUPABASE_PASSWORD);
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setConnectionTimeout(30000); // 30 segundos
            config.setIdleTimeout(600000); // 10 minutos
            config.setMaxLifetime(1800000); // 30 minutos
            
            dataSource = new HikariDataSource(config);
            System.out.println("Pool de conexões Supabase inicializado com sucesso!");
            
        } catch (ClassNotFoundException e) {
            System.err.println("Erro: Driver PostgreSQL não encontrado. Adicione a dependência ao pom.xml");
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
            System.out.println("Tentando conectar ao Supabase em: " + JDBC_URL);
            
            conn = obterConexao();
            if (conn != null && !conn.isClosed()) {
                System.out.println("✓ Conexão com Supabase estabelecida com sucesso!");
                return true;
            }
            
        } catch (Exception e) {
            System.err.println("✗ Erro ao conectar com Supabase: " + e.getMessage());
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
            throw new Exception("Pool de conexões não foi inicializado");
        }
        return dataSource.getConnection();
    }
    
    /**
     * Executa uma query de teste para validar a conexão
     */
    public static void testarConsulta() {
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
