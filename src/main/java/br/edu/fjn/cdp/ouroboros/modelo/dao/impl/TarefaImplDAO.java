package br.edu.fjn.cdp.ouroboros.modelo.dao.impl;

import br.edu.fjn.cdp.ouroboros.modelo.Tarefa;
import br.edu.fjn.cdp.ouroboros.modelo.dao.TarefaDAO;

public class TarefaImplDAO extends DAOGenericoImpl<Tarefa, Integer> implements TarefaDAO {

	public TarefaImplDAO() {
		super(Tarefa.class);
	}

}
