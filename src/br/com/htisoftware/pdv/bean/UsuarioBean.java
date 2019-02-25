package br.com.htisoftware.pdv.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.htisoftware.pdv.modelo.VendaAuxiliar;
import br.com.htisoftware.pdv.service.UsuarioService;

@Named
@ViewScoped
public class UsuarioBean extends VendaAuxiliar implements Serializable {

	private static final long serialVersionUID = -225521994777690461L;
	@Inject
	UsuarioService usuarioService;
	String usuario;
	String senha;

	public void logar() {
		usuarioService.logar(usuario, senha, getFundo());
	}

	public void logout() {
		usuarioService.logout();
	}

	public void cancelarLogout() {
		PrimeFaces.current().dialog().closeDynamic(null);
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
