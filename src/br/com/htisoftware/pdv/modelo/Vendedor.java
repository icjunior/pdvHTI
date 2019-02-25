package br.com.htisoftware.pdv.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Vendedor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendedor_sequence")
	@SequenceGenerator(name = "vendedor_sequence", sequenceName = "vendedor_sequence")
	private int codigo;

	private String nome;
	private boolean ativo;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
