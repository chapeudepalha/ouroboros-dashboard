package br.edu.fjn.cdp.ouroboros.modelo.dao.impl;

import br.edu.fjn.cdp.ouroboros.modelo.Equipe;
import br.edu.fjn.cdp.ouroboros.modelo.dao.EquipeDAO;

public class EquipeImplDAO extends DAOGenericoImpl<Equipe, Integer> implements EquipeDAO {

	public EquipeImplDAO() {
		super(Equipe.class);
	}

}
