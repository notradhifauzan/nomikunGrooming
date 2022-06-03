package nomi.DAO.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nomi.DAO.ConnectionManager;
import nomi.model.user.OwnerModel;

public class UserLoginDAO {
	
	static Connection con;
	static ResultSet rs;
	static PreparedStatement ps, ps2;
	
	public static boolean is_user_exist(String username)
	{
		boolean found = true;
		String CHECK_USERNAME = "select * from pet_owner where username=?";
		try {
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement(CHECK_USERNAME);
			ps.setString(1, username);

			rs = ps.executeQuery();

			if (rs.next()) {
				found = true;
			} else
				found = false;

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("pet_owner table :: sql error");
		}
		
		return found;
	}
	
	public static boolean is_passwordCorrect(String username,String password)
	{
		boolean correct = true;
		
		try {
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement("select * from pet_owner where username=?");
			ps.setString(1, username);

			rs = ps.executeQuery();

			if (rs.next()) {
				String actualPassword = rs.getString("password");
				if(!actualPassword.equals(password))
				{
					correct = false;
				}
				else
					correct = true;
			} else
				System.out.println("no row found for this particular user");

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("pet_owner table :: sql error");
		}
		
		
		return correct;
	}

	public static boolean user_auth(String username, String password) {
		boolean found = true;
		try {
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement("select * from pet_owner where username=? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				found = true;
				System.out.println("row from |pet_owner| fetched!");
			} else
				found = false;

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("pet_owner table :: sql error");
		}

		return found;
	}

	public static void init_user(OwnerModel bean, String username, String password) {
		try {
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement("select * from pet_owner where username=? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("(init_user)row fetched!");
				bean.setUsername(rs.getString("username"));
				bean.setUserID(rs.getInt("ownerID"));
				bean.setEmail(rs.getString("ownerMail"));
				bean.setPhoneNo(rs.getInt("ownerPhone"));
				bean.setPassword(rs.getString("password"));
				System.out.println(bean.getUsername());
			} else {
				System.out.println("no row fetched!");
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void init_user(OwnerModel bean, String username) {
		try {
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement("select * from pet_owner where username=?");
			ps.setString(1, username);

			rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("(init_user)row fetched!");
				bean.setUsername(rs.getString("username"));
				bean.setUserID(rs.getInt("ownerID"));
				bean.setEmail(rs.getString("ownerMail"));
				bean.setPhoneNo(rs.getInt("ownerPhone"));
				bean.setPassword(rs.getString("password"));
				System.out.println(bean.getUsername());
			} else {
				System.out.println("no row fetched!");
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
