package ua.kh.butov.blog.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import ua.kh.butov.blog.dao.mapper.ArticleMapper;
import ua.kh.butov.blog.dao.mapper.ListMapper;
import ua.kh.butov.blog.dao.mapper.MapCategoryMapper;
import ua.kh.butov.blog.entity.Article;
import ua.kh.butov.blog.entity.Category;

public final class SQLDAO {
	private final QueryRunner sql = new QueryRunner();

	public Map<Integer, Category> mapCategories(Connection c) throws SQLException {
		return sql.query(c, "select * from category", new MapCategoryMapper());
	}
	
	public List<Article> listArticles(Connection c, int offset, int limit) throws SQLException {
		return sql.query(c, "select * from article a order by a.id desc limit ? offset ?", new ListMapper<>(new ArticleMapper()), limit, offset);
	}
	
	public int countArticles(Connection c) throws SQLException {
		return sql.query(c, "select count(*) from article a", new ScalarHandler<Number>()).intValue();
	}
	
	public List<Article> listArticlesByCategory(Connection c, String categoryUrl, int offset, int limit) throws SQLException {
		return sql.query(c, "select a.* from article a, category c where c.id=a.id_category and c.url=? order by a.id desc limit ? offset ?", 
				new ListMapper<>(new ArticleMapper()), categoryUrl, limit, offset);
	}
	
	public int countArticlesByCategory(Connection c, String categoryUrl) throws SQLException {
		return sql.query(c, "select count(a.id) from article a, category c where a.id_category=c.id and c.url=?", new ScalarHandler<Number>(), categoryUrl).intValue();
	}
	
	public Category findCategoryByUrl(Connection c, String categoryUrl) throws SQLException {
		return sql.query(c, "select * from category c where c.url = ?", new BeanHandler<>(Category.class), categoryUrl);
	}
}
