package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.htisoftware.pdv.modelo.Produto;
import br.com.htisoftware.pdv.service.ProdutoService;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 3480934218172045272L;
	@Inject
	ProdutoService produtoService;
	List<Produto> produtos;
	Produto produto;
	String descricao;
	String ean;

	public void find() {
		produtos = produtoService.find(ean, descricao);
	}

	public String novo() {
		return "/pages/produto_cadastro?faces-redirect=true";
	}

	public String editar() {
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("produto", produto);
		return "/pages/produto_cadastro?faces-redirect=true";
	}

	public void findByName() {
		produtos = produtoService.findByName(descricao);
	}

	public void passaProduto(Produto produto) {
		PrimeFaces.current().dialog().closeDynamic(produto);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}
}
