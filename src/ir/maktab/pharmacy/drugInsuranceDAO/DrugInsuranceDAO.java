package ir.maktab.pharmacy.drugInsuranceDAO;

import java.util.ArrayList;

import ir.maktab.pharmacy.drugInsuranceModel.DrugInsurance;

public interface DrugInsuranceDAO {

	public void addInsuranceShare(String drugName, String insuranceName, int share);

	public ArrayList<DrugInsurance> getInsuranceShare(String drugName, String insuranceName);

	public ArrayList<DrugInsurance> getAll();

	public void remove(String drugName, String insureName);

	public void update(String drugName, String insuranceName, int share);
}
