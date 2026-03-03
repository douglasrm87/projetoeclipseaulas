package wordzapper.model.view;

import wordzapper.model.Usuario;

public class TelaUsuario {
    public static void main(String[] args) {
        System.out.println("Bem-vindo à Tela do Usuário!");

        Usuario usuario1 = new Usuario();
        usuario1.setId(-1);
        System.out.println("ID do usuário: " + usuario1.getId());
    }
}
