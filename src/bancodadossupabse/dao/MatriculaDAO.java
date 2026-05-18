package bancodadossupabse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import bancodadossupabse.model.Matricula;
import bancodadossupabse.model.StatusMatricula;

public class MatriculaDAO {

    public void salvar(Matricula matricula, int alunoId, String cursoCodigo, Integer professorId) {
        String sql = "INSERT INTO matricula (aluno_id, curso_codigo, professor_id, data, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, alunoId);
            ps.setString(2, cursoCodigo);
            ps.setObject(3, professorId);
            ps.setDate(4, new java.sql.Date(matricula.getData().getTime()));
            ps.setString(5, matricula.getStatus().name());

            ps.executeUpdate();
            System.out.println("✓ Matrícula salva com sucesso!");

        } catch (Exception e) {
            System.err.println("✗ Erro ao salvar matrícula: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Matricula buscar(int id) {
        String sql = "SELECT id, aluno_id, curso_codigo, professor_id, data, status " +
                     "FROM matricula WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return montarMatricula(rs);
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao buscar matrícula: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Matricula> buscarPorAluno(int alunoId) {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT id, aluno_id, curso_codigo, professor_id, data, status " +
                     "FROM matricula WHERE aluno_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, alunoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                matriculas.add(montarMatricula(rs));
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao listar matrículas: " + e.getMessage());
            e.printStackTrace();
        }
        return matriculas;
    }

    public List<Matricula> buscarTodos() {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT id, aluno_id, curso_codigo, professor_id, data, status FROM matricula";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                matriculas.add(montarMatricula(rs));
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao listar matrículas: " + e.getMessage());
            e.printStackTrace();
        }
        return matriculas;
    }

    public void atualizar(Matricula matricula) {
        String sql = "UPDATE matricula SET data = ?, status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(matricula.getData().getTime()));
            ps.setString(2, matricula.getStatus().name());
            ps.setInt(3, matricula.getId());

            ps.executeUpdate();
            System.out.println("✓ Matrícula atualizada com sucesso!");

        } catch (Exception e) {
            System.err.println("✗ Erro ao atualizar matrícula: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM matricula WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                System.out.println("✓ Matrícula deletada com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao deletar matrícula: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Matricula montarMatricula(ResultSet rs) throws Exception {
        Matricula matricula = new Matricula();
        matricula.setId(rs.getInt("id"));
        matricula.setData(new java.util.Date(rs.getDate("data").getTime()));
        matricula.setStatus(StatusMatricula.valueOf(rs.getString("status")));
        return matricula;
    }
}
