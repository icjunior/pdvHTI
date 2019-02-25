package br.com.htisoftware.pdv.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.htisoftware.pdv.modelo.Produto;
import br.com.htisoftware.pdv.modelo.ProdutoAjusteEstoque;
import br.com.htisoftware.pdv.service.ProdutoService;
import br.com.htisoftware.pdv.util.PdvUtils;

@Named
@ViewScoped
public class ProdutoEstoqueBean implements Serializable {

	private static final long serialVersionUID = -1436527245507266436L;
	@Inject
	private ProdutoAjusteEstoque produtoAjuste;
	@Inject
	ProdutoService produtoService;

	public void incluirItem() {
		PdvUtils.abreDialog("/dialog/produto_dialog", false, "100%", "85vh");
	}

	public void produtoSelecionado(SelectEvent event) {
		produtoAjuste.setProduto((Produto) event.getObject());
	}

	public void gravar() {
		produtoService.ajusteEstoque(produtoAjuste);
		produtoAjuste = new ProdutoAjusteEstoque();
	}

	public ProdutoAjusteEstoque getProdutoAjuste() {
		return produtoAjuste;
	}

	public void setProdutoAjuste(ProdutoAjusteEstoque produtoAjuste) {
		this.produtoAjuste = produtoAjuste;
	}
}
