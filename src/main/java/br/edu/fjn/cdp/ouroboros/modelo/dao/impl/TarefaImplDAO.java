package br.edu.fjn.cdp.ouroboros.modelo.dao.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.edu.fjn.cdp.ouroboros.modelo.EstadoTarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Projeto;
import br.edu.fjn.cdp.ouroboros.modelo.Tarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.TarefaDAO;
import br.edu.fjn.cdp.ouroboros.modelo.infraestrutura.HibernateInfra;

public class TarefaImplDAO extends DAOGenericoImpl<Tarefa, Integer> implements TarefaDAO {

	public TarefaImplDAO() {
		super(Tarefa.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tarefa> buscarPorProjeto(Projeto projeto) {
		List<Tarefa> tarefas = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			criterio.createAlias("projeto", "p");
			criterio.add(Restrictions.eq("p.id", projeto.getId()));
			criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			tarefas = criterio.list();

			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}

		return tarefas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tarefa> buscarPorProjetoEEstado(Projeto projeto, EstadoTarefa estadoTarefa) {
		List<Tarefa> tarefas = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			criterio.createAlias("projeto", "p");
			Criterion c1 = Restrictions.eq("p.id", projeto.getId());
			Criterion c2 = Restrictions.eq("estadoTarefa", estadoTarefa);

			criterio.add(Restrictions.and(c1, c2));
			criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			tarefas = criterio.list();

			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}

		return tarefas;
	}

	@Override
	public Long quantidadeDeTarefasPorProjeto(Projeto projeto) {
		Long quantidade = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			criterio.createAlias("projeto", "p");
			criterio.add(Restrictions.eq("p.id", projeto.getId()));
			criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criterio.setProjection(Projections.rowCount());

			quantidade = (Long) criterio.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			manager.close();
		}

		return quantidade;
	}

	@Override
	public Long quantidadeDeTarefasPorProjetoEEstado(Projeto projeto, EstadoTarefa estadoTarefa) {
		Long quantidade = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			criterio.createAlias("projeto", "p");

			Criterion c1 = Restrictions.eq("p.id", projeto.getId());
			Criterion c2 = Restrictions.eq("estadoTarefa", estadoTarefa);

			criterio.add(Restrictions.and(c1, c2));
			criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criterio.setProjection(Projections.rowCount());

			quantidade = (Long) criterio.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			manager.close();
		}

		return quantidade;
	}

	@Override
	public Long quantidadeDeTarefasAtrasadasPorProjeto(Projeto projeto) {
		Long quantidade = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			criterio.createAlias("projeto", "p");

			Criterion c1 = Restrictions.eq("p.id", projeto.getId());

			Calendar calendar = new GregorianCalendar();
			Criterion c2 = Restrictions.lt("fim", calendar);
			Criterion c3 = Restrictions.eq("estadoTarefa", EstadoTarefa.PARAFAZER);
			Criterion c4 = Restrictions.eq("estadoTarefa", EstadoTarefa.EMPROGRESSO);

			criterio.add(Restrictions.and(Restrictions.and(c1, c2), Restrictions.or(c3, c4)));
			criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criterio.setProjection(Projections.rowCount());

			quantidade = (Long) criterio.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			manager.close();
		}

		return quantidade;
	}

	@Override
	public Boolean colaboradorEstaAlocado(Usuario usuario) {
		boolean alocado = false;
		
		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			transaction.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			criterio.createAlias("colaboradorResponsavel", "c");

			Criterion c1 = Restrictions.eq("c.id", usuario.getId());

			criterio.add(c1);
			criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			if (criterio.uniqueResult() == null)
				alocado = false;
			else
				alocado = true;
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			manager.close();
		}
		
		return alocado;
	}

}
