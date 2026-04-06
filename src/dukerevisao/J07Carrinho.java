package dukerevisao;

import java.util.ArrayList;
import java.util.List;

public class J07Carrinho {
    // [Agregação] - O Carrinho "tem" produtos, mas os produtos existem sem o carrinho.
    private List<J05Produto> itens = new ArrayList<>();

    public void adicionarItem(J05Produto p) {
        itens.add(p);
    }

    public double calcularTotal() {
        return itens.stream().mapToDouble(J05Produto::getPreco).sum();
    }
}
