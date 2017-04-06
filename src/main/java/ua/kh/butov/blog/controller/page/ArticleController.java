package ua.kh.butov.blog.controller.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.blog.controller.AbstractController;

@WebServlet("/article")
public class ArticleController extends AbstractController {
	private static final long serialVersionUID = 7089871282763553056L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forwardToPage("article.jsp", req, resp);
	}
}
