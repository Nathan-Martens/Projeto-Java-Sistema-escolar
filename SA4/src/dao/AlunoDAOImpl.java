package dao;

import connection.Conexao;
import interfaces.Gerenciavel;
import models.Aluno;
import models.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

interface AlunoDAO extends Gerenciavel<Aluno> {
    boolean atualizar(Aluno aluno);

    boolean remover(int id);

    List<Aluno> listarPorCurso(int cursoId);
}

public class AlunoDAOImpl implements AlunoDAO {

    private final CursoDAOImpl cursoDAO = new CursoDAOImpl();

    @Override
    public boolean cadastrar(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome,telefone,endereco,cpf,estado_civil,email,data_nasc,ra,historico,ativo,curso_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getTelefone());
            ps.setString(3, aluno.getEndereco());
            ps.setString(4, aluno.getCpf());
            ps.setString(5, aluno.getEstadoCivil());
            ps.setString(6, aluno.getEmail());
            ps.setString(7, aluno.getDataNasc());
            ps.setString(8, aluno.getRaDoAluno());
            ps.setString(9, aluno.getHistoricoAluno());
            ps.setInt(10, aluno.getAtivo());
            ps.setInt(11, aluno.getCurso() != null ? aluno.getCurso().getId() : 0);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) aluno.setId(keys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("[DAO Aluno] Erro ao cadastrar: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Aluno> listar() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alunos ORDER BY id";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso curso = cursoDAO.buscarPorId(rs.getInt("curso_id"));
                Aluno a = new Aluno(
                        rs.getString("nome"), rs.getString("telefone"),
                        rs.getString("endereco"), rs.getString("cpf"),
                        rs.getString("estado_civil"), rs.getString("email"),
                        rs.getString("data_nasc"), rs.getString("ra"),
                        rs.getString("historico"), rs.getInt("ativo"), curso);
                a.setId(rs.getInt("id"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("[DAO Aluno] Erro ao listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Aluno> listarPorCurso(int cursoId) {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alunos WHERE curso_id = ? ORDER BY id";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cursoId);
            try (ResultSet rs = ps.executeQuery()) {
                Curso curso = cursoDAO.buscarPorId(cursoId);
                while (rs.next()) {
                    Aluno a = new Aluno(
                            rs.getString("nome"), rs.getString("telefone"),
                            rs.getString("endereco"), rs.getString("cpf"),
                            rs.getString("estado_civil"), rs.getString("email"),
                            rs.getString("data_nasc"), rs.getString("ra"),
                            rs.getString("historico"), rs.getInt("ativo"), curso);
                    a.setId(rs.getInt("id"));
                    lista.add(a);
                }
            }
        } catch (SQLException e) {
            System.err.println("[DAO Aluno] Erro ao listar por curso: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean atualizar(Aluno aluno) {
        String sql = "UPDATE alunos SET nome=?, telefone=?, especializacao=? WHERE id=?";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE alunos SET nome=?, telefone=? WHERE id=?")) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getTelefone());
            ps.setInt(3, aluno.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[DAO Aluno] Erro ao atualizar: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean remover(int id) {
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement("DELETE FROM alunos WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[DAO Aluno] Erro ao remover: " + e.getMessage());
        }
        return false;
    }
}
