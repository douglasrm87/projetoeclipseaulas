package bancodados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Lab03ContaCorrente;
import model.Lab03ContaCorrenteBancoDados;

public class SelecionaDados {

	public void selecionarDados(Connection con, Lab03ContaCorrenteBancoDados banco) {
		String ins = "Select nome ,agencia ,conta ,saldo " + 
					"From Banco where agencia = ? and conta = ? ";
		try {
			PreparedStatement stmt = con.prepareStatement(ins);
			stmt.setInt(1, banco.getNumAge());
			stmt.setInt(2, banco.getNumConta());
			ResultSet ret = stmt.executeQuery();
			System.out.println("Dados selecionados:");
			while (ret.next()) {
				System.out.println("Agencia: "+ret.getInt("agencia"));
				System.out.println("Conta: "+ret.getInt("conta"));
				System.out.println("Nome: "+ret.getString("nome"));
				banco.setNome(ret.getString("nome"));
				System.out.println("Saldo: "+ret.getDouble("saldo"));
				banco.setSaldo(ret.getDouble("saldo"));
				
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Probleams na criação da tabela.");
			System.out.println("Mensagem: " + e.getMessage());
			System.out.println("ID: " + e.getSQLState());
		}
	}
}
