package ua.kh.butov.blog.controller.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.blog.controller.AbstractController;

@WebServlet("/error")
public class ErrorController extends AbstractController {
	private static final long serialVersionUID = -2011327370316036618L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forwardToPage("error.jsp", req, resp);
	}
}
