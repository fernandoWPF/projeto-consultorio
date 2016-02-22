package br.com.consultorio_odontologico.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.consultorio_odontologico.domain.Paciente;
import br.com.consultorio_odontologico.domain.Promissoria;
import br.com.consultorio_odontologico.domain.StatusPromissoria;

public class PromissoriaDAO extends GenericDAO<Promissoria> {

	public PromissoriaDAO() {
		super(Promissoria.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Promissoria> consultar(Date dataIni, Date dataFim, Paciente paciente, StatusPromissoria status) {

		TypedQuery query = (TypedQuery) getEntityManager().createQuery(
				"SELECT o FROM Promissoria o, Pessoa p  WHERE dataVencto between ? and ? and statusPromissoria in(?) and paciente in(?)");
		query.setParameter(1, dataIni).setParameter(2, dataFim).setParameter(3, status).setParameter(4, paciente);
		return query.getResultList();
	}

	@SuppressWarnings({ "rawtypes" })
	public Long consultarMaximaNumeracao() {

		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT MAX(o.numPromissoria) FROM Promissoria o");
		if ((Long) query.getSingleResult() == null) {
			return 0L;
		} else {

			return (Long) query.getSingleResult();
		}
	}

}
