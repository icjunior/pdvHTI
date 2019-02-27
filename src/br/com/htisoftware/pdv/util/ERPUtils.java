package br.com.htisoftware.pdv.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.htisoftware.pdv.enums.TipoCST;
import br.com.htisoftware.pdv.enums.TipoMovimentacaoEstoque;
import br.com.htisoftware.pdv.modelo.CEST;
import br.com.htisoftware.pdv.modelo.CFOP;
import br.com.htisoftware.pdv.modelo.CST;
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
}
