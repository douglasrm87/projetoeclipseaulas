package padroesdeprojeto.bridge.novosnomes;
public class JanelaDialogo extends JanelaAbstrata {
    public JanelaDialogo(SOInterface j) {
        super(j);
    }
    @Override
    public void desenhar() {
        desenharJanela("Janela de Di�logo");
        desenharBotao("Bot�o Sim");
        desenharBotao("Bot�o N�o");
        desenharBotao("Bot�o Cancelar");
    }
}
