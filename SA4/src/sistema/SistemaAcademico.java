package sistema;

import dao.AlunoDAOImpl;
import dao.CursoDAOImpl;
import dao.DepartamentoDAOImpl;
import dao.ProfessorDAOImpl;
import interfaces.Gerenciavel;
import models.*;

import java.util.List;

/**
 * SistemaAcademico – implementa Gerenciavel para cada entidade
 * e delega toda a persistência aos respectivos DAOs.
 * (PARTE 4 – Interfaces e Organização do Sistema)
 */
public class SistemaAcademico
        implements Gerenciavel<Aluno> {   // implementação principal exigida pelo enunciado

    // ── DAOs ──────────────────────────────────────────────────────────────
    private final AlunoDAOImpl        alunoDAO        = new AlunoDAOImpl();
    private final ProfessorDAOImpl    professorDAO    = new ProfessorDAOImpl();
    private final CursoDAOImpl        cursoDAO        = new CursoDAOImpl();
    private final DepartamentoDAOImpl departamentoDAO = new DepartamentoDAOImpl();

    // ════════════════════════════════════════════════════════════════════
    //  ALUNOS  (implementação da interface Gerenciavel<Aluno>)
    // ════════════════════════════════════════════════════════════════════

    @Override
    public boolean cadastrar(Aluno aluno) {
        return alunoDAO.cadastrar(aluno);
    }

    @Override
    public List<Aluno> listar() {
        return alunoDAO.listar();
    }

    public boolean atualizarAluno(Aluno aluno) {
        return alunoDAO.atualizar(aluno);
    }

    public List<Aluno> listarAlunosPorCurso(int cursoId) {
        return alunoDAO.listarPorCurso(cursoId);
    }

    // ════════════════════════════════════════════════════════════════════
    //  PROFESSORES
    // ════════════════════════════════════════════════════════════════════

    public boolean cadastrarProfessor(Professor professor) {
        return professorDAO.cadastrar(professor);
    }

    public List<Professor> listarProfessores() {
        return professorDAO.listar();
    }

    public boolean atualizarProfessor(Professor professor) {
        return professorDAO.atualizar(professor);
    }

    // ════════════════════════════════════════════════════════════════════
    //  CURSOS
    // ════════════════════════════════════════════════════════════════════

    public boolean cadastrarCurso(Curso curso) {
        return cursoDAO.cadastrar(curso);
    }

    public List<Curso> listarCursos() {
        return cursoDAO.listar();
    }

    public Curso buscarCursoPorId(int id) {
        return cursoDAO.buscarPorId(id);
    }

    // ════════════════════════════════════════════════════════════════════
    //  DEPARTAMENTOS
    // ════════════════════════════════════════════════════════════════════

    public boolean cadastrarDepartamento(Departamento departamento) {
        return departamentoDAO.cadastrar(departamento);
    }

    public List<Departamento> listarDepartamentos() {
        return departamentoDAO.listarComMembros();
    }

    public boolean associarAlunoDepartamento(int departamentoId, int alunoId) {
        return departamentoDAO.associarAluno(departamentoId, alunoId);
    }

    public boolean associarProfessorDepartamento(int departamentoId, int professorId) {
        return departamentoDAO.associarProfessor(departamentoId, professorId);
    }
}
