package br.com.htisoftware.pdv.modelo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Entity
public class Configuracao implements Serializable {

	private static final long serialVersionUID = -4130170520140149972L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int codigo;
	private int terminal;
	@Lob
	private byte[] logo;

	public int getTerminal() {
		return terminal;
	}

	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public StreamedContent getCarregaLogo() {
		InputStream arquivo = new ByteArrayInputStream(logo);
		return new DefaultStreamedContent(arquivo);
	}
}