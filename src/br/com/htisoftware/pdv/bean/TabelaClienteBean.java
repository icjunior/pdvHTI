package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.TabelaCliente;
import br.com.htisoftware.pdv.service.TabelaClienteService;
import br.com.htisoftware.pdv.util.PdvUtils;

@Named
@ViewScoped
public class TabelaClienteBean implements Serializable {

	private static final long serialVersionUID = 3170695952093255882L;
	@Inject
	Cliente cliente;
	@Inject
	TabelaClienteService tabelaClienteService;
	List<TabelaCliente> tabelas;
	@Inject
	TabelaCliente tabelaCliente;

	public void incluirCliente() {
		PdvUtils.abreDialog("/dialog/cliente_consulta", false, "100%", "85vh");
	}

	public void clienteSelecionado(SelectEvent event) {
		cliente = (Cliente) event.getObject();
	}

	public String novo() {
		return "cliente_produto_cadastra?faces-redirect=true";
	}

	public String visualizar() {
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("tabela", tabelaCliente);
		return "cliente_produto_cadastra?faces-redirect=true";
	}

	public void busca() {
		tabelas = tabelaClienteService.findByCustomer(cliente);
	}

	public void excluir() {
		tabelaClienteService.excluir(tabelaCliente);
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Exclusão", "Tabela de materiais excluída com sucesso.");
		tabelas.remove(tabelaCliente);
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
