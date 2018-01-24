package ir.maktab.pharmacy.drugInsuranceDAO;

public interface DrugInsuranceDAO {

	public void addInsuranceShare(int drugID, int insuranceID);
	public int getInsuranceShare(String drugName, String insuranceName);
	
}
