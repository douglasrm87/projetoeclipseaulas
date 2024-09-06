package bancodados;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriaTabela {

	public void criarTabela(Connection con) {
		String tabela = "CREATE TABLE " + "Banco (codigo serial,nome varchar(30) NOT NULL,"
				+ " agencia int NOT NULL,conta int NOT NULL,saldo numeric  NOT NULL," 
				+ " PRIMARY KEY (agencia,conta))";
		try {
			Statement stmt = con.createStatement();
			int ret = stmt.executeUpdate(tabela);
			System.out.println("Tabela criada com sucesso. ID Retorno:" + ret);
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Probleams na criação da tabela.");
			System.out.println("Mensagem: " + e.getMessage());
			System.out.println("ID: " + e.getSQLState());
		}
	}
}
