package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.dto.RelatorioProdutoEstoqueDTO;
import br.com.htisoftware.pdv.modelo.ProdutoAjusteEstoque;
import br.com.htisoftware.pdv.service.ProdutoService;

@Named
@ViewScoped
public class RelatorioProdutoEstoqueBean implements Serializable {

	private static final long serialVersionUID = -2559509590770860788L;
	List<ProdutoAjusteEstoque> ajustes;
	@Inject
	RelatorioProdutoEstoqueDTO relatorioProdutoEstoqueDTO;
	@Inject
	ProdutoService produtoService;

	public void buscar() {
		ajustes = produtoService.buscaAjustes(relatorioProdutoEstoqueDTO);
	}

	public RelatorioProdutoEstoqueDTO getRelatorioProdutoEstoqueDTO() {
		return relatorioProdutoEstoqueDTO;
	}

	public void setRelatorioProdutoEstoqueDTO(RelatorioProdutoEstoqueDTO relatorioProdutoEstoqueDTO) {
		this.relatorioProdutoEstoqueDTO = relatorioProdutoEstoqueDTO;
	}

	public List<ProdutoAjusteEstoque> getAjustes() {
		return ajustes;
	}

	public void setAjustes(List<ProdutoAjusteEstoque> ajustes) {
		this.ajustes = ajustes;
	}
}
