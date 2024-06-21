package jdbc;

import javax.swing.JOptionPane;

public class Msg extends JOptionPane {

	public Msg() {
		// TODO Auto-generated constructor stub
	}
	
	public void error(String title) {
		super.showMessageDialog(null, title,"경고",0);
	}

	public void info(String title) {
		// TODO Auto-generated method stub
		super.showMessageDialog(null, title, "정보",1);
	}

}
