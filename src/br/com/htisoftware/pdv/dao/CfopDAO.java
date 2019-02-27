package br.com.htisoftware.pdv.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.htisoftware.pdv.modelo.CFOP;

public class CfopDAO {

	@Inject
	EntityManager em;

	public CFOP findById(String codigo) {
		String jpql = "SELECT c FROM CFOP c WHERE c.cfop = :pCfop";
		TypedQuery<CFOP> query = em.createQuery(jpql, CFOP.class);
		query.setParameter("pCfop", codigo);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<CFOP> findAll() {
		String jpql = "SELECT c FROM CFOP c";
		TypedQuery<CFOP> query = em.createQuery(jpql, CFOP.class);
		return query.getResultList();
	}
}
