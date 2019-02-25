package br.com.htisoftware.pdv.dao;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.htisoftware.pdv.annotation.ERP;
import br.com.htisoftware.pdv.modelo.Pedido;
import br.com.htisoftware.pdv.modelo.PedidoItem;
import br.com.htisoftware.pdv.util.DataUtils;

public class PedidoDAO {

	@Inject
	EntityManager em;

	@Inject
	@ERP
	EntityManager emHTI;

	public int gravar(Pedido pedido, List<PedidoItem> itensPedido) {
		em.getTransaction().begin();
		em.persist(pedido);
		itensPedido.forEach(i -> {
			i.setPedido(pedido);
			em.persist(i);
		});
		em.getTransaction().commit();
		return pedido.getCodigo();
	}

	public void cancelar(Pedido pedido) {
		em.getTransaction().begin();
		em.merge(pedido);
		em.getTransaction().commit();
	}

	public List<Pedido> find(int numeroPedido, Calendar dataInicial, Calendar dataFinal) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Pedido> query = builder.createQuery(Pedido.class);
		Root<Pedido> from = query.from(Pedido.class);
		Predicate predicate = builder.and();

		if (numeroPedido != 0) {
			predicate = builder.and(predicate, builder.equal(from.get("codigo"), numeroPedido));
		}

		predicate = builder.between(from.get("dataInclusao"), DataUtils.criaDataInicial(dataInicial),
				DataUtils.criaDataFinal(dataFinal));

		TypedQuery<Pedido> typedQuery = em
				.createQuery(query.select(from).where(predicate).orderBy(builder.asc(from.get("codigo"))));
		return typedQuery.getResultList();
	}

	public Pedido findById(int numeroPedido) {
		String jpql = "SELECT i FROM Pedido i WHERE codigo = :pNumeroPedido";
		TypedQuery<Pedido> query = emHTI.createQuery(jpql, Pedido.class);
		query.setParameter("pNumeroPedido", numeroPedido);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}