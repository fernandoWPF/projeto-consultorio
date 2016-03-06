package br.com.consultorio_odontologico.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.consultorio_odontologico.domain.Pessoa;

public class PessoaDAO extends GenericDAO<Pessoa> {

	public PessoaDAO() {
		super(Pessoa.class);

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Pessoa> consultarPaciente(String nome) {

		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT o FROM Pessoa o, Paciente p"
						+ " WHERE UPPER(nome) " + "like ?  and p.pessoa = o");
		query.setParameter(1, "%" + nome + "%");
		return query.getResultList();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Pessoa> consultarDentista(String nome) {

		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT o FROM Pessoa o, Dentista d"
						+ " WHERE UPPER(nome) " + "like ?  and d.pessoa = o");
		query.setParameter(1, "%" + nome + "%");
		return query.getResultList();
	}

}
