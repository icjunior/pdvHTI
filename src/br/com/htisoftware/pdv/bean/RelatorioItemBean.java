package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.CupomItem;
import br.com.htisoftware.pdv.modelo.Produto;
import br.com.htisoftware.pdv.service.CupomItemService;
import br.com.htisoftware.pdv.util.PdvUtils;

@Named
@ViewScoped
public class RelatorioItemBean implements Serializable {

	private static final long serialVersionUID = -7446654010710552092L;
	List<CupomItem> itens;
	@Inject
	CupomItemService cupomItemService;
	private Calendar dataInicial;
	private Calendar dataFinal;
	private int pdv;
	private int cupom;
	
	Cliente cliente;
	Produto produto;

	public void buscar() {
		itens = cupomItemService.buscar(dataInicial, dataFinal, pdv, cupom, cliente, produto);
	}

	public void incluirItem() {
		PdvUtils.abreDialog("/dialog/produto_dialog", false, "100%", "85vh");
	}

	public void incluirCliente() {
		PdvUtils.abreDialog("/dialog/cliente_consulta", false, "100%", "85vh");
	}

	public void produtoSelecionado(SelectEvent event) {
		produto = (Produto) event.getObject();
	}

	public void clienteSelecionado(SelectEvent event) {
		cliente = (Cliente) event.getObject();
	}

	public List<CupomItem> getItens() {
		return itens;
	}

	public void setItens(List<CupomItem> itens) {
		this.itens = itens;
	}

	public Calendar getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Calendar dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}

	public int getPdv() {
		return pdv;
	}

	public void setPdv(int pdv) {
		this.pdv = pdv;
	}

	public int getCupom() {
		return cupom;
	}

	public void setCupom(int cupom) {
		this.cupom = cupom;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}