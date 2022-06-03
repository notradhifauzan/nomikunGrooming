package nomi.model.booking;

public class GroomBookingModel {
	
	private int bookingID;
	private String bookdate;
	private String timeslot;
	private int ownerID;
	private String ownerName;
	private String petName;
	private String ownerphone;
	private String staffName;
	private String progress;
	private int petID;
	private String groomtype;
	private String pettype;
	private double charges;
	private String petAgeClass;
	private String petGender;
	private String petFurType;
	
	public synchronized String getPetAgeClass() {
		return petAgeClass;
	}

	public synchronized void setPetAgeClass(String petAgeClass) {
		this.petAgeClass = petAgeClass;
	}

	public synchronized String getPetGender() {
		return petGender;
	}

	public synchronized void setPetGender(String petGender) {
		this.petGender = petGender;
	}

	public synchronized String getPetFurType() {
		return petFurType;
	}

	public synchronized void setPetFurType(String petFurType) {
		this.petFurType = petFurType;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public String getPettype() {
		return pettype;
	}

	public void setPettype(String pettype) {
		this.pettype = pettype;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getOwnerphone() {
		return ownerphone;
	}

	public void setOwnerphone(String ownerphone) {
		this.ownerphone = ownerphone;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public double getCharges() {
		return charges;
	}

	public void setCharges(double charges) {
		this.charges = charges;
	}

	public int getPetID() {
		return petID;
	}
	
	public void setPetID(int petID) {
		this.petID = petID;
	}
	
	public String getBookdate() {
		return bookdate;
	}
	public void setBookdate(String bookdate) {
		this.bookdate = bookdate;
	}
	public String getTimeslot() {
		return timeslot;
	}
	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}
	public int getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	public String getGroomtype() {
		return groomtype;
	}
	public void setGroomtype(String groomtype) {
		this.groomtype = groomtype;
	}
}
