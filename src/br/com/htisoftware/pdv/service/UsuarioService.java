package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.inject.Any;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;

import br.com.htisoftware.pdv.bean.RelatorioOperadorBean;
import br.com.htisoftware.pdv.dao.UsuarioDAO;
import br.com.htisoftware.pdv.dto.ResumoOperadorDTO;
import br.com.htisoftware.pdv.modelo.ConfiguracaoAtiva;
import br.com.htisoftware.pdv.modelo.Sessao;
import br.com.htisoftware.pdv.modelo.Usuario;
import br.com.htisoftware.pdv.modelo.UsuarioLogado;
import br.com.htisoftware.pdv.util.PdvUtils;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = -64489831281374903L;
	@Inject
	UsuarioDAO dao;
	@Inject
	UsuarioLogado usuarioLogado;
	@Inject
	FundoService fundoService;
	@Inject
	ConfiguracaoAtiva configuracaoAtiva;
	@Inject
	PdvUtils pdvUtils;
	@Inject
	ResumoOperadorDTO resumoOperadorDTO;
	@Inject
	@Any
	RelatorioOperadorBean relatorioOperadorBean;

	public void logar(String usuario, String senha, BigDecimal valor) {
		if (usuarioLogado.isLogado()) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Entrada de operador",
					"Já existe um operador conectado. Primeiro dê a saída e repita o processo");
			return;
		}
		Usuario usr = dao.logar(usuario, senha);
		if (usr == null) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Usuário ou senha inválidos.");
			return;
		}
		usuarioLogado.loga(usr);
		fundoService.registrar(valor, usuarioLogado, configuracaoAtiva, null);
		Sessao sessao = PdvUtils.loginSesssaoOperador(usr);
		dao.loginSesssao(sessao);
		PrimeFaces.current().executeScript("PF('cpfCnpjDialog').show()");
	}

	public void logout() {
		if (!usuarioLogado.isLogado()) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Saída de operador",
					"Nenhum usuário está conectado no momento.");
		}

		relatorioOperadorBean.setData(Calendar.getInstance());
		relatorioOperadorBean.setOperador(usuarioLogado.getUsuario().getCodigo());
		relatorioOperadorBean.pesquisar();

		Sessao sessao = pdvUtils.logoutSessaoOperador();
		dao.logoutSessao(sessao);
		usuarioLogado.invalidaSessao();
	}

	public List<ResumoOperadorDTO> relatorioPorOperador(Calendar data, int operador) {
		Usuario usuario = dao.findById(operador);
		return pdvUtils.totalPorOperador(usuario, data);
	}

	public BigDecimal valorTotalOperador(List<ResumoOperadorDTO> resumo) {
		return resumoOperadorDTO.getTotalDinheiroNoPDV(resumo);
	}

	public List<Usuario> findAll() {
		return dao.findAll();
	}
}
