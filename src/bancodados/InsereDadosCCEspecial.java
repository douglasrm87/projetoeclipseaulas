package bancodados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Lab05ContaCorrenteEspecial;

public class InsereDadosCCEspecial {

	public void inserirDados(Connection con, Lab05ContaCorrenteEspecial banco) {
		String ins = "insert into BancoEspecial ( agencia ,conta , limite )" + 
				" VALUES ( ? , ? , ? )";
		try {
			PreparedStatement stmt = con.prepareStatement(ins);
			stmt.setInt(1, banco.getNumAge());
			stmt.setInt(2, banco.getNumConta());
			stmt.setDouble(3, banco.getLimiteCredito());
			stmt.execute();
			System.out.println("Dados inseridos com sucesso na tabela BancoEspecial.");
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Probleams na criação da tabela.");
			System.out.println("Mensagem: " + e.getMessage());
			System.out.println("ID: " + e.getSQLState());
		}
	}
}
