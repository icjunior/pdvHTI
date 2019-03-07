package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "cpfcnpj", name = "cliente_uk"))
public class Cliente implements Serializable {

	private static final long serialVersionUID = -3379042698485857182L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_sequence")
	@SequenceGenerator(name = "cliente_sequence", sequenceName = "cliente_sequence")
	private int codigo;
	private String nome;
	private String apelido;
	private String cpfCnpj;
	private String rgIe;
	private String im;
	private String telefone;
	private String celular;
	@ManyToOne
	private Usuario usuario;
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> enderecos;
	@Email(message = "Email inválido")
	private String email;
	@Email(message = "Email inválido")
	private String emailNfe;
	@OneToMany(mappedBy = "cliente")
	private List<TabelaCliente> tabelas;

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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getRgIe() {
		return rgIe;
	}

	public void setRgIe(String rgIe) {
		this.rgIe = rgIe;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getIm() {
		return im;
	}

	public void setIm(String im) {
		this.im = im;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmailNfe() {
		return emailNfe;
	}

	public void setEmailNfe(String emailNfe) {
		this.emailNfe = emailNfe;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<TabelaCliente> getTabelas() {
		return tabelas;
	}

	@Override
	public String toString() {
		return codigo + " - " + nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apelido == null) ? 0 : apelido.hashCode());
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((cpfCnpj == null) ? 0 : cpfCnpj.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((emailNfe == null) ? 0 : emailNfe.hashCode());
		result = prime * result + ((enderecos == null) ? 0 : enderecos.hashCode());
		result = prime * result + ((im == null) ? 0 : im.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((rgIe == null) ? 0 : rgIe.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Cliente other = (Cliente) obj;
		if (apelido == null) {
			if (other.apelido != null)
				return false;
		} else if (!apelido.equals(other.apelido))
			return false;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (codigo != other.codigo)
			return false;
		if (cpfCnpj == null) {
			if (other.cpfCnpj != null)
				return false;
		} else if (!cpfCnpj.equals(other.cpfCnpj))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailNfe == null) {
			if (other.emailNfe != null)
				return false;
		} else if (!emailNfe.equals(other.emailNfe))
			return false;
		if (enderecos == null) {
			if (other.enderecos != null)
				return false;
		} else if (!enderecos.equals(other.enderecos))
			return false;
		if (im == null) {
			if (other.im != null)
				return false;
		} else if (!im.equals(other.im))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (rgIe == null) {
			if (other.rgIe != null)
				return false;
		} else if (!rgIe.equals(other.rgIe))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
}
