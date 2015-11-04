package br.edu.fjn.cdp.ouroboros.modelo.dao;

import java.io.Serializable;
import java.util.List;

import br.edu.fjn.cdp.ouroboros.modelo.EntidadeOuroboros;

public interface DAOGenerico<E extends EntidadeOuroboros<I>, I  extends Serializable> {

	void inserir(E entidade);

	void alterar(E entidade);

	void remover(E entidade);

	E buscarPorId(I id);

	List<E> listar();

}
