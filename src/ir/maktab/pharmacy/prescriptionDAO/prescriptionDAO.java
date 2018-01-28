package ir.maktab.pharmacy.prescriptionDAO;

import ir.maktab.pharmacy.prescriptionModel.Prescription;

public interface prescriptionDAO {

	public void addPrescription(Prescription prescription);
	public Prescription getPrescription(int id);
	
}
