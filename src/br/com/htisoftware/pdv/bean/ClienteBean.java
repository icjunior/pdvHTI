package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.service.ClienteService;
import br.com.htisoftware.pdv.util.PdvUtils;

@Named
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = -2114204288351882287L;
	private String nome;
	private String cnpj;
	private String fantasia;

	@Inject
	private ClienteService clienteService;
	@Inject
	Cliente cliente;
	private List<Cliente> clientes;

	public String novo() {
		return "/pages/cliente_cadastro?faces-redirect=true";
	}

	public String editar() {
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("cliente", cliente);
		return "/pages/cliente_cadastro?faces-redirect=true";
	}

	public void consulta() {
		PdvUtils.abreDialog("dialog/cliente_consulta", true, "100%", "85vh");
	}

	public void pesquisar() {
		clientes = clienteService.pesquisar(nome, cnpj, fantasia);
	}

	public void selecionar() {
		PrimeFaces.current().dialog().closeDynamic(cliente);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}
}
