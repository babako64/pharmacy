package ir.maktab.pharmacy.prescriptionModel;

public class Prescription {

	private int id;
	private String pationtName;
	private String doctorName;
	private int insuranceNumber;
	private String referralDate;
	
	public Prescription(int id, String pationtName, String doctorName, int insuranceNumber, String referralDate) {
		super();
		this.id = id;
		this.pationtName = pationtName;
		this.doctorName = doctorName;
		this.insuranceNumber = insuranceNumber;
		this.referralDate = referralDate;
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

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public int getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(int insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public String getReferralDate() {
		return referralDate;
	}

	public void setReferralDate(String referralDate) {
		this.referralDate = referralDate;
	}
	
	
}
