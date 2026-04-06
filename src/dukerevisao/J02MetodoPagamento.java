package dukerevisao;

@FunctionalInterface // [Anotação] Indica que aceita Lambda por ter apenas 1 método
public interface J02MetodoPagamento {
    void processar(double valor) throws J01PagamentoException;
}
