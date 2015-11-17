package br.edu.fjn.cdp.ouroboros.modelo.dao;

import java.util.List;

import br.edu.fjn.cdp.ouroboros.modelo.Projeto;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;

public interface ProjetoDAO extends DAOGenerico<Projeto, Integer> {

	List<Projeto> buscarPorColaborador(Usuario usuario);
	
	List<Projeto> buscarPorCliente(Usuario usuario);
	
}
