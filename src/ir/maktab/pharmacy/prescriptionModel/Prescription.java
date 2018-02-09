package ir.maktab.pharmacy.prescriptionModel;

public class Prescription {

	private int id;
	private String doctorName;
	private String referralDate;
	private int patientId;

	public Prescription(int id, String doctorName, String referralDate, int patientId) {
		this.id = id;
		this.doctorName = doctorName;
		this.referralDate = referralDate;
		this.patientId = patientId;
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

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

}
