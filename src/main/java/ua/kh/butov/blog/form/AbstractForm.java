package ua.kh.butov.blog.form;

import java.util.Locale;

import ua.kh.butov.blog.exception.ValidateException;
import ua.kh.butov.blog.model.AbstractModel;
import ua.kh.butov.blog.service.I18nService;

public abstract class AbstractForm extends AbstractModel{
	protected Locale locale;
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public Locale getLocale() {
		return locale;
	}
	public void validate(I18nService i18nService) throws ValidateException {

	}
}
