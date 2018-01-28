package ir.maktab.pharmacy.insuranceDAO;

import ir.maktab.pharmacy.insuranceModel.Insurance;

public interface InsuranceDAO {

	public void addInsuranse(Insurance obj);
	public Insurance getInsurance(int id);
}
