package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextField;

import base.Comp.BaseFrame;
import base.Comp.BaseLabel;

public class LoginFrom extends BaseFrame {

	private JTextField jtid;
	private JTextField jtpw;
	BaseLabel bl[] = new BaseLabel[8];
	private BaseLabel ReImg;
	private int num;
	private int random;
	private JTextField jtNum;
	private JButton jblogin;
	private Vector list;
	
	public LoginFrom() {
		// TODO Auto-generated constructor stub
		super.setFrame("로그인", 377,382);
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
		jpT.jpC.setFlowLayout(FlowLayout.CENTER).add(new BaseLabel("로그인").setFont("HY헤드라인M", 25));
		jpT.jpL.add(new BaseLabel(null).setimg("로고", 50, 50));
		
		jpC.addChild();
		jpC.jpL.setGridLayout(2, 1, 30, 10).setEmpty(20, 20, 20, 20).add(new BaseLabel("아이디")); 
		jpC.jpL.setGridLayout(2, 1, 30, 10).add(new BaseLabel("비밀번호"));
		
		jpC.jpC.setGridLayout(2, 1, 30, 10).setEmpty(20, 20, 20, 20).add(jtid = new JTextField(15));
		jpC.jpC.setGridLayout(2, 1, 30, 10).add(jtpw = new JTextField(15));
		
		jpC.jpB.addChild();
		for (int i = 0; i < bl.length; i++) {
			jpC.jpB.jpC.setEmpty(0, 20, 0, 20).setGridLayout(2, 4, 0, 0).add(bl[i] = new BaseLabel("").setline().setCenter());
			bl[i].setPreferredSize(new Dimension(50,50));
		}
		
		jpC.jpB.jpR.add(ReImg = new BaseLabel(null).setimg("새로고침", 40, 40));
		
		list = new Vector<>();
		
		random();
		
		jpB.addChild();
		jpB.jpC.setEmpty(10, 20, 0, 20).add(jtNum = new JTextField(4));
		
		jtNum.setForeground(Color.gray);
		jtNum.setText("자동 입력 방지 문자를 입력해주세요");
		jtNum.setHorizontalAlignment(JTextField.CENTER);
		
		jpB.jpB.setEmpty(0, 20, 0, 20).add(jblogin = new JButton("로그인"));
		
		
	}

	@Override
	public void Event() {
		// TODO Auto-generated method stub
		jtNum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				jtNum.setForeground(Color.black);
				jtNum.setText("");
				jtNum.setHorizontalAlignment(JTextField.LEFT);
			}
		});
		
		super.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				jtNum.setForeground(Color.black);
				jtNum.setText("");
				jtNum.setHorizontalAlignment(JTextField.LEFT);
			}
		});
		ReImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				for (int i = 0; i < bl.length; i++) {
					bl[i].setText("");
				}
				random();
			}
		});
		
	}
	
	public void random() {
		list.removeAll(list);
		for (int i = 1; i <= 4; i++) {
			Random rand = new Random();
			Random rand1 = new Random();
			num = rand.nextInt(9);
			random = rand1.nextInt(4) + i;
			
			System.out.println("칸 수 번호: " + (random));
			bl[random].setText("<html> <p text-align=center>" + (num) + "</p></html>");
			System.out.println("번호: " + (num));
			
			list.add(num + "");
		}
		list.sort(Comparator.naturalOrder());
		System.out.println(list);
	}

}
