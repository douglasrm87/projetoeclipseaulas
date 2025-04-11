package padroesdeprojeto.bridge.livraria;
public abstract class LivrariaAbstrata { 
    protected LivrariaInterface formatoGravacao;
    public LivrariaAbstrata(LivrariaInterface j) {
        formatoGravacao = j;
    }
    public void obterDados(String titulo) {
        formatoGravacao.obterDados(titulo);
    }
    public abstract void persistir();
}
