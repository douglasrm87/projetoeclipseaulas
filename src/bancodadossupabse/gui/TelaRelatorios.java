package bancodadossupabse.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.ObjectInputFilter.Status;
import java.util.List;
import bancodadossupabse.dao.AlunoDAO;
import bancodadossupabse.dao.MatriculaDAO;
import bancodadossupabse.model.Aluno;
import bancodadossupabse.model.Matricula;
import bancodadossupabse.model.StatusMatricula;
import bancodadossupabse.util.ProcessadorDados;

public class TelaRelatorios extends JPanel {

    private JTable tabelaRelatorio;
    private DefaultTableModel modelo;
    private AlunoDAO alunoDAO;
    private MatriculaDAO matriculaDAO;
    private JComboBox<String> comboFiltro;

    public TelaRelatorios() {
        this.alunoDAO = new AlunoDAO();
        this.matriculaDAO = new MatriculaDAO();
        inicializar();
    }

    private void inicializar() {
        setLayout(new BorderLayout());

        JPanel panelFiltros = criarPanelFiltros();
        add(panelFiltros, BorderLayout.NORTH);

        tabelaRelatorio = new JTable();
        modelo = new DefaultTableModel(new String[]{
                "ID", "Nome/Dados", "Detalhes", "Status"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaRelatorio.setModel(modelo);
        JScrollPane scroll = new JScrollPane(tabelaRelatorio);
        add(scroll, BorderLayout.CENTER);

        atualizarDados();
    }

    private JPanel criarPanelFiltros() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        panel.add(new JLabel("Filtro:"));

        String[] opcoesFiltro = {
                "Todos os Alunos",
                "Matrículas Ativas",
                "Matrículas Trancadas",
                "Matrículas Canceladas",
                "Resumo Geral"
        };

        comboFiltro = new JComboBox<>(opcoesFiltro);
        comboFiltro.addActionListener(e -> atualizarDados());

        panel.add(comboFiltro);

        return panel;
    }

    public void atualizarDados() {
        SwingUtilities.invokeLater(() -> {
            modelo.setRowCount(0);
            String filtroSelecionado = (String) comboFiltro.getSelectedItem();

            switch (filtroSelecionado) {
                case "Todos os Alunos":
                    exibirTodosAlunos();
                    break;
                case "Matrículas Ativas":
                    exibirMatriculasPorStatus(StatusMatricula.ATIVA);
                    break;
                case "Matrículas Trancadas":
                    exibirMatriculasPorStatus(StatusMatricula.TRANCADA);
                    break;
                case "Matrículas Canceladas":
                    exibirMatriculasPorStatus(StatusMatricula.CANCELADA);
                    break;
                case "Resumo Geral":
                    exibirResumoGeral();
                    break;
            }
        });
    }

    private void exibirTodosAlunos() {
        List<Aluno> alunos = alunoDAO.buscarTodos();

        for (Aluno aluno : alunos) {
            String endereco = aluno.getEndereco() != null ?
                    aluno.getEndereco().getCidade() : "N/A";

            modelo.addRow(new Object[]{
                    aluno.getId(),
                    aluno.getNome(),
                    "Email: " + aluno.getEmail(),
                    "Ativo"
            });
        }
    }

    private void exibirMatriculasPorStatus(StatusMatricula status) {
        List<Matricula> matriculas = matriculaDAO.buscarTodos();

        for (Matricula mat : matriculas) {
            if (mat.getStatus() == status) {
                modelo.addRow(new Object[]{
                        mat.getId(),
                        "Matrícula #" + mat.getId(),
                        "Data: " + ProcessadorDados.formatarData(mat.getData()),
                        mat.getStatus()
                });
            }
        }
    }

    private void exibirResumoGeral() {
        int totalAlunos = alunoDAO.buscarTodos().size();
        List<Matricula> matriculas = matriculaDAO.buscarTodos();

        int ativas = 0, trancadas = 0, canceladas = 0;
        for (Matricula mat : matriculas) {
            if (mat.getStatus() == StatusMatricula.ATIVA) {
                switch (mat.getStatus()) {
                    case ATIVA:
                        ativas++;
                        break;
                    case TRANCADA:
                        trancadas++;
                        break;
                    case CANCELADA:
                        canceladas++;
                        break;
                }
            }
        }

        modelo.addRow(new Object[]{"1", "Total de Alunos", totalAlunos, "✓"});
        modelo.addRow(new Object[]{"2", "Matrículas Ativas", ativas, "✓"});
        modelo.addRow(new Object[]{"3", "Matrículas Trancadas", trancadas, "⏸"});
        modelo.addRow(new Object[]{"4", "Matrículas Canceladas", canceladas, "✗"});
    }
}
