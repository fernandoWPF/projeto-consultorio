package br.com.consultorio_odontologico.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.consultorio_odontologico.domain.Agendamento;
import br.com.consultorio_odontologico.domain.Dentista;
import br.com.consultorio_odontologico.domain.Paciente;

public class AgendamentoDAO extends GenericDAO<Agendamento> {

	public AgendamentoDAO() {
		super(Agendamento.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Agendamento> consultar(Paciente paciente) {

		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT o FROM Agendamento  WHERE paciente " + "= ? and dataAgendamento >= ?");
		query.setParameter(1, paciente).setParameter(2, new Date());
		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Agendamento> consultar(Dentista dentista) {

		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT o FROM Agendamento  WHERE dentista " + "= ? and dataAgendamento >= ?");
		query.setParameter(1, dentista).setParameter(2, new Date());
		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Agendamento> consultar(Date data) {

		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT o FROM Agendamento o  WHERE dataAgendamento " + " = ? ");
		query.setParameter(1, data);
		return query.getResultList();
	}

}
