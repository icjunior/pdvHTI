package br.com.htisoftware.pdv.dao;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.ClienteProduto;
import br.com.htisoftware.pdv.modelo.Produto;
import br.com.htisoftware.pdv.modelo.TabelaCliente;

public class TabelaClienteDAO {

	@Inject
	EntityManager em;

	public void salvar(Cliente cliente, Calendar dataInicial, Calendar dataFinal, List<ClienteProduto> produtos) {
		TabelaCliente tabela = new TabelaCliente(dataInicial, dataFinal, cliente, true);
		em.getTransaction().begin();
		em.persist(tabela);
		produtos.forEach(produto -> {
			produto.setTabelaCliente(tabela);
			em.persist(produto);
		});
		em.getTransaction().commit();
	}

	public List<ClienteProduto> findByCustomerAndProduct(Cliente cliente, Produto produto, Calendar dataInicial) {
		String jpql = "SELECT c FROM ClienteProduto c WHERE c.tabelaCliente.cliente.codigo = :pCliente "
				+ "AND c.produto.ean = :pProduto AND c.tabelaCliente.dataFinal > :pDataAtual "
				+ "AND c.tabelaCliente.ativo = true";
		TypedQuery<ClienteProduto> query = em.createQuery(jpql, ClienteProduto.class);
		query.setParameter("pCliente", cliente.getCodigo());
		query.setParameter("pProduto", produto.getEan());
		query.setParameter("pDataAtual", dataInicial);
		return query.getResultList();
	}

	public List<TabelaCliente> findByCustomer(Cliente cliente) {
		String jpql = "SELECT c FROM TabelaCliente c WHERE c.cliente.codigo = :pCliente AND c.ativo = true";
		TypedQuery<TabelaCliente> query = em.createQuery(jpql, TabelaCliente.class);
		query.setParameter("pCliente", cliente.getCodigo());
		return query.getResultList();
	}

	public ClienteProduto findByCustomerAndProductValids(Cliente cliente, Produto produto) {
		try {
			String jpql = "SELECT c FROM ClienteProduto c WHERE c.tabelaCliente.cliente.codigo = :pCliente "
					+ "AND c.produto.ean = :pProduto AND c.tabelaCliente.dataFinal >= :pDataAtual "
					+ "AND c.tabelaCliente.ativo = true";
			TypedQuery<ClienteProduto> query = em.createQuery(jpql, ClienteProduto.class);
			query.setParameter("pCliente", cliente.getCodigo());
			query.setParameter("pProduto", produto.getEan());
			query.setParameter("pDataAtual", Calendar.getInstance());
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void excluir(TabelaCliente tabelaCliente) {
		em.getTransaction().begin();
		em.merge(tabelaCliente);
		em.getTransaction().commit();
	}
}
