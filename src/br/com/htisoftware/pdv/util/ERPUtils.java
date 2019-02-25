package br.com.htisoftware.pdv.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.htisoftware.pdv.enums.TipoCST;
import br.com.htisoftware.pdv.enums.TipoMovimentacaoEstoque;
import br.com.htisoftware.pdv.modelo.CEST;
import br.com.htisoftware.pdv.modelo.CST;
import br.com.htisoftware.pdv.modelo.NCM;
import br.com.htisoftware.pdv.modelo.ProdutoAjusteEstoque;

public class ERPUtils {

	public static final List<CST> cstPIS(List<CST> csts) {
		List<CST> cstPIS = new ArrayList<>();
		csts.stream().filter(cst -> cst.getTipoCST().equals(TipoCST.PIS)).forEach(cst -> cstPIS.add(cst));
		return cstPIS;
	}

	public static final List<CST> cstICMS(List<CST> csts) {
		List<CST> cstICMS = new ArrayList<>();
		csts.stream().filter(cst -> cst.getTipoCST().equals(TipoCST.ICMS)).forEach(cst -> cstICMS.add(cst));
		return cstICMS;
	}

	public static final List<CST> cstCOFINS(List<CST> csts) {
		List<CST> cstCOFINS = new ArrayList<>();
		csts.stream().filter(cst -> cst.getTipoCST().equals(TipoCST.COFINS)).forEach(cst -> cstCOFINS.add(cst));
		return cstCOFINS;
	}

	public static final List<NCM> ncmFilter(List<NCM> ncms, String filtro) {
		List<NCM> ncm = new ArrayList<>();
		ncms.stream().filter(n -> n.getNcm().contains(filtro)).forEach(n -> ncm.add(n));
		return ncm;
	}

	public static final List<CEST> cestFilter(List<CEST> cests, String filtro) {
		List<CEST> cest = new ArrayList<>();
		cests.stream().filter(c -> c.getCest().contains(filtro)).forEach(c -> cest.add(c));
		return cest;
	}

	public static final BigDecimal calculaAjusteEstoque(ProdutoAjusteEstoque produto) {
		if (produto.getTipoAjuste().getTipoMovimentacaoEstoque().equals(TipoMovimentacaoEstoque.ENTRADA)) {
			return produto.getProduto().getEstoque().add(produto.getQuantidade());
		}
		return produto.getProduto().getEstoque().subtract(produto.getQuantidade());
	}
}
