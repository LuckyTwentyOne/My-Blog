package ua.kh.butov.blog.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import ua.kh.butov.blog.dao.mapper.ArticleMapper;
import ua.kh.butov.blog.dao.mapper.CommentMapper;
import ua.kh.butov.blog.dao.mapper.ListMapper;
import ua.kh.butov.blog.dao.mapper.MapCategoryMapper;
import ua.kh.butov.blog.entity.Account;
import ua.kh.butov.blog.entity.Article;
import ua.kh.butov.blog.entity.Category;
import ua.kh.butov.blog.entity.Comment;
import ua.kh.butov.blog.form.CommentForm;

public final class SQLDAO {
	private final QueryRunner sql = new QueryRunner();

	public Map<Integer, Category> mapCategories(Connection c) throws SQLException {
		return sql.query(c, "select * from category", new MapCategoryMapper());
	}

	public List<Article> listArticles(Connection c, int offset, int limit) throws SQLException {
		return sql.query(c, "select * from article a order by a.id desc limit ? offset ?",
				new ListMapper<>(new ArticleMapper()), limit, offset);
	}

	public int countArticles(Connection c) throws SQLException {
		return sql.query(c, "select count(*) from article a", new ScalarHandler<Number>()).intValue();
	}

	public List<Article> listArticlesByCategory(Connection c, String categoryUrl, int offset, int limit)
			throws SQLException {
		return sql.query(c,
				"select a.* from article a, category c where c.id=a.id_category and c.url=? order by a.id desc limit ? offset ?",
				new ListMapper<>(new ArticleMapper()), categoryUrl, limit, offset);
	}

	public int countArticlesByCategory(Connection c, String categoryUrl) throws SQLException {
		return sql.query(c, "select count(a.id) from article a, category c where a.id_category=c.id and c.url=?",
				new ScalarHandler<Number>(), categoryUrl).intValue();
	}

	public Category findCategoryByUrl(Connection c, String categoryUrl) throws SQLException {
		return sql.query(c, "select * from category c where c.url = ?", new BeanHandler<>(Category.class), categoryUrl);
	}

	public List<Article> listArticlesBySearchQuery(Connection c, String searchQuery, int offset, int limit)
			throws SQLException {
		String q = "%" + searchQuery + "%";
		return sql.query(c,
				"select * from article a where (a.title ilike ? or a.content ilike ?) order by a.id desc limit ? offset ?",
				new ListMapper<>(new ArticleMapper()), q, q, limit, offset);
	}

	public int countArticlesBySearchQuery(Connection c, String searchQuery) throws SQLException {
		String q = "%" + searchQuery + "%";
		return new QueryRunner()
				.query(c, "select count(a.id) from article a where (a.title ilike ? or a.content ilike ?)",
						new ScalarHandler<Number>(), q, q)
				.intValue();
	}

	public Article findArticleById(Connection c, long idArticle) throws SQLException {
		return sql.query(c, "select * from article a where a.id = ?", new ArticleMapper(), idArticle);
	}

	public void updateArticleViews(Connection c, Article article) throws SQLException {
		sql.update(c, "update article set views=? where id=?", article.getViews(), article.getId());
	}

	public List<Comment> listComments(Connection c, long idArticle, int offset, int limit) throws SQLException {
		return sql.query(c,
				"select c.*, a.name, a.email, a.created as accountCreated, a.avatar from "
						+ "comment c, account a where a.id=c.id_account and c.id_article=? order by c.id desc limit ? offset ?",
				new ListMapper<>(new CommentMapper(true)), idArticle, limit, offset);
	}

	public Account findAccountByEmail(Connection c, String email) throws SQLException {
		return sql.query(c, "select * from account a where a.email = ?", new BeanHandler<>(Account.class), email);
	}

	public Account createNewAccount(Connection c, String email, String name, String avatar) throws SQLException {
		return sql.insert(c, "insert into account(id,email,name,avatar) values(nextval('account_seq'),?,?,?)",
				new BeanHandler<>(Account.class), email, name, avatar);
	}

	public Comment createComment(Connection c, CommentForm form, long idAccount) throws SQLException {
		return sql.insert(c,
				"insert into comment(id, id_article,id_account,content) values(nextval('comment_seq'),?,?,?)",
				new CommentMapper(false), form.getIdArticle(), idAccount, form.getContent());
	}

	public Article findArticleForNewCommentNotification(Connection c, long id) throws SQLException {
		return sql.query(c, "select a.id, a.id_category, a.url, a.title from article a where a.id = ?",
				new ArticleMapper(), id);
	}

	public int countComments(Connection c, long id) throws SQLException {
		return sql.query(c, "select count(*) from comment where id_article=?", new ScalarHandler<Number>(), id)
				.intValue();
	}

	public void updateArticleComments(Connection c, Article article) throws SQLException {
		sql.update(c, "update article set comments=? where id=?", article.getComments(), article.getId());
	}
}
