package bancodados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Lab03ContaCorrente;
import model.Lab03ContaCorrenteBancoDados;
import model.Lab05ContaCorrenteEspecial;

public class SelecionaDados {

	public void selecionarDados(Connection con, Lab03ContaCorrenteBancoDados banco) {
		String ins = "Select nome ,agencia ,conta ,saldo " + "From Banco where agencia = ? and conta = ? ";
		try {
			PreparedStatement stmt = con.prepareStatement(ins);
			stmt.setInt(1, banco.getNumAge());
			stmt.setInt(2, banco.getNumConta());
			ResultSet ret = stmt.executeQuery();
			System.out.println("Dados selecionados:");
			while (ret.next()) {
				System.out.println("Agencia: " + ret.getInt("agencia"));
				System.out.println("Conta: " + ret.getInt("conta"));
				System.out.println("Nome: " + ret.getString("nome"));
				banco.setNome(ret.getString("nome"));
				System.out.println("Saldo: " + ret.getDouble("saldo"));
				banco.setSaldo(ret.getDouble("saldo"));

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Probleams na criação da tabela.");
			System.out.println("Mensagem: " + e.getMessage());
			System.out.println("ID: " + e.getSQLState());
		}
	}

	public List<Lab05ContaCorrenteEspecial> selecionarContaNegativa(Connection con) {
		// Vamos utiliza Join em nosso exemplo
		String ins = " Select b.nome ,b.agencia ,b.conta ,b.saldo , be.limite" + 
					 " From Banco b , bancoespecial be where b.saldo < 0"
					+" and b.agencia = be.agencia and b.conta = be.conta";
		List<Lab05ContaCorrenteEspecial> ccLista = new ArrayList<Lab05ContaCorrenteEspecial>();
		try {
			Lab05ContaCorrenteEspecial cc = null;
			PreparedStatement stmt = con.prepareStatement(ins);
			ResultSet ret = stmt.executeQuery();
			while (ret.next()) {
				cc = new Lab05ContaCorrenteEspecial(ret.getInt("agencia"), ret.getInt("conta"), ret.getString("nome"),
						ret.getDouble("saldo"), ret.getDouble("limite"));
				ccLista.add(cc);
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Probleams na seleção de dados da tabela.");
			System.out.println("Mensagem: " + e.getMessage());
			System.out.println("ID: " + e.getSQLState());
		}
		return ccLista;
	}
	public void selecionarLimiteSaldo(Connection con, Lab05ContaCorrenteEspecial banco) {
		String ins = " Select  b.saldo , be.limite" + 
				 " From Banco b , bancoespecial be where b.agencia = ? and b.conta = ? "
				+" and b.agencia = be.agencia and b.conta = be.conta";
		try {
			PreparedStatement stmt = con.prepareStatement(ins);
			stmt.setInt(1, banco.getNumAge());
			stmt.setInt(2, banco.getNumConta());
			ResultSet ret = stmt.executeQuery();
			while (ret.next()) {
				banco.setSaldo(ret.getDouble("saldo"));
				banco.setLimiteCredito(ret.getDouble("limite"));
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Problemas na seleção de dados da tabela.");
			System.out.println("Mensagem: " + e.getMessage());
			System.out.println("ID: " + e.getSQLState());
		}
	}
}