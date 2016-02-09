package br.com.consultorio_odontologico.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import br.com.consultorio_odontologico.dao.CidadeDAO;

@Entity
@Table(name = "tbPessoa")
public class Pessoa implements Serializable {

	public Pessoa() {
		this.cidade = new Cidade();
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "O campo Nome deve ser preenchido!")
	@Column(length = 255, nullable = false)
	private String nome;
	@CPF(message = "Desculpe, mas esse não é um CPF válido!")
	@Column(length = 14, unique = true)
	private String cpf;
	@NotNull(message = "O campo Data de Nascimento deve ser preenchido!")
	@Column(name = "data_nascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@Column(length = 12, unique = true)
	private String rg;
	@NotNull(message = "O campo Idade deve ser preenchido!")
	private Integer idade;
	@Enumerated(EnumType.STRING)
	@Column(length = 1, nullable = false)
	private Sexo sexo;
	@Column(length = 200)
	private String endereco;
	private Long numero;
	@Column(length = 50)
	private String complemento;
	@Column(length = 100)
	private String bairro;
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;
	@Column(length = 9)
	private String cep;
	@Column(length = 19)
	private String telefone;
	@Column(length = 19)
	private String celular;
	@Email(message = "Desculpe, mas esse não é um e-mail válido!")
	@Column(length = 50)
	private String email;
	private String observacao;
	@Enumerated(EnumType.STRING)
	@Column(name = "estado_civil", length = 15)
	private EstadoCivil estadoCivil;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
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

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		
		CidadeDAO dao = new CidadeDAO();
		List<Cidade> listaDeCidades = dao.consultar(cidade.getNome(), cidade.getUf());
		
		if(listaDeCidades.isEmpty()){
			dao.salvar(cidade);
			this.cidade = cidade;
		}else{
			this.cidade = listaDeCidades.get(0);
		}
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

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil civil) {
		this.estadoCivil = civil;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome + ", CPF=" + cpf + ", idade=" + idade + " anos, sexo=" + sexo + ", endereco=" + endereco
				+ ", numero=" + numero + ", complemento=" + complemento + ", bairro=" + bairro + ", cidade="
				+ cidade.getNome() + " - " + cidade.getUf() + "]";
	}

}
