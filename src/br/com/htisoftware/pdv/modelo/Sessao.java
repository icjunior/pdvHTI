package br.com.htisoftware.pdv.modelo;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Sessao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessao_sequence")
	@SequenceGenerator(name = "sessao_sequence", sequenceName = "sessao_sequence")
	private int codigo;
	@ManyToOne
	private Usuario usuario;
	private Calendar entrada;
	private Calendar saida;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getEntrada() {
		return entrada;
	}

	public void setEntrada(Calendar entrada) {
		this.entrada = entrada;
	}

	public Calendar getSaida() {
		return saida;
	}

	public void setSaida(Calendar saida) {
		this.saida = saida;
	}
}
