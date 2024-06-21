package window;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;

import ResModel.Resmodel;
import base.Comp.BaseFrame;
import base.Comp.BaseLabel;
import base.Comp.ImageLabel;
import jdbc.DbManager;
import jdbc.Msg;

public class MusicInfo extends BaseFrame {

	private ImageLabel img;
	private JButton jbshop;
	private JButton jbclose;
	private DbManager db;

	public MusicInfo(BaseFrame preFrame) {
		// TODO Auto-generated constructor stub
		super.setFrame("음원상세정보", 600, 500, preFrame);
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
		jpT.setEmpty(10, 0, 20, 0).setFlowCenter().add(new BaseLabel("음원상세정보").setFont("맑은고딕", 20));
		jpC.addChild();
		jpC.jpT.addChild();
		jpC.jpT.setEmpty(0, 10, 10, 10);
		jpC.jpT.jpL.setFlowLeft().add(new BaseLabel(Resmodel.data.get(1) + "").setFont(null, 20));
		jpC.jpT.jpR.add(img = new ImageLabel(null, "메인", 80, 40));
		
		jpC.jpC.addChild();
		jpC.jpC.setLines();
		jpC.jpC.jpL.addChild();
		//메인폼에서 받아온 값 저장해서 아이콘 가져오기
		jpC.jpC.jpL.jpT.setEmpty(10, 10, 10, 10).add(new ImageLabel(null, Resmodel.icon, 150, 200));
		
		jpC.jpC.jpB.setFlowCenter().setEmpty(10, 10, 10, 10).add(jbshop = new JButton("담기"));
		jpC.jpC.jpB.add(jbclose = new JButton("듣기"));
		
		//음악 정보 String배열 생성
		String st[] = " ,아티스트,작사,작곡,앨범,재생시간,음질".split(",");
		for (int i = 1; i < st.length; i++) {
			jpC.jpC.jpC.setGridLayout(6, 2, 5, 5).add(new BaseLabel(st[i]));
			//만약 i가 6
			//음악정보 폼에서 6번정보는 없기때문에 7번으로 불어와야 함
			if(i == 6) {
				i++;
			}
			jpC.jpC.jpC.add(new BaseLabel(Resmodel.data.get(i+1) + ""));
		}
		
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
		
		jbshop.addActionListener(e -> {
			String u_no = Resmodel.login.get(0) + "";
			String m_no = Resmodel.data.get(0) + "";
			
			//유저와 playlist테이블을 서로 조인해서 입력정보 확인
			Vector<Vector<Object>> data = db.getDb("SELECT * FROM oldpopsong.playlist as p join oldpopsong.user as u\r\n"
					+ "on p.u_no = u.u_no where u.u_no = ? and p.m_no = ?;",u_no,m_no);
		
			for (int i = 0; i < data.size(); i++) {
				if(data.size() != 0) {
					Msg.error("playlist에 담겨진 음원입니다.");
					return;
				}
			}
			
			System.out.println(u_no);
			System.out.println(m_no);
			db.dbSet("INSERT INTO `oldpopsong`.`playlist` (`u_no`, `m_no`) VALUES (?, ?);\r\n"
					,u_no,m_no);
			
			Msg.info("담겨졌습니다.");
				
		});
		
		jbclose.addActionListener(e -> {
			String u_no = Resmodel.login.get(0) + "";
			String m_no = Resmodel.data.get(0) + "";
			
			Vector<Vector<Object>> data = db.getDb("SELECT * FROM oldpopsong.playlist as p join oldpopsong.user as u\r\n"
					+ "on p.u_no = u.u_no where u.u_no = ? and p.m_no = ?;",u_no,m_no);
			
			if(data.size() != 0) {
				jbclose.setText("멈춤");
				return;
			}
			
			if(jbclose.equals("멈춤")) {
				Msg.error("음원 재생이 중지되었습니다.");
				return;
			}
			
			Msg.error("음원담기를 먼저 실행해주세요.");
		});
		
	}

}
