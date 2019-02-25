package br.com.htisoftware.pdv.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.htisoftware.pdv.modelo.Aliquota;

public class AliquotaDAO {

	@Inject
	EntityManager em;

	public List<Aliquota> findAll() {
		String jpql = "SELECT a FROM Aliquota a ORDER BY a.codigo";
		TypedQuery<Aliquota> query = em.createQuery(jpql, Aliquota.class);
		return query.getResultList();
	}

	public Aliquota findById(int codigo) {
		return em.find(Aliquota.class, codigo);
	}

	public void gravar(Aliquota aliquota) {
		em.getTransaction().begin();
		em.merge(aliquota);
		em.getTransaction().commit();
	}
}