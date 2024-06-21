package base.Comp;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class BaseLabel extends JLabel {

	public BaseLabel(String title) {
		// TODO Auto-generated constructor stub
		super(title);
	}

	public BaseLabel setFont(String title, int size) {
		super.setFont(new Font(title,Font.BOLD,size));
		return this;
	}
	
	public BaseLabel setCenter() {
		super.setHorizontalAlignment(JLabel.CENTER);
		super.setVerticalAlignment(JLabel.CENTER);
		return this;
	}
	public BaseLabel setBlue() {
		super.setForeground(Color.blue);
		return this;
	}
	
	public BaseLabel setRed() {
		super.setForeground(Color.red);
		return this;
	}
}
