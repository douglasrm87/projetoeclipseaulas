package bancodados;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriaTabelaCCEspecial {

	public void criarTabela(Connection con) {
		try {
			String tabela = "CREATE TABLE " + "BancoEspecial" + " (agencia int NOT NULL,conta int NOT NULL,"
					+ " limite numeric  NOT NULL," 
					+ " PRIMARY KEY (agencia,conta) , "
					+ " CONSTRAINT fk_banco FOREIGN KEY (agencia,conta) " + " REFERENCES Banco (agencia,conta)  )";
			Statement stmt = con.createStatement();
			int ret = stmt.executeUpdate(tabela);
			System.out.println("Tabela BancoEspecial criada com sucesso. ID Retorno:" + ret);
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Problemas na criação da tabela BancoEspecial.");
			System.out.println("Mensagem: " + e.getMessage());
			System.out.println("ID: " + e.getSQLState());
		}
	}
}
