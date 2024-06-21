package window;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JTextField;

import ResModel.Resmodel;
import base.Comp.BaseFrame;
import base.Comp.BaseLabel;
import base.Comp.ImageLabel;
import jdbc.DbManager;
import jdbc.Msg;

public class GuessInfo extends BaseFrame {

	private ImageLabel img;
	private ImageLabel imgCal;
	private ImageLabel imgMik;
	private JButton jbSig;
	String[] str = "이름,생년월일,ID,PW".split(",");
	JTextField[] jt = new JTextField[4];
	private DbManager db;

	public GuessInfo(BaseFrame preFrame) {
		// TODO Auto-generated constructor stub
		super.setFrame("회원정보",439,378, preFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		db = new DbManager();
	}

	@Override
	public void setDsign() {
		// TODO Auto-generated method stub
		//디자인 회원정보
		jpM.setEmpty(10, 10, 10, 10);
		jpT.addChild();
		jpT.jpC.setFlowCenter().add(new BaseLabel("회원정보").setFont("맑은고딕", 30));
		jpT.jpR.setEmpty(5, 5, 5, 5).add(img = new ImageLabel(null, "메인", 50, 50));
		
		jpC.addChild();
		
		for (int i = 0; i < str.length; i++) {
			jpC.jpL.setGridLayout(4, 1, 20, 20).setEmpty(0, 50, 0, 10).add(new BaseLabel(str[i]));	
			jpC.jpC.setGridLayout(4, 1, 20, 20).setEmpty(0, 0, 0, 5).add(jt[i] = new JTextField(15));
		}
		
		jpC.jpR.setGridLayout(4, 1, 20, 20).setEmpty(0, 0, 0, 40).add(new BaseLabel(null));
		jpC.jpR.add(imgCal = new ImageLabel(null, "달력", 30, 30));
		jpC.jpR.setGridLayout(4, 1, 20, 20).setEmpty(0, 0, 0, 40).add(new BaseLabel(null));
		jpC.jpR.add(imgMik = new ImageLabel(null, "마이크", 30, 30));
		
		jpB.setFlowCenter().setEmpty(30, 0, 0, 0).add(jbSig = new JButton("정보수정"));
		jbSig.setPreferredSize(new Dimension(200,30));
		
		jt[3].setEnabled(false);
		jt[1].setEnabled(false);
		
		//만약 로그인값이 널값이 아니라면?(로그인 1, 로그아웃 0)
		if(Resmodel.login != null) {
			jt[0].setText(Resmodel.login.get(3) + "");
			jt[1].setText(Resmodel.login.get(4) + "");
			jt[2].setText(Resmodel.login.get(1) + "");
			jt[3].setText(Resmodel.login.get(2) + "");
		}
		
		jt[2].setEnabled(false);
		refrush();
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
		
		imgMik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				Close();
				new FavMusic(preFrame);
			}
		});
		
		imgCal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				Close();
				new Calender();
			}
		});
		
		jbSig.addActionListener(e -> {
			String name = jt[0].getText().trim();
			String birth = jt[1].getText().trim();
			String id = jt[2].getText().trim();
			String pw = jt[3].getText().trim();
			String u_no = Resmodel.login.get(0) + "";
			
			//빈칸제어
			if(name.isBlank() || birth.isBlank() || id.isBlank() || pw.isBlank()) {
				Msg.error("빈칸이 있습니다.");
				return;
			}
			
			//이부분 외우기
			//한글제어
			String Patternname = "^([ㄱ-힣-ㅣ-가-힣]+)$";
			if(!Pattern.matches(Patternname, name)) {
				Msg.error("이름은 한글만 가능합니다");
				return;
			}
			
			//DB저장 후 메인페이지 이동
			db.dbSet("UPDATE `oldpopsong`.`user` SET `id` = ?, `pw` = ?, `u_name` = ?, `birth` = ? WHERE (`u_no` = ?);\r\n"
					,id,pw,name,birth,u_no);
			Msg.info("정보수정 성공");
			Close();
			new MainFrame();
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
