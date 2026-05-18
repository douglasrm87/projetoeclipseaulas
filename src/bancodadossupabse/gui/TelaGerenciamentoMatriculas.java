package bancodadossupabse.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import bancodadossupabse.dao.MatriculaDAO;
import bancodadossupabse.model.Matricula;
import bancodadossupabse.model.StatusMatricula;
import bancodadossupabse.util.ProcessadorDados;

public class TelaGerenciamentoMatriculas extends JPanel {

    private JTable tabelaMatriculas;
    private DefaultTableModel modelo;
    private MatriculaDAO matriculaDAO;

    public TelaGerenciamentoMatriculas() {
        this.matriculaDAO = new MatriculaDAO();
        inicializar();
    }

    private void inicializar() {
        setLayout(new BorderLayout());

        JPanel panelBotoes = criarPanelBotoes();
        add(panelBotoes, BorderLayout.NORTH);

        tabelaMatriculas = new JTable();
        modelo = new DefaultTableModel(new String[]{
                "ID", "Data", "Status"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaMatriculas.setModel(modelo);
        JScrollPane scroll = new JScrollPane(tabelaMatriculas);
        add(scroll, BorderLayout.CENTER);

        atualizarTabela();
    }

    private JPanel criarPanelBotoes() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton btnAtualizar = new JButton("🔄 Atualizar");
        btnAtualizar.addActionListener(e -> atualizarTabela());

        JButton btnMudarStatus = new JButton("📝 Mudar Status");
        btnMudarStatus.addActionListener(e -> mudarStatus());

        JButton btnDeletar = new JButton("❌ Deletar");
        btnDeletar.addActionListener(e -> deletarMatricula());

        panel.add(btnMudarStatus);
        panel.add(btnDeletar);
        panel.add(btnAtualizar);

        return panel;
    }

    public void atualizarTabela() {
        SwingUtilities.invokeLater(() -> {
            modelo.setRowCount(0);
            List<Matricula> matriculas = matriculaDAO.buscarTodos();

            for (Matricula mat : matriculas) {
                modelo.addRow(new Object[]{
                        mat.getId(),
                        ProcessadorDados.formatarData(mat.getData()),
                        mat.getStatus()
                });
            }
        });
    }

    private void mudarStatus() {
        int linha = tabelaMatriculas.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma matrícula",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idMatricula = (int) modelo.getValueAt(linha, 0);
        Matricula matricula = matriculaDAO.buscar(idMatricula);

        if (matricula != null) {
            StatusMatricula[] opcoes = StatusMatricula.values();
            StatusMatricula novoStatus = (StatusMatricula) JOptionPane.showInputDialog(
                    this, "Selecione o novo status:", "Mudar Status",
                    JOptionPane.QUESTION_MESSAGE, null, opcoes, matricula.getStatus()
            );

            if (novoStatus != null) {
                matricula.setStatus(novoStatus);
                matriculaDAO.atualizar(matricula);
                atualizarTabela();
                JOptionPane.showMessageDialog(this, "Status atualizado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void deletarMatricula() {
        int linha = tabelaMatriculas.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma matrícula para deletar",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idMatricula = (int) modelo.getValueAt(linha, 0);
        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja deletar esta matrícula?", "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            matriculaDAO.deletar(idMatricula);
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Matrícula deletada com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
