package padroesdeprojeto.bridge.novosnomes;
public class JanelaAviso extends JanelaAbstrata {
    public JanelaAviso(SOInterface j) {
        super(j);
    }
    @Override
    public void desenhar() {
        desenharJanela("Janela de Aviso");
        desenharBotao("Ok");
    }
}
