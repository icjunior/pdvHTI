package br.com.htisoftware.pdv.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.htisoftware.pdv.modelo.TipoAjuste;

public class TipoAjusteDAO {

	@Inject
	EntityManager em;

	public TipoAjuste findById(int codigo) {
		return em.find(TipoAjuste.class, codigo);
	}

	public List<TipoAjuste> findAll() {
		String jpql = "SELECT a FROM TipoAjuste a ORDER BY a.codigo";
		TypedQuery<TipoAjuste> query = em.createQuery(jpql, TipoAjuste.class);
		return query.getResultList();
	}

	public void gravar(TipoAjuste tipoAjuste) {
		em.getTransaction().begin();
		em.merge(tipoAjuste);
		em.getTransaction().commit();
	}
}
