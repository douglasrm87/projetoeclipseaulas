package bancodadossupabse.util;

public class ValidadorInput {

    public static boolean validarEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean validarMatricula(String matricula) {
        return matricula != null && !matricula.trim().isEmpty() && matricula.length() <= 20;
    }

    public static boolean validarNome(String nome) {
        return nome != null && nome.trim().length() >= 3 && nome.length() <= 255;
    }

    public static boolean validarSalario(double salario) {
        return salario > 0;
    }

    public static boolean validarCEP(String cep) {
        return cep != null && cep.matches("\\d{5}-?\\d{3}");
    }

    public static boolean validarCodigos(String codigo) {
        return codigo != null && !codigo.trim().isEmpty() && codigo.length() <= 10;
    }

    public static String sanitizar(String entrada) {
        if (entrada == null) return "";
        return entrada.trim().replaceAll("[<>\"']", "");
    }
}
