package window;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextField;

import ResModel.Resmodel;
import base.Comp.BaseFrame;
import base.Comp.BaseLabel;
import base.Comp.ImageLabel;
import jdbc.DbManager;
import jdbc.Msg;

public class LoginFrame extends BaseFrame {

	private ImageLabel img;
	private JTextField jtid;
	private JTextField jtpw;
	private JButton jblog;
private DbManager db;

	public LoginFrame(BaseFrame preFrame) {
		// TODO Auto-generated constructor stub
		super.setFrame("로그인", 431,292, preFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		db = new DbManager();
	}

	@Override
	public void setDsign() {
		// TODO Auto-generated method stub
		
		//디자인
		jpM.setEmpty(10, 10, 10, 10);
		jpC.addChild();
		jpC.jpC.removeAll();
		
		jpT.addChild();
		jpT.jpC.setFlowCenter().add(new BaseLabel("로그인").setFont("맑은고딕", 25));
		jpT.jpR.setEmpty(5, 5, 5, 5).add(img = new ImageLabel(null, "메인", 80, 40));
		
		jpC.jpL.setFlowRight().setGridLayout(3, 1, 10, 10).setEmpty(0, 30, 0, 30).add(new BaseLabel("ID ").setFont("맑은고딕", 17));
		jpC.jpL.setGridLayout(3, 1, 10, 10).add(new BaseLabel("PW ").setFont("맑은고딕", 17));
		
		jpC.jpC.setGridLayout(3, 1, 10, 10).setEmpty(0, 30, 10, 30).add(jtid = new JTextField(15));
		jpC.jpC.setGridLayout(3, 1, 10, 10).add(jtpw = new JTextField(15));
		jpC.jpC.add(jblog = new JButton("로그인"));
		
		jtpw.setEnabled(false);
		jtpw.setText("");
		
		//다지 로그인창이 떳을떄?
		//만약 선호 팝송화면을 다녀왔을 경우 == numData값이 있음 
		//numData값이 있을 경우 아이디와 비밀번호를 텍스트 설정
		//이후 데이터 값 널
		if(Resmodel.numData != null) {
			jtpw.setText(Resmodel.numData);
			jtid.setText(Resmodel.id);
			Resmodel.numData = null;
		}
		refrush();
	}

	@Override
	public void setEvent() {
		// TODO Auto-generated method stub
		jblog.addActionListener(e -> {
			String id = jtid.getText().trim();
			String pw = jtpw.getText().trim();
			
			//빈칸제어
			if(id.isBlank() || pw.isBlank()) {
				Msg.error("빈칸이 있습니다.");
				return;
			}
			
			//유저값있는지 SQL문으로 확인
			Vector<Vector<Object>> data = db.getDb("SELECT * FROM oldpopsong.user where id = ? and pw = ?;",id,pw);
			
			try {
				
				//밑에 코드우리는 데이터값이 있으니 비교할 id와 나이만 출력하면 됨
				//select id,ROUND((REPLACE(SUBSTR(now(),1,13),'-','') - REPLACE(birth,'-',''))/10000) from oldpopshong.user;
				String sql = "SELECT u_no, u_name, birth, ROUND((REPLACE(SUBSTR(NOW(), 1, 13), '-', '') - REPLACE(birth, '-', '')) / 10000) AS age FROM "
						+ "oldpopsong.user WHERE id = '"+id+"';";
				
				//sql문 준비
				//우리가 쓰는 DBMANAGER으로는 한계가 있어서 여기에만 이거 씀
				Connection con = DbManager.getConnetion();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				
				//rs.다음행 검색
				rs.next();
				System.out.println(rs.getInt(4));					
				
				//로그인 확인
				for (int i = 0; i < data.size(); i++) {
					//만약 데이터의 값이 0이 아니라면 == 로그인 성공
					if(data.size() != 0) {
						Resmodel.login = data.get(i); // 로그인한 유저의 데이터가져오기
						Resmodel.age = rs.getInt(4); // 나이 가져오기
						Close();
						new MainFrame();
						return;
					}
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch bloc4k
				e1.printStackTrace();
				Msg.error("로그인 정보가 일치하지 않습니다.");
			}
		
			Msg.error("로그인 정보가 일치하지 않습니다.");
		});
		
		jtpw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				//선호 팝송 페이지 이동 코드
				String id = jtid.getText().trim();
				Close();
				//id저장
				Resmodel.id = id;
				System.out.println(Resmodel.id);
				new FavMusic(preFrame);
			}
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
		
		
	}

}
