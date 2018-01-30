package ir.maktab.pharmacy.GUI.mainFrame;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import ir.maktab.pharmacy.GUI.login.SignUp;
import ir.maktab.pharmacy.GUI.mainFrame.drgInsuranse.DrugInsuranceView;
import ir.maktab.pharmacy.GUI.mainFrame.patient.patientView;

public class MainFrameView extends JFrame{

	JMenu menu,drug,insurance,patient,register;  
    JMenuItem i1,i2,i3,i4,i5,i6,i7;
	public JFrame f;
	panel1 Panel1;
	panel2 Panel2;
	DrugInsuranceView drugInsurePanel;
	patientView patientView;
	public MainFrameView() {
		f = this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
//		Panel1 = new panel1();
//		Panel1.setBounds(197, 87, 500, 351);
//		getContentPane().add(Panel1);
//		Panel1.setVisible(false);
//		
//		Panel2 = new panel2();
//		Panel2.setBounds(197, 87, 500, 351);
//		getContentPane().add(Panel2);
//		Panel2.setVisible(false);
		
         JMenuBar mb=new JMenuBar();  
         menu=new JMenu("Menu");
         drug=new JMenu("دارو");
         insurance=new JMenu("بیمه");
         patient=new JMenu("بیمار");
 
         i1=new JMenuItem("ورود  کاربر");
         i2=new JMenuItem("خروج");
         i3=new JMenuItem("لیست دارو");
         i4=new JMenuItem("لیست بیمه");
         i5=new JMenuItem("سهمیه بیمه");
         i6=new JMenuItem("ثبت بیمار");
         i7=new JMenuItem("ثبت کاربر");
         menu.add(i1); 
         menu.add(i2); 
         menu.add(i7);
         drug.add(i3);
         insurance.add(i4);
         insurance.add(i5);
         patient.add(i6);
         i1.addActionListener(new clickListen());
         i2.addActionListener(new clickListen());
         i3.addActionListener(new clickListen());
         i4.addActionListener(new clickListen());
         i5.addActionListener(new clickListen());
         i6.addActionListener(new clickListen());
         i7.addActionListener(new clickListen());
         mb.add(menu); 
         mb.add(drug);
         mb.add(insurance);
         mb.add(patient);
         setJMenuBar(mb);  
       //  setContentPane(mPanel);
       //  mPanel.setLayout(null);
         setSize(960,600);
         setVisible(true);
	}
	
//	public static void main(String[] args) {
//		
//		new MainFrameView();
//	}
	
	class clickListen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==i3) {
			
				getContentPane().removeAll();
				Panel1 = new panel1();
				Panel1.setBounds(0, 0, 960, 600);
				setContentPane(Panel1);
				
			}
			if(e.getSource()==i4) {
				getContentPane().removeAll();
				Panel2 = new panel2();
				Panel2.setBounds(0, 0, 960, 600);
				setContentPane(Panel2);
				
			}
			
			if(e.getSource()==i6) {
				getContentPane().removeAll();
				try {
					patientView = new patientView();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				patientView.setBounds(0, 0, 960, 600);
				setContentPane(patientView);
				
			}
			
			if(e.getSource()==i7) {
				
				new SignUp();
			}
			
			if(e.getSource()==i5) {
				getContentPane().removeAll();
				try {
					drugInsurePanel = new DrugInsuranceView();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				drugInsurePanel.setBounds(0, 0, 960, 600);
				setContentPane(drugInsurePanel);
				
			}
		}
		
	}

}

 