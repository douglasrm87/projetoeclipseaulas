package bancodados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Lab03ContaCorrente;

public class InsereDados {

	public void inserirDados(Connection con, Lab03ContaCorrente banco) {
		String ins = "insert into banco ( nome ,agencia ,conta ,saldo )" + 
				" VALUES ( ? , ? , ? , ? )";
		try {
			PreparedStatement stmt = con.prepareStatement(ins);
			stmt.setString(1, banco.getNome());
			stmt.setInt(2, banco.getNumAge());
			stmt.setInt(3, banco.getNumConta());
			stmt.setDouble(4, banco.getSaldo());
			stmt.execute();
			System.out.println("Dados inseridos com sucesso.");
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Probleams na criação da tabela.");
			System.out.println("Mensagem: " + e.getMessage());
			System.out.println("ID: " + e.getSQLState());
		}
	}
}
