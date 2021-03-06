package ua.kh.butov.blog.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "ErrorHandlerFilter", urlPatterns = "/*")
public class ErrorHandlerFilter extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(req, resp);
		} catch (Throwable th) {
			String requestUrl = req.getRequestURI();
			LOGGER.error("Error during processing the request: " + requestUrl, th);
			if (requestUrl.startsWith("/MyBlog/ajax/")) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} else if (!requestUrl.startsWith("/MyBlog/error")) {
				resp.sendRedirect("/MyBlog/error");
			} else {
				throw new ServletException(th);
			}
		}
	}
}
