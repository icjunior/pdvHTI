package br.com.htisoftware.pdv.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.modelo.Configuracao;
import br.com.htisoftware.pdv.service.ConfiguracaoService;

@Named
@ViewScoped
public class ConfiguracaoBean implements Serializable {

	private static final long serialVersionUID = 75396779440641065L;
	@Inject
	Configuracao configuracao;
	@Inject
	ConfiguracaoService configuracaoService;

	public void gravar() {
		configuracaoService.gravar(configuracao);
	}

	public Configuracao getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}
}
