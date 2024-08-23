package window;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;

import base.Comp.BaseFrame;
import base.Comp.BaseLabel;

public class MainFrom extends BaseFrame{
	
	String[] text = "로그인,비회원 애매하기,비회원 예매 확인 및 변경,노션 보기".split(",");
	JButton jb[] = new JButton[text.length];
	private int ImgIndex;

	public MainFrom() {
		// TODO Auto-generated constructor stub
		super.setFrame("메인", 455,450);
	}

	@Override
	public void Comp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DS() {
		// TODO Auto-generated method stub
		jpM.setEmpty(10, 10, 10, 10);
		jpT.addChild();
		jpT.jpL.add(new BaseLabel(null).setimg("로고", 60, 60));
		jpT.jpC.setFlowLayout(FlowLayout.CENTER).add(new BaseLabel("시외버스 예약 시스템").setFont("HY헤드라인M", 25));
		
		for (int i = 0; i < text.length; i++) {
			jpC.setGridLayout(4, 1, 10, 10).add(jb[i] = new JButton(text[i]));
		}
		
		//ImageIcon gifIcon = new ImageIcon("./datafiles/홍보/1.gif");
		//Image gifImage = gifIcon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		//ImageIcon scaledGifIcon = new ImageIcon(gifImage);
		//JLabel label = new JLabel(scaledGifIcon);
		
		jpB.setEmpty(10, 0, 0, 0).add(new BaseLabel(null).setimggif("홍보","1", 500, 80));
		
		Timer timer = new Timer(1500, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changeImg();
			}
		});
		timer.start();
		
		
	}
	
	private void changeImg() {
		// TODO Auto-generated method stub
		jpB.removeAll();
		String[] path = {"./datafiles/홍보/1.gif","./datafiles/홍보/2.gif","./datafiles/홍보/3.gif"
				,"./datafiles/홍보/4.gif"};
		ImgIndex = (ImgIndex + 1) % path.length;
		jpB.setEmpty(10, 0, 0, 0).add(new BaseLabel(null).setimggif(path[ImgIndex], 500, 80));
		jpB.repaint();
		jpB.validate();
	}

	@Override
	public void Event() {
		// TODO Auto-generated method stub
		jb[0].addActionListener(e -> {
			dispose();
			new LoginFrom();
		});
		
		jb[1].addActionListener(e -> {
			dispose();
			new NullGuest();
		});
		
	}

}
