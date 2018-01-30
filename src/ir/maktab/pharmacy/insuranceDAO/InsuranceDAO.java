package ir.maktab.pharmacy.insuranceDAO;

import java.util.ArrayList;

import ir.maktab.pharmacy.drugModel.Drug;
import ir.maktab.pharmacy.insuranceModel.Insurance;

public interface InsuranceDAO {

	public void addInsuranse(Insurance obj);
	public Insurance getInsurance(int id);
	public ArrayList<Insurance> getall();
	public void update(Insurance insurance);
	public void remove(int id);
}
