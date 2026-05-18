package bancodadossupabse.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProcessadorDados {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static String formatarData(Date data) {
        return data != null ? dateFormat.format(data) : "N/A";
    }

    public static String formatarMoeda(double valor) {
        return String.format("R$ %.2f", valor);
    }

    public static String formatarPercentual(double valor) {
        return String.format("%.2f%%", valor);
    }

    public static double calcularMedia(double[] valores) {
        if (valores == null || valores.length == 0) return 0;
        double soma = 0;
        for (double v : valores) {
            soma += v;
        }
        return soma / valores.length;
    }

    public static double calcularTotal(double[] valores) {
        if (valores == null || valores.length == 0) return 0;
        double soma = 0;
        for (double v : valores) {
            soma += v;
        }
        return soma;
    }

    public static int calcularIdade(Date dataNascimento) {
        Date hoje = new Date();
        long diferenca = hoje.getTime() - dataNascimento.getTime();
        return (int) (diferenca / (1000L * 60 * 60 * 24 * 365));
    }

    public static String truncarString(String texto, int comprimento) {
        if (texto == null) return "";
        return texto.length() > comprimento ? texto.substring(0, comprimento) + "..." : texto;
    }

    public static boolean estaVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
