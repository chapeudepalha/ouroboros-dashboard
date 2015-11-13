package br.edu.fjn.cdp.ouroboros.modelo.dao.impl;

import br.edu.fjn.cdp.ouroboros.modelo.Projeto;
import br.edu.fjn.cdp.ouroboros.modelo.dao.ProjetoDAO;

public class ProjetoImplDAO extends DAOGenericoImpl<Projeto, Integer> implements ProjetoDAO {

	public ProjetoImplDAO() {
		super(Projeto.class);
	}
	
}
