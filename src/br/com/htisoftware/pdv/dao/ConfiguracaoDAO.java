package br.com.htisoftware.pdv.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.htisoftware.pdv.modelo.Configuracao;

public class ConfiguracaoDAO {

	@Inject
	EntityManager em;

	public void gravar(Configuracao configuracao) {
		em.getTransaction().begin();
		em.merge(configuracao);
		em.getTransaction().commit();
	}

	public Configuracao buscar() {
		String jpql = "SELECT c FROM Configuracao c";
		TypedQuery<Configuracao> query = em.createQuery(jpql, Configuracao.class);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void gravarLogo(byte[] arquivo) {
		Configuracao configuracao = em.find(Configuracao.class, 14);
		configuracao.setLogo(arquivo);
		try {
			em.getTransaction().begin();
			em.merge(configuracao);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}