package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.ProdutoDAO;
import br.com.htisoftware.pdv.dto.RelatorioProdutoEstoqueDTO;
import br.com.htisoftware.pdv.modelo.Produto;
import br.com.htisoftware.pdv.modelo.ProdutoAjusteEstoque;
import br.com.htisoftware.pdv.util.PdvUtils;

public class ProdutoService implements Serializable {

	private static final long serialVersionUID = 6607792755843270023L;
	@Inject
	ProdutoDAO dao;

	public Produto findByEAN(Produto produto) {
		return dao.materialExiste(produto);
	}

	public List<Produto> findByName(String descricao) {
		return dao.findByName(descricao);
	}

	public List<Produto> findAll() {
		return dao.findAll();
	}

	public List<Produto> find(String ean, String descricao) {
		return dao.find(ean, descricao);
	}

	public void gravar(Produto produto) {
		dao.gravar(produto);
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Cadastro de produtos", "Material gravado com sucesso.");
	}

	public void gravarVenda(Produto produto, BigDecimal valorVenda) {
		produto.setValor(valorVenda);
		dao.gravar(produto);
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Alteração de preço",
				"Produto " + produto.getDescricao() + " alterado com sucesso.");
	}

	public void ajusteEstoque(ProdutoAjusteEstoque produtoAjuste) {
		dao.ajusteEstoque(produtoAjuste);
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Ajuste de estoque", "Ajuste de estoque para material "
				+ produtoAjuste.getProduto().getDescricao() + " lançado com sucesso");
	}

	public List<ProdutoAjusteEstoque> buscaAjustes(RelatorioProdutoEstoqueDTO relatorioProdutoEstoqueDTO) {
		return dao.buscaAjustes(relatorioProdutoEstoqueDTO);
	}
}
