package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.htisoftware.pdv.modelo.Produto;
import br.com.htisoftware.pdv.service.ProdutoService;
import br.com.htisoftware.pdv.util.PdvUtils;

@Named
@ViewScoped
public class ProdutoVendaBean implements Serializable {

	private static final long serialVersionUID = -3054170389251158810L;
	@Inject
	private Produto produto;
	private BigDecimal valorVenda;
	@Inject
	ProdutoService produtoService;

	public void incluirItem() {
		PdvUtils.abreDialog("/dialog/produto_dialog", false, "100%", "85vh");
	}

	public void produtoSelecionado(SelectEvent event) {
		produto = (Produto) event.getObject();
	}

	public void gravar() {
		produtoService.gravarVenda(produto, valorVenda);
		produto = new Produto();
		valorVenda = new BigDecimal(0.00);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
}
