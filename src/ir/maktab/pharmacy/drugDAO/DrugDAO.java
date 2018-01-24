package ir.maktab.pharmacy.drugDAO;

import java.util.ArrayList;

import ir.maktab.pharmacy.drugModel.Drug;

public interface DrugDAO {

	public ArrayList<Drug> getAll();
	public Drug getDrug(String drugName);
	public void updatePrice();
	public int getCount();
	public void updateCount();
	
	
}
