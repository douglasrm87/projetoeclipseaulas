package bancodados;

import java.sql.Connection;
import java.sql.SQLException;

import model.Lab03ContaCorrente;

public class GestaoBancoDados {
	public static void main(String[] args) {
		ConexaoBancoDados objLocal = new ConexaoBancoDados();
		Connection conex = objLocal.conectarBanco();
		if (conex != null) {
			System.out.println("Conectado com sucesso no Postgree.");
			CriaTabela cr = new CriaTabela();
			RemoverTabela rem = new RemoverTabela();
			InsereDados ins = new InsereDados();
			SelecionaDados sel = new SelecionaDados();
			try {
				//rem.removerTabela(conex);
				//cr.criarTabela(conex);
				//ins.inserirDados(conex, new Lab03ContaCorrente(10,20,"Douglas Mendes",100.50));
				sel.selecionarDados(conex, new Lab03ContaCorrente(10,20));
				conex.close();
				System.out.println("Conexão encerrada com sucesso.");
			} catch (SQLException e) {
				System.out.println("Problemas para encerrar a conexão.");
				e.printStackTrace();
			}
		}
	}
}
