package base.Comp;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import base.IDesign;

public abstract class BaseFrame extends JFrame implements IDesign {
	
	public BasePanel jpCenter;
	public BasePanel jpTop;
	public BasePanel jpBottom;
	public BasePanel jpLeft;
	public BasePanel jpRight;
	public BaseFrame preFrame;
	
	public void setFrame(String title, int x,int y, BaseFrame preFrame) {
		// TODO Auto-generated constructor stub
		
		
		jpCenter = new BasePanel();
		jpTop = new BasePanel();
		jpBottom = new BasePanel();
		jpLeft = new BasePanel();
		jpRight = new BasePanel();
		
		super.add(jpCenter, BorderLayout.CENTER);
		super.add(jpTop, BorderLayout.EAST);
		super.add(jpBottom, BorderLayout.WEST);
		super.add(jpLeft, BorderLayout.NORTH);
		super.add(jpRight, BorderLayout.SOUTH);
		
		setComp();
		setDesign();
		setEvent();
		
		this.preFrame = preFrame;
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./datafiles/마이크.png");
		super.setIconImage(img);
		
		super.setSize(x, y);
		super.setTitle(title);
		super.setLocationRelativeTo(null);
		super.setVisible(true);
	
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.out.println(e.getWindow().getWidth() + "," + e.getWindow().getHeight());
			}
		});
		
		super.repaint();
		super.invalidate();
		super.validate(); 
		
	}
	public void Close() {
		this.dispose();
		if(preFrame != null) {
			preFrame.setVisible(true);
			preFrame.setState(JFrame.NORMAL);
		}
	}
	
	public BaseFrame refresh() {
		super.repaint();
		super.validate();
		return this;
	}
	
	

}
