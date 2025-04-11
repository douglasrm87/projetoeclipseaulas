package padroesdeprojeto.bridge.novosnomes;
public class SOLinux implements SOInterface {
    @Override
    public void desenharJanela(String titulo) {
        System.out.println(titulo + " - Janela Linux");
    }
    @Override
    public void desenharBotao(String titulo) {
        System.out.println(titulo + " - Bot�o Linux");
    }
}
