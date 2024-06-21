package jdbc;

import javax.swing.JOptionPane;

public class Msg extends JOptionPane {

	public Msg() {
		// TODO Auto-generated constructor stub
	}
	
	public static void error(String title) {
		JOptionPane.showMessageDialog(null, title,"경고",0);
	}

	public static void info(String title) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, title, "정보",1);
	}

}
