package ua.kh.butov.blog.controller.ajax;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.blog.controller.AbstractController;
import ua.kh.butov.blog.entity.Comment;
import ua.kh.butov.blog.form.CommentForm;

@WebServlet("/ajax/comment")
public class NewCommentController extends AbstractController {
	private static final long serialVersionUID = -3100941790315370252L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CommentForm form = createForm(req, CommentForm.class);
		Comment comment = getBusinessService().createComment(form);
		req.setAttribute("comments", Collections.singleton(comment));
		forwardToFragment("comments.jsp", req, resp);
	}
}
