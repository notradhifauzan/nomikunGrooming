package nomi.model.booking;

import nomi.model.user.PetModel;

public class GBpriceModel {
	
	public static double calculateBill(PetModel petbean,String groomtype)
	{
		double cost = 0.00;
		
		//implement business logic here
		
		if(groomtype.equalsIgnoreCase("basic"))
		{
			//base cost = ?
			cost += 5;
			if(petbean.getPetType().equalsIgnoreCase("cat"))
			{
				//cost +?
				cost += 5;
			}
			else
			{
				//cost +?
				cost += 7;
			}
			
			if(petbean.getAgeClass().equalsIgnoreCase("junior"))
			{
				//cost +?
				cost += 3;
			}
			else
			{
				//cost +?
				cost += 5;
			}
			
			if(petbean.getFurType().equalsIgnoreCase("short"))
			{
				//cost +?
				cost += 2;
			}
			else if(petbean.getFurType().equalsIgnoreCase("medium"))
			{
				//cost +?
				cost += 5;
			}
			else if(petbean.getFurType().equalsIgnoreCase("long"))
			{
				//cost +?
				cost += 7;
			}
			
		}
		else if(groomtype.equals("medicated"))
		{
			//base cost = ?
			cost += 10;
			if(petbean.getPetType().equalsIgnoreCase("cat"))
			{
				//cost +?
				cost += 5;
			}
			else
			{
				//cost +?
				cost += 7;
			}
			
			if(petbean.getAgeClass().equalsIgnoreCase("junior"))
			{
				//cost +?
				cost += 3;
			}
			else
			{
				//cost +?
				cost += 5;
			}
			
			if(petbean.getFurType().equalsIgnoreCase("short"))
			{
				//cost +?
				cost += 2;
			}
			else if(petbean.getFurType().equalsIgnoreCase("medium"))
			{
				//cost +?
				cost += 5;
			}
			else if(petbean.getFurType().equalsIgnoreCase("long"))
			{
				//cost +?
				cost += 7;
			}
		}
		else if(groomtype.equalsIgnoreCase("full"))
		{
			//base cost = ?
			cost += 15;
			if(petbean.getPetType().equalsIgnoreCase("cat"))
			{
				//cost +?
				cost += 5;
			}
			else
			{
				//cost +?
				cost += 7;
			}
			
			if(petbean.getAgeClass().equalsIgnoreCase("junior"))
			{
				//cost +?
				cost += 3;
			}
			else
			{
				//cost +?
				cost += 5;
			}
			
			if(petbean.getFurType().equalsIgnoreCase("short"))
			{
				//cost +?
				cost += 2;
			}
			else if(petbean.getFurType().equalsIgnoreCase("medium"))
			{
				//cost +?
				cost += 5;
			}
			else if(petbean.getFurType().equalsIgnoreCase("long"))
			{
				//cost +?
				cost += 7;
			}
		}
		
		return cost;
	}

}
