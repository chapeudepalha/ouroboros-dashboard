package br.edu.fjn.cdp.ouroboros.modelo.dao.impl;

import br.edu.fjn.cdp.ouroboros.modelo.Subtarefa;
import br.edu.fjn.cdp.ouroboros.modelo.dao.SubtarefaDAO;

public class SubtarefaImplDAO extends DAOGenericoImpl<Subtarefa, Integer> implements SubtarefaDAO {

	public SubtarefaImplDAO() {
		super(Subtarefa.class);
	}

}