package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.ClienteProduto;
import br.com.htisoftware.pdv.modelo.Produto;
import br.com.htisoftware.pdv.modelo.TabelaCliente;
import br.com.htisoftware.pdv.service.TabelaClienteService;
import br.com.htisoftware.pdv.util.PdvUtils;

@Named
@ViewScoped
public class TabelaClienteCadastraBean implements Serializable {

	private static final long serialVersionUID = 3170695952093255882L;

	List<ClienteProduto> itens;
	@Inject
	Cliente cliente;
	@Inject
	ClienteProduto clienteProduto;
	@Inject
	TabelaClienteService tabelaClienteService;
	private Calendar dataInicial;
	private Calendar dataFinal;
	List<TabelaCliente> tabelas;
	@Inject
	TabelaCliente tabelaCliente;

	@PostConstruct
	private void init() {
		tabelaCliente = (TabelaCliente) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap()
				.get("tabela");
		if (tabelaCliente != null) {
			cliente = tabelaCliente.getCliente();
			itens = tabelaCliente.getProdutos();
			dataInicial = tabelaCliente.getDataInicial();
			dataFinal = tabelaCliente.getDataFinal();
			FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().remove("tabela");
			return;
		}

		tabelaCliente = new TabelaCliente();
	}

	public void incluirItem() {
		PdvUtils.abreDialog("/dialog/produto_dialog", false, "100%", "70vh");
	}

	public void produtoSelecionado(SelectEvent event) {
		Produto produto = (Produto) event.getObject();
		clienteProduto = new ClienteProduto(produto, new BigDecimal(0.00));
	}

	public void gravarItem() {
		List<ClienteProduto> itensJaCadastrados = tabelaClienteService.findByCustomerAndProduct(cliente,
				clienteProduto.getProduto(), dataInicial);

		if (!itensJaCadastrados.isEmpty()) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_ERROR, "Validação de material",
					"O material já existe em outra tabela. Sua inclusão só será possível após o término da vigência");
			return;
		}

		if (itens == null) {
			itens = new ArrayList<>();
		}
		itens.add(clienteProduto);

		clienteProduto = new ClienteProduto();
	}

	public void buscarItem() {
		PdvUtils.openDialog("clienteProdutoDialog");
	}

	public void eliminarItem() {
		itens.remove(clienteProduto);
	}

	public void salvar() {
		tabelaClienteService.salvar(cliente, dataInicial, dataFinal, itens);
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Tabela de materiais criada com sucesso.");

		// limpeza de variáveis
		itens = null;
		cliente = new Cliente();
		dataInicial = null;
		dataFinal = null;
	}

	public void incluirCliente() {
		PdvUtils.abreDialog("/dialog/cliente_consulta", false, "100%", "85vh");
	}

	public void clienteSelecionado(SelectEvent event) {
		cliente = (Cliente) event.getObject();
	}

	public List<ClienteProduto> getItens() {
		return itens;
	}

	public ClienteProduto getClienteProduto() {
		return clienteProduto;
	}

	public void setClienteProduto(ClienteProduto clienteProduto) {
		this.clienteProduto = clienteProduto;
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

	public Cliente getCliente() {
		return cliente;
	}

	public List<TabelaCliente> getTabelas() {
		return tabelas;
	}

	public void setTabelas(List<TabelaCliente> tabelas) {
		this.tabelas = tabelas;
	}

	public TabelaCliente getTabelaCliente() {
		return tabelaCliente;
	}

	public void setTabelaCliente(TabelaCliente tabelaCliente) {
		this.tabelaCliente = tabelaCliente;
	}
}
