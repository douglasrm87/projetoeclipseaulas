package padroesdeprojeto.bridge.livraria;
public class Livro extends LivrariaAbstrata {
    public Livro(LivrariaInterface j) {
        super(j);
    }
    @Override
    public void persistir() {
        obterDados("Dados ser�o mantidos em:");
    }
}
