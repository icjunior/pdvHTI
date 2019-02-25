package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.CupomDAO;
import br.com.htisoftware.pdv.dao.FormaPagamentoDAO;
import br.com.htisoftware.pdv.enums.TipoOperacao;
import br.com.htisoftware.pdv.modelo.ConfiguracaoAtiva;
import br.com.htisoftware.pdv.modelo.Cupom;
import br.com.htisoftware.pdv.modelo.CupomPagamento;
import br.com.htisoftware.pdv.modelo.FormaPagamento;
import br.com.htisoftware.pdv.modelo.UsuarioLogado;
import br.com.htisoftware.pdv.util.PdvUtils;

public class FundoService implements Serializable {

	private static final long serialVersionUID = 5964718154695243091L;
	@Inject
	FormaPagamentoDAO formaPagamentoDAO;
	@Inject
	CupomDAO cupomDAO;

	public void registrar(BigDecimal valor, UsuarioLogado usuarioLogado, ConfiguracaoAtiva configuracao,
			String observacao) {
		Cupom cupom = PdvUtils.montaCupomCabecalho(usuarioLogado, null, true, configuracao.getConfiguracao(), null,
				null, TipoOperacao.DESCRICAO.FUNDO_TROCO, valor, false, observacao);

		FormaPagamento formaPagamento = formaPagamentoDAO.findDinheiro();

		List<CupomPagamento> pagamentos = PdvUtils.montaCupomPagamento(formaPagamento, valor);

		cupomDAO.registraCupom(cupom);
		cupomDAO.registraPagamentos(pagamentos, cupom);
	}
}