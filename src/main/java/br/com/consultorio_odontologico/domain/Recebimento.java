package br.com.consultorio_odontologico.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbRecebimento")
public class Recebimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id_promissoria")
	private Promissoria promissoria;
	@Column(name = "valor_lancto", precision = 10, scale = 6)
	private BigDecimal valorLancto;
	@Column(name = "data_lancto")
	private Date dataLancto;

	public Long getId() {
		return id;
	}

	public Promissoria getPromissoria() {
		return promissoria;
	}

	public void setPromissoria(Promissoria promissoria) {
		this.promissoria = promissoria;
	}

	public BigDecimal getValorLancto() {
		return valorLancto;
	}

	public void setValorLancto(BigDecimal valorLancto) {
		this.valorLancto = valorLancto;
	}

	public Date getDataLancto() {
		return dataLancto;
	}

	public void setDataLancto(Date dataLancto) {
		this.dataLancto = dataLancto;
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
		Recebimento other = (Recebimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
