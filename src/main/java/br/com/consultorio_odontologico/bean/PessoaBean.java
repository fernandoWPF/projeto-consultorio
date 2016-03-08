package br.com.consultorio_odontologico.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.consultorio_odontologico.dao.PessoaDAO;
import br.com.consultorio_odontologico.domain.Pessoa;

@ManagedBean
@ViewScoped
public class PessoaBean {

	public List<String> completeDentista(String texto) {

		List<String> sugestoes = new ArrayList<String>();

		PessoaDAO dao = new PessoaDAO();
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas = dao.consultarDentista(texto.toUpperCase());

		for (Pessoa p : pessoas) {
			sugestoes.add(p.getNome());
		}
		return sugestoes;
	}
	
	public List<String> completePaciente(String texto) {

		List<String> sugestoes = new ArrayList<String>();

		PessoaDAO dao = new PessoaDAO();
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas = dao.consultarPaciente(texto.toUpperCase());

		for (Pessoa p : pessoas) {
			sugestoes.add(p.getNome());
		}
		return sugestoes;
	}
	

}
