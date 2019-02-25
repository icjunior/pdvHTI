package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.htisoftware.pdv.modelo.CEST;
import br.com.htisoftware.pdv.modelo.CST;
import br.com.htisoftware.pdv.modelo.NCM;
import br.com.htisoftware.pdv.modelo.Produto;
import br.com.htisoftware.pdv.service.CestService;
import br.com.htisoftware.pdv.service.CstService;
import br.com.htisoftware.pdv.service.NcmService;
import br.com.htisoftware.pdv.service.ProdutoService;
import br.com.htisoftware.pdv.util.ERPUtils;

@Named
@ViewScoped
public class ProdutoCadastroBean implements Serializable {

	private static final long serialVersionUID = 3480934218172045272L;
	@Inject
	ProdutoService produtoService;
	@Inject
	CstService cstService;
	@Inject
	NcmService ncmService;
	@Inject
	CestService cestService;
	List<Produto> produtos;
	Produto produto;
	String descricao;
	String ean;
	List<CST> csts;
	List<NCM> ncms;
	List<CEST> cests;

	@PostConstruct
	private void init() {
		csts = cstService.findAll();
		ncms = ncmService.findAll();
		cests = cestService.findAll();

		produto = (Produto) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("produto");
		if (produto != null) {
			FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().remove("produto");
			return;
		}

		produto = new Produto();
	}

	public List<CST> getCSTPIS() {
		return ERPUtils.cstPIS(csts);
	}

	public List<CST> getCSTCOFINS() {
		return ERPUtils.cstCOFINS(csts);
	}

	public List<NCM> ncmAutoComplete(String filtro) {
		return ERPUtils.ncmFilter(ncms, filtro);
	}

	public List<CEST> cestAutoComplete(String filtro) {
		return ERPUtils.cestFilter(cests, filtro);
	}

	public void find() {
		produtos = produtoService.find(ean, descricao);
	}

	public void findByName() {
		produtos = produtoService.findByName(descricao);
	}

	public void gravar() {
		produtoService.gravar(produto);
		produto = new Produto();
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

	public List<CST> getCsts() {
		return csts;
	}

	public void setCsts(List<CST> csts) {
		this.csts = csts;
	}

	public List<NCM> getNcms() {
		return ncms;
	}

	public void setNcms(List<NCM> ncms) {
		this.ncms = ncms;
	}

	public List<CEST> getCests() {
		return cests;
	}

	public void setCests(List<CEST> cests) {
		this.cests = cests;
	}
}
