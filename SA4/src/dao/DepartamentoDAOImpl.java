package dao;

import connection.Conexao;
import interfaces.Gerenciavel;
import models.Aluno;
import models.Departamento;
import models.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

interface DepartamentoDAO extends Gerenciavel<Departamento> {
    boolean associarAluno(int departamentoId, int alunoId);

    boolean associarProfessor(int departamentoId, int professorId);

    List<Departamento> listarComMembros();
}

public class DepartamentoDAOImpl implements DepartamentoDAO {

    private final AlunoDAOImpl alunoDAO = new AlunoDAOImpl();
    private final ProfessorDAOImpl professorDAO = new ProfessorDAOImpl();

    @Override
    public boolean cadastrar(Departamento d) {
        String sql = "INSERT INTO departamentos (nome) VALUES (?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, d.getNome());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) d.setId(keys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("[DAO Departamento] Erro ao cadastrar: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Departamento> listar() {
        List<Departamento> lista = new ArrayList<>();
        String sql = "SELECT id, nome FROM departamentos ORDER BY id";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Departamento d = new Departamento(rs.getString("nome"));
                d.setId(rs.getInt("id"));
                lista.add(d);
            }
        } catch (SQLException e) {
            System.err.println("[DAO Departamento] Erro ao listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Departamento> listarComMembros() {
        List<Departamento> deptos = listar();
        for (Departamento d : deptos) {
            // carrega alunos do departamento
            String sqlA = "SELECT aluno_id FROM departamento_aluno WHERE departamento_id=?";
            try (Connection con = Conexao.getConexao();
                 PreparedStatement ps = con.prepareStatement(sqlA)) {
                ps.setInt(1, d.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // busca aluno individualmente via lista completa
                        for (Aluno a : alunoDAO.listar()) {
                            if (a.getId() == rs.getInt("aluno_id")) {
                                d.adicionarAluno(a);
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println("[DAO Departamento] Erro ao carregar alunos: " + e.getMessage());
            }

            // carrega professores do departamento
            String sqlP = "SELECT professor_id FROM departamento_professor WHERE departamento_id=?";
            try (Connection con = Conexao.getConexao();
                 PreparedStatement ps = con.prepareStatement(sqlP)) {
                ps.setInt(1, d.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Professor p = professorDAO.buscarPorId(rs.getInt("professor_id"));
                        if (p != null) d.adicionarProfessor(p);
                    }
                }
            } catch (SQLException e) {
                System.err.println("[DAO Departamento] Erro ao carregar professores: " + e.getMessage());
            }
        }
        return deptos;
    }

    @Override
    public boolean associarAluno(int departamentoId, int alunoId) {
        String sql = "INSERT IGNORE INTO departamento_aluno (departamento_id, aluno_id) VALUES (?,?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, departamentoId);
            ps.setInt(2, alunoId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[DAO Departamento] Erro ao associar aluno: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean associarProfessor(int departamentoId, int professorId) {
        String sql = "INSERT IGNORE INTO departamento_professor (departamento_id, professor_id) VALUES (?,?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, departamentoId);
            ps.setInt(2, professorId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[DAO Departamento] Erro ao associar professor: " + e.getMessage());
        }
        return false;
    }
}
