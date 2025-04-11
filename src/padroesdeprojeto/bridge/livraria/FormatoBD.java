package padroesdeprojeto.bridge.livraria;
public class FormatoBD implements LivrariaInterface {
    @Override
    public void obterDados(String titulo) {
        System.out.println(titulo + " - Formato banco de dados");
    }
}
