package ir.maktab.pharmacy.drupPrescriptionDAO;

public interface DrugPerscriptionDAO {

	public void addDrupPrescription(int drugId, int prescriptionID, int count, double payment);

	public double getPayment(int drugId, int prescriptionID);

}
