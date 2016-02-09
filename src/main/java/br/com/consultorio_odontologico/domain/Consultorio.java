package br.com.consultorio_odontologico.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbConsultorio")
public class Consultorio implements Serializable {

	public Consultorio() {
		this.cidade = new Cidade();
		this.proprietario = new Dentista();
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "id_proprietario")
	private Dentista proprietario;
	@Column(name = "id_cidade")
	private Cidade cidade;
	@Column(name = "razao_social", length = 150)
	private String razaoSocial;
	@Column(name = "nome_fantasia", length = 150)
	private String nomeFantasia;
	@Column(length = 200, nullable = false)
	private String endereco;
	private Integer numero;
	@Column(length = 50)
	private String complemento;
	@Column(length = 100)
	private String bairro;
	@Column(length = 8)
	private String cep;
	private String telefone;
	@Column(name = "data_abertura")
	private Date dataAbertura;
	@Column(name = "hora_inicio_atendimento", length = 5)
	private String horaInicioAtendimento;
	@Column(name = "hora_fim_atendimento", length = 5)
	private String horaFimAtendimento;
	@Column(name = "hora_inicio_almoco", length = 5)
	private String horaInicioAlmoco;
	@Column(name = "hora_fim_almoco", length = 5)
	private String horaFimAlmoco;
	@Column(name = "hora_inicio_sabado", length = 5)
	private String horaInicioSabado;
	@Column(name = "hora_fim_sabado", length = 5)
	private String horaFimSabado;

	public Long getId() {
		return id;
	}

	public Dentista getProprietario() {
		return proprietario;
	}

	public void setProprietario(Dentista proprietario) {
		this.proprietario = proprietario;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getHoraInicioAtendimento() {
		return horaInicioAtendimento;
	}

	public void setHoraInicioAtendimento(String horaInicioAtendimento) {
		this.horaInicioAtendimento = horaInicioAtendimento;
	}

	public String getHoraFimAtendimento() {
		return horaFimAtendimento;
	}

	public void setHoraFimAtendimento(String horaFimAtendimento) {
		this.horaFimAtendimento = horaFimAtendimento;
	}

	public String getHoraInicioAlmoco() {
		return horaInicioAlmoco;
	}

	public void setHoraInicioAlmoco(String horaInicioAlmoco) {
		this.horaInicioAlmoco = horaInicioAlmoco;
	}

	public String getHoraFimAlmoco() {
		return horaFimAlmoco;
	}

	public void setHoraFimAlmoco(String horaFimAlmoco) {
		this.horaFimAlmoco = horaFimAlmoco;
	}

	public String getHoraInicioSabado() {
		return horaInicioSabado;
	}

	public void setHoraInicioSabado(String horaInicioSabado) {
		this.horaInicioSabado = horaInicioSabado;
	}

	public String getHoraFimSabado() {
		return horaFimSabado;
	}

	public void setHoraFimSabado(String horaFimSabado) {
		this.horaFimSabado = horaFimSabado;
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
		Consultorio other = (Consultorio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
