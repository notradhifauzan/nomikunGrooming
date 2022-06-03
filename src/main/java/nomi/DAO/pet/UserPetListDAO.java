package nomi.DAO.pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nomi.DAO.ConnectionManager;
import nomi.model.user.PetModel;

public class UserPetListDAO {
	
	static Connection con;
	static ResultSet rs;
	static PreparedStatement ps;
	
	public static void get_pet_details(int petID,PetModel petbean)
	{
		con = ConnectionManager.getConnection();
		
		String GET_PET_INFO = "select * from pet where petID=?;";
		
		try {
			ps = con.prepareStatement(GET_PET_INFO);
			ps.setInt(1, petID);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				petbean.setPetType(rs.getString("petType"));
				petbean.setOwnerID(rs.getInt("ownerID"));
				petbean.setPetName(rs.getString("petname"));
				petbean.setAgeClass(rs.getString("ageClass"));
				petbean.setFurType(rs.getString("furType"));
			}
			else
			{
				petbean = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static List<PetModel> getUserPet(int ownerID)
	{
		con = nomi.DAO.ConnectionManager.getConnection();
		List<PetModel> petmodel = new ArrayList<PetModel>();
		
		String GET_ALL_PET = "select * from pet where ownerID=?;";
		con = ConnectionManager.getConnection();
		try {
			ps = con.prepareStatement(GET_ALL_PET);
			ps.setInt(1, ownerID);
			boolean row = ps.execute();
			rs = ps.executeQuery();
			
			if(row)
			{
				while(rs.next())
				{
					PetModel pm = new PetModel();
					pm.setOwnerID(rs.getInt("ownerID"));
					pm.setPetID(rs.getInt("petID"));
					pm.setPetName(rs.getString("petName"));
					pm.setPetType(rs.getString("petType"));
					
					petmodel.add(pm);

				}
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return petmodel;
	}

}
