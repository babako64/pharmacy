package ir.maktab.pharmacy.drugDAO;

import java.util.ArrayList;

import ir.maktab.pharmacy.drugModel.Drug;

public interface DrugDAO {

	public void addDrug(Drug drug);

	public ArrayList<Drug> getAll();

	public Drug getDrug(String drugName);

	public void updatePrice(int id, double price);

	public int getCount();

	public void updateStock(int id, int count);

	public void updateDrug(Drug drug);

	public void removeDrugByName(String name);

	public void removeDrugById(int id);
}
