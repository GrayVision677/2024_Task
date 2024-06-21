package jdbc;

import javax.swing.JOptionPane;

public class Msg {

	public Msg() {
		// TODO Auto-generated constructor stub
	}

	public static void Info(String title) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, title, "정보", 1);
	}
	
	
	public static void error(String title) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, title, "경고", 0);
	}

}
