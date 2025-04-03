package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Lab04Historico {
	private int numAge = 0;
	private int numConta = 0;
	private int dia = 0;
	private int mes = 0;
	private int ano = 0;
	private int hora = 0;
	private int min = 0;
	private int seg = 0;
	private int codHist = 0;
	private double valor = 0.0;
	private List<String> vetOperacoes = new ArrayList<String>();

	public Lab04Historico(int numAge, int numConta) {
		super();
		this.numAge = numAge;
		this.numConta = numConta;
	}

	public boolean gravar(int p_hist, double p_valor) {
		// codHist. 1 - Saque e 2 - Deposito
		FileWriter tArq1;
		PrintWriter tArq2;
		try {
			// Operação I - Abrir o aquivo
			tArq1 = new FileWriter(numAge + "." + numConta + ".hist", true);
			tArq2 = new PrintWriter(tArq1);
			Date hoje = new Date();
			Calendar cal = new GregorianCalendar();
			cal.setTime(hoje);
			dia = cal.get(Calendar.DAY_OF_MONTH);
			// O mês em Java inicia com 0
			mes = cal.get(Calendar.MONTH) + 1;
			ano = cal.get(Calendar.YEAR);
			hora = cal.get(Calendar.HOUR);
			min = cal.get(Calendar.MINUTE);
			seg = cal.get(Calendar.SECOND);
			tArq2.print(numAge + " ");
			tArq2.print(numConta + " ");
			tArq2.print(dia + " ");
			tArq2.print(mes + " ");
			tArq2.print(ano + " ");
			tArq2.print(hora + " ");
			tArq2.print(min + " ");
			tArq2.print(seg + " ");
			tArq2.print(p_hist + " ");
			tArq2.println(p_valor);
			// Operação III - Fechar o arquivo
			tArq2.close();
			return true;
		} catch (IOException tExcept) {
			tExcept.printStackTrace();
			return false;
		}
	}

	public void imprimir() {
		recuperarHistorico();
		// GGGG NNNNNNN DD MM AAAA HH MM SS XXX VVVVVVVVVVVVVV
//		System.out.print(this.numAge + " ");
//		System.out.print(this.numConta + " ");
//		System.out.print(this.dia + " ");
//		System.out.print(this.mes + " ");
//		System.out.print(this.ano + " ");
//		System.out.print(this.hora + " ");
//		System.out.print(this.min + " ");
//		System.out.print(this.seg + " ");
//		if (this.codHist == 1)
//			System.out.print("Saque ");
//		else
//			System.out.print("Deposito ");
//		System.out.println(this.valor);
		for (int i=0; i < this.vetOperacoes.size();i++) {
			System.out.println(this.vetOperacoes);
		}

	}

	private void recuperarHistorico() {
		FileReader tArq1;
		BufferedReader tArq2;
		String tLinha = null;
		try {
			// Operação I - Abrir o arquivo
			tArq1 = new FileReader(numAge + "." + numConta + ".hist");
			tArq2 = new BufferedReader(tArq1);
			// Operação III - Ler atributo/valor e colocar na matriz
			while (true) {
				tLinha = tArq2.readLine();
				if (tLinha == null)
					break;
				// Criar vetOperacoes como um atributo do tipo Vector
				vetOperacoes.add(tLinha);
			}
			// Operação IV - Fechar o arquivo
			tArq2.close();
		} catch (FileNotFoundException e) {
			System.out.println("\n Conta sem movimento \n\n");
		} catch (IOException tExcept) {
			tExcept.printStackTrace();
		}
	}

}
