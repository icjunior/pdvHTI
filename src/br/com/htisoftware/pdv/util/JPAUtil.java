package br.com.htisoftware.pdv.util;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import br.com.htisoftware.pdv.annotation.ERP;

public class JPAUtil {

	@PersistenceUnit(unitName = "hti")
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("hti");

	@PersistenceUnit(unitName = "erp")
	private static EntityManagerFactory emfERP = Persistence.createEntityManagerFactory("hti");

	@Produces
	@Default
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Produces
	@ERP
	public EntityManager getERP() {
		return emfERP.createEntityManager();
	}
}
