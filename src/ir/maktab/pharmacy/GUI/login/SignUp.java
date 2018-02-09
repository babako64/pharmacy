package ir.maktab.pharmacy.GUI.login;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ir.maktab.pharmacy.GUI.mainFrame.MainFrameView;
import ir.maktab.pharmacy.userModel.User;
import ir.maktab.pharmacy.usersDAO.UserDAOImp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

public class SignUp extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public SignUp() {
		getContentPane().setLayout(null);

		JLabel lblFirstName = new JLabel("first name");
		lblFirstName.setBounds(49, 28, 108, 14);
		getContentPane().add(lblFirstName);

		textField = new JTextField();
		textField.setBounds(191, 25, 100, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblLastName = new JLabel("last name");
		lblLastName.setBounds(49, 59, 89, 14);
		getContentPane().add(lblLastName);

		textField_1 = new JTextField();
		textField_1.setBounds(191, 56, 100, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblPosition = new JLabel("position");
		lblPosition.setBounds(49, 90, 89, 14);
		getContentPane().add(lblPosition);

		textField_2 = new JTextField();
		textField_2.setBounds(191, 87, 100, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JLabel lblUserName = new JLabel("user name");
		lblUserName.setBounds(49, 121, 89, 14);
		getContentPane().add(lblUserName);

		textField_3 = new JTextField();
		textField_3.setBounds(191, 118, 100, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);

		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(49, 155, 89, 14);
		getContentPane().add(lblPassword);

		textField_4 = new JTextField();
		textField_4.setBounds(191, 149, 100, 20);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);

		JButton btnSignUp = new JButton("sign up");
		btnSignUp.setBounds(152, 219, 89, 23);
		btnSignUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (MainFrameView.userCheck) {
					User user = new User(0, textField.getText(), textField_1.getText(), textField_2.getText(),
							textField_3.getText(), textField_4.getText());
					try {
						new UserDAOImp().addUser(user);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Access denied \n", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		getContentPane().add(btnSignUp);

		setSize(400, 400);
		getContentPane().setLayout(null);
		setVisible(true);
	}

}
