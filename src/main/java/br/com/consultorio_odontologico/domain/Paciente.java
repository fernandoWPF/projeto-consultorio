package br.com.consultorio_odontologico.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "tbPaciente")
public class Paciente implements Serializable {

	public Paciente() {
		this.pessoa = new Pessoa();
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_retorno_prevencao")
	private Date dataRetornoPrevencao;
	@Enumerated(EnumType.STRING)
	@Column(name = "status_paciente", length = 15)
	private StatusPaciente statusPaciente;
	@Column(name = "nome_mae")
	private String nomeMae;
	@Column(name = "nome_pai")
	private String nomePai;
	@Enumerated(EnumType.STRING)
	@Column(length = 25)
	private Escolaridade escolaridade;
	@Column(name = "local_trabalho")
	private String localTrabalho;
	private String profissao;
	@Column(name = "renda_mensal")
	private BigDecimal rendaMensal;

	@Transient
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", targetEntity = Agendamento.class)
	private List<Agendamento> agendamentos = new ArrayList<>();
	@Transient
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", targetEntity = Promissoria.class)
	private List<Promissoria> promissorias = new ArrayList<>();

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public Long getId() {
		return id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Date getDataPrimeiraConsulta() {
		return dataCadastro;
	}

	public void setDataPrimeiraConsulta(Date dataPrimeiraConsulta) {
		this.dataCadastro = dataPrimeiraConsulta;
	}

	public Date getDataRetornoPrevencao() {
		return dataRetornoPrevencao;
	}

	public void setDataRetornoPrevencao(Date dataRetornoPrevencao) {
		this.dataRetornoPrevencao = dataRetornoPrevencao;
	}

	public StatusPaciente getStatusPaciente() {
		return statusPaciente;
	}

	public void setStatusPaciente(StatusPaciente statusPaciente) {
		this.statusPaciente = statusPaciente;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	public BigDecimal getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(BigDecimal rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
