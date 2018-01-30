package ir.maktab.pharmacy.patientDAO;

import java.util.ArrayList;

import ir.maktab.pharmacy.patientModel.Patient;

public interface PatientDAO {

	public int addPatient(Patient patient);
	public Patient getPatientByName(String name);
	public ArrayList<Patient> getAll();
}
