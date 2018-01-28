package ir.maktab.pharmacy.drugDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import ir.maktab.pharmacy.drugModel.Drug;

public class DrugDAOImp implements DrugDAO{

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/pharmacy";

	private static final String USER = "root";
	private static final String PASS = "";
	
	Connection conn = null;
	Statement stmt = null;
	
	private final String SELECT = "SELECT";
	private final String FROM = "FROM";
	private final String WHERE = "WHERE";
	private final String INSERT = "INSERT";
	
	public static void main(String[] args) {
		
	//	Drug d = new Drug(1, "jelphen", 35, "12/4/2018", 2000);
		DrugDAOImp di = new DrugDAOImp();
	//	ArrayList<Drug> list = new ArrayList<>();
		
		di.updateStock(2, 20);
		
		Drug list=di.getDrug("asetaminophen");
		System.out.println(list.getId() +" " + list.getName() + " " + list.getStock() + " " + list.getPrice());
	}
	
	@Override
	public void addDrug(Drug drug) {
//		Connection conn = null;
//		Statement stmt = null;
		String sql=null;
		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String selectSQL = "SELECT id,name FROM drug WHERE name='" + drug.getName() + "' or id= '" + drug.getId() + "'";
			ResultSet rs = stmt.executeQuery(selectSQL);
			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");
				if (drug.getName().equals(name) || drug.getId()==id) {
					System.out.println("Exist this Drug");
					JOptionPane.showMessageDialog(null,"Exist this Drug \n" + "Drug ID : " + id,"Warning",JOptionPane.WARNING_MESSAGE);    
					System.out.println("ID: " + drug.getId());
					return;
				}
			}
			
			
			if(drug.getId()==0) {
			 sql = "INSERT INTO drug (name, stock,expire_date,price) " + "VALUES('" + drug.getName() + "','"
					+ drug.getStock() + "','" + drug.getExpireDate() + "','" + drug.getPrice() + "')";
			}else {
				
				sql = "INSERT INTO drug (id,name,stock,expire_date,price) " + "VALUES('" + drug.getId() + "','" + drug.getName() + "','"
						+ drug.getStock() + "','" + drug.getExpireDate() + "','" + drug.getPrice() + "')";
			}
			stmt.executeUpdate(sql);

		} catch (Exception e) {

		} finally {

			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
	}

	@Override
	public ArrayList<Drug> getAll() {
		
		ArrayList<Drug> stList = new ArrayList<>();
		int id;
		String name = null;
		int stock;
		String expireDate=null;
		double price;
		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String sql = "SELECT * FROM drug ";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				id = rs.getInt("id");
				name = rs.getString("name");
				stock = rs.getInt("stock");
				expireDate = rs.getString("expire_date");
				price = rs.getDouble("price");
				Drug drug = new Drug(id, name, stock, expireDate,price);
				stList.add(drug);
			}

		} catch (Exception e) {

		} finally {

			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return stList;
	}

	@Override
	public Drug getDrug(String drugName) {
		
		Drug drug = null;
		int id;
		String name = null;
		int stock;
		String expireDate=null;
		double price;
		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String sql = "SELECT * FROM drug WHERE drug.name='" + drugName + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				id = rs.getInt("id");
				name = rs.getString("name");
				stock = rs.getInt("stock");
				expireDate = rs.getString("expire_date");
				price = rs.getDouble("price");
				drug = new Drug(id, name, stock, expireDate,price);
				
			}

		} catch (Exception e) {

		} finally {

			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return drug;
	}

	@Override
	public void updatePrice(int id, double price) {
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			String sql = "UPDATE drug " + "SET price = '" + price + "' WHERE id = '" + id + "'";
					
			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		} catch (Exception e) {

		}
	}

	@Override
	public int getCount() {
		
		return 0;
	}

	@Override
	public void updateStock(int id, int count) {
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			
			String selectSQL = "SELECT stock FROM drug WHERE id='" + id + "'";
			ResultSet rs = stmt.executeQuery(selectSQL);
			int i = 0;
			while (rs.next()) {

				 i = rs.getInt("stock");
				
			}
			
			int stock = i - count;
			
			String sql = "UPDATE drug " + "SET stock = '" + stock + "' WHERE id = '" + id + "'";
					
			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		} catch (Exception e) {

		}
	}

}
