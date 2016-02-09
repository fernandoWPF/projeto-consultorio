package br.com.consultorio_odontologico.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.consultorio_odontologico.domain.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario> {

	public UsuarioDAO() {
		super(Usuario.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Usuario> autenticar(String login, String senha) {

		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT o FROM Usuario o  WHERE login = ? and senha = ?");
		query.setParameter(1, login).setParameter(2, senha);
		return query.getResultList();
	}
	
}
