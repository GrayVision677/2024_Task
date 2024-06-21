package base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class BasePanel extends JPanel {

	public BasePanel jpC;
	public BasePanel jpR;
	public BasePanel jpL;
	public BasePanel jpT;
	public BasePanel jpB;
	private TitledBorder tb;

	public BasePanel() {
		// TODO Auto-generated constructor stub
		super.setLayout(new BorderLayout());
		super.setBackground(Color.white);
	}
	
	public BasePanel setFlowLayout() {
		// TODO Auto-generated constructor stub
		super.setLayout(new FlowLayout());
		return this;
	}
	public BasePanel setFlowRight() {
		// TODO Auto-generated constructor stub
		super.setLayout(new FlowLayout(FlowLayout.RIGHT));
		return this;
	}
	public BasePanel setFlowCenter() {
		// TODO Auto-generated constructor stub
		super.setLayout(new FlowLayout(FlowLayout.CENTER));
		return this;
	}
	
	public BasePanel setGridLayout(int r, int c, int h, int v) {
		// TODO Auto-generated constructor stub
		super.setLayout(new GridLayout(r,c,h,v));
		return this;
	}
	
	public BasePanel setEmpty(int top, int left, int bottom, int right) {
		// TODO Auto-generated constructor stub
		super.setBorder(new EmptyBorder(top, left, bottom, right));
		return this;
	}
	
	public BasePanel setBack() {
		Color color = new Color(80,188,230);
		super.setBackground(color);
		return this;
	}
	
	public BasePanel setline(String title, int size) {
		Border border = BorderFactory.createLineBorder(Color.black);
		tb = BorderFactory.createTitledBorder(border,title);
		tb.setTitleFont(new Font("HY헤드라인M",Font.PLAIN,size));
		super.setBorder(tb);
		return this;
	}
	public BasePanel addChild() {
		
		jpC = new BasePanel();
		jpR = new BasePanel();
		jpL = new BasePanel();
		jpT = new BasePanel();
		jpB = new BasePanel();
		
		super.add(jpC, BorderLayout.CENTER);
		super.add(jpR, BorderLayout.EAST);
		super.add(jpL, BorderLayout.WEST);
		super.add(jpT, BorderLayout.NORTH);
		super.add(jpB, BorderLayout.SOUTH);
		
		return this;
	}

}
