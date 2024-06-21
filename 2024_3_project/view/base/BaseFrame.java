package base;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import base.Comp.Idsgin;

public abstract class BaseFrame extends JFrame implements Idsgin {

	public BaseFrame preFrame;
	public BasePanel jpC;
	public BasePanel jpR;
	public BasePanel jpL;
	public BasePanel jpT;
	public BasePanel jpB;
	public BasePanel jpM;
	private int ImgIndex = 0;
	private Image img;
	private int Imgindex = 0;

	public void setFrame(String title,int x, int y,BaseFrame preFrame) {
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
		
		Timer timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changeIcon();
			}
		});
		
		timer.start();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.out.println(e.getWindow().getWidth() + "," + e.getWindow().getHeight());
			}
		});
		
		setComp();
		setDsign();
		setEvent();
		
		this.preFrame = preFrame;
		
		super.setTitle(title);
		super.setSize(x, y);
		super.setLocationRelativeTo(null);
		super.setVisible(true);
	}
	
	private void changeIcon() {
		// TODO Auto-generated method stub
		String[] Imgpath = {"./datafiles/icon/icon1.png","./datafiles/icon/icon2.png"};
		Imgindex = (Imgindex + 1) % Imgpath.length;
		ImageIcon icon = new ImageIcon(Imgpath[Imgindex]);
		super.setIconImage(icon.getImage());
	}
	
	public void Close() {
		this.dispose();
		if(preFrame != null) {
			preFrame.setVisible(true);
			preFrame.setState(JFrame.NORMAL);
		}
	}

}
