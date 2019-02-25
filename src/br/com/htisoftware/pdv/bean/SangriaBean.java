package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.modelo.ConfiguracaoAtiva;
import br.com.htisoftware.pdv.modelo.UsuarioLogado;
import br.com.htisoftware.pdv.service.SangriaService;
import br.com.htisoftware.pdv.util.PdvUtils;

@Named
@ViewScoped
public class SangriaBean implements Serializable {

	private static final long serialVersionUID = -4136796079572661555L;
	private BigDecimal valor;
	private String observacao;
	@Inject
	SangriaService sangriaService;
	@Inject
	UsuarioLogado usuarioLogado;
	@Inject
	ConfiguracaoAtiva configuracaoAtiva;

	public void registrar() {
		if (!usuarioLogado.isLogado()) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Sangria",
					"Nenhum usuário conectado. Impossível efetuar sangria");
		}
		sangriaService.registrar(valor, usuarioLogado, configuracaoAtiva, observacao);
		valor = null;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}