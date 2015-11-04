package br.edu.fjn.cdp.ouroboros.modelo.infraestrutura;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateInfra {

	private static EntityManagerFactory fabrica;
	
	public HibernateInfra() {
		// TODO Auto-generated constructor stub
	}	
	
	private static EntityManagerFactory getFabrica() {
		if (fabrica == null) {
			criarConexao();
		}
		return fabrica;
	}

	private static void criarConexao() {
		fabrica = Persistence.createEntityManagerFactory("unit-ouroboros");
	}

	public static EntityManager getManager() {
		if (fabrica == null) {
			getFabrica();
		}
		return fabrica.createEntityManager();
	}
	
}
