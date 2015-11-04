package br.edu.fjn.cdp.ouroboros.modelo.dao.impl;

import br.edu.fjn.cdp.ouroboros.modelo.Pessoa;
import br.edu.fjn.cdp.ouroboros.modelo.dao.PessoaDAO;

public class PessoaImplDAO extends DAOGenericoImpl<Pessoa, Integer> implements PessoaDAO {

	public PessoaImplDAO() {
		super(Pessoa.class);
	}

}
