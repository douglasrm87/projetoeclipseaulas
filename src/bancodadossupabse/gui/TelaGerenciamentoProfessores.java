package bancodadossupabse.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import bancodadossupabse.dao.ProfessorDAO;
import bancodadossupabse.model.Professor;
import bancodadossupabse.model.Endereco;

public class TelaGerenciamentoProfessores extends JPanel {

    private JTable tabelaProfessores;
    private DefaultTableModel modelo;
    private ProfessorDAO professorDAO;

    public TelaGerenciamentoProfessores() {
        this.professorDAO = new ProfessorDAO();
        inicializar();
    }

    private void inicializar() {
        setLayout(new BorderLayout());

        JPanel panelBotoes = criarPanelBotoes();
        add(panelBotoes, BorderLayout.NORTH);

        tabelaProfessores = new JTable();
        modelo = new DefaultTableModel(new String[]{
                "ID", "Nome", "Email", "Salário", "Cidade"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaProfessores.setModel(modelo);
        JScrollPane scroll = new JScrollPane(tabelaProfessores);
        add(scroll, BorderLayout.CENTER);

        atualizarTabela();
    }

    private JPanel criarPanelBotoes() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton btnAdicionar = new JButton("➕ Adicionar");
        btnAdicionar.addActionListener(e -> adicionarProfessor());

        JButton btnEditar = new JButton("✏️ Editar");
        btnEditar.addActionListener(e -> editarProfessor());

        JButton btnDeletar = new JButton("❌ Deletar");
        btnDeletar.addActionListener(e -> deletarProfessor());

        JButton btnAtualizar = new JButton("🔄 Atualizar");
        btnAtualizar.addActionListener(e -> atualizarTabela());

        panel.add(btnAdicionar);
        panel.add(btnEditar);
        panel.add(btnDeletar);
        panel.add(btnAtualizar);

        return panel;
    }

    public void atualizarTabela() {
        SwingUtilities.invokeLater(() -> {
            modelo.setRowCount(0);
            List<Professor> professores = professorDAO.buscarTodos();

            for (Professor prof : professores) {
                String cidade = prof.getEndereco() != null ? prof.getEndereco().getCidade() : "N/A";

                modelo.addRow(new Object[]{
                        prof.getId(),
                        prof.getNome(),
                        prof.getEmail(),
                        String.format("R$ %.2f", prof.getSalario()),
                        cidade
                });
            }
        });
    }

    private void adicionarProfessor() {
        JTextField tfNome = new JTextField();
        JTextField tfEmail = new JTextField();
        JTextField tfSalario = new JTextField();
        JTextField tfRua = new JTextField();
        JTextField tfCidade = new JTextField();
        JTextField tfCEP = new JTextField();

        Object[] campos = {
                "Nome:", tfNome,
                "Email:", tfEmail,
                "Salário:", tfSalario,
                "Rua:", tfRua,
                "Cidade:", tfCidade,
                "CEP:", tfCEP
        };

        int resultado = JOptionPane.showConfirmDialog(this, campos, "Adicionar Professor",
                JOptionPane.OK_CANCEL_OPTION);

        if (resultado == JOptionPane.OK_OPTION) {
            try {
                double salario = Double.parseDouble(tfSalario.getText());
                Professor novoProfessor = new Professor();
                novoProfessor.setNome(tfNome.getText());
                novoProfessor.setEmail(tfEmail.getText());
                novoProfessor.setSalario(salario);

                Endereco endereco = new Endereco(tfRua.getText(), tfCidade.getText(), tfCEP.getText());
                novoProfessor.setEndereco(endereco);

                professorDAO.salvar(novoProfessor);
                atualizarTabela();
                JOptionPane.showMessageDialog(this, "Professor adicionado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Salário deve ser um número válido", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarProfessor() {
        int linha = tabelaProfessores.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um professor para editar",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idProfessor = (int) modelo.getValueAt(linha, 0);
        Professor professor = professorDAO.buscar(idProfessor);

        if (professor != null) {
            JTextField tfNome = new JTextField(professor.getNome());
            JTextField tfEmail = new JTextField(professor.getEmail());
            JTextField tfSalario = new JTextField(String.valueOf(professor.getSalario()));

            Object[] campos = {
                    "Nome:", tfNome,
                    "Email:", tfEmail,
                    "Salário:", tfSalario
            };

            int resultado = JOptionPane.showConfirmDialog(this, campos, "Editar Professor",
                    JOptionPane.OK_CANCEL_OPTION);

            if (resultado == JOptionPane.OK_OPTION) {
                try {
                    professor.setNome(tfNome.getText());
                    professor.setEmail(tfEmail.getText());
                    professor.setSalario(Double.parseDouble(tfSalario.getText()));

                    professorDAO.atualizar(professor);
                    atualizarTabela();
                    JOptionPane.showMessageDialog(this, "Professor atualizado com sucesso!", "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Salário deve ser um número válido", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void deletarProfessor() {
        int linha = tabelaProfessores.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um professor para deletar",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idProfessor = (int) modelo.getValueAt(linha, 0);
        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja deletar este professor?", "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            professorDAO.deletar(idProfessor);
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Professor deletado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
