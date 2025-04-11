package aula01;
import java.util.Scanner;



public class JogoVelha {
    private char[][] tabuleiro;
    private char jogadorAtual;

    public JogoVelha() {
        tabuleiro = new char[3][3];
        inicializarTabuleiro();
        jogadorAtual = 'X';
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = '-';
            }
        }
    }

    public void exibirTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean fazerJogada(int linha, int coluna) {
        if (linha < 0 || linha >= 3 || coluna < 0 || coluna >= 3 || tabuleiro[linha][coluna] != '-') {
            return false;
        }
        tabuleiro[linha][coluna] = jogadorAtual;
        return true;
    }

    public boolean verificarVitoria() {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) {
                return true;
            }
            if (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual) {
                return true;
            }
        }
        if (tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) {
            return true;
        }
        if (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual) {
            return true;
        }
        return false;
    }

    public boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public void alternarJogador() {
        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
    }

    public void iniciarJogo() {
        Scanner scanner = new Scanner(System.in);
        boolean jogoAtivo = true;

        while (jogoAtivo) {
            exibirTabuleiro();
            System.out.println("Jogador " + jogadorAtual + ", faça sua jogada (linha e coluna): ");
            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();

            if (fazerJogada(linha, coluna)) {
                if (verificarVitoria()) {
                    exibirTabuleiro();
                    System.out.println("Jogador " + jogadorAtual + " venceu!");
                    jogoAtivo = false;
                } else if (verificarEmpate()) {
                    exibirTabuleiro();
                    System.out.println("O jogo empatou!");
                    jogoAtivo = false;
                } else {
                    alternarJogador();
                }
            } else {
                System.out.println("Jogada inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        JogoVelha jogo = new JogoVelha();
        jogo.iniciarJogo();
    }
}
