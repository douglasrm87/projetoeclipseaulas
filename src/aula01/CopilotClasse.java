package aula01;

public class CopilotClasse {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("Digite o valor de a:");
        double a = scanner.nextDouble();

        System.out.println("Digite o valor de b:");
        double b = scanner.nextDouble();

        System.out.println("Digite o valor de c:");
        double c = scanner.nextDouble();

        double delta = (b * b) - (4 * a * c);

        if (delta < 0) {
            System.out.println("A equação não possui raízes reais.");
        } else {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);

            System.out.println("As raízes da equação são:");
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);
        }

        scanner.close();
    }
}
