package br.com.htisoftware.pdv.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.htisoftware.pdv.dto.CestDTO;
import br.com.htisoftware.pdv.modelo.CEST;

public class CestDAO {

	@Inject
	EntityManager em;

	public List<CEST> findAll() {
		String jpql = "SELECT c FROM CEST c ORDER BY c.codigo";
		TypedQuery<CEST> query = em.createQuery(jpql, CEST.class);
		return query.getResultList();
	}

	public CEST findById(String codigo) {
		CEST cest;
		String jpql = "SELECT c FROM CEST c WHERE c.cest = :pCest";
		TypedQuery<CEST> query = em.createQuery(jpql, CEST.class);
		query.setParameter("pCest", codigo);
		try {
			cest = query.getSingleResult();
			return cest;
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<CEST> buscar(CestDTO cestDTO) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<CEST> query = builder.createQuery(CEST.class);
		Root<CEST> from = query.from(CEST.class);
		Predicate predicate = builder.and();

		if (cestDTO.getCest() != "") {
			predicate = builder.and(predicate, builder.equal(from.get("cest"), cestDTO.getCest()));
		}

		if (cestDTO.getDescricao() != "") {
			predicate = builder.and(predicate, builder.like(builder.lower(from.get("descricao")),
					"%" + cestDTO.getDescricao().toLowerCase() + "%"));
		}

		TypedQuery<CEST> typedQuery = em
				.createQuery(query.select(from).where(predicate).orderBy(builder.asc(from.get("descricao"))));
		return typedQuery.getResultList();
	}

	public void gravar(CEST cest) {
		em.getTransaction().begin();
		em.merge(cest);
		em.getTransaction().commit();
	}
}
