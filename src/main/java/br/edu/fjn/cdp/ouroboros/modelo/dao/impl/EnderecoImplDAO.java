package br.edu.fjn.cdp.ouroboros.modelo.dao.impl;

import br.edu.fjn.cdp.ouroboros.modelo.Endereco;
import br.edu.fjn.cdp.ouroboros.modelo.dao.EnderecoDAO;

public class EnderecoImplDAO extends DAOGenericoImpl<Endereco, Integer>implements EnderecoDAO {

	public EnderecoImplDAO() {
		super(Endereco.class);
	}

}