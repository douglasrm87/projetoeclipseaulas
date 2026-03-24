package gestaobiblioteca.view;
import java.util.Scanner;

import gestaobiblioteca.model.Administrador;
import gestaobiblioteca.model.Aluno;
import gestaobiblioteca.model.Perfil;
import gestaobiblioteca.model.Professor;
import gestaobiblioteca.model.Usuario;

public class MenuPrincipal {

    public Usuario login() {
        // Lógica para exibir a tela de login
        System.out.println("Exibindo tela de login...");
        // Aqui você pode implementar a lógica para autenticar o usuário
        Scanner leia = new Scanner(System.in);
        System.out.print("Digite seu Login: ");
        String login = leia.nextLine();
        System.out.print("Digite sua Senha: ");
        String senha = leia.nextLine();
       // Escolher seu perfil
       System.out.println("Escolha seu perfil:");
       System.out.println("1. Administrador");
       System.out.println("2. Professor");
       System.out.println("3. Aluno");
       int perfilEscolhido = leia.nextInt();
       Perfil perfil = null;
       Usuario usuario = null; // Aqui você pode criar um objeto do tipo Usuario com os dados fornecidos
       switch (perfilEscolhido) {
           case 1:
               perfil = Perfil.ADMIN;
               usuario = new Administrador(login, senha, perfil);
               break;
           case 2:
               perfil = Perfil.PROFESSOR;
            usuario = new Professor(login, senha, perfil);
               break;
           case 3:
               perfil = Perfil.ALUNO;
               usuario = new Aluno(login, senha, perfil);            
               break;
           default:
               System.out.println("Perfil inválido.");
       }
       if (usuario != null && usuario.autenticar(login, senha)) {
           System.out.println("Login realizado com sucesso!");
       } else {
           System.out.println("Credenciais inválidas.");
           throw new RuntimeException("Login falhou. Credenciais inválidas. Encerrando programa");
       }
       return usuario;

    }

    public void exibirMenu() {
        System.out.println("Bem-vindo ao Sistema de Gestão de Biblioteca!");
        System.out.println("1. Login");
        System.out.println("4. Sair");
        Usuario usuarioRetornado = null;
        Scanner leia = new Scanner(System.in);
        int opcao = leia.nextInt();
        try {
            switch (opcao) {
                case 1:
                    System.out.println("Opção de Login selecionada.");
                    // Aqui você pode chamar o método para exibir a tela de login
                    usuarioRetornado = login();
                    break;
                case 4:
                    System.out.println("Saindo do sistema. Até logo!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }

         } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        // Polimorfismo: O objeto usuarioRetornado pode ser do tipo Administrador, Professor ou Aluno, mas todos eles são tratados como objetos do tipo Usuario.
         if (usuarioRetornado != null) {
             System.out.println("Exibindo menu para o usuário autenticado..."); 
             // Aqui temos o polimorfismo, pois o método exibirmenu() pode ter implementações diferentes para cada tipo de usuário (Administrador, Professor, Aluno), mas todos eles são tratados como objetos do tipo Usuario.
             usuarioRetornado.exibirmenu();
         }
        }
}
