package br.com.consultorio_odontologico.domain;

import java.io.Serializable;
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

import br.com.consultorio_odontologico.domain.Pessoa;

@Entity
@Table(name = "tbDentista")
public class Dentista implements Serializable {

	public Dentista() {
		this.pessoa = new Pessoa();
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	@Column(name = "especialidade_principal", length = 60)
	private String especialidadePrincipal;
	@Column(name = "especialidade_secundaria", length = 60)
	private String especialidadeSecundaria;
	@Column(length = 20, nullable = false)
	private String cro;
	@Enumerated(EnumType.STRING)
	@Column(name = "uf_cro")
	private Uf ufCro;
	@Temporal(TemporalType.DATE)
	@Column(name = "ano_formatura")
	private Date anoFormatura;
	private String instituicao;
	@Transient
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dentista", targetEntity = Agendamento.class)
	private List<Agendamento> agendamentos = new ArrayList<>();

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

	public String getEspecialidadePrincipal() {
		return especialidadePrincipal;
	}

	public void setEspecialidadePrincipal(String especialidadePrincipal) {
		this.especialidadePrincipal = especialidadePrincipal;
	}

	public String getEspecialidadeSecundaria() {
		return especialidadeSecundaria;
	}

	public void setEspecialidadeSecundaria(String especialidadeSecundaria) {
		this.especialidadeSecundaria = especialidadeSecundaria;
	}

	public String getCro() {
		return cro;
	}

	public void setCro(String cro) {
		this.cro = cro;
	}

	public Uf getUfCro() {
		return ufCro;
	}

	public void setUfCro(Uf ufCro) {
		this.ufCro = ufCro;
	}

	public Date getAnoFormatura() {
		return anoFormatura;
	}

	public void setAnoFormatura(Date anoFormatura) {
		this.anoFormatura = anoFormatura;
	}
	
	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
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
		Dentista other = (Dentista) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
