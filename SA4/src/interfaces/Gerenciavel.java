package interfaces;

import java.util.List;

/**
 * Interface Gerenciavel — define o contrato mínimo para qualquer
 * sistema de cadastro do projeto acadêmico (PARTE 4).
 *
 * @param <T> tipo da entidade gerenciada (Aluno, Professor, Curso, Departamento)
 */
public interface Gerenciavel<T> {

    /**
     * Persiste um novo registro no banco de dados.
     * @param entidade objeto a ser cadastrado
     * @return true se o cadastro foi bem-sucedido
     */
    boolean cadastrar(T entidade);

    /**
     * Retorna todos os registros da entidade.
     * @return lista com todos os registros (vazia se não houver nenhum)
     */
    List<T> listar();
}
