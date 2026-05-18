package bancodadossupabse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import bancodadossupabse.model.Aluno;
import bancodadossupabse.model.Endereco;

public class AlunoDAO {

    public void salvar(Aluno aluno) {
        String sqlEndereco = "INSERT INTO endereco (rua, cidade, cep) VALUES (?, ?, ?) RETURNING id";
        String sqlPessoa = "INSERT INTO pessoa (nome, email, endereco_id, tipo) VALUES (?, ?, ?, 'ALUNO') RETURNING id";
        String sqlAluno = "INSERT INTO aluno (pessoa_id, matricula) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);

            int enderecoId = -1;
            if (aluno.getEndereco() != null) {
                try (PreparedStatement psEnd = conn.prepareStatement(sqlEndereco)) {
                    psEnd.setString(1, aluno.getEndereco().getRua());
                    psEnd.setString(2, aluno.getEndereco().getCidade());
                    psEnd.setString(3, aluno.getEndereco().getCep());
                    ResultSet rs = psEnd.executeQuery();
                    if (rs.next()) {
                        enderecoId = rs.getInt(1);
                        aluno.getEndereco().setId(enderecoId);
                    }
                }
            }

            int pessoaId;
            try (PreparedStatement psPessoa = conn.prepareStatement(sqlPessoa)) {
                psPessoa.setString(1, aluno.getNome());
                psPessoa.setString(2, aluno.getEmail());
                psPessoa.setObject(3, enderecoId > 0 ? enderecoId : null);
                ResultSet rs = psPessoa.executeQuery();
                if (rs.next()) {
                    pessoaId = rs.getInt(1);
                    aluno.setId(pessoaId);
                }
            }

            try (PreparedStatement psAluno = conn.prepareStatement(sqlAluno)) {
                psAluno.setInt(1, aluno.getId());
                psAluno.setString(2, aluno.getMatricula());
                psAluno.executeUpdate();
            }

            conn.commit();
            System.out.println("✓ Aluno salvo com sucesso!");
        } catch (Exception e) {
            System.err.println("✗ Erro ao salvar aluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Aluno buscar(int id) {
        String sql = "SELECT a.id, a.matricula, p.id as pessoa_id, p.nome, p.email, " +
                     "e.id as endereco_id, e.rua, e.cidade, e.cep " +
                     "FROM aluno a " +
                     "JOIN pessoa p ON a.pessoa_id = p.id " +
                     "LEFT JOIN endereco e ON p.endereco_id = e.id " +
                     "WHERE a.id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return montarAluno(rs);
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao buscar aluno: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Aluno> buscarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT a.id, a.matricula, p.id as pessoa_id, p.nome, p.email, " +
                     "e.id as endereco_id, e.rua, e.cidade, e.cep " +
                     "FROM aluno a " +
                     "JOIN pessoa p ON a.pessoa_id = p.id " +
                     "LEFT JOIN endereco e ON p.endereco_id = e.id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                alunos.add(montarAluno(rs));
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao listar alunos: " + e.getMessage());
            e.printStackTrace();
        }
        return alunos;
    }

    public void atualizar(Aluno aluno) {
        String sqlPessoa = "UPDATE pessoa SET nome = ?, email = ? WHERE id = ?";
        String sqlAluno = "UPDATE aluno SET matricula = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {
            try (PreparedStatement psPessoa = conn.prepareStatement(sqlPessoa)) {
                psPessoa.setString(1, aluno.getNome());
                psPessoa.setString(2, aluno.getEmail());
                psPessoa.setInt(3, aluno.getId());
                psPessoa.executeUpdate();
            }

            try (PreparedStatement psAluno = conn.prepareStatement(sqlAluno)) {
                psAluno.setString(1, aluno.getMatricula());
                psAluno.setInt(2, aluno.getId());
                psAluno.executeUpdate();
            }

            System.out.println("✓ Aluno atualizado com sucesso!");
        } catch (Exception e) {
            System.err.println("✗ Erro ao atualizar aluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sqlPessoa = "DELETE FROM pessoa WHERE id = (SELECT pessoa_id FROM aluno WHERE id = ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlPessoa)) {
            ps.setInt(1, id);
            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                System.out.println("✓ Aluno deletado com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao deletar aluno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Aluno montarAluno(ResultSet rs) throws Exception {
        Aluno aluno = new Aluno();
        aluno.setId(rs.getInt("id"));
        aluno.setMatricula(rs.getString("matricula"));
        aluno.setNome(rs.getString("nome"));
        aluno.setEmail(rs.getString("email"));

        int enderecoId = rs.getInt("endereco_id");
        if (enderecoId > 0) {
            Endereco endereco = new Endereco();
            endereco.setId(enderecoId);
            endereco.setRua(rs.getString("rua"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setCep(rs.getString("cep"));
            aluno.setEndereco(endereco);
        }

        return aluno;
    }
}
