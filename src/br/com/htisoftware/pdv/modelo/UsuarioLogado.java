package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class UsuarioLogado implements Serializable {

	private static final long serialVersionUID = -704289026171431724L;
	Usuario usuario;

	public void loga(Usuario usuario) {
		this.usuario = usuario;
	}

	public void invalidaSessao() {
		this.usuario = null;
	}

	public boolean isLogado() {
		return this.usuario != null;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	// public void verificaLogin() {
	// if (!isLogado()) {
	// // PdvUtils.openDialog("operadorDialog");
	// PdvUtils.abreDialog("operador_login");
	// PdvUtils.mensagem(FacesMessage.SEVERITY_WARN, "Usuário",
	// "Nenhum usuário está conectado. Efetue o login e senha para continuar.");
	// return;
	// }
	// }
}
