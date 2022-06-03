package nomi.controller.register;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import nomi.DAO.pet.PetRegisterDAO;
import nomi.model.user.OwnerModel;
import nomi.model.user.PetModel;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/pet-register-controller")
public class PetRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PetRegisterController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("servlet call success");
		
		//retrieving ownerID
		OwnerModel ownerbean =(OwnerModel)request.getSession().getAttribute("ownermodel");
		int ownerID = ownerbean.getUserID();
		
		//retriving all necessary pet details for registration
		String petname = request.getParameter("petname");
		String petGender = request.getParameter("gender");
		String petType = request.getParameter("pettype");
		String furtype = request.getParameter("furtype");
		String ageClass = request.getParameter("ageclass");
		
		//store new pet details into petmodel bean
		PetModel petbean = new PetModel();
		
		petbean.setPetName(petname);
		petbean.setPetType(petType);
		petbean.setAgeClass(ageClass);
		petbean.setFurType(furtype);
		petbean.setGender(petGender);
		petbean.setOwnerID(ownerID);
		
		String success = "";
		if(petbean != null && !PetRegisterDAO.is_petname_exist(petname, ownerID))
		{
			//send petbean to PetRegisterDAO
			PetRegisterDAO.registerPet(petbean);
			success = "true";
			System.out.println("petbean initialized!");
			out.print(success);
		}
		else if(PetRegisterDAO.is_petname_exist(petname, ownerID)) 
		{
			System.out.println("pet name already exist, please try other name");
			success = "false";
			out.print(success);
		}


	}

}
