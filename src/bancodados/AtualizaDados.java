package bancodados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Lab03ContaCorrenteBancoDados;

public class AtualizaDados {

	public void atualizarDados(Connection con, Lab03ContaCorrenteBancoDados banco) {
		String upd  = "update banco set saldo=? "
				+ "where agencia=? and conta=?";
		try {
			PreparedStatement stmt = con.prepareStatement(upd);
			stmt.setDouble(1, banco.getSaldo());
			stmt.setInt(2, banco.getNumAge());
			stmt.setInt(3, banco.getNumConta());
			stmt.execute();
			System.out.println("Dados atualizados com sucesso.");
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Probleams na atualização da tabela.");
			System.out.println("Mensagem: " + e.getMessage());
			System.out.println("ID: " + e.getSQLState());
		}
	}
}
