package restauranterevisaooo;

/**
 * [EXCEÇÃO CUSTOMIZADA - Tratamento de Erros Específicos]
 * 
 * Exceções customizadas permitem:
 * 1. Tratar erros específicos do negócio
 * 2. Diferenciar tipos de erro (pagamento, validação, etc.)
 * 3. Melhorar a experiência do usuário com mensagens claras
 * 
 * Herda de Exception, tornando obrigatório o try-catch
 */
public class RestauranteException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public RestauranteException(String mensagem) {
        super(mensagem);
    }
    
    public RestauranteException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
