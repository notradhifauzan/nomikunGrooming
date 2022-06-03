package nomi.controller.pet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nomi.DAO.pet.UserPetListDAO;
import nomi.model.user.OwnerModel;
import nomi.model.user.PetModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//this class will mainly redirect user to groomingBooking.jsp
//the class starts by retrieving ownerID, get all their pet(s) information
//redirect user to groomingBooking.jsp

@WebServlet("/init-user-pet")
public class PetListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PetListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//this class should be able to retrieve owner id,get all their pet list
		//and redirect to booking page
		
		//retrieving ownerID
		OwnerModel ownerBean = new OwnerModel();
		ownerBean = (OwnerModel)request.getSession().getAttribute("userinfo");
		int ownerID = ownerBean.getUserID();
		
		//test if ownerID is successfully retrieved
		System.out.println("from init-user-pet servlet: ");
		System.out.println("ownerID: " + ownerID);
		
		//create a List of type petmodel
		List<PetModel> petmodel = new ArrayList<PetModel>();
		
		//to get ALL pet(s) registered by the user #fetch the data from DAO
		petmodel = UserPetListDAO.getUserPet(ownerID);
		
		//set a session of user's registered pet
		request.getSession().setAttribute("all_pet", petmodel);
		
		//redirect to groomBooking.jsp
		response.sendRedirect("groomingBooking.jsp");
	
		
	}


}
