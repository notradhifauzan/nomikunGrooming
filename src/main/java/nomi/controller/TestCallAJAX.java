package nomi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.HttpSession;
import nomi.DAO.user.UserLoginDAO;
import nomi.model.user.OwnerModel;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/greetingFromAjax")
public class TestCallAJAX extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OwnerModel bean = new OwnerModel();
	
	public TestCallAJAX()
	{
		super();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		
		System.out.println("servlet call success!");
		System.out.println("hello ajax!!!");
		
		String username = request.getParameter("username");
		System.out.println("from test call AJAX:: username =  " + username);
		
		if(UserLoginDAO.is_user_exist(username))
		{
			UserLoginDAO.init_user(bean, username);
			session.setAttribute("userinfo",bean);
		}
		else {
			out.print("false");
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		doGet(request,response);
	}
}
