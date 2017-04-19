package ua.kh.butov.blog.controller.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import ua.kh.butov.blog.Constants;
import ua.kh.butov.blog.controller.AbstractController;
import ua.kh.butov.blog.entity.Article;
import ua.kh.butov.blog.entity.Comment;
import ua.kh.butov.blog.exception.RedirectToValidUrlException;

@WebServlet("/article/*")
public class ArticleController extends AbstractController {
	private static final long serialVersionUID = 7089871282763553056L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUrl = req.getRequestURI();
		try {
			String id = StringUtils.split(requestUrl, "/")[2];
			Article article = getBusinessService().viewArticle(Long.parseLong(id), requestUrl);
			if (article == null) {
				resp.sendRedirect("/MyBlog/404?url="+requestUrl);
			}
			else{
				req.setAttribute("article", article);
				List<Comment> comments = getBusinessService().listComments(article.getId(), 0, Constants.LIMIT_COMMENTS_PER_PAGE);
				req.setAttribute("comments", comments);
				forwardToPage("article.jsp", req, resp);
			}
		} catch (RedirectToValidUrlException e) {
			resp.sendRedirect(e.getUrl());
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			resp.sendRedirect("/404?url="+requestUrl);
		}
	}
}
