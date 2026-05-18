package bancodadossupabse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import bancodadossupabse.model.Curso;

public class CursoDAO {

    public void salvar(Curso curso) {
        String sql = "INSERT INTO curso (codigo, nome) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, curso.getCodigo());
            ps.setString(2, curso.getNome());

            ps.executeUpdate();
            System.out.println("✓ Curso salvo com sucesso!");

        } catch (Exception e) {
            System.err.println("✗ Erro ao salvar curso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Curso buscar(String codigo) {
        String sql = "SELECT codigo, nome FROM curso WHERE codigo = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return montarCurso(rs);
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao buscar curso: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Curso> buscarTodos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT codigo, nome FROM curso ORDER BY nome";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cursos.add(montarCurso(rs));
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao listar cursos: " + e.getMessage());
            e.printStackTrace();
        }
        return cursos;
    }

    public void atualizar(Curso curso) {
        String sql = "UPDATE curso SET nome = ? WHERE codigo = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getCodigo());

            ps.executeUpdate();
            System.out.println("✓ Curso atualizado com sucesso!");

        } catch (Exception e) {
            System.err.println("✗ Erro ao atualizar curso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deletar(String codigo) {
        String sql = "DELETE FROM curso WHERE codigo = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                System.out.println("✓ Curso deletado com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao deletar curso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Curso montarCurso(ResultSet rs) throws Exception {
        Curso curso = new Curso();
        curso.setCodigo(rs.getString("codigo"));
        curso.setNome(rs.getString("nome"));
        return curso;
    }
}
