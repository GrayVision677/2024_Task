package base;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel {

	public ImageLabel(String text,String way,String title,String str,int w, int h) {
		// TODO Auto-generated constructor stub
		super(text);
		ImageIcon icon = new ImageIcon("./datafiles/" + way +"/" + title + str);
		Image img = icon.getImage();
		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		super.setIcon(new ImageIcon(img));
	}
	
	public ImageLabel setCenter() {
		super.setHorizontalAlignment(JLabel.CENTER);
		super.setVerticalAlignment(JLabel.CENTER);
		return this;
	}
	
	public ImageLabel setBottom() {
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.BOTTOM);
		return this;
	}
	
	public ImageLabel setTop() {
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.TOP);
		return this;
	}
	
	public ImageLabel setLine() {
		super.setBorder(BorderFactory.createLineBorder(Color.black));
		return this;
	}
	

}
