package dao;

import connection.Conexao;
import interfaces.Gerenciavel;
import models.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// ── Interface ────────────────────────────────────────────────────────────────
interface CursoDAO extends Gerenciavel<Curso> {
    Curso buscarPorId(int id);
}

// ── Implementação ─────────────────────────────────────────────────────────────
public class CursoDAOImpl implements CursoDAO {

    @Override
    public boolean cadastrar(Curso curso) {
        String sql = "INSERT INTO cursos (nome) VALUES (?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, curso.getNome());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) curso.setId(keys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("[DAO Curso] Erro ao cadastrar: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Curso> listar() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT id, nome FROM cursos ORDER BY id";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso c = new Curso(rs.getString("nome"));
                c.setId(rs.getInt("id"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("[DAO Curso] Erro ao listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Curso buscarPorId(int id) {
        String sql = "SELECT id, nome FROM cursos WHERE id = ?";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Curso c = new Curso(rs.getString("nome"));
                    c.setId(rs.getInt("id"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.err.println("[DAO Curso] Erro ao buscar: " + e.getMessage());
        }
        return null;
    }
}
