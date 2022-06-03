package nomi.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import nomi.DAO.pet.UserPetListDAO;
import nomi.DAO.user.UserLoginDAO;
import nomi.model.user.OwnerModel;
import nomi.model.user.PetModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user-login")
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public UserLoginController() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String loginError = null;
		
		if(is_user_exist(username) && is_passwordCorrect(username,password) 
				&& authenticate_user(username,password))
		{
			//login success, redirect to home page
			OwnerModel bean = new OwnerModel();
			
			//prepare ownermodel session to use accross servlets
			UserLoginDAO.init_user(bean, username, password);
			session.setAttribute("ownermodel", bean);
			
			//prepare userpetlist session to use across servlets
			List<PetModel> userPetList = new ArrayList<PetModel>();
			userPetList = UserPetListDAO.getUserPet(bean.getUserID());
			if(userPetList != null)
			{
				session.setAttribute("all_pet", userPetList);
			}
			
			response.sendRedirect("home.jsp");
			
		}
		else if(!is_user_exist(username))
		{
			//username does not exist
			loginError = "the username does not exist";
			session.setAttribute("loginError",loginError);
			response.sendRedirect("login.jsp");
		}
		else if(!is_passwordCorrect(username,password))
		{
			//password not correct
			loginError = "password is incorrect";
			session.setAttribute("loginError",loginError);
			response.sendRedirect("login.jsp");

		}
		
	}
	
	private boolean is_passwordCorrect(String username,String password)
	{
		boolean correct =true;
		
		if(UserLoginDAO.is_passwordCorrect(username, password))
			correct = true;
		else
			correct = false;

		return correct ;
		
	}
	
	private boolean authenticate_user(String username,String password)
	{
		boolean authenticate=true;
		
		if(UserLoginDAO.user_auth(username, password))
			authenticate = true;
		else
			authenticate = false;
		
		return authenticate;
	}
	
	private boolean is_user_exist(String username)
	{
		boolean exist = true;
		
		if(UserLoginDAO.is_user_exist(username))
			exist = true;
		else
			exist = false;
		
		return exist;
	}

}
