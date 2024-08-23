package base.Comp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
	
	public BaseLabel setimgjpg(String way,String name,int w, int h) {
		ImageIcon icon = new ImageIcon("./datafiles/" + way + "/" + name + ".jpg");
		Image img = icon.getImage();
		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		super.setIcon(new ImageIcon(img));
		return this;
	}
	
	public BaseLabel setimgpng(String way,String name,int w, int h) {
		ImageIcon icon = new ImageIcon("./datafiles/" + way + "/" + name + ".png");
		Image img = icon.getImage();
		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		super.setIcon(new ImageIcon(img));
		return this;
	}
	
	public BaseLabel setimggif(String way,int w, int h) {
		ImageIcon icon = new ImageIcon(way);
		Image img = icon.getImage();
		img = img.getScaledInstance(w, h, Image.SCALE_DEFAULT);
		super.setIcon(new ImageIcon(img));
		return this;
	}
	
	public BaseLabel setimggif(String way,String title,int w, int h) {
		ImageIcon icon = new ImageIcon("./datafiles/" + way + "/" + title + ".gif");
		Image img = icon.getImage();
		img = img.getScaledInstance(w, h, Image.SCALE_DEFAULT);
		super.setIcon(new ImageIcon(img));
		return this;
	}
	
	public BaseLabel setimg(String name,int w, int h) {
		ImageIcon icon = new ImageIcon("./datafiles/" + name + ".png");
		Image img = icon.getImage();
		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		super.setIcon(new ImageIcon(img));
		return this;
	}
	
	public BaseLabel setline() {
		super.setBorder(BorderFactory.createLineBorder(Color.black));
		return this;
	}
	
	public BaseLabel setCenter(){
		super.setHorizontalAlignment(JLabel.CENTER);
		super.setVerticalAlignment(JLabel.CENTER);
		return this;
	}
	

}
