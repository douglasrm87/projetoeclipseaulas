package padroesdeprojeto.bridge.novosnomes;

public class Principal {
	public static void main(String[] args) {
	    JanelaAbstrata janela = new JanelaDialogo(new SOMac());
	    janela.desenhar();

	}
}
