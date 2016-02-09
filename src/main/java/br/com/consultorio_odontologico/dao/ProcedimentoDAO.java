package br.com.consultorio_odontologico.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.consultorio_odontologico.domain.Procedimento;

public class ProcedimentoDAO extends GenericDAO<Procedimento> {

	public ProcedimentoDAO() {
		super(Procedimento.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Procedimento> consultar(String descricao) {

		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT o FROM Procedimento o WHERE descricao " + "like ? ");
		query.setParameter(1, "%" + descricao + "%");
		return query.getResultList();
	}

}
