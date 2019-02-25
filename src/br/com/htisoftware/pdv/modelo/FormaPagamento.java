package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FormaPagamento implements Serializable {

	private static final long serialVersionUID = -1793830533146720052L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int codigo;
	private String nome;
	private boolean status;
	@ManyToOne
	private Loja loja;
	private boolean permiteTroco;
	private boolean permiteDesconto;
	private boolean descontoAutomatico;

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public boolean isPermiteTroco() {
		return permiteTroco;
	}

	public void setPermiteTroco(boolean permiteTroco) {
		this.permiteTroco = permiteTroco;
	}

	public boolean isPermiteDesconto() {
		return permiteDesconto;
	}

	public void setPermiteDesconto(boolean permiteDesconto) {
		this.permiteDesconto = permiteDesconto;
	}

	public boolean isDescontoAutomatico() {
		return descontoAutomatico;
	}

	public void setDescontoAutomatico(boolean descontoAutomatico) {
		this.descontoAutomatico = descontoAutomatico;
	}

	@Override
	public String toString() {
		return this.nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((loja == null) ? 0 : loja.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormaPagamento other = (FormaPagamento) obj;
		if (codigo != other.codigo)
			return false;
		if (loja == null) {
			if (other.loja != null)
				return false;
		} else if (!loja.equals(other.loja))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
