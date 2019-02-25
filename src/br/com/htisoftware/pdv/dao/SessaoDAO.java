package br.com.htisoftware.pdv.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.htisoftware.pdv.modelo.Sessao;

public class SessaoDAO {

	@Inject
	EntityManager em;

	public Sessao sessaoAberta() {
		String jpql = "SELECT s FROM Sessao s WHERE s.saida IS NULL";
		TypedQuery<Sessao> query = em.createQuery(jpql, Sessao.class);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
