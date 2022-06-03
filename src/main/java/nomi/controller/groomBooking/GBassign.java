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

@WebServlet("/gb-assign")
public class GBassign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GBassign() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("servlet call success! #gb-assign");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//fetching staff details
		StaffModel staffBean = new StaffModel();
		staffBean = (StaffModel)request.getSession().getAttribute("staffinfo");
		
		//fetch bookingDetails
		int bookingID =Integer.parseInt(request.getParameter("bookingID"));
		System.out.println("updating bookings for ID:"+bookingID);
		
		if(!GBupdateDAO.is_job_assigned(bookingID))
		{
			//if job is not assigned to anyone yet, then
			//assign myself(staff) to the job
			GBupdateDAO.assign_me(bookingID, staffBean.getStaffID());
			
			//replace new session for bookings table
			List<GroomBookingModel> groomBookingList = new ArrayList<GroomBookingModel>();
			groomBookingList = GroomBookDAO.get_todays_booking();
			request.getSession().setAttribute("todays_booking", groomBookingList);
			
			
			out.print("job successfully assigned to " +staffBean.getName());
			
		}
		else
		{
			String staffname = GroomBookDAO.get_staff_inCharge(bookingID);
			out.print("job is already assigned to " + staffname);
		}
	
	}

	

}
