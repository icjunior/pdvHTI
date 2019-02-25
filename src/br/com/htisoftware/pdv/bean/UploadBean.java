package br.com.htisoftware.pdv.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.htisoftware.pdv.service.ConfiguracaoService;
import br.com.htisoftware.pdv.util.PdvUtils;

@Named
@ViewScoped
public class UploadBean implements Serializable {

	private static final long serialVersionUID = 6322976904683653257L;
	private StreamedContent logo;
	@Inject
	ConfiguracaoService configuracaoService;

	public void upload(FileUploadEvent file) {

		UploadedFile arquivo = file.getFile();
		if (arquivo != null) {
			configuracaoService.gravarLogo(arquivo.getContents());
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Importação de logo", "Arquivo importado com sucesso.");
		}
	}

	public StreamedContent getLogo() {
		return logo;
	}

	public void setLogo(StreamedContent logo) {
		this.logo = logo;
	}
}
