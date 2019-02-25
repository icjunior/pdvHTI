package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;

import br.com.htisoftware.pdv.dao.CupomDAO;
import br.com.htisoftware.pdv.dao.FormaPagamentoDAO;
import br.com.htisoftware.pdv.enums.TipoOperacao;
import br.com.htisoftware.pdv.modelo.ConfiguracaoAtiva;
import br.com.htisoftware.pdv.modelo.Cupom;
import br.com.htisoftware.pdv.modelo.CupomPagamento;
import br.com.htisoftware.pdv.modelo.FormaPagamento;
import br.com.htisoftware.pdv.modelo.UsuarioLogado;
import br.com.htisoftware.pdv.util.PdvUtils;

public class SangriaService implements Serializable {

	private static final long serialVersionUID = -535674688101598215L;
	@Inject
	FormaPagamentoDAO formaPagamentoDAO;
	@Inject
	CupomDAO cupomDAO;
	@Inject
	PdvUtils pdvUtils;

	public void registrar(BigDecimal valor, UsuarioLogado usuarioLogado, ConfiguracaoAtiva configuracao,
			String observacao) {
		if (valor.compareTo(pdvUtils.totalDinheiroPorOperador(usuarioLogado)) == 1) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Sangria",
					"O total de dinheiro contido no PDV é inferior ao valor da sangria. Impossível continuar.");
			return;
		}

		Cupom cupom = PdvUtils.montaCupomCabecalho(usuarioLogado, null, true, configuracao.getConfiguracao(), null,
				null, TipoOperacao.DESCRICAO.SANGRIA, valor, false, observacao);

		FormaPagamento formaPagamento = formaPagamentoDAO.findDinheiro();

		List<CupomPagamento> pagamentos = PdvUtils.montaCupomPagamento(formaPagamento, valor);

		cupomDAO.registraCupom(cupom);
		cupomDAO.registraPagamentos(pagamentos, cupom);

		PrimeFaces.current().executeScript("PF('sangriaDialog').hide()");
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Sangria", "Sangria executada com sucesso");
	}
}
