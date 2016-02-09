package br.com.consultorio_odontologico.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.consultorio_odontologico.dao.CidadeDAO;
import br.com.consultorio_odontologico.domain.Cidade;
import br.com.consultorio_odontologico.domain.Uf;

@ManagedBean
@ViewScoped
public class CidadeBean {

	private Cidade cidade;
	private Uf uf;

	public List<String> complete(String texto) {

		List<String> sugestoes = new ArrayList<String>();

		CidadeDAO dao = new CidadeDAO();
		List<Cidade> cidades = new ArrayList<Cidade>();
		cidades = dao.consultar(texto.toUpperCase());

		for (Cidade c : cidades) {
			sugestoes.add(c.getNome());
		}
		return sugestoes;
	}

	public Cidade getCidade() {
		if (cidade == null) {
			cidade = new Cidade();
		}
		return cidade;

	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void salvar() {

		CidadeDAO dao = new CidadeDAO();
		dao.salvar(cidade);
		cidade = new Cidade();
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

}
