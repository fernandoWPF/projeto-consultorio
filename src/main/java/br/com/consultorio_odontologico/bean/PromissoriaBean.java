package br.com.consultorio_odontologico.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.consultorio_odontologico.dao.PromissoriaDAO;
import br.com.consultorio_odontologico.domain.Promissoria;
import br.com.consultorio_odontologico.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PromissoriaBean implements Serializable, GenericBean {

	private static final long serialVersionUID = 1L;

	List<Promissoria> promissorias;
	List<Promissoria> parcelas = new ArrayList<>();

	Promissoria promissoriaCadastro;

	private String acao;
	private Long id;
	private Long numeroPromissoria;

	public List<Promissoria> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Promissoria> parcelas) {
		this.parcelas = parcelas;
	}

	public Long getNumeroPromissoria() {
		return numeroPromissoria;
	}

	public void setNumeroPromissoria(Long numeroPromissoria) {
		this.numeroPromissoria = numeroPromissoria;
	}

	public List<Promissoria> getPromissorias() {
		return promissorias;
	}

	public void setPromissorias(List<Promissoria> promissorias) {
		this.promissorias = promissorias;
	}

	public Promissoria getPromissoriaCadastro() {
		return promissoriaCadastro;
	}

	public void setPromissoriaCadastro(Promissoria promissoriaCadastro) {
		this.promissoriaCadastro = promissoriaCadastro;
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

		promissoriaCadastro = new Promissoria();

	}

	@Override
	public void salvar() {
		try {
			PromissoriaDAO dao = new PromissoriaDAO();
			dao.salvar(promissoriaCadastro);
			promissoriaCadastro = new Promissoria();
			FacesUtil.addMsgInfo("Título Salvo com Sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Salvar o Título!\n" + e.getMessage());
		}

	}

	@Override
	public void excluir() {
		try {
			PromissoriaDAO dao = new PromissoriaDAO();
			dao.excluir(promissoriaCadastro);

			FacesUtil.addMsgInfo("´Título Excluído com Sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Excluir o Título!\n" + e.getMessage());
		}

	}

	@Override
	public void carregarPesquisa() {
		try {
			PromissoriaDAO dao = new PromissoriaDAO();
			promissorias = dao.findAll();

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Listar os Títulos!\n" + e.getMessage());
		}

	}

	@Override
	public void carregarCadastro() {
		try {
			if (id != null) {
				PromissoriaDAO dao = new PromissoriaDAO();
				promissoriaCadastro = dao.getById(id);
			} else {
				promissoriaCadastro = new Promissoria();
			}

			if (acao.equals("Novo")) {
				PromissoriaDAO dao = new PromissoriaDAO();
				numeroPromissoria = dao.consultarMaximaNumeracao() + 1;
			}

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Carregar o Titulo!\n" + e.getMessage());
		}

	}

	@Override
	public void editar() {
		try {
			PromissoriaDAO dao = new PromissoriaDAO();
			dao.update(promissoriaCadastro);
			FacesUtil.addMsgInfo("Dados Atualizados com Sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Alterar o Título\n: " + e.getMessage());
		}

	}

	public void gerarParcelas() {
		try {
			Calendar calendar = Calendar.getInstance();
			for (int i = 1; i <= promissoriaCadastro.getQtdeParcelas(); i++) {
				System.out.println(i);
				calendar.setTime(promissoriaCadastro.getDataEmissao());
				calendar.add(Calendar.MONTH, i);
				promissoriaCadastro.setDataVencto(calendar.getTime());
				promissoriaCadastro.setNumParcela(i);
				promissoriaCadastro.setValorParcela(promissoriaCadastro.getValorTotal()
						.divide(new BigDecimal(promissoriaCadastro.getQtdeParcelas())));
				promissoriaCadastro.setValorSaldoParcela(promissoriaCadastro.getValorParcela());
				parcelas.add(promissoriaCadastro);
			}

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Gerar as Parcelas!\n: " + e.getMessage());
			e.printStackTrace();
		}

	}

}
