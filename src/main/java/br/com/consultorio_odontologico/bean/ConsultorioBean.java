package br.com.consultorio_odontologico.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.consultorio_odontologico.dao.ConsultorioDAO;
import br.com.consultorio_odontologico.domain.Consultorio;
import br.com.consultorio_odontologico.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ConsultorioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Consultorio> consultorios;
	private Consultorio consultorioCadastro;

	public Consultorio getConsultorioCadastro() {
		if (consultorioCadastro == null) {
			consultorioCadastro = new Consultorio();
		}
		return consultorioCadastro;
	}

	public void setConsultorioCadastro(Consultorio consultorio) {
		this.consultorioCadastro = consultorio;
	}

	public List<Consultorio> getConsultorios() {
		return consultorios;
	}

	public void setConsultorios(List<Consultorio> consultorios) {
		this.consultorios = consultorios;
	}

	public void carregarCadastro() {

		try {

			ConsultorioDAO dao = new ConsultorioDAO();
			consultorios = dao.findAll();

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao carregar o Consultório!\n: " + e.getMessage());
		}

	}

	public void salvar() {

		try {

			ConsultorioDAO dao = new ConsultorioDAO();
			if (dao.findAll().isEmpty()) {
				dao.salvar(consultorioCadastro);
				FacesUtil.addMsgInfo("Consultório Salvo com Sucesso!");
			} else {
				dao.update(consultorioCadastro);
				FacesUtil.addMsgInfo("Consultório Atualizado com Sucesso!");
			}

			

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Salvar o Consultório!\n" + e.getMessage());

		}

	}

}
