package br.com.consultorio_odontologico.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.consultorio_odontologico.domain.Dentista;
import br.com.consultorio_odontologico.domain.Pessoa;

public class DentistaDAO extends GenericDAO<Dentista> {

	public DentistaDAO() {
		super(Dentista.class);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Dentista> consultar(String nome) {

		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT o FROM Dentista o, Pessoa p  WHERE nome " + "like ?");
		query.setParameter(1, "%" + nome + "%");
		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Dentista> aniversariantes(Integer mes) {

		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT o FROM Dentista o, Pessoa p  WHERE extract(month from data_nascimento) = ?");
		query.setParameter(1, mes);
		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Dentista> listAll() {
		List<Dentista> lista;
		ArrayList<Dentista> dentista = new ArrayList<>();
		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT o FROM Dentista o, Pessoa p  where p.id = o.pessoa order by nome asc");
		lista = query.getResultList();
		for (Dentista d : lista) {
			dentista.add(d);
		}
		return dentista;
	}

	@Override
	public void salvar(Dentista objeto) {

		Pessoa pessoa = objeto.getPessoa();
		PessoaDAO pessoaDAO = new PessoaDAO();

		pessoaDAO.salvar(pessoa);

		super.salvar(objeto);
	}
}
