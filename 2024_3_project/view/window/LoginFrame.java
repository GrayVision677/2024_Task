	package window;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextField;

import base.BaseButton;
import base.BaseFrame;
import base.BaseLabel;
import jdbc.DbManager;
import jdbc.Msg;

public class LoginFrame extends BaseFrame {

	private JTextField jtid;
	private JTextField jtpw;
	private JButton jblogin;
	private JButton jbpw;
	private DbManager db;
	private JButton jbsign;

	public LoginFrame() {
		// TODO Auto-generated constructor stub
		super.setFrame("로그인", 403,227, preFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		db = new DbManager();
	}

	@Override
	public void setDsign() {
		// TODO Auto-generated method stub
		jpM.setEmpty(20, 20, 20, 20);
		jpC.addChild();
		jpC.jpL.setGridLayout(2, 1, 10, 10).setEmpty(0, 0, 0, 30).add(new BaseLabel("아이디"));
		jpC.jpL.add(new BaseLabel("회원가입"));
		jpC.jpC.setGridLayout(2, 1, 10, 10).add(jtid = new JTextField(15));
		jpC.jpC.add(jtpw = new JTextField(15));
		jpB.setEmpty(20, 0, 0, 0).setGridLayout(1, 2, 10, 10);
		jpB.add(jblogin = new BaseButton("로그인").setcolor());
		jpB.add(jbsign = new BaseButton("회원가입").setcolor());
	}

	@Override
	public void setEvent() { 
		// TODO Auto-generated method stub
		jbsign.addActionListener(e -> {
			Close();
			new SignFrom(this);
		});
		
		jblogin.addActionListener(e -> {
			String id = jtid.getText().trim();
			String pw = jtpw.getText().trim();
			
			if(id.isBlank() || pw.isBlank()){
				Msg.error("빈칸이 있습니다.");
				return;
			}
			
			if(id.equals("admin") && pw.equals("1234")) {
				Msg.info("관리자님 환영합니다.");
				new Manager(preFrame);
				return;
			}
			
			Vector<Vector<String>> data = db.getDb("SELECT * FROM auction.user where u_id = ? and u_pw = ?;",id,pw);
			if(data.size() != 0) {
				Msg.info("환영합니다.");
				Close();
				new MainFrame();
				return;
			}
			
			Msg.error("존재하지 않는 회원입니다.");
		});
		
	}

}
