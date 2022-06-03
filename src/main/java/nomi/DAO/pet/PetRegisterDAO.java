package nomi.DAO.pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nomi.DAO.ConnectionManager;
import nomi.model.user.PetModel;

public class PetRegisterDAO {
	
	static Connection con;
	static ResultSet rs;
	static PreparedStatement ps;
	
	public static boolean is_petname_exist(String petname,int ownerid)
	{
		boolean found = true;
		con = ConnectionManager.getConnection();
		String FIND_PETNAME = "select * from pet where petName=? and ownerID=?";
		try {
			ps = con.prepareStatement(FIND_PETNAME);
			ps.setString(1, petname);
			ps.setInt(2, ownerid);
			rs = ps.executeQuery();
			if(rs.next())
			{
				found = true;
			}
			else
				found = false;
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return found;
	}
	
	public static void registerPet(PetModel petbean)
	{
		if(petbean == null)
		{
			System.out.println("petbean is null");
		}
		else
		{
			System.out.println("registring pet details...");
			
			String REGISTER_PET = "insert into pet(petName,petType,ownerID,ageClass,furType,gender)\r\n"
					+ "values(?,?,?,?,?,?);";
			
			try {
				con = ConnectionManager.getConnection();
				ps = con.prepareStatement(REGISTER_PET);
				ps.setString(1, petbean.getPetName());
				ps.setString(2, petbean.getPetType());
				ps.setInt(3, petbean.getOwnerID());
				ps.setString(4, petbean.getAgeClass());
				ps.setString(5, petbean.getFurType());
				ps.setString(6, petbean.getGender());
				
				int row = ps.executeUpdate();
				
				if(row>0)
				{
					System.out.println("pet registration success!");
				}
				else
				{
					System.out.println("fail to register pet");
				}
				
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("fail to register pet");
			}
			
		}
	}

}
