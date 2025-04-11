package padroesdeprojeto.bridge.livraria;
public class Revista extends LivrariaAbstrata {
    public Revista(LivrariaInterface j) {
        super(j);
    }
    @Override
    public void persistir() {
        obterDados("Ok");
    }
}
