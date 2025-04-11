package padroesdeprojeto.bridge.novosnomes;
public class SOMac implements SOInterface {
    @Override
    public void desenharJanela(String titulo) {
        System.out.println(titulo + " - Janela MAC");
    }
    @Override
    public void desenharBotao(String titulo) {
        System.out.println(titulo + " - Bot�o MAC");
    }
}
