package window;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ResModel.ImgModelData;
import ResModel.Resmodel;
import base.Comp.BaseFrame;
import base.Comp.BaseLabel;
import base.Comp.BaseNullPanel;
import base.Comp.BasePanel;
import base.Comp.ImageLabel;
import jdbc.DbManager;
import jdbc.Msg;

public class MusicSel extends BaseFrame {

	private ImageLabel img;
	private JComboBox jcTit;
	private JTextField jtSel;
	private ImageLabel imgSel;
	private JComboBox jcOder;
	private DbManager db;
	private BasePanel jp;
	private ImageLabel imgL;
	private JScrollPane jsp;
	private ImageLabel img19;
	private ImageLabel imgdown;
	private String str;
	private String order;
	private BasePanel jpN;

	
	public MusicSel(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.setFrame("음원검색", 777,700, mainFrame);
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
		jpM.setEmpty(5, 5, 5, 5);
		jpT.addChild();
		jpT.jpC.setFlowCenter().add(new BaseLabel("음원검색").setCenter().setFont("맑은고딕", 25));
		jpT.jpR.setEmpty(0, 0, 0, 20).add(img = new ImageLabel(null,"메인", 100, 60));
		jpT.jpB.setFlowLeft().add(new BaseLabel("검색기준").setFont("맑은고딕", 18));
		jpT.jpB.add(jcTit = new JComboBox<>());
		jcTit.addItem("제목");
		jcTit.addItem("아티스트");
		jpT.jpB.add(jtSel = new JTextField(15));
		jpT.jpB.add(imgSel = new ImageLabel(null, "검색", 35, 35));
		
		jpC.addChild();
		jpC.jpT.setFlowRight().setEmpty(0, 10, 0, 10).add(new BaseLabel("정렬기준"));
		jpC.jpT.add(jcOder = new JComboBox<>());
		jcOder.addItem("오름차순");
		jcOder.addItem("내림차순");
		
		
		//미성년자 이미지 데이터 가져오기
		ImgModelData ageData = db.getImg("SELECT * FROM oldpopsong.music where agelimit = 0;",9);
		
		Vector<Vector<String>> agedatas = ageData.datas;
		Vector<ImageIcon> ageicons = ageData.icons;
		
		jp = new BasePanel();
		jp.setGridLayout(0, 6, 10, 10);
		
		jpN = new BasePanel();
		
		jpN.setVisible(false);
		
		//미성년자 이미지 불러오기
		if(Resmodel.age < 19) {
			for (int i = 0; i < agedatas.size(); i++) {
				jp.setEmpty(5, 5, 5, 5).add(imgL = new ImageLabel(ss(agedatas.get(i).get(1)), ageicons.get(i), 110, 110).setBottom().setCenter());
				
				//ToolTipText 툴팁
				imgL.setToolTipText(null); 
				imgL.setToolTipText("<html>아티스트: " + agedatas.get(i).get(2) + "<br>제목: " + agedatas.get(i).get(1));
				
				int ii = i;
				imgL.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						super.mouseClicked(e);
						Resmodel.data = agedatas.get(ii);
						Resmodel.icon = ageicons.get(ii);
						new MusicInfo(preFrame);
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						super.mouseEntered(e);
						imgdown = new ImageLabel(null, "담기",20, 20);
						BaseNullPanel bnp = new BaseNullPanel(0, 0, imgdown, 70, 70, 20, 20, imgL, 0, 0, 130, 130);
						jpN.add(bnp);
						jp.add(jpN);
						jpN.setVisible(true);
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						super.mouseExited(e);
						jpN.setVisible(false);
					}
				});
				
				jsp = new JScrollPane(jp);
				jpC.jpC.setEmpty(10, 10, 10, 10).add(jsp);
				
			}
			
		}else {
			//성인 이미지 불러오기
			ImgModelData allData = db.getImg("SELECT * FROM oldpopsong.music;",9);
			
			Vector<Vector<String>> datas = allData.datas;
			Vector<ImageIcon> icons = allData.icons;
			
				for (int i = 0; i < 98; i++) {
					jp.setEmpty(5, 5, 5, 5).add(imgL = new ImageLabel(ss(datas.get(i).get(1)), icons.get(i), 110, 110).setBottom().setCenter());
					if(Integer.parseInt(datas.get(i).get(7)) == 1) {
						 img19 = new ImageLabel(null, "19금", 30, 30);
						 BaseNullPanel baseN = new BaseNullPanel(0, 0, img19, 70, 0, 40, 40, imgL, -5, -10, 130, 130);
						 jp.add(baseN);
					}
					
					imgL.setToolTipText(null); 
					imgL.setToolTipText("<html>아티스트: " + datas.get(i).get(2) + "<br>제목: " + datas.get(i).get(1));
					
					int ii = i;
					imgL.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub
							super.mouseClicked(e);
							Resmodel.data = datas.get(ii);
							Resmodel.icon = icons.get(ii);
							new MusicInfo(preFrame);
						}
						
						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
							super.mouseEntered(e);
						}
						
						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub
							super.mouseExited(e);
						}
					});
					jsp = new JScrollPane(jp);
					jpC.jpC.setEmpty(10, 10, 10, 10).add(jsp);
				}
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
		
		//검색 이미지 클릭 이벤트
		imgSel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				jpC.jpC.removeAll();
				String Select = jtSel.getText().trim();
				int TArist = jcTit.getSelectedIndex();
				int Order = jcOder.getSelectedIndex();
				//아티스트 선택과 정렬을 위한 문자열 생성
				str = "";
				order = "";
				
				if(Select.isBlank()) {
					System.out.println("초기화");
					Msg.error("검색 결과가 없습니다.");
					Blank();
					return;
				}
				
				System.out.println(TArist);
				System.out.println(Order);
				
				//만약 TARITST의 콤보박스 인덱스가 0이라면
				if(TArist == 0) {
					str = "m_name"; //제목
				}
				
				//마찬가지로 0이라면 정렬 오름차순
				if(Order == 0) {
					order = "asc";
				}
				
				//1이라면 가수 선택
				if(TArist == 1) {
					str = "singer";
				}
				
				//만약 1이라면 내리치ㅏ순
				if(Order == 1) {
					order = "desc";
				}
				
				//성인 이미지와 미성년자 이미지 불러오기 코드
				if(Resmodel.age < 19) {
					addImg();
				}else {
					addImg19();					
				}
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
	
	private void addImg19() {
		// TODO Auto-generated method stub
		jpC.jpC.removeAll();
		String Select = jtSel.getText().trim();
		
		ImgModelData allData = db.getImg("SELECT * FROM oldpopsong.music where "+ str +" like '%"+Select+"%'\r\n"
				+ "order by("+str+") "+order+";",9);
		
		Vector<Vector<String>> datas = allData.datas;
		Vector<ImageIcon> icons = allData.icons;
		
		jp = new BasePanel();
		jp.setGridLayout(0, 6, 10, 10);
		for (int i = 0; i < datas.size(); i++) {
			jp.setEmpty(5, 5, 5, 5).add(imgL = new ImageLabel(ss(datas.get(i).get(1)), icons.get(i), 110, 110).setBottom().setCenter());
			if(Integer.parseInt(datas.get(i).get(7)) == 1) {
				 img19 = new ImageLabel(null, "19금", 30, 30);
				 BaseNullPanel baseN = new BaseNullPanel(0, 0, img19, 70, -5, 40, 40, imgL, -5, -10, 130, 130);
				 jp.add(baseN);
			}
			int ii = i;
			imgL.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					Resmodel.data = datas.get(ii);
					Resmodel.icon = icons.get(ii);
					new MusicInfo(preFrame);
				}
			});
		}
		
		jsp = new JScrollPane(jp);
		jpC.jpC.setEmpty(10, 10, 10, 10).add(jsp);
		
		
		repaint();
		validate();
	}
	
	private void addImg() {
		// TODO Auto-generated method stub
		jpC.jpC.removeAll();
		String Select = jtSel.getText().trim();
		
		ImgModelData allData = db.getImg("SELECT * FROM oldpopsong.music where "+ str +" like '%"+Select+"%'\r\n"
				+ "and agelimit = 0 order by("+str+") "+order+";",9);
		
		Vector<Vector<String>> datas = allData.datas;
		Vector<ImageIcon> icons = allData.icons;
		
		jp = new BasePanel();
		jp.setGridLayout(0, 6, 10, 10);
		for (int i = 0; i < datas.size(); i++) {
			jp.setEmpty(5, 5, 5, 5).add(imgL = new ImageLabel(ss(datas.get(i).get(1)), icons.get(i), 110, 110).setBottom().setCenter());
			if(Integer.parseInt(datas.get(i).get(7)) == 1) {
				 img19 = new ImageLabel(null, "19금", 30, 30);
				 BaseNullPanel baseN = new BaseNullPanel(0, 0, img19, 70, -5, 40, 40, imgL, -5, -10, 130, 130);
				 jp.add(baseN);
			}
			int ii = i;
			imgL.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					Resmodel.data = datas.get(ii);
					Resmodel.icon = icons.get(ii);
					new MusicInfo(preFrame);
				}
			});
		}
		
		jsp = new JScrollPane(jp);
		jpC.jpC.setEmpty(10, 10, 10, 10).add(jsp);
		
		
		repaint();
		validate();
	}
	
	//빈칸 제어
	public void Blank() {
		jpC.jpC.removeAll();
		
		ImgModelData allData = db.getImg("SELECT * FROM oldpopsong.music;",9);
		
		Vector<Vector<String>> datas = allData.datas;
		Vector<ImageIcon> icons = allData.icons;
		
		jp = new BasePanel();
		jp.setGridLayout(0, 6, 10, 10);
		for (int i = 0; i < 98; i++) {
			jp.setEmpty(5, 5, 5, 5).add(imgL = new ImageLabel(ss(datas.get(i).get(1)), icons.get(i), 110, 110).setBottom().setCenter());
			if(Integer.parseInt(datas.get(i).get(7)) == 1) {
				 img19 = new ImageLabel(null, "19금", 30, 30);
				 BaseNullPanel baseN = new BaseNullPanel(0, 0, img19, 70, -5, 40, 40, imgL, -5, -10, 130, 130);
				 jp.add(baseN);
			}
			int ii = i;
			imgL.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					Resmodel.data = datas.get(ii);
					Resmodel.icon = icons.get(ii);
					new MusicInfo(preFrame);
				}
			});
		}
		
		jsp = new JScrollPane(jp);
		jpC.jpC.setEmpty(10, 10, 10, 10).add(jsp);
		
		repaint();
		validate();
	}
	
	
	
	public String ss(String s) {
		if(s.length() >= 9) {
			return s.substring(0,9) + "...";
		}else {
			return s;
		}
	}

}
