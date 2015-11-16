package br.edu.fjn.cdp.ouroboros.modelo.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.fjn.cdp.ouroboros.modelo.Equipe;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.EquipeDAO;
import br.edu.fjn.cdp.ouroboros.modelo.infraestrutura.HibernateInfra;

public class EquipeImplDAO extends DAOGenericoImpl<Equipe, Integer> implements EquipeDAO {

	public EquipeImplDAO() {
		super(Equipe.class);
	}

	@Override
	public void removerColaborador(Equipe equipe, Usuario colaborador) {
		Equipe eq = null;
		Usuario u = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();

			eq = manager.find(getClassePersistente(), equipe.getId());
			u = manager.find(Usuario.class, colaborador.getId());

			eq.getColaboradores().remove(u);

			manager.merge(eq);
			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}
	}

	@Override
	public void adicionarColaborador(Equipe equipe, Usuario colaborador) {
		Equipe eq = null;
		Usuario u = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();

			eq = manager.find(getClassePersistente(), equipe.getId());
			u = manager.find(Usuario.class, colaborador.getId());

			eq.getColaboradores().add(u);

			manager.merge(eq);
			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}
	}

}
