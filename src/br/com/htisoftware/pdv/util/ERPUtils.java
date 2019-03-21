package br.com.htisoftware.pdv.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.htisoftware.pdv.dto.FinanceiroDTO;
import br.com.htisoftware.pdv.enums.StatusPagamentoFinanceiro;
import br.com.htisoftware.pdv.enums.TipoCST;
import br.com.htisoftware.pdv.enums.TipoDirecaoFinanceiro;
import br.com.htisoftware.pdv.enums.TipoMovimentacaoEstoque;
import br.com.htisoftware.pdv.modelo.CEST;
import br.com.htisoftware.pdv.modelo.CFOP;
import br.com.htisoftware.pdv.modelo.CST;
import br.com.htisoftware.pdv.modelo.Financeiro;
import br.com.htisoftware.pdv.modelo.NCM;
import br.com.htisoftware.pdv.modelo.ProdutoAjusteEstoque;

public class ERPUtils {

	public static final List<CST> cstPIS(List<CST> csts) {
		return csts.stream().filter(cst -> cst.getTipoCST().equals(TipoCST.PIS)).collect(Collectors.toList());
	}

	public static final List<CST> cstICMS(List<CST> csts) {
		return csts.stream().filter(cst -> cst.getTipoCST().equals(TipoCST.ICMS)).collect(Collectors.toList());
	}

	public static final List<CST> cstCOFINS(List<CST> csts) {
		return csts.stream().filter(cst -> cst.getTipoCST().equals(TipoCST.COFINS)).collect(Collectors.toList());
	}

	public static final List<NCM> ncmFilter(List<NCM> ncms, String filtro) {
		return ncms.stream().filter(n -> n.getNcm().contains(filtro)).collect(Collectors.toList());
	}

	public static final List<CEST> cestFilter(List<CEST> cests, String filtro) {
		return cests.stream().filter(c -> c.getCest().contains(filtro)).collect(Collectors.toList());
	}

	public static final List<CFOP> cfopFilter(List<CFOP> cfops, String filtro, TipoMovimentacaoEstoque entrada) {
		return cfops.stream()
				.filter(cfop -> cfop.getTipoMovimentacaoEstoque().equals(entrada) && cfop.getCfop().contains(filtro))
				.collect(Collectors.toList());
	}

	public static final BigDecimal calculaAjusteEstoque(ProdutoAjusteEstoque produto) {
		if (produto.getTipoAjuste().getTipoMovimentacaoEstoque().equals(TipoMovimentacaoEstoque.ENTRADA)) {
			return produto.getProduto().getEstoque().add(produto.getQuantidade());
		}
		return produto.getProduto().getEstoque().subtract(produto.getQuantidade());
	}

	public static BigDecimal financeiroTotalBruto(List<Financeiro> financeiros) {
		if (financeiros != null) {
			return financeiros.stream().map(financeiro -> financeiro.getValor()).reduce(BigDecimal.ZERO,
					BigDecimal::add);
		}
		return new BigDecimal(0.00);
	}

	public static BigDecimal financeiroTotalDescontos(List<Financeiro> financeiros) {
		if (financeiros != null) {
			return financeiros.stream().map(financeiro -> financeiro.getValor().multiply(financeiro.getDesconto()))
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		}
		return new BigDecimal(0.00);
	}

	public static BigDecimal financeiroTotalAcrescimo(List<Financeiro> financeiros) {
		if (financeiros != null) {
			return financeiros.stream().map(financeiro -> financeiro.getValor().multiply(financeiro.getAcrescimo()))
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		}
		return new BigDecimal(0.00);
	}

	public static BigDecimal financeiroTotalLiquido(List<Financeiro> financeiros) {
		if (financeiros != null) {
			return financeiros.stream().map(financeiro -> financeiro.getTotalLiquido()).reduce(BigDecimal.ZERO,
					BigDecimal::add);
		}
		return new BigDecimal(0.00);
	}

	public static List<Financeiro> calculaDesmembramento(Financeiro financeiro, int numeroParcelas) {
		List<Financeiro> financeiroDesmembrado = new ArrayList<>();
		List<Financeiro> financeirosRelacionados = new ArrayList<>();
		financeirosRelacionados.add(financeiro);
		BigDecimal valorLiquido = financeiro.getValor().add(financeiro.getAcrescimo())
				.subtract(financeiro.getDesconto());
		BigDecimal valorParcela = valorLiquido.divide(new BigDecimal(numeroParcelas));
		for (int i = 0; i < numeroParcelas; i++) {
			financeiroDesmembrado.add(new Financeiro(financeiro.getNotaCabecalho(), financeiro.getAtribuicao(), null,
					valorParcela, financeiro.getCodBarras(), BigDecimal.ZERO, BigDecimal.ZERO,
					financeiro.getStatusPagamentoFinanceiro(), financeiro.getTipoDirecaoFinanceiro(),
					financeiro.getObservacao(), financeiro.isExcluido(), financeiro.getTipoPagamentoFinanceiro(),
					financeirosRelacionados, financeiro.getReferencia()));
		}
		return financeiroDesmembrado;
	}

	public static Financeiro criaAgrupamento(List<Financeiro> financeiros, FinanceiroDTO financeiroDTO) {
		Financeiro financeiro;

		BigDecimal valorTotalNotas = financeiros.stream().map(fin -> fin.getValor()).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		BigDecimal valorDescontoNotas = financeiros.stream().map(fin -> fin.getDesconto()).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		BigDecimal valorAcrescimoNotas = financeiros.stream().map(fin -> fin.getAcrescimo()).reduce(BigDecimal.ZERO,
				BigDecimal::add);

		financeiro = new Financeiro(financeiros.get(0).getNotaCabecalho(), financeiroDTO.getAtribuicao(),
				financeiroDTO.getVencimento(), valorTotalNotas, financeiroDTO.getCodBarras(), valorDescontoNotas,
				valorAcrescimoNotas, StatusPagamentoFinanceiro.ABERTO, TipoDirecaoFinanceiro.PAGAR,
				financeiroDTO.getObservacao(), false, null, financeiros, financeiroDTO.getReferencia());
		return financeiro;
	}
}
