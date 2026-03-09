package wordzapper.model.view;

import java.util.Scanner;

import wordzapper.model.Usuario;

public class TelaUsuario {
    public static void main(String[] args) {
        System.out.println("Bem-vindo à Tela do Usuário!");

        Usuario usuario1 = new Usuario();
        Scanner captura = new Scanner(System.in);
        System.out.print("Digite o ID do usuário: ");
        int id = captura.nextInt();
        usuario1.setId(id); 
        System.out.println("ID do usuário: " + usuario1.getId());
    }
}
