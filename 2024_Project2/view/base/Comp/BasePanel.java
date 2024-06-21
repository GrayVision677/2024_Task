package base.Comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BasePanel extends JPanel {

	public BasePanel jpCenter;
	public BasePanel jpTop;
	public BasePanel jpBottom;
	public BasePanel jpLeft;
	public BasePanel jpRight;

	public BasePanel() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
	}
	
	public BasePanel setFlowPanel() {
		setLayout(new FlowLayout());
		return this;
	}
	
	public BasePanel setNullLay() {
		setLayout(null);
		return this;
	}
	
	public BasePanel setFlowLeft() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		return this;
	}
	
	public BasePanel setFlowRight() {
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		return this;
	}
	
	public BasePanel setFlowCenter() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		return this;
	}
	
	public BasePanel setGridLayout(int x, int y,int h, int v) {
		setLayout(new GridLayout(x,y,h,v));
		return this;
	}
	
	public BasePanel setEmpty(int top, int left, int bottom, int right) {
		super.setBorder(new EmptyBorder(top, top, bottom, right));
		return this;
	}
	
	public BasePanel addChild() {
		  jpCenter = new BasePanel();
		  jpTop = new BasePanel();
		  jpBottom = new BasePanel();
		  jpLeft = new BasePanel();
		  jpRight = new BasePanel();

		
		super.add(jpCenter, BorderLayout.CENTER);
		super.add(jpTop);
		super.add(jpBottom, BorderLayout.WEST);
		super.add(jpLeft, BorderLayout.NORTH);
		super.add(jpRight, BorderLayout.SOUTH);
		return this;
	}
	
	public BasePanel setLines() {
		super.setBorder(BorderFactory.createLineBorder(Color.black));
		return this;
	}

}
