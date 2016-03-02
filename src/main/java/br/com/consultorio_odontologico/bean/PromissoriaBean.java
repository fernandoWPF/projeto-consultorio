package br.com.consultorio_odontologico.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.consultorio_odontologico.dao.PacienteDAO;
import br.com.consultorio_odontologico.dao.PromissoriaDAO;
import br.com.consultorio_odontologico.domain.Paciente;
import br.com.consultorio_odontologico.domain.Promissoria;
import br.com.consultorio_odontologico.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PromissoriaBean implements Serializable, GenericBean {

	private static final long serialVersionUID = 1L;

	String pagina;

	List<Promissoria> promissorias;
	List<Promissoria> parcelas = new ArrayList<>();

	Promissoria promissoriaCadastro;
	Long numPromissoria;
	Integer qtdeParcelas;
	Paciente paciente;
	Date dataEmissao;
	BigDecimal valorTotal;

	private String acao;
	private Long id;

	public Long getNumPromissoria() {
		return numPromissoria;
	}

	public void setNumPromissoria(Long numPromissoria) {
		this.numPromissoria = numPromissoria;
	}

	public Integer getQtdeParcelas() {
		return qtdeParcelas;
	}

	public void setQtdeParcelas(Integer qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}

	public Paciente getPaciente() {
		if (paciente == null) {
			paciente = new Paciente();
		}
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<Promissoria> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Promissoria> parcelas) {
		this.parcelas = parcelas;
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

	public String getPagina() {

		PacienteDAO dao = new PacienteDAO();

		if (dao.findAll().isEmpty()) {
			FacesUtil.addMsgInfo("Para uma nova Promissória, é necessário ter pelo menos um Paciente cadastrado!");
			return "/pages/promissoriaPesquisa";
		} else {
			return "/pages/promissoriaCadastro";
		}

	}

	@Override
	public void novo() {

		promissoriaCadastro = new Promissoria();
	}

	@Override
	public void salvar() {
		try {
			PromissoriaDAO dao = new PromissoriaDAO();
			for (int i = 0; i < parcelas.size(); i++) {
				dao.salvar(parcelas.get(i));
			}
			promissoriaCadastro = new Promissoria();
			parcelas = new ArrayList<>();
			FacesUtil.addMsgInfo("Título Salvo com Sucesso!");
		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Salvar o Título!\n" + e.getMessage());
			e.printStackTrace();
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
				numPromissoria = dao.consultarMaximaNumeracao() + 1;
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

			if (parcelas.size() > 0) {
				parcelas = new ArrayList<>();
			}
			Calendar calendar = Calendar.getInstance();
			BigDecimal valorParcela = valorTotal.divide(new BigDecimal(qtdeParcelas.toString()), 2, RoundingMode.UP);
			Promissoria promissoria;

			for (int i = 1; i <= qtdeParcelas; i++) {

				promissoria = new Promissoria();
				calendar.setTime(this.dataEmissao);
				calendar.add(Calendar.MONTH, i);
				promissoria.setNumPromissoria(this.numPromissoria);
				promissoria.setQtdeParcelas(this.qtdeParcelas);
				promissoria.setDataVencto(calendar.getTime());
				promissoria.setNumParcela(i);
				promissoria.setValorParcela(valorParcela);
				promissoria.setValorSaldoParcela(valorParcela);
				promissoria.setPaciente(this.paciente);
				parcelas.add(promissoria);

			}

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Gerar as Parcelas!\n: " + e.getMessage());
			e.printStackTrace();
		}

	}

}
