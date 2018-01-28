package ir.maktab.pharmacy.patientDAO;

import ir.maktab.pharmacy.patientModel.Patient;

public interface PatientDAO {

	public void addPatient(Patient patient);
	public Patient getPatientByName(String name);
}
