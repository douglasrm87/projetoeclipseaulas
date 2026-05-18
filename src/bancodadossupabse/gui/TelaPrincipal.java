package bancodadossupabse.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

    private JTabbedPane abas;
    private TelaDashboard telaDashboard;
    private TelaGerenciamentoAlunos telaAlunos;
    private TelaGerenciamentoProfessores telaProfessores;
    private TelaGerenciamentoMatriculas telaMatriculas;
    private TelaGerenciamentoPagamentos telaPagamentos;
    private TelaRelatorios telaRelatorios;

    public TelaPrincipal() {
        configurarJanela();
        criarComponentes();
        exibir();
    }

    private void configurarJanela() {
        setTitle("🎓 Sistema de Gerenciamento Escolar - Supabase");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void criarComponentes() {
        abas = new JTabbedPane();

        telaDashboard = new TelaDashboard();
        telaAlunos = new TelaGerenciamentoAlunos();
        telaProfessores = new TelaGerenciamentoProfessores();
        telaMatriculas = new TelaGerenciamentoMatriculas();
        telaPagamentos = new TelaGerenciamentoPagamentos();
        telaRelatorios = new TelaRelatorios();

        abas.addTab("📊 Dashboard", telaDashboard);
        abas.addTab("👨‍🎓 Alunos", telaAlunos);
        abas.addTab("👨‍🏫 Professores", telaProfessores);
        abas.addTab("📋 Matrículas", telaMatriculas);
        abas.addTab("💳 Pagamentos", telaPagamentos);
        abas.addTab("📈 Relatórios", telaRelatorios);

        criarBarraMenu();
        add(abas, BorderLayout.CENTER);
    }

    private void criarBarraMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemCarregarDados = new JMenuItem("Carregar Dados de Teste");
        itemCarregarDados.addActionListener(e -> carregarDadosTeste());
        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> System.exit(0));
        menuArquivo.add(itemCarregarDados);
        menuArquivo.addSeparator();
        menuArquivo.add(itemSair);

        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem itemSobre = new JMenuItem("Sobre");
        itemSobre.addActionListener(e -> mostrarSobre());
        menuAjuda.add(itemSobre);

        menuBar.add(menuArquivo);
        menuBar.add(menuAjuda);

        setJMenuBar(menuBar);
    }

    private void carregarDadosTeste() {
        int opcao = JOptionPane.showConfirmDialog(this,
                "Deseja carregar dados de teste no banco?\nIsso pode sobrescrever dados existentes.",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            SwingUtilities.invokeLater(() -> {
                try {
                    bancodadossupabse.util.DadosTeste.popularBancoDados();
                    JOptionPane.showMessageDialog(this, "Dados de teste carregados com sucesso!", "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                    atualizarTodasAsAbas();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    private void atualizarTodasAsAbas() {
        telaDashboard.atualizarDados();
        telaAlunos.atualizarTabela();
        telaProfessores.atualizarTabela();
        telaMatriculas.atualizarTabela();
        telaPagamentos.atualizarTabela();
        telaRelatorios.atualizarDados();
    }

    private void mostrarSobre() {
        JOptionPane.showMessageDialog(this,
                "Sistema de Gerenciamento Escolar\n" +
                        "Versão 1.0\n\n" +
                        "Conceitos Educacionais:\n" +
                        "✓ Programação Orientada a Objetos (Herança, Polimorfismo, Interfaces)\n" +
                        "✓ Persistência em Banco de Dados (Supabase/PostgreSQL)\n" +
                        "✓ Processamento Assíncrono (Threads)\n" +
                        "✓ Interface Gráfica (Swing/MVC)\n" +
                        "✓ Padrões de Design (DAO, MVC)",
                "Sobre", JOptionPane.INFORMATION_MESSAGE);
    }

    public void exibir() {
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal());
    }
}
