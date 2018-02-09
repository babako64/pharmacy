package ir.maktab.pharmacy.patientModel;

public class Patient {

	private int id;
	private String pationtName;
	private int insuranceNumber;
	private int insuranceID;

	public Patient(int id, String pationtName, int insuranceNumber, int insuranceID) {
		super();
		this.id = id;
		this.pationtName = pationtName;
		this.insuranceNumber = insuranceNumber;
		this.insuranceID = insuranceID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPationtName() {
		return pationtName;
	}

	public void setPationtName(String pationtName) {
		this.pationtName = pationtName;
	}

	public int getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(int insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public int getInsuranceID() {
		return insuranceID;
	}

	public void setInsuranceID(int insuranceID) {
		this.insuranceID = insuranceID;
	}

}
