package ir.maktab.pharmacy.usersDAO;

import ir.maktab.pharmacy.userModel.User;

public interface UserDAO {

	public void addUser(User user);
	public User getUserById(int id);
	public boolean checkPassword(String userName, String password);
}
