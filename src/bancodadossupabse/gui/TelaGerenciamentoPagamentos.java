package bancodadossupabse.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import bancodadossupabse.dao.PagamentoDAO;
import bancodadossupabse.model.Pagamento;
import bancodadossupabse.model.PagamentoPix;
import bancodadossupabse.thread.ProcessadorPagamentoThread;
import bancodadossupabse.thread.GerenciadorThreads;
import bancodadossupabse.util.ProcessadorDados;

public class TelaGerenciamentoPagamentos extends JPanel {

    private JTable tabelaPagamentos;
    private DefaultTableModel modelo;
    private PagamentoDAO pagamentoDAO;
    private JLabel labelStatus;
    private GerenciadorThreads gerenciadorThreads;

    public TelaGerenciamentoPagamentos() {
        this.pagamentoDAO = new PagamentoDAO();
        this.gerenciadorThreads = new GerenciadorThreads();
        inicializar();
    }

    private void inicializar() {
        setLayout(new BorderLayout());

        JPanel panelBotoes = criarPanelBotoes();
        add(panelBotoes, BorderLayout.NORTH);

        tabelaPagamentos = new JTable();
        modelo = new DefaultTableModel(new String[]{
                "ID", "Valor", "Tipo", "Processado"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaPagamentos.setModel(modelo);
        JScrollPane scroll = new JScrollPane(tabelaPagamentos);
        add(scroll, BorderLayout.CENTER);

        labelStatus = new JLabel("🔄 Pronto para processar pagamentos");
        labelStatus.setFont(new Font("Arial", Font.ITALIC, 12));
        add(labelStatus, BorderLayout.SOUTH);

        atualizarTabela();
    }

    private JPanel criarPanelBotoes() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton btnProcessar = new JButton("⚡ Processar Selecionado (Thread)");
        btnProcessar.addActionListener(e -> processarPagamentoEmThread());

        JButton btnProcessarTodos = new JButton("⚡⚡ Processar Todos (Multi-Thread)");
        btnProcessarTodos.addActionListener(e -> processarTodosEmThread());

        JButton btnAtualizar = new JButton("🔄 Atualizar");
        btnAtualizar.addActionListener(e -> atualizarTabela());

        JButton btnDeletar = new JButton("❌ Deletar");
        btnDeletar.addActionListener(e -> deletarPagamento());

        panel.add(btnProcessar);
        panel.add(btnProcessarTodos);
        panel.add(btnDeletar);
        panel.add(btnAtualizar);

        return panel;
    }

    public void atualizarTabela() {
        SwingUtilities.invokeLater(() -> {
            modelo.setRowCount(0);
            List<Pagamento> pagamentos = pagamentoDAO.buscarTodos();

            for (Pagamento pag : pagamentos) {
                modelo.addRow(new Object[]{
                        pag.getId(),
                        ProcessadorDados.formatarMoeda(pag.getValor()),
                        pag.getTipo(),
                        "Não"
                });
            }
        });
    }

    private void processarPagamentoEmThread() {
        int linha = tabelaPagamentos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um pagamento para processar",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idPagamento = (int) modelo.getValueAt(linha, 0);
        Pagamento pagamento = pagamentoDAO.buscar(idPagamento);

        if (pagamento != null) {
            labelStatus.setText("⏳ Processando pagamento em background...");

            ProcessadorPagamentoThread processador = new ProcessadorPagamentoThread(pagamento, idPagamento);
            Thread thread = new Thread(processador);
            thread.start();

            new Thread(() -> {
                try {
                    thread.join();
                    SwingUtilities.invokeLater(() -> {
                        pagamentoDAO.marcarComoProcessado(idPagamento);
                        atualizarTabela();
                        labelStatus.setText("✓ Pagamento processado com sucesso!");
                    });
                } catch (InterruptedException e) {
                    SwingUtilities.invokeLater(() ->
                        labelStatus.setText("✗ Erro ao processar pagamento")
                    );
                }
            }).start();
        }
    }

    private void processarTodosEmThread() {
        int totalPagamentos = modelo.getRowCount();
        if (totalPagamentos == 0) {
            JOptionPane.showMessageDialog(this, "Nenhum pagamento para processar",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Deseja processar " + totalPagamentos + " pagamento(s) em paralelo?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            labelStatus.setText("⏳ Processando " + totalPagamentos + " pagamento(s) em paralelo...");

            gerenciadorThreads.limparThreads();

            for (int i = 0; i < totalPagamentos; i++) {
                int idPagamento = (int) modelo.getValueAt(i, 0);
                Pagamento pagamento = pagamentoDAO.buscar(idPagamento);

                if (pagamento != null) {
                    ProcessadorPagamentoThread processador =
                        new ProcessadorPagamentoThread(pagamento, idPagamento);
                    gerenciadorThreads.adicionarTarefa(processador);
                }
            }

            gerenciadorThreads.executarTodas();

            new Thread(() -> {
                try {
                    gerenciadorThreads.aguardarConclusao();
                    SwingUtilities.invokeLater(() -> {
                        atualizarTabela();
                        labelStatus.setText("✓ Todos os pagamentos foram processados!");
                    });
                } catch (InterruptedException e) {
                    SwingUtilities.invokeLater(() ->
                        labelStatus.setText("✗ Erro ao processar pagamentos")
                    );
                }
            }).start();
        }
    }

    private void deletarPagamento() {
        int linha = tabelaPagamentos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um pagamento para deletar",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idPagamento = (int) modelo.getValueAt(linha, 0);
        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja deletar este pagamento?", "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            pagamentoDAO.deletar(idPagamento);
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Pagamento deletado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
