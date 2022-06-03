package nomi.DAO.bookings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import nomi.DAO.ConnectionManager;
import nomi.model.booking.GroomBookingModel;

public class GroomBookDAO {
	
	static Connection con;
	static ResultSet rs;
	static PreparedStatement ps;
	
	public static String get_staff_inCharge(int bookingID)
	{
		String staffname = "";
		
		con = ConnectionManager.getConnection();
		
		final String GET_STAFF_NAME = "select staff.staffName\r\n"
				+ "from bookings\r\n"
				+ "left join staff on bookings.staffID = staff.staffID\r\n"
				+ "where bookingID = ?;";
		
		try {
			ps = con.prepareStatement(GET_STAFF_NAME);
			ps.setInt(1, bookingID);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				staffname = rs.getString("staffName");
			}
			else
			{
				System.out.println("no row found #GroomBookDAO.get_staff_inCharge");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return staffname;
	}
	
	public static List<GroomBookingModel> get_todays_booking()
	{
		LocalDate currentDate = LocalDate.now();
		
		con = ConnectionManager.getConnection();
		List<GroomBookingModel> groomBookingList = new ArrayList<GroomBookingModel>();
		
		final String GET_ALL_BOOKINGS = "select bookingID, bookdate, timeslot, \r\n"
				+ "		pet_owner.username, pet_owner.ownerPhone, \r\n"
				+ "        pet.petName, pet.petType, pet.furType, pet.ageClass, pet.gender,  \r\n"
				+ "        groomType, \r\n"
				+ "        staff.staffName,progress,charges\r\n"
				+ "from bookings\r\n"
				+ "left join pet_owner on bookings.ownerID = pet_owner.ownerID\r\n"
				+ "left join pet on bookings.petID = pet.petID\r\n"
				+ "left join staff on bookings.staffID = staff.staffID\r\n"
				+ "order by bookdate;";
		
		try {
			ps = con.prepareStatement(GET_ALL_BOOKINGS);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				GroomBookingModel gbBean = new GroomBookingModel();
				String bookdate = rs.getString("bookdate");
				if(bookdate.equals(currentDate.toString()))
				{
					gbBean.setBookingID(rs.getInt("bookingID"));
					gbBean.setBookdate(rs.getString("bookdate"));
					gbBean.setTimeslot(rs.getString("timeslot"));
					gbBean.setOwnerName(rs.getString("username"));
					gbBean.setPetName(rs.getString("petName"));
					gbBean.setPettype(rs.getString("petType"));
					gbBean.setOwnerphone(rs.getString("ownerPhone"));
					gbBean.setGroomtype(rs.getString("groomType"));
					gbBean.setStaffName(rs.getString("staffName"));
					gbBean.setProgress(rs.getString("progress"));
					gbBean.setCharges(rs.getDouble("charges"));
					gbBean.setPetAgeClass(rs.getString("ageClass"));
					gbBean.setPetFurType(rs.getString("furType"));
					gbBean.setPetGender(rs.getString("gender"));
					
					groomBookingList.add(gbBean);
				}
			}
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return groomBookingList;
	}
	
	//drive method for debugging purposes
	public static void main(String[] args)
	{
		List<GroomBookingModel> groomBookingList = new ArrayList<GroomBookingModel>();
		
		groomBookingList = GroomBookDAO.get_todays_booking();
		
		for(int i=0;i<groomBookingList.size();i++)
		{
			System.out.println(groomBookingList.get(i).getBookingID() + "\n" +
								groomBookingList.get(i).getBookdate() + "\n" +
								groomBookingList.get(i).getGroomtype() + "\n" +
								groomBookingList.get(i).getPetAgeClass() + "\n" +
								groomBookingList.get(i).getPetFurType() + "\n" +
								groomBookingList.get(i).getPetGender());
		}
	}
	//--
	
	public static List<GroomBookingModel> get_completed_bookings()
	{
		con = ConnectionManager.getConnection();
		List<GroomBookingModel> groomBookingList = new ArrayList<GroomBookingModel>();
		
		final String GET_ALL_BOOKINGS = "select bookingID, bookdate, timeslot, pet_owner.username, pet.petName, pet.petType, pet_owner.ownerPhone, groomType, staff.staffName,progress,charges\r\n"
				+ "from bookings\r\n"
				+ "inner join pet_owner on bookings.ownerID = pet_owner.ownerID\r\n"
				+ "inner join pet on bookings.petID = pet.petID\r\n"
				+ "left join staff on bookings.staffID = staff.staffID;";
		
		try {
			ps = con.prepareStatement(GET_ALL_BOOKINGS);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				GroomBookingModel gbBean = new GroomBookingModel();
				if(rs.getString("progress").equalsIgnoreCase("completed") || 
						rs.getString("progress").equalsIgnoreCase("complete"))
				{
					gbBean.setBookdate(rs.getString("bookdate"));
					gbBean.setTimeslot(rs.getString("timeslot"));
					gbBean.setOwnerName(rs.getString("username"));
					gbBean.setPetName(rs.getString("petName"));
					gbBean.setPettype(rs.getString("petType"));
					gbBean.setOwnerphone(rs.getString("ownerPhone"));
					gbBean.setGroomtype(rs.getString("groomType"));
					gbBean.setStaffName(rs.getString("staffName"));
					gbBean.setProgress(rs.getString("progress"));
					gbBean.setCharges(rs.getDouble("charges"));
					
					groomBookingList.add(gbBean);
				}
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return groomBookingList;
	}
	
	public static List<GroomBookingModel> get_all_bookings()
	{
		con = ConnectionManager.getConnection();
		List<GroomBookingModel> groomBookingList = new ArrayList<GroomBookingModel>();
		
		final String GET_ALL_BOOKINGS = "select bookingID, bookdate, timeslot, pet_owner.username, pet.petName, pet.petType, pet_owner.ownerPhone, groomType, staff.staffName,progress,charges\r\n"
				+ "from bookings\r\n"
				+ "inner join pet_owner on bookings.ownerID = pet_owner.ownerID\r\n"
				+ "inner join pet on bookings.petID = pet.petID\r\n"
				+ "left join staff on bookings.staffID = staff.staffID;";
		
		try {
			ps = con.prepareStatement(GET_ALL_BOOKINGS);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				GroomBookingModel gbBean = new GroomBookingModel();
				
				gbBean.setBookdate(rs.getString("bookdate"));
				gbBean.setTimeslot(rs.getString("timeslot"));
				gbBean.setOwnerName(rs.getString("username"));
				gbBean.setPetName(rs.getString("petName"));
				gbBean.setPettype(rs.getString("petType"));
				gbBean.setOwnerphone(rs.getString("ownerPhone"));
				gbBean.setGroomtype(rs.getString("groomType"));
				gbBean.setStaffName(rs.getString("staffName"));
				gbBean.setCharges(rs.getDouble("charges"));
				
				groomBookingList.add(gbBean);
			}
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return groomBookingList;
	}
	
	public static void register_groom_booking(GroomBookingModel gbBean)
	{
		con = ConnectionManager.getConnection();
		final String GROOM_REGISTER = "insert into bookings(bookdate,timeslot,ownerID,progress,petID,groomType,charges)\r\n"
				+ "values(?,?,?,\"pending\",?,?,?);";
		try {
			ps = con.prepareStatement(GROOM_REGISTER);
			
			ps.setString(1, gbBean.getBookdate());
			ps.setString(2, gbBean.getTimeslot());
			ps.setInt(3, gbBean.getOwnerID());
			ps.setInt(4, gbBean.getPetID());
			ps.setString(5, gbBean.getGroomtype());
			ps.setDouble(6, gbBean.getCharges());
			
			int row = ps.executeUpdate();
			
			if(row>0)
			{
				System.out.println("successfully registered your booking");
			}
			else
			{
				System.out.println("nothing has been updated");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static boolean is_date_available(String bookdate,String timeslot)
	{
		System.out.println("checking date for: " + bookdate);
		System.out.println("checking timeslot for: " + timeslot);
		con = ConnectionManager.getConnection();
		boolean available = true;
		final String CHECK_BOOKING = "select * from bookings where bookdate=? and timeslot =?;";
		try {
			ps = con.prepareStatement(CHECK_BOOKING);
			ps.setString(1, bookdate);
			ps.setString(2, timeslot);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				available = false;
			}
			else {
				available = true;
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("boolean result from groomBookDAO: " + available);
		
		return available;
	}
}
