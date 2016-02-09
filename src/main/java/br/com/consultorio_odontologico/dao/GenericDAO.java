package br.com.consultorio_odontologico.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.com.consultorio_odontologico.util.Conexao;

public abstract class GenericDAO<T> {

	private final EntityManager entityManager = Conexao.getEntityManager();

	private final Class<T> objeto;

	private EntityTransaction getTransaction() {
		return entityManager.getTransaction();
	}

	public EntityManager getEntityManager() {

		return entityManager;

	}
	
	public void close(){
		entityManager.close();
	}

	public GenericDAO(Class<T> objeto) {
		this.objeto = objeto;
	}

	public T getById(Long id) {
		return entityManager.find(objeto, id);
	}

	public List<T> findAll() {
		TypedQuery<T> q = entityManager.createQuery(" FROM " + objeto.getSimpleName(), objeto);
		return q.getResultList();
	}

	public void salvar(T objeto) {
		getTransaction().begin();
		entityManager.persist(objeto);
		getTransaction().commit();
	}

	public void update(T objeto) {
		getTransaction().begin();
		entityManager.merge(objeto);
		getTransaction().commit();
	}

	public void excluir(T objeto) {
		getTransaction().begin();
		entityManager.remove(entityManager.merge(objeto));
		getTransaction().commit();
	}

	public void excluir(Long id) {
		excluir(getById(id));
	}

}
