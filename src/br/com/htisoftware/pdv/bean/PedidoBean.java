package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.Pedido;
import br.com.htisoftware.pdv.modelo.PedidoItem;
import br.com.htisoftware.pdv.modelo.Produto;
import br.com.htisoftware.pdv.service.PedidoService;
import br.com.htisoftware.pdv.util.PdvUtils;
import br.com.htisoftware.pdv.util.PedidoUtils;

@Named
@ViewScoped
public class PedidoBean implements Serializable {

	private static final long serialVersionUID = -2456474544747805568L;

	private List<PedidoItem> itens;
	@Inject
	Cliente cliente;
	boolean novoPedido;
	int numeroPedido;
	Calendar dataInicial;
	Calendar dataFinal;
	@Inject
	PedidoService pedidoService;
	List<Pedido> pedidos;
	@Inject
	PedidoItem item;

	public String novo() {
		return "/pages/pedido_cadastra?faces-redirect=true";
	}

	public void buscar() {
		pedidos = pedidoService.find(numeroPedido, dataInicial, dataFinal);
	}

	public void finalizar() {
		if (itens == null || itens.isEmpty()) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_FATAL, "Finalização de pedido",
					"O pedido não possui itens incluídos. Impossível finalizar");
		}
		pedidoService.gravar(null, itens);
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Finalização de pedido",
				"Pedido " + itens.get(0).getPedido().getCodigo() + " gravado com sucesso!!");
		// limpeza de variáveis
		cliente = new Cliente();
		itens = new ArrayList<>();
	}

	public void incluirItem() {
		PdvUtils.abreDialog("/dialog/produto_dialog", false, "100%", "85vh");
	}

	public void incluirCliente() {
		PdvUtils.abreDialog("/dialog/cliente_consulta", false, "100%", "85vh");
	}

	public void produtoSelecionado(SelectEvent event) {
		Produto produto = (Produto) event.getObject();
		item = new PedidoItem(produto, Calendar.getInstance(), produto.getValor());

		if (itens == null) {
			itens = new ArrayList<>();
		}
		itens.add(item);

		item = new PedidoItem();
	}

	public void clienteSelecionado(SelectEvent event) {
		cliente = (Cliente) event.getObject();
	}

	public void processaPedidoCupom() {
		Pedido pedido = pedidoService.findById(numeroPedido);
		if (pedido == null) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Seleção de pedidos",
					"O pedido " + numeroPedido + " não foi encontrado.");
			return;
		}
		PrimeFaces.current().dialog().closeDynamic(pedido);
	}

	public String inicio() {
		return "/pages/pedido?faces-redirect=true";
	}

	public void eliminarItem() {
		itens.remove(item);
	}

	public BigDecimal getSubTotal() {
		if (itens == null) {
			return new BigDecimal(0.00);
		}
		return PedidoUtils.getSubTotal(itens);
	}

	public List<PedidoItem> getItens() {
		return itens;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isNovoPedido() {
		return novoPedido;
	}

	public void setNovoPedido(boolean novoPedido) {
		this.novoPedido = novoPedido;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public PedidoItem getItem() {
		return item;
	}

	public void setItem(PedidoItem item) {
		this.item = item;
	}
}