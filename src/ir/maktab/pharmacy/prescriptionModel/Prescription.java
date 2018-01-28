package ir.maktab.pharmacy.prescriptionModel;

public class Prescription {

	private int id;
	private String doctorName;
	private String referralDate;
	
	public Prescription(int id, String doctorName, String referralDate) {
		super();
		this.id = id;
		this.doctorName = doctorName;
		this.referralDate = referralDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getReferralDate() {
		return referralDate;
	}

	public void setReferralDate(String referralDate) {
		this.referralDate = referralDate;
	}
	
	
}
