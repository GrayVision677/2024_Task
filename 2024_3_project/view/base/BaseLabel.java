package base;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class BaseLabel extends JLabel {

	public BaseLabel(String text) {
		// TODO Auto-generated constructor stub
		super(text);
	}
	
	public BaseLabel setFont(String font, int size) {
		super.setFont(new Font(font, Font.BOLD,size));
		return this;
	}
	
	public BaseLabel setlineCenter() {
		super.setBorder(new EmptyBorder(10, 10, 0, 0));
		return this;
	}
	
	public BaseLabel setWhite() {
		super.setForeground(Color.white);
		return this;
	}
	
	public BaseLabel setCenter() {
		super.setHorizontalAlignment(JLabel.CENTER);
		super.setVerticalAlignment(JLabel.CENTER);
		return this;
	}
	
	public BaseLabel setline() {
		super.setBorder(BorderFactory.createLineBorder(Color.black));
		return this;
	}

}
