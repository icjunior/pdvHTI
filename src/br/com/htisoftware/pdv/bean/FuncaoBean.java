package br.com.htisoftware.pdv.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.htisoftware.pdv.util.PdvUtils;

@Named
@ViewScoped
public class FuncaoBean implements Serializable {

	private static final long serialVersionUID = 8491751202348617690L;
	private Integer funcao;

	public void selecionar() {
		switch (funcao) {
		case 1:
			PdvUtils.openDialog("subTotal");
			funcao = null;
			break;
		case 2:
			// PdvUtils.openDialog("consultaProdutoDialog");
			PdvUtils.abreDialog("/dialog/produto_dialog", false, "100%", "85vh");
			funcao = null;
			break;
		case 3:
			PdvUtils.openDialog("operadorLogin");
			funcao = null;
			break;
		case 4:
			PdvUtils.openDialog("estornoDialog");
			funcao = null;
			break;
		case 5:
			PdvUtils.openDialog("operadorLogout");
			funcao = null;
			break;
		case 6:
			PdvUtils.openDialog("configDialog");
			funcao = null;
			break;
		case 7:
			PdvUtils.openDialog("estornoCupomDialog");
			funcao = null;
			break;
		case 8:
			PdvUtils.openDialog("cpfCnpjDialog");
			funcao = null;
			break;
		case 9:
			PdvUtils.openDialog("operadorRelatorio");
			funcao = null;
			break;
		case 10:
			PdvUtils.openDialog("sangriaDialog");
			funcao = null;
			break;
		case 11:
			PdvUtils.openDialog("reforcoDialog");
			funcao = null;
			break;
		case 12:
			PdvUtils.abreDialog("dialog/pedido_dialog", true, "50%", "50%");
			funcao = null;
			break;
		default:
			PdvUtils.mensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Função não encontrada");
			funcao = null;
			break;
		}
	}

	public Integer getFuncao() {
		return funcao;
	}

	public void setFuncao(Integer funcao) {
		this.funcao = funcao;
	}
}
