package br.com.htisoftware.pdv.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.htisoftware.pdv.modelo.Sessao;
import br.com.htisoftware.pdv.modelo.Usuario;
import br.com.htisoftware.pdv.util.PdvUtils;

public class UsuarioDAO {

	@Inject
	EntityManager em;

	public Usuario logar(String usuario, String senha) {
		String jpql = "SELECT u FROM Usuario u WHERE u.login = UPPER(:pLogin) AND u.senha = UPPER(:pSenha) AND u.ativo = true";
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		query.setParameter("pLogin", usuario);
		query.setParameter("pSenha", senha);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_WARN, "Erro", "Usuário ou senha inválidos");
		}
		return null;
	}

	public void loginSesssao(Sessao sessao) {
		em.getTransaction().begin();
		em.persist(sessao);
		em.getTransaction().commit();
	}

	public void logoutSessao(Sessao sessao) {
		em.getTransaction().begin();
		em.merge(sessao);
		em.getTransaction().commit();
	}

	public Usuario findById(int operador) {
		Usuario usuario = em.find(Usuario.class, operador);
		return usuario;
	}

	public List<Usuario> findAll() {
		String jpql = "SELECT u FROM Usuario u ORDER BY u.nome";
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		return query.getResultList();
	}
}
