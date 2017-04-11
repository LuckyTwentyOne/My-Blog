package ua.kh.butov.blog.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;

import ua.kh.butov.blog.dao.mapper.MapCategoryMapper;
import ua.kh.butov.blog.entity.Category;

public final class SQLDAO {
	private final QueryRunner sql = new QueryRunner();

	public Map<Integer, Category> mapCategories(Connection c) throws SQLException {
		return sql.query(c, "select * from category", new MapCategoryMapper());
	}
}
