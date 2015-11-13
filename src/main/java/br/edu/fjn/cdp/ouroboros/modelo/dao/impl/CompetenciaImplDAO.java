package br.edu.fjn.cdp.ouroboros.modelo.dao.impl;

import br.edu.fjn.cdp.ouroboros.modelo.Competencia;
import br.edu.fjn.cdp.ouroboros.modelo.dao.CompetenciaDAO;

public class CompetenciaImplDAO extends DAOGenericoImpl<Competencia, Integer> implements CompetenciaDAO {

	public CompetenciaImplDAO() {
		super(Competencia.class);
	}

}
