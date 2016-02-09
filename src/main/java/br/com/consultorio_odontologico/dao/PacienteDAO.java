package br.com.consultorio_odontologico.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.consultorio_odontologico.domain.Paciente;
import br.com.consultorio_odontologico.domain.Pessoa;

public class PacienteDAO extends GenericDAO<Paciente> {

	public PacienteDAO() {
		super(Paciente.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Paciente> consultar(String nome, String cpf) {

		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT o FROM Paciente o, Pessoa p  WHERE nome " + "like ? or cpf = ?");
		query.setParameter(1, "%" + nome + "%").setParameter(2, "" + cpf + "");
		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Paciente> aniversariantes(Integer mes) {

		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT o FROM Paciente o, Pessoa p  WHERE extract(month from data_nascimento) = ?");
		query.setParameter(1, mes);
		return query.getResultList();
	}

	@Override
	public void salvar(Paciente objeto) {

		Pessoa pessoa = objeto.getPessoa();
		PessoaDAO pessoaDAO = new PessoaDAO();

		pessoaDAO.salvar(pessoa);

		objeto.setDataCadastro(new Date());

		super.salvar(objeto);
	}
}
