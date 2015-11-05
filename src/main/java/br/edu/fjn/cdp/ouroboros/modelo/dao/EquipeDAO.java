package br.edu.fjn.cdp.ouroboros.modelo.dao;

import br.edu.fjn.cdp.ouroboros.modelo.Equipe;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;

public interface EquipeDAO extends DAOGenerico<Equipe, Integer> {

	void removerColaborador(Equipe equipe, Usuario colaborador);

	void adicionarColaborador(Equipe equipe, Usuario colaborador);
}
