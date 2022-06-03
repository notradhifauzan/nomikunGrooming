package nomi.controller.groomBooking;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nomi.DAO.bookings.GroomBookDAO;
import nomi.model.booking.GroomBookingModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/refresh-list")
public class GBrefreshTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public GBrefreshTable() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("servlet call success #GBrefreshTable");
		
		String nullMessage = request.getParameter("message");
		
		//prepare new session of latest grooming booking appointment
		List<GroomBookingModel> groomBookingList = new ArrayList<GroomBookingModel>();
		groomBookingList = GroomBookDAO.get_todays_booking();
		request.getSession().setAttribute("todays_booking", groomBookingList);
		
		out.print(nullMessage);
		
	}
}
