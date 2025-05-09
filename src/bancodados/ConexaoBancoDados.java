package bancodados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// Primeira classe necessária para interagir com banco de dados. 
public class ConexaoBancoDados {
	public Connection conectarBanco() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = 
            		"jdbc:postgresql://localhost:5432/postgres";
            Connection con = DriverManager.getConnection(url,
            		"postgres", "12345");
            return con;// Se tudo bem retorna uma conexão.
        } catch (,,ClassNotFoundException e) {
        	System.out.println("Driver informado esta incorreto ou JAR não está presente em seu ambiente.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
}
