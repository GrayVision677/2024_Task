package window;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;

import ResModel.Resmodel;
import base.Comp.BaseFrame;
import base.Comp.BaseLabel;
import base.Comp.ImageLabel;

public class Calender extends BaseFrame {

	private ImageLabel img;
	private JComboBox jcYear;
	private int a;
	private JButton jbL;
	private JButton jbR;
	private BaseLabel jl;
	BaseLabel[] bl = new BaseLabel[7];

	public Calender() {
		// TODO Auto-generated constructor stub
		super.setFrame("날짜 선택", 500, 600, preFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDsign() {
		// TODO Auto-generated method stub
		jpT.addChild();
		jpT.jpC.setFlowCenter().add(new BaseLabel("날짜 선택").setFont("맑은 고딕", 25));
		jpT.jpR.setEmpty(5, 5, 5, 5).add(img = new ImageLabel(null, "메인", 60, 40));
		jpT.jpB.setFlowRight().add(jcYear = new JComboBox<>());
		for (int i = 1950; i < 2025; i++) {
			jcYear.addItem(i);
		}
		jpT.jpB.setEmpty(0, 5, 0, 10).add(new BaseLabel("년"));
		
		a = 1;
		jpT.jpB.setEmpty(0, 20, 0, 0).add(jl = new BaseLabel(a + "월"));
		jpT.jpB.add(jbL = new JButton("<"));
		jpT.jpB.add(jbR = new JButton(">"));
		
		
		jpC.addChild();
		String[] Moth = "일,월,화,수,목,금,토".split(",");
		BaseLabel[] bl = new BaseLabel[7];
		
		for (int i = 0; i < Moth.length; i++) {
			jpC.jpT.setGridLayout(1, 6, 10, 10).setEmpty(30, 10, 0, 10).add(bl[i] = new BaseLabel(Moth[i]).setCenter());
		}
		
		bl[0].setRed();
		bl[6].setBlue();
		
	}

	@Override
	public void setEvent() {
		// TODO Auto-generated method stub
		img.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				Close();
				new MainFrame();
			}
		});
		jbL.addActionListener(e -> {
			jl.setText(a + "월");
			if(a < 1) {
				a = 12;
				jl.setText(a + "월");
			}else if(a > 12) {
				a = 1;
				jl.setText(a + "월");
			}
			jl.setText(a + "월");
			a--;
		});
		jbR.addActionListener(e -> {
			jl.setText(a + "월");
			if(a < 1) {
				a = 12;
				jl.setText(a + "월");
			}else if(a > 12) {
				a = 1;
				jl.setText(a + "월");
			}
			jl.setText(a + "월");
			a++;
		});
		
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				if(Resmodel.move == 1) {
					Close();
					new SginFrom(preFrame);
				}else if(Resmodel.move == 2) {
					Close();
					new GuessInfo(preFrame);
				}
			}
		});
		
	}
	

}
