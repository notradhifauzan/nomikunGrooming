package nomi.controller.groomBooking;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nomi.DAO.bookings.GroomBookDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/gb-validation")
public class GBvalidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GBvalidation() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		response.setContentType("text/html");
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate currentDate = LocalDate.now();
		
		System.out.println("gb-validation servlet call success");
		//
		String bookingdate = request.getParameter("bookingdate");
		String timeslot = request.getParameter("timeslot");
		//
		LocalDate bookdate = LocalDate.parse(bookingdate, dtf);
		
		PrintWriter out = response.getWriter();
		
		String message = null;
		
		if(bookdate.compareTo(currentDate)>0 || bookdate.compareTo(currentDate)==0)
		{
			//this is a valid booking date
			System.out.println("booking date: " + bookdate);
			System.out.println("current date: " + currentDate);
			
			System.out.println("booking date is more or equal to current date");
			
			if(GroomBookDAO.is_date_available(bookingdate, timeslot))
			{
				System.out.println("date is available");
				message = "true";
			}
			else
			{
				System.out.println("date is not available");
				message = "false";
			}
			
		}
		else
		{
			//this is NOT a valid booking date
			System.out.println("booking date: " + bookdate);
			System.out.println("current date: " + currentDate);
			
			System.out.println("booking date is less or not equal to current date");
			
			message = "false";
		}
		
		out.print(message);
	}
}
