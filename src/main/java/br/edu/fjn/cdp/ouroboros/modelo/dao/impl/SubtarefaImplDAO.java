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
import br.edu.fjn.cdp.ouroboros.modelo.Subtarefa;
import br.edu.fjn.cdp.ouroboros.modelo.Tarefa;
import br.edu.fjn.cdp.ouroboros.modelo.dao.SubtarefaDAO;
import br.edu.fjn.cdp.ouroboros.modelo.infraestrutura.HibernateInfra;

public class SubtarefaImplDAO extends DAOGenericoImpl<Subtarefa, Integer> implements SubtarefaDAO {

	public SubtarefaImplDAO() {
		super(Subtarefa.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subtarefa> buscarPorTarefa(Tarefa tarefa) {
		List<Subtarefa> tarefas = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();

			tarefa = manager.find(Tarefa.class, tarefa.getId());

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			Criterion c1 = Restrictions.eq("tarefa", tarefa);

			criterio.add(c1);
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
	public Long quantidadeDeSubtarefasPorTarefa(Tarefa tarefa) {
		long quantidade = 0;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			criterio.createAlias("tarefa", "t");
			criterio.add(Restrictions.eq("t.id", tarefa.getId()));
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
	public Long quantidadeDeSubtarefasPorProjeto(Projeto projeto) {
		long quantidade = 0;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			criterio.createAlias("tarefa", "t");
			criterio.createAlias("t.projeto", "p");
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
	public Long quantidadeDeSubtarefasPorProjetoEEstado(Projeto projeto, EstadoTarefa estadoTarefa) {
		long quantidade = 0;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			criterio.createAlias("tarefa", "t");
			criterio.createAlias("t.projeto", "p");
			
			Criterion c1 = Restrictions.eq("t.estadoTarefa", estadoTarefa);
			Criterion c2 = Restrictions.eq("p.id", projeto.getId());
			
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
	public Long quantidadeDeSubtarefasAtrasadasPorProjeto(Projeto projeto) {
		long quantidade = 0;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			criterio.createAlias("tarefa", "t");
			criterio.createAlias("t.projeto", "p");
			
			Criterion c1 = Restrictions.eq("p.id", projeto.getId());
			
			Calendar calendar = new GregorianCalendar();
			Criterion c2 = Restrictions.lt("t.fim", calendar);
			Criterion c3 = Restrictions.eq("t.estadoTarefa", EstadoTarefa.PARAFAZER);
			Criterion c4 = Restrictions.eq("t.estadoTarefa", EstadoTarefa.EMPROGRESSO);

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

}