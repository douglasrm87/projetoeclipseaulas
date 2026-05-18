package bancodadossupabse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import bancodadossupabse.model.Pagamento;
import bancodadossupabse.model.PagamentoPix;
import bancodadossupabse.model.PagamentoCartao;
import bancodadossupabse.model.TipoPagamento;

public class PagamentoDAO {

    public void salvar(Pagamento pagamento, int matriculaId) {
        String sql = "INSERT INTO pagamento (matricula_id, valor, tipo, processado) VALUES (?, ?, ?, false)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, matriculaId);
            ps.setDouble(2, pagamento.getValor());
            ps.setString(3, pagamento.getTipo().name());

            ps.executeUpdate();
            System.out.println("✓ Pagamento salvo com sucesso!");

        } catch (Exception e) {
            System.err.println("✗ Erro ao salvar pagamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Pagamento buscar(int id) {
        String sql = "SELECT id, valor, tipo FROM pagamento WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return montarPagamento(rs);
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao buscar pagamento: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Pagamento> buscarPorMatricula(int matriculaId) {
        List<Pagamento> pagamentos = new ArrayList<>();
        String sql = "SELECT id, valor, tipo FROM pagamento WHERE matricula_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, matriculaId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pagamentos.add(montarPagamento(rs));
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao listar pagamentos: " + e.getMessage());
            e.printStackTrace();
        }
        return pagamentos;
    }

    public List<Pagamento> buscarTodos() {
        List<Pagamento> pagamentos = new ArrayList<>();
        String sql = "SELECT id, valor, tipo FROM pagamento ORDER BY data_pagamento DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pagamentos.add(montarPagamento(rs));
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao listar pagamentos: " + e.getMessage());
            e.printStackTrace();
        }
        return pagamentos;
    }

    public void marcarComoProcessado(int id) {
        String sql = "UPDATE pagamento SET processado = true WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("✓ Pagamento marcado como processado!");

        } catch (Exception e) {
            System.err.println("✗ Erro ao processar pagamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM pagamento WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhas = ps.executeUpdate();
            if (linhas > 0) {
                System.out.println("✓ Pagamento deletado com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("✗ Erro ao deletar pagamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Pagamento montarPagamento(ResultSet rs) throws Exception {
        Pagamento pagamento;
        TipoPagamento tipo = TipoPagamento.valueOf(rs.getString("tipo"));

        if (tipo == TipoPagamento.PIX) {
            pagamento = new PagamentoPix();
        } else if (tipo == TipoPagamento.CARTAO) {
            pagamento = new PagamentoCartao();
        } else {
            pagamento = new PagamentoPix();
        }

        pagamento.setId(rs.getInt("id"));
        pagamento.setValor(rs.getDouble("valor"));
        pagamento.setTipo(tipo);

        return pagamento;
    }
}
