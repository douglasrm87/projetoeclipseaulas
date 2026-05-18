package bancodadossupabse.gui;

import javax.swing.*;
import java.awt.*;
import bancodadossupabse.dao.AlunoDAO;
import bancodadossupabse.dao.ProfessorDAO;
import bancodadossupabse.dao.MatriculaDAO;

public class TelaDashboard extends JPanel {

    private JLabel lblTotalAlunos;
    private JLabel lblTotalProfessores;
    private JLabel lblTotalMatriculas;
    private AlunoDAO alunoDAO;
    private ProfessorDAO professorDAO;
    private MatriculaDAO matriculaDAO;

    public TelaDashboard() {
        this.alunoDAO = new AlunoDAO();
        this.professorDAO = new ProfessorDAO();
        this.matriculaDAO = new MatriculaDAO();
        inicializar();
    }

    private void inicializar() {
        setLayout(new GridLayout(2, 2, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblTotalAlunos = criarCaixaEstatistica("👨‍🎓 Total de Alunos", "0");
        lblTotalProfessores = criarCaixaEstatistica("👨‍🏫 Total de Professores", "0");
        lblTotalMatriculas = criarCaixaEstatistica("📋 Total de Matrículas", "0");
        JLabel lblSistema = criarCaixaEstatistica("🎓 Sistema de Gerenciamento Escolar", "v1.0");

        add(lblTotalAlunos);
        add(lblTotalProfessores);
        add(lblTotalMatriculas);
        add(lblSistema);

        atualizarDados();
    }

    private JLabel criarCaixaEstatistica(String titulo, String valor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createRaisedBevelBorder());

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Arial", Font.BOLD, 36));
        lblValor.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(20));
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblValor);
        panel.add(Box.createVerticalStrut(20));

        add(panel);
        return lblValor;
    }

    public void atualizarDados() {
        SwingUtilities.invokeLater(() -> {
            int totalAlunos = alunoDAO.buscarTodos().size();
            int totalProfessores = professorDAO.buscarTodos().size();
            int totalMatriculas = matriculaDAO.buscarTodos().size();

            lblTotalAlunos.setText(String.valueOf(totalAlunos));
            lblTotalProfessores.setText(String.valueOf(totalProfessores));
            lblTotalMatriculas.setText(String.valueOf(totalMatriculas));
        });
    }
}
