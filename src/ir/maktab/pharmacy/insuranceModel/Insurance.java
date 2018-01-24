package ir.maktab.pharmacy.insuranceModel;

public class Insurance {

	private int id;
	private String name;
	private int insuranceCode;
	
	public Insurance(int id, String name, int insuranceCode) {
		super();
		this.id = id;
		this.name = name;
		this.insuranceCode = insuranceCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInsuranceCode() {
		return insuranceCode;
	}
	public void setInsuranceCode(int insuranceCode) {
		this.insuranceCode = insuranceCode;
	}
	
	
	
}
