package br.com.consultorio_odontologico.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.consultorio_odontologico.dao.DentistaDAO;
import br.com.consultorio_odontologico.domain.Dentista;
import br.com.consultorio_odontologico.util.FacesUtil;

@ManagedBean
@ViewScoped
public class DentistaBean implements Serializable, GenericBean {

	private static final long serialVersionUID = 1L;

	private List<Dentista> dentistas;
	private List<Dentista> dentistasFiltrados;

	private String acao;
	private Long id;

	private Dentista dentistaCadastro;

	public Dentista getDentistaCadastro() {

		if (dentistaCadastro == null) {
			dentistaCadastro = new Dentista();
		}

		return dentistaCadastro;
	}

	public void setDentistaCadastro(Dentista dentistaCadastro) {
		this.dentistaCadastro = dentistaCadastro;
	}

	public List<Dentista> getDentistas() {
		return dentistas;
	}

	public void setDentistas(List<Dentista> dentista) {
		this.dentistas = dentista;
	}

	public List<Dentista> getDentistasFiltrados() {
		return dentistasFiltrados;
	}

	public void setDentistasFiltrados(List<Dentista> dentistasFiltrados) {
		this.dentistasFiltrados = dentistasFiltrados;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void novo() {
		dentistaCadastro = new Dentista();
	}

	public void salvar() {
		try {

			DentistaDAO dao = new DentistaDAO();
			dao.salvar(dentistaCadastro);
			dentistaCadastro = new Dentista();
			FacesUtil.addMsgInfo("Dentista Salvo com Sucesso!");

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Salvar o Dentista!\n" + e.getMessage());
			e.printStackTrace();

		}
	}

	public void excluir() {
		try {

			DentistaDAO dao = new DentistaDAO();
			dao.excluir(dentistaCadastro);
			FacesUtil.addMsgInfo("Dentista Excluído com Sucesso!");

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Excluir o Dentista!\n" + e.getMessage());

		}
	}

	public void carregarPesquisa() {
		try {
			DentistaDAO dao = new DentistaDAO();
			dentistas = dao.findAll();
		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Listar os Dentistas\n: " + e.getMessage());
		}
	}

	public void carregarCadastro() {
		try {

			if (id != null) {
				DentistaDAO dao = new DentistaDAO();
				dentistaCadastro = dao.getById(id);
			} else {

				dentistaCadastro = new Dentista();
			}

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Carregar o Dentistas\n: " + e.getMessage());
		}
	}

	public void editar() {
		try {

			DentistaDAO dao = new DentistaDAO();
			dao.update(dentistaCadastro);

			FacesUtil.addMsgInfo("Dados Atualizados com Sucesso!");

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Alterar os dados do Dentista\n: " + e.getMessage());
		}
	}
}
