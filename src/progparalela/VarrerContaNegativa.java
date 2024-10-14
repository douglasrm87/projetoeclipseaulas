package progparalela;

import java.sql.Connection;
import java.util.List;

import bancodados.ConexaoBancoDados;
import bancodados.SelecionaDados;
import model.Lab05ContaCorrenteEspecial;

public class VarrerContaNegativa extends Thread {
	public void run() {
		while (true) {
			try {
				Thread.sleep(15000);// 15 segundos
			} catch (InterruptedException e) {
				System.out.println("\n*** Sistema de varredura encerrado.\n*** ");
				break;
			}
			SelecionaDados s = new SelecionaDados();
			ConexaoBancoDados conexPost = new ConexaoBancoDados();
			Connection con = conexPost.conectarBanco();
			List<Lab05ContaCorrenteEspecial> ret = s.selecionarContaNegativa(con);
			if (ret.size() > 0) {
				System.out.println("\n*** Conta negativa: ***\n" + ret);
				System.out.println("\nTemos uma oportunidade de empréstimo entre em contato com seu gerente pelo telefone 0800909060.");
			}
		}
	}

//	public static void main(String[] args) {
//		SelecionaDados s = new SelecionaDados();
//		ConexaoBancoDados conexPost = new ConexaoBancoDados();
//		Connection con = conexPost.conectarBanco();
//		System.out.println(s.selecionarContaNegativa(con));
//	}
}