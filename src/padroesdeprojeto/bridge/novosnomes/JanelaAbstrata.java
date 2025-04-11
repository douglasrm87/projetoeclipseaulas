package padroesdeprojeto.bridge.novosnomes;
public abstract class JanelaAbstrata { 
    protected SOInterface janela;
    public JanelaAbstrata(SOInterface j) {
        janela = j;
    }
    public void desenharJanela(String titulo) {
        janela.desenharJanela(titulo);
    }
    public void desenharBotao(String titulo) {
        janela.desenharBotao(titulo);
    }
    public abstract void desenhar();
}
