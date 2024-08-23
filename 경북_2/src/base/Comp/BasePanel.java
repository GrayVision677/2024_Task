package base.Comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BasePanel extends JPanel{

	public BasePanel jpC;
	public BasePanel jpR;
	public BasePanel jpL;
	public BasePanel jpT;
	public BasePanel jpB;

	public BasePanel() {
		// TODO Auto-generated constructor stub
		super.setBackground(Color.white);
		super.setLayout(new BorderLayout());
	}
	
	public BasePanel setFlowLayout(int f) {
		super.setLayout(new FlowLayout(f));
		return this;
	}
	
	public BasePanel setGridLayout(int c, int r, int w, int h) {
		super.setLayout(new GridLayout(c, r, w, h));
		return this;
	}
	
	public BasePanel setEmpty(int t, int l, int b, int r) {
		super.setBorder(new EmptyBorder(t, l, b, r));
		return this;
	}
	
	public BasePanel addChild() {
		jpC = new BasePanel();
		jpR = new BasePanel();
		jpL = new BasePanel();
		jpT = new BasePanel();
		jpB = new BasePanel();
		
		super.add(jpC,BorderLayout.CENTER);
		super.add(jpR,BorderLayout.EAST);
		super.add(jpL,BorderLayout.WEST);
		super.add(jpT,BorderLayout.NORTH);
		super.add(jpB,BorderLayout.SOUTH);
		return this;
	}

}
