package bancodadossupabse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 * Exemplo de uso da classe ConexaoSupaBase
 * Demonstra operações CRUD básicas com Supabase
 */
public class ExemploConexaoSupaBase {
    
    public static void main(String[] args) {
        System.out.println("=== Exemplo de Uso com Supabase ===\n");
        
        // 1. Testar conexão
        testarConexao();
        
        // 2. Criar tabela de exemplo
        criarTabelaExemplo();
        
        // 3. Inserir dados
        inserirDados();
        
        // 4. Consultar dados
        consultarDados();
        
        // 5. Fechar pool
        ConexaoSupaBase.fecharPool();
    }
    
    /**
     * Testa a conexão com Supabase
     */
    public static void testarConexao() {
        try {
            System.out.println("1. Testando conexão com Supabase...");
            if (ConexaoSupaBase.conectarSupaBase()) {
                System.out.println("   ✓ Conexão bem-sucedida!\n");
            } else {
                System.out.println("   ✗ Falha na conexão!\n");
                return;
            }
        } catch (Exception e) {
                System.err.println("   ✗ Erro ao testar conexão: " +             e.getMessage());
            //.printStackTrace();
        }
    }
    
    /**
     * Cria uma tabela de exemplo
     */
    public static void criarTabelaExemplo() {
        System.out.println("2. Criando tabela de exemplo...");
        
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "    id SERIAL PRIMARY KEY," +
                "    nome VARCHAR(100) NOT NULL," +
                "    email VARCHAR(100) NOT NULL UNIQUE," +
                "    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        
        try (Connection conn = ConexaoSupaBase.obterConexao();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(sql);
            System.out.println("   ✓ Tabela 'usuarios' criada (ou já existe)\n");
            
        } catch (Exception e) {
            System.err.println("   ✗ Erro ao criar tabela: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Insere dados de exemplo
     */
    public static void inserirDados() {
        System.out.println("3. Inserindo dados de exemplo...");
        
        String[][] usuarios = {
            {"João Silva", "joao@example.com"},
            {"Maria Santos", "maria@example.com"},
            {"Pedro Oliveira", "pedro@example.com"}
        };
        
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?) " +
                     "ON CONFLICT (email) DO NOTHING";
        
        try (Connection conn = ConexaoSupaBase.obterConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            for (String[] usuario : usuarios) {
                pstmt.setString(1, usuario[0]);
                pstmt.setString(2, usuario[1]);
                pstmt.addBatch();
            }
            
            int[] resultados = pstmt.executeBatch();
            System.out.println("   ✓ " + resultados.length + " registro(s) processado(s)\n");
            
        } catch (Exception e) {
            System.err.println("   ✗ Erro ao inserir dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Consulta e exibe os dados
     */
    public static void consultarDados() {
        System.out.println("4. Consultando dados...");
        
        String sql = "SELECT id, nome, email, data_criacao FROM usuarios ORDER BY id";
        
        try (Connection conn = ConexaoSupaBase.obterConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("   Usuários cadastrados:");
            System.out.println("   " + String.format("%-5s %-25s %-25s %s", 
                    "ID", "Nome", "Email", "Data Criação"));
            System.out.println("   " + "-".repeat(85));
            
            while (rs.next()) {
                System.out.println("   " + String.format("%-5d %-25s %-25s %s", 
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getTimestamp("data_criacao")));
            }
            System.out.println();
            
        } catch (Exception e) {
            System.err.println("   ✗ Erro ao consultar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
