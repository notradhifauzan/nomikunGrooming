package nomi.controller.groomBooking;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nomi.DAO.bookings.GBupdateDAO;
import nomi.DAO.bookings.GroomBookDAO;
import nomi.model.booking.GroomBookingModel;
import nomi.model.user.StaffModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class GBupdate
 * CONTROLLER TO COMPLETE BOOKINGS
 */ 
@WebServlet("/complete-booking")
public class GBupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GBupdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("servlet call success! #GBupdate servlet::complete-booking");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//retrieve the ownerID of the current logged in staff
		StaffModel staffbean = new StaffModel();
		staffbean = (StaffModel)request.getSession().getAttribute("staffinfo");
		
		//retrieve the bookingID sent from ADMIMviewBooking.jsp
		int bookingID = Integer.parseInt(request.getParameter("bookingID"));
		
		//retrieve the current assiged staff of that particular bookingID
		int assignedStaff = GBupdateDAO.getStaffInCharged(bookingID);
		
		if(staffbean.getStaffID() != assignedStaff)
		{
			out.print("you are not allowed to perform this operation!");
		}
		else
		{
			GBupdateDAO.update_to_complete(bookingID);
			
			//prepare updated data for today's booking
			List<GroomBookingModel> groomBookingList = new ArrayList<GroomBookingModel>();
			groomBookingList = GroomBookDAO.get_todays_booking();
			request.getSession().setAttribute("todays_booking", groomBookingList);
			
			out.print("Job successfully completed!");
		}
		
		
	}

	

}
