package br.com.consultorio_odontologico.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.consultorio_odontologico.domain.Cidade;
import br.com.consultorio_odontologico.domain.Uf;
import br.com.consultorio_odontologico.util.FacesUtil;

public class CidadeDAO extends GenericDAO<Cidade> {

	public CidadeDAO() {

		super(Cidade.class);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Cidade> consultar(String nome) {

		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT o FROM Cidade o  WHERE UPPER(nome) " + "like ? ");
		query.setParameter(1, "%" + nome + "%");
		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Cidade> consultar(String nome, Uf uf) {

		TypedQuery query = (TypedQuery) getEntityManager()
				.createQuery("SELECT o FROM Cidade o  WHERE UPPER(nome) " + " like ? and uf = ?");
		query.setParameter(1, "%" + nome + "%").setParameter(2, uf);
		return query.getResultList();
	}

	@Override
	public void salvar(Cidade objeto) {

		if (consultar(objeto.getNome().toUpperCase(), objeto.getUf()).isEmpty()) {
			super.salvar(objeto);
			FacesUtil.addMsgInfo("Cidade Salva com sucesso!!");
		} else {
			FacesUtil.addMsgInfo("A cidade " + objeto.getNome() + "-" + objeto.getUf() + ",\njá está cadastrada!");
		}

	}

}
