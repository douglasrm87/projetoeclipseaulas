package bancodadossupabse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import bancodadossupabse.model.Professor;
import bancodadossupabse.model.Endereco;

public class ProfessorDAO {

    public void salvar(Professor professor) {
        String sqlEndereco = "INSERT INTO endereco (rua, cidade, cep) VALUES (?, ?, ?) RETURNING id";
        String sqlPessoa = "INSERT INTO pessoa (nome, email, endereco_id, tipo) VALUES (?, ?, ?, 'PROFESSOR') RETURNING id";
        String sqlProfessor = "INSERT INTO professor (pessoa_id, salario) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);

            int enderecoId = -1;
            if (professor.getEndereco() != null) {
                try (PreparedStatement psEnd = conn.prepareStatement(sqlEndereco)) {
                    psEnd.setString(1, professor.getEndereco().getRua());
                    psEnd.setString(2, professor.getEndereco().getCidade());
                    psEnd.setString(3, professor.getEndereco().getCep());
                    ResultSet rs = psEnd.executeQuery();
                    if (rs.next()) {
                        enderecoId = rs.getInt(1);
                        professor.getEndereco().setId(enderecoId);
                    }
                }
            }

            int pessoaId;
            try (PreparedStatement psPessoa = conn.prepareStatement(sqlPessoa)) {
                psPessoa.setString(1, professor.getNome());
                psPessoa.setString(2, professor.getEmail());
                psPessoa.setObject(3, enderecoId > 0 ? enderecoId : null);
                ResultSet rs = psPessoa.executeQuery();
                if (rs.next()) {
                    pessoaId = rs.getInt(1);
                    professor.setId(pessoaId);
                }
            }

            try (PreparedStatement psProfessor = conn.prepareStatement(sqlProfessor)) {
                psProfessor.setInt(1, professor.getId());
                psProfessor.setDouble(2, professor.getSalario());
                psProfessor.executeUpdate();
            }

            conn.commit();
            System.out.println("✓ Professor salvo com sucesso!");
        } catch (Exception e) {
            System.err.println("✗ Erro ao salvar professor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Professor buscar(int id) {
        String sql = "SELECT pr.id, pr.salario, p.id as pessoa_id, p.nome, p.email, " +
                     "e.id as endereco_id, e.rua, e.cidade, e.cep " +
                     "FROM professor pr " +
                     "JOIN pessoa p ON pr.pessoa_id = p.id " +
                     "LEFT JOIN endereco e ON p.endereco_id = e.id " +
                     "WHERE pr.id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return montarProfessor(rs);
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao buscar professor: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Professor> buscarTodos() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT pr.id, pr.salario, p.id as pessoa_id, p.nome, p.email, " +
                     "e.id as endereco_id, e.rua, e.cidade, e.cep " +
                     "FROM professor pr " +
                     "JOIN pessoa p ON pr.pessoa_id = p.id " +
                     "LEFT JOIN endereco e ON p.endereco_id = e.id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                professores.add(montarProfessor(rs));
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao listar professores: " + e.getMessage());
            e.printStackTrace();
        }
        return professores;
    }

    public void atualizar(Professor professor) {
        String sqlPessoa = "UPDATE pessoa SET nome = ?, email = ? WHERE id = ?";
        String sqlProfessor = "UPDATE professor SET salario = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {
            try (PreparedStatement psPessoa = conn.prepareStatement(sqlPessoa)) {
                psPessoa.setString(1, professor.getNome());
                psPessoa.setString(2, professor.getEmail());
                psPessoa.setInt(3, professor.getId());
                psPessoa.executeUpdate();
            }

            try (PreparedStatement psProfessor = conn.prepareStatement(sqlProfessor)) {
                psProfessor.setDouble(1, professor.getSalario());
                psProfessor.setInt(2, professor.getId());
                psProfessor.executeUpdate();
            }

            System.out.println("✓ Professor atualizado com sucesso!");
        } catch (Exception e) {
            System.err.println("✗ Erro ao atualizar professor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sqlPessoa = "DELETE FROM pessoa WHERE id = (SELECT pessoa_id FROM professor WHERE id = ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlPessoa)) {
            ps.setInt(1, id);
            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                System.out.println("✓ Professor deletado com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao deletar professor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Professor montarProfessor(ResultSet rs) throws Exception {
        Professor professor = new Professor();
        professor.setId(rs.getInt("id"));
        professor.setSalario(rs.getDouble("salario"));
        professor.setNome(rs.getString("nome"));
        professor.setEmail(rs.getString("email"));

        int enderecoId = rs.getInt("endereco_id");
        if (enderecoId > 0) {
            Endereco endereco = new Endereco();
            endereco.setId(enderecoId);
            endereco.setRua(rs.getString("rua"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setCep(rs.getString("cep"));
            professor.setEndereco(endereco);
        }

        return professor;
    }
}
