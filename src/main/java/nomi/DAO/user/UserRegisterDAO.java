package nomi.DAO.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nomi.DAO.ConnectionManager;
import nomi.model.user.OwnerModel;

//user this class for user registration and user validation only
public class UserRegisterDAO {

	static Connection con;
	static PreparedStatement ps;
	static ResultSet rs;

	// check if username already exist in the database
	public static boolean is_username_taken(String username) {
		boolean is_taken = true;
		String GET_USERNAME = "select * from pet_owner where username=?";
		try {
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement(GET_USERNAME);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {
				is_taken = true;
			} else {
				is_taken = false;
				System.out.println("from:RegisterDAO.is_username_taken:: no row fetched");
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("from:RegisterDAO.is_username_taken:: sql error");
		}

		return is_taken;
	}

	public static void registerUser_toDB(OwnerModel bean) {
		String INSERT_NEW_USER = "insert into pet_owner(username,ownerPhone,ownerMail,password)\r\n"
				+ "values(?,?,?,?)";
		try {
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement(INSERT_NEW_USER);
			ps.setString(1, bean.getUsername());
			ps.setInt(2, bean.getPhoneNo());
			ps.setString(3, bean.getEmail());
			ps.setString(4, bean.getPassword());

			int row = ps.executeUpdate();

			if (row > 0) {
				System.out.println("user details successfully registered");
			} else {
				System.out.println("fail to register user");
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("from:RegisterDAO.registerUser_toDB:: sql error");
		}
	}

}
