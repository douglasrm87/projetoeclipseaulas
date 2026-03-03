package gestaobiblioteca.view;

import gestaobiblioteca.model.Usuario;

public class TelaUsuario {
    public static void main(String[] args) {
            System.out.println("Tela de Usuário");

            Usuario usuario1 = new Usuario();
            usuario1.setId(1);
            System.out.println("ID do usuário: " + usuario1.getId());
            
             // Testando ID inválido
             usuario1.setId(-10);
    }
}
