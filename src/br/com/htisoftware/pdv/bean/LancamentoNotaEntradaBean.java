package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.htisoftware.pdv.enums.TipoNota;
import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.NotaCabecalho;
import br.com.htisoftware.pdv.modelo.NotaItem;
import br.com.htisoftware.pdv.modelo.Produto;
import br.com.htisoftware.pdv.service.NotaService;
import br.com.htisoftware.pdv.util.PdvUtils;

@Named
@ViewScoped
public class LancamentoNotaEntradaBean implements Serializable {

	private static final long serialVersionUID = -7898687517763188502L;
	@Inject
	NotaCabecalho notaCabecalho;
	@Inject
	NotaItem notaItem;
	@Inject
	NotaService notaService;

	public void incluirItem() {
		PdvUtils.abreDialog("/dialog/produto_dialog", false, "100%", "85vh");
	}

	public void incluirCliente() {
		PdvUtils.abreDialog("/dialog/cliente_consulta", false, "100%", "85vh");
	}

	public void produtoSelecionado(SelectEvent event) {
		Produto produto = (Produto) event.getObject();
		notaItem.setProduto(produto);
		notaCabecalho.adicionaItem(notaItem);
		notaItem = new NotaItem();
	}

	public void clienteSelecionado(SelectEvent event) {
		Cliente cliente = (Cliente) event.getObject();
		notaCabecalho.setCliente(cliente);
	}

	public void gravar() {
		notaService.gravar(notaCabecalho);
		notaCabecalho = new NotaCabecalho();

	}

	public void excluirItem(NotaItem item) {
		notaCabecalho.excluiItem(item);
	}

	public List<TipoNota> getTiposNota() {
		return Arrays.asList(TipoNota.values());
	}

	public NotaCabecalho getNotaCabecalho() {
		return notaCabecalho;
	}

	public void setNotaCabecalho(NotaCabecalho notaCabecalho) {
		this.notaCabecalho = notaCabecalho;
	}

	public NotaItem getNotaItem() {
		return notaItem;
	}

	public void setNotaItem(NotaItem notaItem) {
		this.notaItem = notaItem;
	}
}
