package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.htisoftware.pdv.enums.TipoCartao;

@Entity
public class OperadoraCartao implements Serializable {

	private static final long serialVersionUID = 8193584577082164925L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int codigo;
	private String nome;
	private TipoCartao tipoCartao;
	private double taxa;
	private int diasRecebimento;
	@ManyToOne
	private Loja loja;

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

	public TipoCartao getTipoCartao() {
		return tipoCartao;
	}

	public void setTipoCartao(TipoCartao tipoCartao) {
		this.tipoCartao = tipoCartao;
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public int getDiasRecebimento() {
		return diasRecebimento;
	}

	public void setDiasRecebimento(int diasRecebimento) {
		this.diasRecebimento = diasRecebimento;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}
}
