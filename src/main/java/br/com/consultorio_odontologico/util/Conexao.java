package br.com.consultorio_odontologico.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class Conexao {

	@PersistenceContext
	private static EntityManagerFactory entityManager;

	static {
		entityManager = Persistence.createEntityManagerFactory("consultorio_odontologicoPU");
	}

	public static EntityManager getEntityManager() {
		return entityManager.createEntityManager();
	}

}
