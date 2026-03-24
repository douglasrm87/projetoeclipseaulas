package gestaobiblioteca.model;

public interface Autenticavel {
    // Assinatura do método de autenticação, que deve ser implementado por todas as classes que implementam esta interface
    boolean autenticar(String login, String senha);
    void alterarSenha(String novaSenha);
    
}
