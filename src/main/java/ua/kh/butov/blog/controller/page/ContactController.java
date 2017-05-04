package ua.kh.butov.blog.controller.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kh.butov.blog.controller.AbstractController;
import ua.kh.butov.blog.exception.ApplicationException;
import ua.kh.butov.blog.exception.ValidateException;
import ua.kh.butov.blog.form.ContactForm;

@WebServlet("/contact")
public class ContactController extends AbstractController {
	private static final long serialVersionUID = 7089871282763553056L;
	private static final String CONTACT_REQUEST_SUCESS = "CONTACT_REQUEST_SUCESS";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Boolean isSuccess = (Boolean) req.getSession().getAttribute(CONTACT_REQUEST_SUCESS);
		if (isSuccess == null) {
			isSuccess = Boolean.FALSE;
		} else {
			req.getSession().removeAttribute(CONTACT_REQUEST_SUCESS);
		}
		req.setAttribute("success", isSuccess);
		forwardToPage("contact.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ContactForm form = createForm(req, ContactForm.class);
			getBusinessService().createContactRequest(form);
			req.getSession().setAttribute(CONTACT_REQUEST_SUCESS, Boolean.TRUE);
			resp.sendRedirect("/MyBlog/contact");
		} catch (ValidateException e) {
			throw new ApplicationException("Validation should be done on client side: " + e.getMessage(), e);
		}
	}
}
