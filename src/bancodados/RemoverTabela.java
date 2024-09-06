package bancodados;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RemoverTabela {

	public void removerTabela(Connection con) {
		String tabela = "DROP TABLE Banco";
		try {
			Statement stmt = con.createStatement();
			int ret = stmt.executeUpdate(tabela);
			System.out.println("Tabela removida com sucesso. ID Retorno:" + ret);
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Probleams na remoção da tabela.");
			System.out.println("Mensagem: " + e.getMessage());
			System.out.println("ID: " + e.getSQLState());
		}
	}
}
