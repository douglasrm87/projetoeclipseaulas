package padroesdeprojeto.bridge;
public class JanelaLinux implements JanelaImplementada {
    @Override
    public void desenharJanela(String titulo) {
        System.out.println(titulo + " - Janela Linux");
    }
    @Override
    public void desenharBotao(String titulo) {
        System.out.println(titulo + " - Bot�o Linux");
    }
}
