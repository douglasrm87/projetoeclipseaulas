package bancodadossupabse.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void info(String mensagem) {
        System.out.println("[" + timestamp() + "] ℹ️  INFO: " + mensagem);
    }

    public static void sucesso(String mensagem) {
        System.out.println("[" + timestamp() + "] ✓ SUCESSO: " + mensagem);
    }

    public static void aviso(String mensagem) {
        System.out.println("[" + timestamp() + "] ⚠️  AVISO: " + mensagem);
    }

    public static void erro(String mensagem) {
        System.err.println("[" + timestamp() + "] ✗ ERRO: " + mensagem);
    }

    public static void debug(String mensagem) {
        if (isDebugMode()) {
            System.out.println("[" + timestamp() + "] 🔧 DEBUG: " + mensagem);
        }
    }

    public static void separador() {
        System.out.println("═".repeat(60));
    }

    private static String timestamp() {
        return sdf.format(new Date());
    }

    private static boolean isDebugMode() {
        return "true".equalsIgnoreCase(System.getenv("DEBUG"));
    }
}
