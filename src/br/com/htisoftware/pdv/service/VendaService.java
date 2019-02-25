package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.primefaces.PrimeFaces;

import br.com.htisoftware.pdv.dao.CupomDAO;
import br.com.htisoftware.pdv.enums.TipoOperacao;
import br.com.htisoftware.pdv.modelo.ConfiguracaoAtiva;
import br.com.htisoftware.pdv.modelo.Cupom;
import br.com.htisoftware.pdv.modelo.CupomItem;
import br.com.htisoftware.pdv.modelo.CupomPagamento;
import br.com.htisoftware.pdv.modelo.UsuarioLogado;
import br.com.htisoftware.pdv.util.PdvUtils;
import br.com.htisoftware.pdv.util.Impressora;
import br.com.htisoftware.pdv.util.Mensagens;

public class VendaService implements Serializable {

	private static final long serialVersionUID = -3913706956987520388L;
	@Inject
	CupomDAO dao;
	@Inject
	PdvUtils pdvUtils;

	public void registrar(String operacao, List<CupomItem> itens, List<CupomPagamento> pagamentos,
			UsuarioLogado usuario, ConfiguracaoAtiva config, BigDecimal troco, String cliente, BigDecimal valor,
			boolean preVenda, boolean cancelado, String observacao) {

		if (operacao != TipoOperacao.DESCRICAO.CANCELAMENTO) {
			if (preVenda) {
				String cupomPreVenda = pdvUtils.preparaCupomPreVenda(cliente, itens, pagamentos);
				int codigoRetornoImpressora = Impressora.imprimirCupomPreVenda(cupomPreVenda);

				if (codigoRetornoImpressora != 1) {
					Mensagens.mensagemTextoLivre(codigoRetornoImpressora);
					return;
				}
			} else {
				String cupomSAT = pdvUtils.preparaCupomSAT(cliente, itens, pagamentos);
				int codigoRetornoSAT = Impressora.imprimirCupomSAT(cupomSAT);

				if (codigoRetornoSAT != 1) {
					Mensagens.mensagemSAT(codigoRetornoSAT);
					return;
				}
			}
		}
		gravarCupom(operacao, itens, pagamentos, usuario, config, troco, cliente, valor, preVenda, cancelado,
				observacao);
		pagamentos = new ArrayList<>();
	}

	private void gravarCupom(String operacao, List<CupomItem> itens, List<CupomPagamento> pagamentos,
			UsuarioLogado usuario, ConfiguracaoAtiva config, BigDecimal troco, String cliente, BigDecimal valor,
			boolean preVenda, boolean cancelado, String observacao) {
		Cupom cupom = PdvUtils.montaCupomCabecalho(usuario, itens, cancelado, config.getConfiguracao(), troco, cliente,
				operacao, valor, preVenda, observacao);

		dao.registraCupom(cupom);

		if (operacao.equals(TipoOperacao.DESCRICAO.VENDA) || operacao.equals(TipoOperacao.DESCRICAO.CANCELAMENTO)) {
			dao.registraItens(itens, cupom);
		}

		if (operacao.equals(TipoOperacao.DESCRICAO.VENDA)) {
			dao.registraPagamentos(pagamentos, cupom);
		}

		PrimeFaces.current().executeScript("PF('subTotal').hide()");
		PrimeFaces.current().executeScript("PF('trocoDialog').show()");
	}
}