package br.com.htisoftware.pdv.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.htisoftware.pdv.dto.FormaPagamentoDTO;
import br.com.htisoftware.pdv.modelo.Cupom;
import br.com.htisoftware.pdv.modelo.CupomPagamento;
import br.com.htisoftware.pdv.modelo.FormaPagamento;
import br.com.htisoftware.pdv.util.DataUtils;

public class FormaPagamentoDAO implements Serializable {

	private static final long serialVersionUID = -907931482330753765L;
	@Inject
	EntityManager em;

	public FormaPagamento findById(int idPagamento) {
		return em.find(FormaPagamento.class, idPagamento);
	}

	public List<FormaPagamento> findAll() {
		String jpql = "SELECT f FROM FormaPagamento f ORDER BY f.codigo";
		TypedQuery<FormaPagamento> query = em.createQuery(jpql, FormaPagamento.class);
		return query.getResultList();
	}

	public FormaPagamento findDinheiro() {
		String jpql = "SELECT f FROM FormaPagamento f WHERE f.nome = 'Dinheiro'";
		TypedQuery<FormaPagamento> query = em.createQuery(jpql, FormaPagamento.class);
		return query.getSingleResult();
	}

	public List<FormaPagamentoDTO> buscarPorDataEPDV(Calendar dataInicial, Calendar dataFinal, int pdv) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<FormaPagamentoDTO> query = builder.createQuery(FormaPagamentoDTO.class);
		Root<CupomPagamento> fromPagamento = query.from(CupomPagamento.class);
		Predicate predicate = builder.and();
		List<Predicate> predicates = new ArrayList<>();

		Join<CupomPagamento, Cupom> joinCupom = fromPagamento.join("cupom");
		Join<CupomPagamento, FormaPagamento> joinFormaPagamento = fromPagamento.join("formaPagamento");

		predicate = builder.between(joinCupom.get("data"), DataUtils.criaDataInicial(dataInicial),
				DataUtils.criaDataFinal(dataFinal));
		predicates.add(predicate);

		predicate = builder.equal(joinCupom.get("operacao"), "Venda");
		predicates.add(predicate);

		if (pdv != 0) {
			predicate = builder.and(builder.equal(joinCupom.get("terminal"), pdv));
			predicates.add(predicate);
		}

		Predicate[] pr = new Predicate[predicates.size()];
		predicates.toArray(pr);

		List<Expression<?>> groupBy = Arrays.asList(joinFormaPagamento.get("nome"), joinCupom.get("terminal"));

		TypedQuery<FormaPagamentoDTO> typedQuery = em
				.createQuery(query
						.select(builder.construct(FormaPagamentoDTO.class, joinFormaPagamento.get("nome"),
								builder.diff(builder.sum(fromPagamento.get("valor")),
										builder.sum(fromPagamento.get("troco"))),
								builder.count(joinFormaPagamento.get("nome")), joinCupom.get("terminal")))
						.where(pr).groupBy(groupBy));

		return typedQuery.getResultList();

	}
}
