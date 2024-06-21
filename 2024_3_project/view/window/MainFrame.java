package window;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import base.BaseFrame;
import base.BaseLabel;
import base.BaseNullPanel;
import base.BasePanel;
import base.ImageLabel;
import jdbc.DbManager;

public class MainFrame extends BaseFrame {


	private ImageLabel imgSel;
	private ImageLabel imgHome;
	private ImageLabel imgCal;
	private ImageLabel imgAll;
	private BaseLabel bl;
	private DbManager db;
	private BasePanel jp;
	private JScrollPane jsp;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		super.setFrame("메인", 740,500, null);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		db = new DbManager();
		bl = new BaseLabel("지도 보기").setCenter().setline();
		imgAll = new ImageLabel(null,"map","전체",".jpg", 300, 400).setTop().setLine();
	}

	@Override
	public void setDsign() {
		// TODO Auto-generated method stub
		jpT.addChild();
		jpT.setBack().add(new BaseLabel(" ").setFont(null, 45));
		jpT.jpL.setBack().setFlowLayout().add(new BaseLabel("경매알림이").setFont("맑은 고딕", 20).setlineCenter().setWhite());
		jpT.jpR.setBack().setFlowRight().add(imgSel = new ImageLabel("검색","image", "검색",".png", 30, 30).setCenter().setBottom());
		jpT.jpR.add(imgHome = new ImageLabel("마이홈","image","마이홈",".png", 30, 30).setCenter().setBottom());
		jpT.jpR.add(imgCal = new ImageLabel("경매일정","image", "경매일정",".png", 30, 30).setCenter().setBottom());
		
		jpC.addChild();
		
		bl.setOpaque(true);
		bl.setBackground(Color.white);
		
		BaseNullPanel bNullP = new BaseNullPanel(300, 400, bl, 110, 10, 100, 30, imgAll, 0, 0, 300, 400);
		jpC.jpL.add(bNullP);
		
		jpC.jpR.addChild();
		jpC.jpR.jpT.setline("인기경매", 18);
		
		Vector<Vector<String>> data = db.getDb("SELECT * FROM auction.building as b join auction.interest as i on b.b_no = i.b_no order by(u_no) desc limit 5;");
		jp = new BasePanel();
		jp.setGridLayout(1, 0, 10, 10);
		for (int i = 0; i < data.size(); i++) {
			BaseLabel blnum = new BaseLabel(data.get(i).get(2)).setFont("맑은 고딕", 12);
			String str = "<html>" + blnum + "<br>";
			jp.add(new ImageLabel(data.get(i).get(2), "building", data.get(i).get(1) + "1", ".jpg", 100,50).setBottom());
		}
		jsp = new JScrollPane(jp);
		jpC.jpR.jpT.add(jsp);
	}

	@Override
	public void setEvent() {
		// TODO Auto-generated method stub
	}

}
