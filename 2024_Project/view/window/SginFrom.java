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

public class SginFrom extends BaseFrame {

	private ImageLabel img;
	private ImageLabel imgCal;
	private ImageLabel imgMik;
	private JButton jbSig;
	String[] str = "이름,생년월일,ID,PW".split(",");
	JTextField[] jt = new JTextField[4];
private DbManager db;

	public SginFrom(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.setFrame("회원가입",439,378, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		db = new DbManager();
	}

	@Override
	public void setDsign() {
		// TODO Auto-generated method stub
		jpM.setEmpty(10, 10, 10, 10);
		jpC.addChild();
		jpC.jpC.removeAll();
		jpT.addChild();
		jpT.jpC.setFlowCenter().add(new BaseLabel("회원정보").setFont("맑은고딕", 30));
		jpT.jpR.setEmpty(5, 5, 5, 5).add(img = new ImageLabel(null, "메인", 50, 50));
		
		
		for (int i = 0; i < str.length; i++) {
			jpC.jpL.setGridLayout(4, 1, 20, 20).setEmpty(0, 50, 0, 10).add(new BaseLabel(str[i]));	
			jpC.jpC.setGridLayout(4, 1, 20, 20).setEmpty(0, 0, 0, 5).add(jt[i] = new JTextField(15));
		}
		
		jpC.jpR.setGridLayout(4, 1, 20, 20).setEmpty(0, 0, 0, 40).add(new BaseLabel(null));
		jpC.jpR.add(imgCal = new ImageLabel(null, "달력", 30, 30));
		jpC.jpR.setGridLayout(4, 1, 20, 20).setEmpty(0, 0, 0, 40).add(new BaseLabel(null));
		jpC.jpR.add(imgMik = new ImageLabel(null, "마이크", 30, 30));
		
		jpB.setFlowCenter().setEmpty(30, 0, 0, 0).add(jbSig = new JButton("회원가입"));
		jbSig.setPreferredSize(new Dimension(200,30));
		
		jt[3].setEnabled(false);
		jt[3].setText("");
		
		if(Resmodel.numData != null) {
			jt[3].setText(Resmodel.numData);
		}
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
			
			if(name.isBlank() || birth.isBlank() || id.isBlank() || pw.isBlank()) {
				Msg.error("빈칸이 있습니다.");
				return;
			}
			
			//이부분 외우기
			String Patternname = "^([ㄱ-힣-ㅣ-가-힣].+)$";
			if(!Pattern.matches(Patternname, name)) {
				Msg.error("이름은 한글만 가능합니다");
				return;
			}
			
			Vector<Vector<Object>> datas = db.getDb("SELECT * FROM oldpopsong.user where id = ?;",id);
			for (int i = 0; i < datas.size(); i++) {
				if(datas.size() != 0) {
					Msg.error("이미 존재하는 ID입니다.");
					return;
				}
			}
			
			db.dbSet("INSERT INTO `oldpopsong`.`user` (`id`, `pw`, `u_name`, `birth`) VALUES (?, ?, ?, ?);\r\n"
					,id,pw,name,birth);
			Msg.info("회원가입 성공");
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
