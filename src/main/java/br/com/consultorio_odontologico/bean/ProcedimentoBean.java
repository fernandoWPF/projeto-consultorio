package br.com.consultorio_odontologico.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.consultorio_odontologico.dao.ProcedimentoDAO;
import br.com.consultorio_odontologico.domain.Procedimento;
import br.com.consultorio_odontologico.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProcedimentoBean implements Serializable, GenericBean {

	private static final long serialVersionUID = 1L;

	List<Procedimento> procedimentos;
	Procedimento procedimentoCadastro;

	private String acao;
	private Long id;

	public List<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public Procedimento getProcedimentoCadastro() {
		return procedimentoCadastro;
	}

	public void setProcedimentoCadastro(Procedimento procedimentoCadastro) {
		this.procedimentoCadastro = procedimentoCadastro;
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

	@Override
	public void novo() {
		procedimentoCadastro = new Procedimento();

	}

	@Override
	public void salvar() {

		try {
			ProcedimentoDAO dao = new ProcedimentoDAO();
			dao.salvar(procedimentoCadastro);
			procedimentoCadastro = new Procedimento();
			FacesUtil.addMsgInfo("Procedimento Salvo com Sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Salvar o Procedimento!\n" + e.getMessage());
		}

	}

	@Override
	public void excluir() {

		try {
			ProcedimentoDAO dao = new ProcedimentoDAO();
			dao.excluir(procedimentoCadastro);

			FacesUtil.addMsgInfo("Procedimento Excluído com Sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Excluir o Procedimento!\n" + e.getMessage());
		}

	}

	@Override
	public void carregarPesquisa() {

		try {
			ProcedimentoDAO dao = new ProcedimentoDAO();
			procedimentos = dao.findAll();

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Listar os Procedimentos!\n" + e.getMessage());
		}

	}

	@Override
	public void carregarCadastro() {

		try {
			if (id != null) {
				ProcedimentoDAO dao = new ProcedimentoDAO();
				procedimentoCadastro = dao.getById(id);
			} else {
				procedimentoCadastro = new Procedimento();
			}
		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Carregar o Procedimento!\n" + e.getMessage());
		}

	}

	@Override
	public void editar() {
		try {
			ProcedimentoDAO dao = new ProcedimentoDAO();
			dao.update(procedimentoCadastro);
			FacesUtil.addMsgInfo("Dados Atualizados com Sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Alterar o Procedimento\n: " + e.getMessage());
		}
	}

}
