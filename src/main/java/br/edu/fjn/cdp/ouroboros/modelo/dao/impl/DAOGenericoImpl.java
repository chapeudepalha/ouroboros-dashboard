package br.edu.fjn.cdp.ouroboros.modelo.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.edu.fjn.cdp.ouroboros.modelo.EntidadeOuroboros;
import br.edu.fjn.cdp.ouroboros.modelo.dao.DAOGenerico;
import br.edu.fjn.cdp.ouroboros.modelo.infraestrutura.HibernateInfra;

public class DAOGenericoImpl<E extends EntidadeOuroboros<I>, I extends Serializable> implements DAOGenerico<E, I> {

	private Class<E> classePersistente;

	public DAOGenericoImpl(Class<E> classeEntidade) {
		classePersistente = classeEntidade;
	}

	public Class<E> getClassePersistente() {
		return classePersistente;
	}

	public void setClassePersistente(Class<E> classePersistente) {
		this.classePersistente = classePersistente;
	}

	public void inserir(E entidade) {
		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();
		
		try {
			transacao.begin();
			manager.persist(entidade);
			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}
	}

	public void alterar(E entidade) {
		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();	
			manager.merge(entidade);
			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}
	}

	public void remover(E entidade) {
		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();
			entidade = manager.merge(entidade);
			manager.remove(entidade);
			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}
	}

	public E buscarPorId(I id) {
		E resultado = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();

			resultado = (E) manager.find(getClassePersistente(), id);

			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}

		return resultado;
	}

	@SuppressWarnings("unchecked")
	public List<E> listar() {
		List<E> resultado = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());
			criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criterio.addOrder(Order.asc("id"));

			resultado = criterio.list();

			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}

		return resultado;
	}

}
