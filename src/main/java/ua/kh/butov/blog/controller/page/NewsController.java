package ua.kh.butov.blog.controller.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.blog.Constants;
import ua.kh.butov.blog.controller.AbstractController;
import ua.kh.butov.blog.entity.Article;
import ua.kh.butov.blog.entity.Category;
import ua.kh.butov.blog.model.Items;
import ua.kh.butov.blog.model.Pagination;

@WebServlet({ "/news", "/news/*" })
public class NewsController extends AbstractController {
	private static final long serialVersionUID = -109448214235583824L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int offset = getOffset(req, Constants.LIMIT_ARTICLES_PER_PAGE);
		String requestUrl = req.getRequestURI();
		Items<Article> items = listArticles(requestUrl, offset, req);
		if (items == null) {
			resp.sendRedirect("/404?url=" + requestUrl);
		} else {
			req.setAttribute("list", items.getItems());
			Pagination pagination = new Pagination.Builder(requestUrl + "?", offset, items.getCount())
					.withLimit(Constants.LIMIT_ARTICLES_PER_PAGE).build();
			req.setAttribute("pagination", pagination);
			forwardToPage("news.jsp", req, resp);
		}
	}

	private Items<Article> listArticles(String requestUrl, int offset, HttpServletRequest req) {
		Items<Article> items;
		if (requestUrl.endsWith("/MyBlog//news") || requestUrl.endsWith("/MyBlog//news/")) {
			items = getBusinessService().listArticles(offset, Constants.LIMIT_ARTICLES_PER_PAGE);
			req.setAttribute("isNewsPage", Boolean.TRUE);
		} else {
			String categoryUrl = requestUrl.replace("/MyBlog//news", "");
			Category category = getBusinessService().findCategoryByUrl(categoryUrl);
			if (category == null) {
				return null;
			}
			items = getBusinessService().listArticlesByCategory(categoryUrl, offset, Constants.LIMIT_ARTICLES_PER_PAGE);
			req.setAttribute("selectedCategory", category);
		}
		return items;
	}
}
