package br.com.htisoftware.pdv.converter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.inject.Named;

@SuppressWarnings("rawtypes")
@Named
public class DateConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String dataString) {
		DateTimeConverter converter = new DateTimeConverter();
		converter.setPattern("dd/MM/yyyy");
		converter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		Date data = (Date) converter.getAsObject(context, component, dataString);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);

		return calendar;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(new Date());
	}
}
