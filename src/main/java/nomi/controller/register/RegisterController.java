package nomi.controller.register;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import nomi.DAO.user.UserRegisterDAO;
import nomi.model.user.OwnerModel;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/user-registration")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OwnerModel bean;

	public RegisterController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		int phone = Integer.parseInt(request.getParameter("phone"));
		String password1 = request.getParameter("password");
		String password2 = request.getParameter("password2");

		if (is_email_valid(email) && is_password_match(password1, password2) && !is_username_taken(username)) {
			// registration success
			String message = "registration successful, you may login in to your registered account";
			bean = new OwnerModel();
			bean.setUsername(username);
			bean.setEmail(email);
			bean.setPhoneNo(phone);
			bean.setPassword(password1);
			UserRegisterDAO.registerUser_toDB(bean);
			
			//please initialize userID during login
			session.setAttribute("register_success", message);

			// need to initialize userID once logged in.
			response.sendRedirect("login.jsp");
			
		} else if (!is_email_valid(email)) {
			// email not valid
			System.out.println("email is not valid");
			String message = "not a valid email";
			session.setAttribute("register_error", message);
			response.sendRedirect("registration.jsp");
		} else if (!is_password_match(password1, password2)) {
			// password does not match
			System.out.println("password does not match");
			String message = "password does not match";
			session.setAttribute("register_error", message);
			response.sendRedirect("registration.jsp");
		} else if (is_username_taken(username)) {
			// username is already taken
			System.out.println("username is already taken");
			String message = "username already taken";
			session.setAttribute("register_error", message);
			response.sendRedirect("registration.jsp");
		}
	}

	// check if email is valid
	private boolean is_email_valid(String email) {
		boolean valid_mail = true;
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		if (matcher.matches())
			valid_mail = true;
		else
			valid_mail = false;

		return valid_mail;
	}

	// check if password is matched
	private boolean is_password_match(String password1, String password2) {
		boolean is_match = true;

		if (password1.equals(password2)) {
			is_match = true;
		} else {
			is_match = false;
		}

		return is_match;
	}

	// check if username is available
	private boolean is_username_taken(String username) {
		boolean is_taken = true;

		if (UserRegisterDAO.is_username_taken(username)) {
			is_taken = true;
		} else
			is_taken = false;

		return is_taken;
	}
}
