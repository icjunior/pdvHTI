package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.ClienteDAO;
import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.Endereco;

public class ClienteService implements Serializable {

	private static final long serialVersionUID = -9109492438585970879L;
	@Inject
	ClienteDAO dao;

	public List<Cliente> pesquisar(String nome, String cnpj, String fantasia) {
		return dao.pesquisar(nome, cnpj, fantasia);
	}

	public void gravar(Cliente cliente, List<Endereco> enderecos) {
		cliente.setEnderecos(enderecos);
		dao.gravar(cliente);
	}
}
