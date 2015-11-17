package br.edu.fjn.cdp.ouroboros.modelo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.edu.fjn.cdp.ouroboros.modelo.Projeto;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.ProjetoDAO;
import br.edu.fjn.cdp.ouroboros.modelo.infraestrutura.HibernateInfra;

public class ProjetoImplDAO extends DAOGenericoImpl<Projeto, Integer> implements ProjetoDAO {

	public ProjetoImplDAO() {
		super(Projeto.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projeto> buscarPorColaborador(Usuario usuario) {
		List<Projeto> projetos = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			criterio.createAlias("equipe", "e");
			criterio.createAlias("e.colaboradores", "c");
			Criterion c1 = Restrictions.eq("c.id", usuario.getId());

			criterio.add(c1);
			criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			projetos = criterio.list();

			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}

		return projetos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projeto> buscarPorCliente(Usuario usuario) {
		List<Projeto> projetos = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			criterio.createAlias("cliente", "c");
			Criterion c1 = Restrictions.eq("c.id", usuario.getId());

			criterio.add(c1);
			criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			projetos = criterio.list();

			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}

		return projetos;
	}
	
}
