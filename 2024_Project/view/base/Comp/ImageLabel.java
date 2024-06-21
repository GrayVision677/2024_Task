package base.Comp;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel {

	public ImageLabel(String text,ImageIcon imageIcon,int x, int y) {
		// TODO Auto-generated constructor stub
		super(text);
		
		Image img = imageIcon.getImage();
		img = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		super.setIcon(new ImageIcon(img));
	}
	
	public ImageLabel(String text,String title,int x, int y) {
		// TODO Auto-generated constructor stub
		super(text);
		ImageIcon icon = new ImageIcon("./datafiles/" + title + ".png");
		Image img = icon.getImage();
		img = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		super.setIcon(new ImageIcon(img));
	}
	
	public ImageLabel setBottom() {
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.BOTTOM);
		return this;
	}
	
	public ImageLabel setCenter() {
		super.setHorizontalAlignment(JLabel.CENTER);
		super.setVerticalAlignment(JLabel.CENTER);
		return this;
	}
	
	public ImageLabel setRedLines() {
		super.setBorder(BorderFactory.createLineBorder(Color.red));
		return this;
	}
}
