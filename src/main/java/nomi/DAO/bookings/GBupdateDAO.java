package nomi.DAO.bookings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nomi.DAO.ConnectionManager;

public class GBupdateDAO {

	static Connection con;
	static ResultSet rs;
	static PreparedStatement ps;
	
	//update the grooming status to complete
	public static void update_to_complete(int bookingID)
	{
		final String UPDATE_TO_COMPLETE = "update bookings set progress = \"completed\" where bookingID=?;";
		con = ConnectionManager.getConnection();
		try {
			ps = con.prepareStatement(UPDATE_TO_COMPLETE);
			ps.setInt(1, bookingID);
			int row = ps.executeUpdate();
			
			if(row>0)
			{
				System.out.println("bookingID: " + bookingID + " has been updated to complete");
			}
			else
			{
				System.out.println("no row found for particular bookingID: " +bookingID);
			}
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//retrieve the staffID who currently in charged of a particular job
	public static int getStaffInCharged(int bookingID)
	{
		int staffID=0;
		
		final String GET_STAFF_ID = "select staffID from bookings where bookingID = ?";
		con = ConnectionManager.getConnection();
		try {
			ps = con.prepareStatement(GET_STAFF_ID);
			ps.setInt(1, bookingID);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				staffID = rs.getInt("staffID");
			}
			else
			{
				System.out.println("no row found #GBupdateDAO,getStaffInCharged");
			}
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return staffID;
	}
	
	//assign the job to the staff whom requested to do the job
	public static void assign_me(int bookingID, int staffID)
	{
		final String ASSIGN_ME = "update bookings set staffID = ? where bookingID = ?;";
		con = ConnectionManager.getConnection();
		try {
			ps = con.prepareStatement(ASSIGN_ME);
			ps.setInt(1, staffID);
			ps.setInt(2, bookingID);
			int row = ps.executeUpdate();
			if(row >0)
			{
				System.out.println("bookings has been updated!");
			}
			else
			{
				System.out.println("no row is being updated");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void update_to_cancel(int bookingID)
	{
		con = ConnectionManager.getConnection();
		final String UPDATE_TO_CANCEL = "update bookings set progress =\"cancelled\" where bookingID = ?;";
		try {
			ps = con.prepareStatement(UPDATE_TO_CANCEL);
			ps.setInt(1, bookingID);
			int row = ps.executeUpdate();
			
			if(row >0)
			{
				System.out.println("successfully cancel booking #GBupdateDAO.update_to_cancel");
			}
			else
			{
				System.out.println("fail to cancel boooking #GBupdateDAO.update_to_cancel");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static boolean is_job_pending(int bookingID)
	{
		boolean is_pending = true;
		
		con = ConnectionManager.getConnection();
		final String GET_STATUS = "select progress from bookings where bookingID = ?";
		try {
			ps = con.prepareStatement(GET_STATUS);
			ps.setInt(1, bookingID);
			rs = ps.executeQuery();
			if(rs.next())
			{
				if(rs.getString("progress").equalsIgnoreCase("pending"))
				{
					is_pending = true;
				}
				else
					is_pending = false;
			}
			else
			{
				System.out.println("could not retrieve any row #GbupdateDAO.is_job_pending()");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return is_pending;
	}
	
	public static boolean is_job_assigned(int bookingID)
	{
		boolean is_assign = true;
		
		con = ConnectionManager.getConnection();
		final String GET_STAFF = "select * from bookings where bookingID=?";
		try {
			ps = con.prepareStatement(GET_STAFF);
			ps.setInt(1, bookingID);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				int staffID = rs.getInt("staffID");
				if(staffID == 0)
				{
					System.out.println("no staff #GBupdateDAO");
					is_assign = false;
				}
				else
				{
					System.out.println("there is a staff #GBupdateDAO");
					is_assign = true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return is_assign;
	}
	
}
