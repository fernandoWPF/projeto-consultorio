package br.com.consultorio_odontologico.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.consultorio_odontologico.dao.UsuarioDAO;
import br.com.consultorio_odontologico.domain.Usuario;
import br.com.consultorio_odontologico.util.FacesUtil;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable, GenericBean {

	private List<Usuario> usuarios;
	private List<Usuario> usuariosFiltrados;

	private String acao;
	private Long id;

	private Usuario usuarioCadastro;

	private static final long serialVersionUID = 1L;

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
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

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	@Override
	public void novo() {

		usuarioCadastro = new Usuario();

	}

	@Override
	public void salvar() {

		try {

			UsuarioDAO dao = new UsuarioDAO();
			dao.salvar(usuarioCadastro);
			usuarioCadastro = new Usuario();

			FacesUtil.addMsgInfo("Usuário Salvo com Sucesso!");

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Salvar o Usuário!\n" + e.getMessage());

		}

	}

	@Override
	public void excluir() {

		try {

			UsuarioDAO dao = new UsuarioDAO();
			dao.excluir(usuarioCadastro);
			FacesUtil.addMsgInfo("Usuário Excluído com Sucesso!");

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Excluir o Usuário!\n" + e.getMessage());

		}

	}

	@Override
	public void carregarPesquisa() {

		try {

			UsuarioDAO dao = new UsuarioDAO();
			usuarios = dao.findAll();

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Listar os Usuários\n: " + e.getMessage());
		}

	}

	@Override
	public void carregarCadastro() {

		try {

			if (id != null) {
				UsuarioDAO dao = new UsuarioDAO();
				usuarioCadastro = dao.getById(id);
			} else {

				usuarioCadastro = new Usuario();
			}

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Carregar o Usuário\n: " + e.getMessage());
		}

	}

	@Override
	public void editar() {

		try {

			UsuarioDAO dao = new UsuarioDAO();
			dao.update(usuarioCadastro);

			FacesUtil.addMsgInfo("Dados Atualizados com Sucesso!");

		} catch (Exception e) {
			FacesUtil.addMsgError("Houve um erro ao Alterar os dados do Usuário\n: " + e.getMessage());
		}

	}

}
