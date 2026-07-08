package dao;

import connection.Conexao;
import interfaces.Gerenciavel;
import models.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

interface ProfessorDAO extends Gerenciavel<Professor> {
    boolean atualizar(Professor professor);

    Professor buscarPorId(int id);
}

public class ProfessorDAOImpl implements ProfessorDAO {

    @Override
    public boolean cadastrar(Professor p) {
        String sql = "INSERT INTO professores (nome,telefone,endereco,cpf,estado_civil,email,data_nasc,especializacao,ativo) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getTelefone());
            ps.setString(3, p.getEndereco());
            ps.setString(4, p.getCpf());
            ps.setString(5, p.getEstadoCivil());
            ps.setString(6, p.getEmail());
            ps.setString(7, p.getDataNasc());
            ps.setString(8, p.getEspecializacao());
            ps.setInt(9, p.getAtivo());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) p.setId(keys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("[DAO Professor] Erro ao cadastrar: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Professor> listar() {
        List<Professor> lista = new ArrayList<>();
        String sql = "SELECT * FROM professores ORDER BY id";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Professor p = new Professor(
                        rs.getString("nome"), rs.getString("telefone"),
                        rs.getString("endereco"), rs.getString("cpf"),
                        rs.getString("estado_civil"), rs.getString("email"),
                        rs.getString("data_nasc"), rs.getString("especializacao"),
                        rs.getInt("ativo"));
                p.setId(rs.getInt("id"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("[DAO Professor] Erro ao listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean atualizar(Professor p) {
        String sql = "UPDATE professores SET nome=?, especializacao=? WHERE id=?";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getEspecializacao());
            ps.setInt(3, p.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[DAO Professor] Erro ao atualizar: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Professor buscarPorId(int id) {
        String sql = "SELECT * FROM professores WHERE id=?";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Professor p = new Professor(
                            rs.getString("nome"), rs.getString("telefone"),
                            rs.getString("endereco"), rs.getString("cpf"),
                            rs.getString("estado_civil"), rs.getString("email"),
                            rs.getString("data_nasc"), rs.getString("especializacao"),
                            rs.getInt("ativo"));
                    p.setId(rs.getInt("id"));
                    return p;
                }
            }
        } catch (SQLException e) {
            System.err.println("[DAO Professor] Erro ao buscar: " + e.getMessage());
        }
        return null;
    }
}
