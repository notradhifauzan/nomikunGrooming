package nomi.controller.groomBooking;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nomi.DAO.bookings.GBupdateDAO;
import nomi.DAO.bookings.GroomBookDAO;
import nomi.model.booking.GroomBookingModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cancel-booking")
public class GBcancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GBcancel() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String message ="";
		
		System.out.println("servlet call success #GBcancel.java");
		int bookingID = Integer.parseInt(request.getParameter("bookingID"));
		
		//if grooming status is pending, cancel is valid
		//else, cancel is prohibited
		
		if(GBupdateDAO.is_job_pending(bookingID))
		{
			GBupdateDAO.update_to_cancel(bookingID);
			
			//replace new session for bookings table
			List<GroomBookingModel> groomBookingList = new ArrayList<GroomBookingModel>();
			groomBookingList = GroomBookDAO.get_todays_booking();
			request.getSession().setAttribute("todays_booking", groomBookingList);
			
			message = "job cancelled";
			System.out.println("successfully cancelled job");
		}
		else
		{
			System.out.println("job status is pending or already cancelled!");
			message = "job is either ongoing or already cancelled!";
		}
		
		out.print(message);
	}
			
}
