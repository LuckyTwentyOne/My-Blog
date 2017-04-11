package ua.kh.butov.blog.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.AbstractListHandler;

public final class ListMapper<T> extends AbstractListHandler<T> {
	private final AbstractMapper<T> handler;

	public ListMapper(AbstractMapper<T> handler) {
		this.handler = handler;
		this.handler.shouldBeIterateThroughResultSet = false;
	}

	@Override
	protected T handleRow(ResultSet rs) throws SQLException {
		return handler.handle(rs);
	}
}
