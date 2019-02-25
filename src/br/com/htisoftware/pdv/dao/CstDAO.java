package br.com.htisoftware.pdv.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.htisoftware.pdv.modelo.CST;

public class CstDAO {

	@Inject
	EntityManager em;

	public List<CST> findAll() {
		String jpql = "SELECT c FROM CST c ORDER BY c.codigo";
		TypedQuery<CST> query = em.createQuery(jpql, CST.class);
		return query.getResultList();
	}

	public CST findById(int codigo) {
		return em.find(CST.class, codigo);
	}
}
