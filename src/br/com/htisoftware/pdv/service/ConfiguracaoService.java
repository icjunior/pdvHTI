package br.com.htisoftware.pdv.service;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.ConfiguracaoDAO;
import br.com.htisoftware.pdv.modelo.Configuracao;
import br.com.htisoftware.pdv.util.PdvUtils;

public class ConfiguracaoService implements Serializable {

	private static final long serialVersionUID = -2284944936312488352L;
	@Inject
	ConfiguracaoDAO dao;

	public void gravar(Configuracao configuracao) {
		dao.gravar(configuracao);
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Terminal gravado com sucesso");
	}

	public Configuracao buscar() {
		return dao.buscar();
	}

	public void gravarLogo(byte[] arquivo) {
		dao.gravarLogo(arquivo);
	}
}
