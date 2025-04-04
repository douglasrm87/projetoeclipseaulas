package aula01;
import java.util.Scanner;
import java.util.Base64;

// VS Code Shortcuts for Java:
// - Ctrl+Shift+P: Open Command Palette
// - Ctrl+`: Toggle Terminal
// - Ctrl+Shift+B: Run Build Task
// - F5: Start Debugging
// - Ctrl+F5: Run Without Debugging
// - Ctrl+/: Toggle Line Comment
// - Alt+Shift+F: Format Document
// - Ctrl+Shift+O: Show Outline
// - Ctrl+Shift+M: Show Problems Panel
// - Ctrl+Space: Trigger Suggestion
// - F12: Go to Definition
// - Ctrl+Click: Go to Definition
// - Alt+Left/Right: Navigate Back/Forward
// - Ctrl+Shift+F: Find in Files
// - Ctrl+H: Replace in Files
// - Ctrl+Shift+E: Focus on Explorer
// - Ctrl+Shift+X: Focus on Extensions
// - Ctrl+Shift+G: Focus on Source Control
// - Ctrl+Shift+D: Focus on Debug View
// - Ctrl+Shift+U: Focus on Output Panel

public class LoginConsole {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Encrypt the password using Base64
        String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());

        // Simulate login validation
        System.out.println("Login attempt:");
        System.out.println("Username: " + username);
        System.out.println("Encrypted Password: " + encryptedPassword);

        scanner.close();
    }
}
