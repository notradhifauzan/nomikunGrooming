package nomi.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nomi.DAO.bookings.GroomBookDAO;
import nomi.DAO.staff.StaffDAO;
import nomi.model.booking.GroomBookingModel;
import nomi.model.user.StaffModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/staff-login")
public class StaffLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StaffLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		if(StaffDAO.admin_authentication(username, password))
		{
			//prepare session for staff details
			StaffModel staffBean = new StaffModel();
			StaffDAO.init_staff_details(staffBean, username);
			request.getSession().setAttribute("staffinfo", staffBean);
			
			
			//prepare all booking details
			List<GroomBookingModel> groomBookingList = new ArrayList<GroomBookingModel>();
			groomBookingList = GroomBookDAO.get_todays_booking();
			
			//prepare list of completed bookings
			List<GroomBookingModel> completeBookingList = new ArrayList<GroomBookingModel>();
			completeBookingList = GroomBookDAO.get_completed_bookings();
			
			request.getSession().setAttribute("todays_booking", groomBookingList);
			request.getSession().setAttribute("completed_booking",completeBookingList);
			response.sendRedirect("ADMINviewBooking.jsp");
			
		}
		else {
			//redirect to staff login
			response.sendRedirect("ADMINlogin.jsp");
		}
		
		
	}

}
