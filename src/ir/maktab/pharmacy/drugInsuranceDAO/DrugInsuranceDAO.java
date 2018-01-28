package ir.maktab.pharmacy.drugInsuranceDAO;

public interface DrugInsuranceDAO {

	public void addInsuranceShare(int drugID, int insuranceID,int share);
	public int getInsuranceShare(String drugName, String insuranceName);
	
}
