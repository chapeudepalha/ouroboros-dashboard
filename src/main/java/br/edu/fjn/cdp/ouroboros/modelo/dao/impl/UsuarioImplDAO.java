package br.edu.fjn.cdp.ouroboros.modelo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.edu.fjn.cdp.ouroboros.modelo.Competencia;
import br.edu.fjn.cdp.ouroboros.modelo.TipoUsuario;
import br.edu.fjn.cdp.ouroboros.modelo.Usuario;
import br.edu.fjn.cdp.ouroboros.modelo.dao.UsuarioDAO;
import br.edu.fjn.cdp.ouroboros.modelo.infraestrutura.HibernateInfra;

public class UsuarioImplDAO extends DAOGenericoImpl<Usuario, Integer> implements UsuarioDAO {

	public UsuarioImplDAO() {
		super(Usuario.class);
	}

	public Usuario buscarPorUsuarioESenha(String usuario, String senha) {
		Usuario resultado = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			Criterion c1 = Restrictions.eq("usuario", usuario);
			Criterion c2 = Restrictions.eq("senha", senha);

			criterio.add(Restrictions.and(c1, c2));

			resultado = (Usuario) criterio.uniqueResult();

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
	@Override
	public List<Usuario> buscarPorTipoUsuario(TipoUsuario tipoUsuario) {
		List<Usuario> usuarios = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			Criterion c1 = Restrictions.eq("tipoUsuario", tipoUsuario);

			criterio.add(c1);
			criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			usuarios = criterio.list();

			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}

		return usuarios;
	}

	@Override
	public Usuario buscarPorUsuario(String usuario) {
		Usuario resultado = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			Criterion c1 = Restrictions.eq("usuario", usuario);

			criterio.add(c1);

			resultado = (Usuario) criterio.uniqueResult();

			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}

		return resultado;
	}

	@Override
	public void adicionarCompetencia(Usuario usuario, Competencia competencia) {
		Competencia cp = null;
		Usuario u = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();

			u = manager.find(getClassePersistente(), usuario.getId());
			cp = manager.find(Competencia.class, competencia.getId());

			u.getCompetencias().add(cp);

			manager.merge(u);
			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}
	}

	@Override
	public void removerCompetencia(Usuario usuario, Competencia competencia) {
		Competencia cp = null;
		Usuario u = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();

			u = manager.find(getClassePersistente(), usuario.getId());
			cp = manager.find(Competencia.class, competencia.getId());

			u.getCompetencias().remove(cp);

			manager.merge(u);
			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> buscarPorTipoUsuarioECompetencia(TipoUsuario tipoUsuario, Competencia competencia) {
		List<Usuario> usuarios = null;

		EntityManager manager = HibernateInfra.getManager();
		EntityTransaction transacao = manager.getTransaction();

		try {
			transacao.begin();

			Criteria criterio = ((Session) manager.getDelegate()).createCriteria(getClassePersistente());

			criterio.createAlias("competencias", "c");

			Criterion c1 = Restrictions.eq("tipoUsuario", tipoUsuario);
			Criterion c2 = Restrictions.eq("c.id", competencia.getId());

			criterio.add(Restrictions.and(c1, c2));
			criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			usuarios = criterio.list();

			transacao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transacao.rollback();
		} finally {
			manager.close();
		}

		return usuarios;
	}

}
