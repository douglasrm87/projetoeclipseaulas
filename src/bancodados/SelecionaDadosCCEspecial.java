package bancodados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Lab05ContaCorrenteEspecial;

public class SelecionaDadosCCEspecial {

	public void selecionarDados(Connection con, Lab05ContaCorrenteEspecial banco) {
		String ins = "Select agencia ,conta , limite " + 
					"From BancoEspecial where agencia = ? and conta = ? ";
		try {
			PreparedStatement stmt = con.prepareStatement(ins);
			stmt.setInt(1, banco.getNumAge());
			stmt.setInt(2, banco.getNumConta());
			ResultSet ret = stmt.executeQuery();
			System.out.println("Dados selecionados:");
			while (ret.next()) {
				System.out.println("Agencia: "+ret.getInt("agencia"));
				System.out.println("Conta: "+ret.getInt("conta"));
				System.out.println("Limite: "+ret.getDouble("limite"));
 			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Probleams na seleção da tabela BancoEspecial.");
			System.out.println("Mensagem: " + e.getMessage());
			System.out.println("ID: " + e.getSQLState());
		}
	}
}
