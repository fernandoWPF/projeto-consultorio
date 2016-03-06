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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbPromissoria")
public class Promissoria implements Serializable {

	public Promissoria() {
		this.paciente = new Paciente();
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "num_promissoria")
	private Long numPromissoria;
	@Column(name = "num_parcela")
	private Integer numParcela;
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;
	@Column(name = "data_emissao")
	private Date dataEmissao;
	@Column(name = "data_vencto")
	private Date dataVencto;
	@Column(name = "valor_total", precision = 10, scale = 6)
	private BigDecimal valorTotal;
	@Column(name = "valor_saldo", precision = 10, scale = 6)
	private BigDecimal valorSaldo;
	@Column(name = "valor_parcela", precision = 10, scale = 6)
	private BigDecimal valorParcela;
	@Column(name = "valor_saldo_parcela", precision = 10, scale = 6)
	private BigDecimal valorSaldoParcela;

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status_promissoria", length = 15)
	private StatusPromissoria statusPromissoria;
	@Column(name = "qtde_pacelas")
	private Integer qtdeParcelas;
	private String observacao;
	@Transient
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "promissoria", targetEntity = Recebimento.class)
	private List<Recebimento> recebimentos = new ArrayList<>();

	public List<Recebimento> getRecebimentos() {
		return recebimentos;
	}

	public void setRecebimentos(List<Recebimento> recebimentos) {
		this.recebimentos = recebimentos;
	}

	public Long getId() {
		return id;
	}

	public Long getNumPromissoria() {
		return numPromissoria;
	}

	public void setNumPromissoria(Long numPromissoria) {
		this.numPromissoria = numPromissoria;
	}

	public Integer getNumParcela() {
		return numParcela;
	}

	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}

	public Paciente getPaciente() {
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

	public Date getDataVencto() {
		return dataVencto;
	}

	public void setDataVencto(Date dataVencto) {
		this.dataVencto = dataVencto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorSaldo() {
		return valorSaldo;
	}

	public void setValorSaldo(BigDecimal valorSaldo) {
		this.valorSaldo = valorSaldo;
	}

	public StatusPromissoria getStatusPromissoria() {
		return statusPromissoria;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public BigDecimal getValorSaldoParcela() {
		return valorSaldoParcela;
	}

	public void setValorSaldoParcela(BigDecimal valorSaldoParcela) {
		this.valorSaldoParcela = valorSaldoParcela;
	}

	public void setStatusPromissoria(StatusPromissoria statusPromissoria) {
		this.statusPromissoria = statusPromissoria;
	}

	public Integer getQtdeParcelas() {
		return qtdeParcelas;
	}

	public void setQtdeParcelas(Integer qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
		Promissoria other = (Promissoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
