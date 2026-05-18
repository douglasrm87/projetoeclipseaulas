package bancodadossupabse.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import bancodadossupabse.dao.AlunoDAO;
import bancodadossupabse.model.Aluno;
import bancodadossupabse.model.Endereco;

public class TelaGerenciamentoAlunos extends JPanel {

    private JTable tabelaAlunos;
    private DefaultTableModel modelo;
    private AlunoDAO alunoDAO;

    public TelaGerenciamentoAlunos() {
        this.alunoDAO = new AlunoDAO();
        inicializar();
    }

    private void inicializar() {
        setLayout(new BorderLayout());

        JPanel panelBotoes = criarPanelBotoes();
        add(panelBotoes, BorderLayout.NORTH);

        tabelaAlunos = new JTable();
        modelo = new DefaultTableModel(new String[]{
                "ID", "Matrícula", "Nome", "Email", "Cidade", "CEP"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaAlunos.setModel(modelo);
        JScrollPane scroll = new JScrollPane(tabelaAlunos);
        add(scroll, BorderLayout.CENTER);

        atualizarTabela();
    }

    private JPanel criarPanelBotoes() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton btnAdicionar = new JButton("➕ Adicionar");
        btnAdicionar.addActionListener(e -> adicionarAluno());

        JButton btnEditar = new JButton("✏️ Editar");
        btnEditar.addActionListener(e -> editarAluno());

        JButton btnDeletar = new JButton("❌ Deletar");
        btnDeletar.addActionListener(e -> deletarAluno());

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
            List<Aluno> alunos = alunoDAO.buscarTodos();

            for (Aluno aluno : alunos) {
                String cidade = aluno.getEndereco() != null ? aluno.getEndereco().getCidade() : "N/A";
                String cep = aluno.getEndereco() != null ? aluno.getEndereco().getCep() : "N/A";

                modelo.addRow(new Object[]{
                        aluno.getId(),
                        aluno.getMatricula(),
                        aluno.getNome(),
                        aluno.getEmail(),
                        cidade,
                        cep
                });
            }
        });
    }

    private void adicionarAluno() {
        JTextField tfNome = new JTextField();
        JTextField tfEmail = new JTextField();
        JTextField tfMatricula = new JTextField();
        JTextField tfRua = new JTextField();
        JTextField tfCidade = new JTextField();
        JTextField tfCEP = new JTextField();

        Object[] campos = {
                "Nome:", tfNome,
                "Email:", tfEmail,
                "Matrícula:", tfMatricula,
                "Rua:", tfRua,
                "Cidade:", tfCidade,
                "CEP:", tfCEP
        };

        int resultado = JOptionPane.showConfirmDialog(this, campos, "Adicionar Aluno",
                JOptionPane.OK_CANCEL_OPTION);

        if (resultado == JOptionPane.OK_OPTION) {
            Aluno novoAluno = new Aluno();
            novoAluno.setNome(tfNome.getText());
            novoAluno.setEmail(tfEmail.getText());
            novoAluno.setMatricula(tfMatricula.getText());

            Endereco endereco = new Endereco(tfRua.getText(), tfCidade.getText(), tfCEP.getText());
            novoAluno.setEndereco(endereco);

            alunoDAO.salvar(novoAluno);
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Aluno adicionado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void editarAluno() {
        int linha = tabelaAlunos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno para editar",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idAluno = (int) modelo.getValueAt(linha, 0);
        Aluno aluno = alunoDAO.buscar(idAluno);

        if (aluno != null) {
            JTextField tfNome = new JTextField(aluno.getNome());
            JTextField tfEmail = new JTextField(aluno.getEmail());
            JTextField tfMatricula = new JTextField(aluno.getMatricula());

            Object[] campos = {
                    "Nome:", tfNome,
                    "Email:", tfEmail,
                    "Matrícula:", tfMatricula
            };

            int resultado = JOptionPane.showConfirmDialog(this, campos, "Editar Aluno",
                    JOptionPane.OK_CANCEL_OPTION);

            if (resultado == JOptionPane.OK_OPTION) {
                aluno.setNome(tfNome.getText());
                aluno.setEmail(tfEmail.getText());
                aluno.setMatricula(tfMatricula.getText());

                alunoDAO.atualizar(aluno);
                atualizarTabela();
                JOptionPane.showMessageDialog(this, "Aluno atualizado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void deletarAluno() {
        int linha = tabelaAlunos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno para deletar",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idAluno = (int) modelo.getValueAt(linha, 0);
        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja deletar este aluno?", "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            alunoDAO.deletar(idAluno);
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Aluno deletado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
