package ir.maktab.pharmacy.drugPrescriptionModel;

public class DrugPrescription {

	int dId;
	int pID;
	int count;
	double payment;

	public DrugPrescription(int dId, int pID, int count, double payment) {
		super();
		this.dId = dId;
		this.pID = pID;
		this.count = count;
		this.payment = payment;
	}

	public int getdId() {
		return dId;
	}

	public void setdId(int dId) {
		this.dId = dId;
	}

	public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

}
