package bancodadossupabse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import bancodadossupabse.model.Endereco;

public class EnderecoDAO {

    public void salvar(Endereco endereco) {
        String sql = "INSERT INTO endereco (rua, cidade, cep) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, endereco.getRua());
            ps.setString(2, endereco.getCidade());
            ps.setString(3, endereco.getCep());

            ps.executeUpdate();
            System.out.println("✓ Endereço salvo com sucesso!");

        } catch (Exception e) {
            System.err.println("✗ Erro ao salvar endereço: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Endereco buscar(int id) {
        String sql = "SELECT id, rua, cidade, cep FROM endereco WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return montarEndereco(rs);
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao buscar endereço: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Endereco> buscarTodos() {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT id, rua, cidade, cep FROM endereco ORDER BY cidade";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                enderecos.add(montarEndereco(rs));
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao listar endereços: " + e.getMessage());
            e.printStackTrace();
        }
        return enderecos;
    }

    public void atualizar(Endereco endereco) {
        String sql = "UPDATE endereco SET rua = ?, cidade = ?, cep = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, endereco.getRua());
            ps.setString(2, endereco.getCidade());
            ps.setString(3, endereco.getCep());
            ps.setInt(4, endereco.getId());

            ps.executeUpdate();
            System.out.println("✓ Endereço atualizado com sucesso!");

        } catch (Exception e) {
            System.err.println("✗ Erro ao atualizar endereço: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM endereco WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                System.out.println("✓ Endereço deletado com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao deletar endereço: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Endereco montarEndereco(ResultSet rs) throws Exception {
        Endereco endereco = new Endereco();
        endereco.setId(rs.getInt("id"));
        endereco.setRua(rs.getString("rua"));
        endereco.setCidade(rs.getString("cidade"));
        endereco.setCep(rs.getString("cep"));
        return endereco;
    }
}
