package ua.kh.butov.blog.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import ua.kh.butov.blog.entity.Account;
import ua.kh.butov.blog.entity.Article;
import ua.kh.butov.blog.entity.Comment;
import ua.kh.butov.blog.exception.ApplicationException;
import ua.kh.butov.blog.exception.ValidateException;
import ua.kh.butov.blog.form.CommentForm;
import ua.kh.butov.blog.model.SocialAccount;

public class DemoBusinessService extends BusinessServiceImpl {

	DemoBusinessService(ServiceManager serviceManager) {
		super(serviceManager);
	}

	@Override
	public Comment createComment(CommentForm form) throws ValidateException {
		form.validate(i18nService);
		try (Connection c = dataSource.getConnection()) {
			SocialAccount socialAccount = socialService.getSocialAccount(form.getAuthToken());
			Account a = new Account();
			a.setId(0L);
			a.setAvatar(socialAccount.getAvatar());
			a.setCreated(new Timestamp(System.currentTimeMillis()));
			a.setEmail(socialAccount.getEmail());
			a.setName(socialAccount.getName());
			Comment comment = new Comment(form.getIdArticle(), a, form.getContent(),
					new Timestamp(System.currentTimeMillis()));
			Article article = sql.findArticleForNewCommentNotification(c, form.getIdArticle());
			sendNewCommentNotification(article, form.getContent(), form.getLocale());
			return comment;
		} catch (SQLException e) {
			throw new ApplicationException("Can't execute db command: " + e.getMessage(), e);
		}
	}
}