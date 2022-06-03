package nomi.DAO.pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nomi.DAO.ConnectionManager;

import nomi.model.user.PetModel;


public class PetListDAO {
	
	static Connection con;
	static ResultSet rs;
	static PreparedStatement ps;
	
	
	public static List<PetModel> getAllPet()
	{
		con = nomi.DAO.ConnectionManager.getConnection();
		List<PetModel> petmodel = new ArrayList<PetModel>();
		
		String GET_ALL_PET = "select * from pet;";
		con = ConnectionManager.getConnection();
		try {
			ps = con.prepareStatement(GET_ALL_PET);
			rs = ps.executeQuery();
			int petcount = 0;
			while(rs.next() && petcount<4)
			{
				PetModel pm = new PetModel();
				pm.setOwnerID(rs.getInt("ownerID"));
				pm.setPetID(rs.getInt("petID"));
				pm.setPetName(rs.getString("petName"));
				pm.setPetType(rs.getString("petType"));
				
				petmodel.add(pm);
				
				petcount ++;
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return petmodel;
	}

}
