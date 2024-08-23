package base.Comp;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import base.IDS;

public abstract class BaseFrame extends JFrame implements IDS{
	
	public BasePanel jpC;
	public BasePanel jpR;
	public BasePanel jpL;
	public BasePanel jpT;
	public BasePanel jpB;
	public BasePanel jpM;
	
	public void setFrame(String title, int w, int h) {
		// TODO Auto-generated constructor stub
		jpC = new BasePanel();
		jpR = new BasePanel();
		jpL = new BasePanel();
		jpT = new BasePanel();
		jpB = new BasePanel();
		jpM = new BasePanel();
		
		jpM.add(jpC,BorderLayout.CENTER);
		jpM.add(jpR,BorderLayout.EAST);
		jpM.add(jpL,BorderLayout.WEST);
		jpM.add(jpT,BorderLayout.NORTH);
		jpM.add(jpB,BorderLayout.SOUTH);
		super.add(jpM,BorderLayout.CENTER);
		
		Comp();
		DS();
		Event();
		
		ImageIcon icon = new ImageIcon("./datafiles/로고.png");
		super.setIconImage(icon.getImage());
		
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.out.println(e.getWindow().getWidth() + "," + e.getWindow().getHeight());
			}
		});
		
		super.setTitle(title);
		super.setSize(w, h);
		super.setLocationRelativeTo(null);
		super.setVisible(true);
	}

}
