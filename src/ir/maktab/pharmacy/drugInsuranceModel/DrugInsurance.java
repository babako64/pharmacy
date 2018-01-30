package ir.maktab.pharmacy.drugInsuranceModel;

public class DrugInsurance {

	String drugId;
	String insuranceId;
	int insuranceShare;
	public DrugInsurance(String drugId, String insuranceId, int insuranceShare) {
		super();
		this.drugId = drugId;
		this.insuranceId = insuranceId;
		this.insuranceShare = insuranceShare;
	}
	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	public String getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}
	public int getInsuranceShare() {
		return insuranceShare;
	}
	public void setInsuranceShare(int insuranceShare) {
		this.insuranceShare = insuranceShare;
	}
	
	

	
	
}
