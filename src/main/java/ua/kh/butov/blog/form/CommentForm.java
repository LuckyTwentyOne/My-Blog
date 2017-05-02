package ua.kh.butov.blog.form;

import org.apache.commons.lang.StringUtils;

import ua.kh.butov.blog.exception.ValidateException;
import ua.kh.butov.blog.service.I18nService;

public class CommentForm extends AbstractForm {
	private Long idArticle;
	private String content;
	private String authToken;
	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getContent() {
		return content;
	}
	public Long getIdArticle() {
		return idArticle;
	}
	public String getAuthToken() {
		return authToken;
	}
	
	@Override
	public void validate(I18nService i18nService) throws ValidateException {
		if(idArticle == null) {
			throw new ValidateException("idArticle is required");
		}
		if(StringUtils.isBlank(content)) {
			throw new ValidateException("content is required");
		}
		if(StringUtils.isBlank(authToken)) {
			throw new ValidateException("authToken is required");
		}
	}
}
