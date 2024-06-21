package window;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import ResModel.Resmodel;
import base.Comp.BaseFrame;
import base.Comp.BaseLabel;
import base.Comp.ImageLabel;

public class Mypage extends BaseFrame {

	private ImageLabel img;
	private JButton jbInfo;

	public Mypage(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.setFrame("마이페이지", 700, 500, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDsign() {
		// TODO Auto-generated method stub
		jpM.setEmpty(10, 10, 10, 10);
		jpT.addChild();
		jpT.jpC.setFlowCenter().add(new BaseLabel("마이페이지").setFont("맑은고딕", 20));
		jpT.jpR.add(img = new ImageLabel(null, "메인", 60, 30));
		
		jpC.addChild();
		jpC.jpL.setFlowLeft().add(new BaseLabel(Resmodel.login.get(3) + "님").setFont("맑은고딕", 20));
		jpC.jpR.setFlowRight().add(jbInfo = new JButton("회원 정보수정"));
		
	}

	@Override
	public void setEvent() {
		// TODO Auto-generated method stub
		jbInfo.addActionListener(e -> {
			new GuessInfo(preFrame);
		});
		
		img.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				Close();
				new MainFrame();
			}
		});
		
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				Close();
				new MainFrame();
			}
		});
	}

}
