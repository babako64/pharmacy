package ir.maktab.pharmacy.drugModel;

public class Drug {

	private int id;
	private String name;
	private int stock;
	private String expireDate;
	private double price;

	public Drug(int id, String name, int stock, String expireDate, double price) {
		super();
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.expireDate = expireDate;
		this.price = price;
	}

	public Drug() {

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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
