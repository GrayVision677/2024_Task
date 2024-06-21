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

import base.IDsign;
import window.MainFrame;

public abstract class BaseFrame extends JFrame implements IDsign {
	
	public BasePanel jpC;
	public BasePanel jpR;
	public BasePanel jpL;
	public BasePanel jpT;
	public BasePanel jpB;
	public BasePanel jpM;
	public MainFrame preFrame;
	
	public void setFrame(String title, int x,int y, BaseFrame preFrame) {
		// TODO Auto-generated constructor stub
		
		
		jpC = new BasePanel();
		jpR = new BasePanel();
		jpL = new BasePanel();
		jpT = new BasePanel();
		jpB = new BasePanel();
		jpM = new BasePanel();
		
		jpM.add(jpC, BorderLayout.CENTER);
		jpM.add(jpR, BorderLayout.EAST);
		jpM.add(jpL, BorderLayout.WEST);
		jpM.add(jpT, BorderLayout.NORTH);
		jpM.add(jpB, BorderLayout.SOUTH);
		super.add(jpM, BorderLayout.CENTER);
		
		setComp();
		setDsign();
		setEvent();
		
		this.preFrame = (MainFrame) preFrame;
		
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
			//preFrame.setVisible(true);
			//preFrame.setState(JFrame.NORMAL);
		}
	}
	
	public BaseFrame refrush() {
		super.repaint();
		super.validate();
		return this;
	}
	
	

}
