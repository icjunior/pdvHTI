package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.TabelaClienteDAO;
import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.ClienteProduto;
import br.com.htisoftware.pdv.modelo.Produto;
import br.com.htisoftware.pdv.modelo.TabelaCliente;

public class TabelaClienteService implements Serializable {

	private static final long serialVersionUID = -8455379809998670443L;
	@Inject
	TabelaClienteDAO dao;

	public void salvar(Cliente cliente, Calendar dataInicial, Calendar dataFinal, List<ClienteProduto> itens) {
		dao.salvar(cliente, dataInicial, dataFinal, itens);
	}

	public List<ClienteProduto> findByCustomerAndProduct(Cliente cliente, Produto produto, Calendar dataInicial) {
		return dao.findByCustomerAndProduct(cliente, produto, dataInicial);
	}

	public List<TabelaCliente> findByCustomer(Cliente cliente) {
		return dao.findByCustomer(cliente);
	}

	public ClienteProduto findByCustomerAndProductValids(Cliente cliente, Produto produto) {
		return dao.findByCustomerAndProductValids(cliente, produto);
	}

	public void excluir(TabelaCliente tabelaCliente) {
		tabelaCliente.setAtivo(false);
		tabelaCliente.setDataEliminacao(Calendar.getInstance());
		dao.excluir(tabelaCliente);
	}
}
