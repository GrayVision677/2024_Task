package base;

import java.awt.Color;

import javax.swing.JButton;

public class BaseButton extends JButton {

	public BaseButton(String text) {
		// TODO Auto-generated constructor stub
		super(text);
	}
	
	public BaseButton setcolor() {
		Color color = new Color(178,221,239);
		super.setBackground(color);
		super.setForeground(color.white);
		return this;
	}

}
