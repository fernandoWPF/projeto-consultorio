package br.com.consultorio_odontologico.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.consultorio_odontologico.domain.Promissoria;
import br.com.consultorio_odontologico.domain.Recebimento;

public class RecebimentoDAO extends GenericDAO<Recebimento> {

	public RecebimentoDAO() {
		super(Recebimento.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Recebimento> consultar(Promissoria promissoria, Date dataIni, Date dataFim) {

		TypedQuery query = (TypedQuery) getEntityManager().createQuery(
				"SELECT p FROM Promissoria o, Recebimento p  WHERE promissoria = ? and dataLancto between ? and ?");
		query.setParameter(1, promissoria).setParameter(2, dataIni).setParameter(3, dataFim);
		return query.getResultList();
	}
}
