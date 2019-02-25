package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.Endereco;
import br.com.htisoftware.pdv.service.ClienteService;
import br.com.htisoftware.pdv.util.PdvUtils;

@Named
@ViewScoped
public class ClienteCadastroBean implements Serializable {

	private static final long serialVersionUID = 5950662617174359286L;
	@Inject
	private ClienteService clienteService;
	Cliente cliente;
	@Inject
	Endereco endereco;
	private List<Endereco> enderecos;
	private List<Cliente> clientes;

	@PostConstruct
	private void init() {
		cliente = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("cliente");
		if (cliente != null) {
			enderecos = cliente.getEnderecos();
			FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().remove("cliente");
			return;
		}

		cliente = new Cliente();

	}

	public void gravar() {
		clienteService.gravar(cliente, enderecos);
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Cadastro de clientes",
				"Cliente " + cliente.getNome() + " cadastrado com sucesso.");

		// limpeza de variáveis
		cliente = new Cliente();
		enderecos = new ArrayList<>();
	}

	public void incluirEndereco() {
		if (enderecos == null) {
			enderecos = new ArrayList<>();
		}
		enderecos.add(endereco);

		// limpeza de variáveis
		endereco = new Endereco();
	}

	public void excluirEndereco() {
		this.enderecos.remove(endereco);
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}
}
