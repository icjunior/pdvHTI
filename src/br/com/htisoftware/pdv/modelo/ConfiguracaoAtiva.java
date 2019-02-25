package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ConfiguracaoAtiva implements Serializable {

	private static final long serialVersionUID = -682670211590770919L;
	Configuracao configuracao;

	public void carrega(Configuracao configuracao) {
		this.configuracao = configuracao;
	}

	public Configuracao getConfiguracao() {
		return this.configuracao;
	}
}
