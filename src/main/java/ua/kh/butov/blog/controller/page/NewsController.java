package ua.kh.butov.blog.controller.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.blog.Constants;
import ua.kh.butov.blog.controller.AbstractController;
import ua.kh.butov.blog.entity.Article;
import ua.kh.butov.blog.model.Items;

@WebServlet({"/news","/news/*"})
public class NewsController extends AbstractController {
	private static final long serialVersionUID = -109448214235583824L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUrl = req.getRequestURI();
		Items<Article> items = null;
		if(requestUrl.endsWith("/MyBlog/news") || requestUrl.endsWith("/MyBlog/news/")){
			items = getBusinessService().listArticles(0, Constants.LIMIT_ARTICLES_PER_PAGE);
		}
		else{
			//TODO display articles for category
		}
		req.setAttribute("list", items.getItems());
		forwardToPage("news.jsp", req, resp);
	}
}
