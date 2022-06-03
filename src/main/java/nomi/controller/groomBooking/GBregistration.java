package nomi.controller.groomBooking;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nomi.DAO.bookings.GroomBookDAO;
import nomi.DAO.pet.UserPetListDAO;
import nomi.model.booking.GBpriceModel;
import nomi.model.booking.GroomBookingModel;
import nomi.model.user.OwnerModel;
import nomi.model.user.PetModel;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/gb-register")
public class GBregistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GBregistration() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		OwnerModel bean = new OwnerModel();
		GroomBookingModel gbBean = new GroomBookingModel();
		bean = (OwnerModel)request.getSession().getAttribute("ownermodel");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//get all the necessary groom booking infomation
		String bookdate = request.getParameter("bookdate");
		String timeslot = request.getParameter("timeslot");
		String groomtype = request.getParameter("groomtype");
		int petID = Integer.parseInt(request.getParameter("petID"));
		int ownerID = bean.getUserID();
		//---
		
		
		if(GroomBookDAO.is_date_available(bookdate, timeslot))
		{
			//you also need to retrieve the pet information
			//why? because price will depends on :: 
			//{pet type,age class,fur type,groom type}
			
			PetModel petbean = new PetModel();
			UserPetListDAO.get_pet_details(petID, petbean);
			
			double charges = GBpriceModel.calculateBill(petbean, groomtype);
			
			//init petbean for preparation, to send to DAO
			gbBean.setBookdate(bookdate);
			gbBean.setGroomtype(groomtype);
			gbBean.setOwnerID(ownerID);
			gbBean.setTimeslot(timeslot);
			gbBean.setPetID(petID);
			gbBean.setCharges(charges);
			
			//send to DAO
			GroomBookDAO.register_groom_booking(gbBean);
			
			out.print("true");
		}
		else
		{
			out.print("false");
		}

	}


}
