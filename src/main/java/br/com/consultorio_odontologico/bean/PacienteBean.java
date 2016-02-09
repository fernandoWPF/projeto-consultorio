package br.com.consultorio_odontologico.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.consultorio_odontologico.dao.PacienteDAO;
import br.com.consultorio_odontologico.domain.Paciente;
import br.com.consultorio_odontologico.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PacienteBean implements Serializable, GenericBean {

	private static final long serialVersionUID = 1L;

	private List<Paciente> pacientes;
	private List<Paciente> pacientesFiltrados;

	private String acao;
	private Long id;

	private Paciente pacienteCadastro;

	public Paciente getPacienteCadastro() {

		if (pacienteCadastro == null) {
			pacienteCadastro = new Paciente();
		}

		return pacienteCadastro;
	}

	public void setPacienteCadastro(Paciente pacienteCadastro) {
		this.pacienteCadastro = pacienteCadastro;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public List<Paciente> getPacientesFiltrados() {
		return pacientesFiltrados;
	}

	public void setPacientesFiltrados(List<Paciente> pacientesFiltrados) {
		this.pacientesFiltrados = pacientesFiltrados;
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
		pacienteCadastro = new Paciente();
	}

	public void salvar() {
		try {

			PacienteDAO dao = new PacienteDAO();
			dao.salvar(pacienteCadastro);
			pacienteCadastro = new Paciente();
			FacesUtil.addMsgInfo("Paciente Salvo com Sucesso!");

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Salvar o Paciente!\n" + e.getMessage());

		}
	}

	public void excluir() {
		try {

			PacienteDAO dao = new PacienteDAO();
			dao.excluir(pacienteCadastro);
			FacesUtil.addMsgInfo("Paciente Excluído com Sucesso!");

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Excluir o Paciente!\n" + e.getMessage());

		}
	}

	public void carregarPesquisa() {
		try {
			PacienteDAO dao = new PacienteDAO();
			pacientes = dao.findAll();
		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Listar os Pacientes\n: " + e.getMessage());
		}
	}

	public void carregarCadastro() {
		try {

			if (id != null) {
				PacienteDAO dao = new PacienteDAO();
				pacienteCadastro = dao.getById(id);
			} else {

				pacienteCadastro = new Paciente();
			}

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Carregar o Paciente\n: " + e.getMessage());
		}
	}

	public void editar() {
		try {

			PacienteDAO dao = new PacienteDAO();
			dao.update(pacienteCadastro);

			FacesUtil.addMsgInfo("Dados Atualizados com Sucesso!");

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Alterar os dados do Paciente\n: " + e.getMessage());
		}
	}

}
