package br.com.htisoftware.pdv.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.htisoftware.pdv.modelo.NCM;

public class NcmDAO {

	@Inject
	EntityManager em;

	public List<NCM> findAll() {
		String jpql = "SELECT n FROM NCM n ORDER BY n.codigo";
		TypedQuery<NCM> query = em.createQuery(jpql, NCM.class);
		return query.getResultList();
	}

	public NCM findById(String codigoNCM) {
		NCM ncm;
		String jpql = "SELECT n FROM NCM n WHERE n.ncm = :pNcm";
		TypedQuery<NCM> query = em.createQuery(jpql, NCM.class);
		query.setParameter("pNcm", codigoNCM);
		try {
			ncm = query.getSingleResult();
			return ncm;
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<NCM> buscar(String ncm) {
		String jpql = "SELECT n FROM NCM n WHERE n.ncm = :pNcm";
		TypedQuery<NCM> query = em.createQuery(jpql, NCM.class);
		query.setParameter("pNcm", ncm);
		return query.getResultList();
	}

	public void gravar(NCM ncm) {
		em.getTransaction().begin();
		em.merge(ncm);
		em.getTransaction().commit();
	}
}
