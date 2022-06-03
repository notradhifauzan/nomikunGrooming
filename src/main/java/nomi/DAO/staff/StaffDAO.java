package nomi.DAO.staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nomi.DAO.ConnectionManager;
import nomi.model.user.StaffModel;

public class StaffDAO {
	
	static Connection con;
	static ResultSet rs;
	static PreparedStatement ps;
	
	public static void init_staff_details(StaffModel staffBean,String username)
	{
		String GET_STAFF_DETAILS ="select * from staff where staffName=?;";
		con = ConnectionManager.getConnection();
		try {
			ps = con.prepareStatement(GET_STAFF_DETAILS);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				staffBean.setName(rs.getString("staffName"));
				staffBean.setPassword(rs.getString("password"));
				staffBean.setStaffID(rs.getInt("staffID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static boolean admin_authentication(String username,String password)
	{
		boolean authentication = true;
		String STAFF_AUTH = "select * from staff where staffName=? and password = ?";
		con = ConnectionManager.getConnection();
		
		try {
			ps = con.prepareStatement(STAFF_AUTH);
			ps.setString(1, username);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				authentication = true;
			}
			else
			{
				authentication = false;
			}
			
			con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return authentication;
	}
}
