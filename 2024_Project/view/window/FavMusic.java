package window;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ResModel.ImgModelData;
import ResModel.Resmodel;
import base.Comp.BaseFrame;
import base.Comp.BaseLabel;
import base.Comp.BasePanel;
import base.Comp.ImageLabel;
import jdbc.DbManager;
import jdbc.Msg;

public class FavMusic extends BaseFrame{

	private JTextField jtSelect;
	private ImageLabel img;
	private ImageLabel imgReset;
	private JButton jbSel;
	private DbManager db;
	private BasePanel jp;
	private ImageLabel[] imgJs  = new ImageLabel[99];
	private JScrollPane jsp;

	public FavMusic(MainFrame preFrame) {
		// TODO Auto-generated constructor stub
		super.setFrame("선호 POP SONG", 707,595, preFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		db = new DbManager();
		
	}  

	@Override
	public void setDsign() {
		// TODO Auto-generated method stub
		//디자인 선호 pop song폐이지 
		jpM.setEmpty(10, 10, 10, 10);
		jpT.addChild();
		jpT.jpC.setFlowCenter().add(new BaseLabel("선호 POP SONG").setCenter().setFont("맑은고딕", 20));
		jpT.jpR.add(img = new ImageLabel(null, "메인", 80, 70));
		
		jpC.addChild();
		jpC.jpT.setFlowLeft().add(new BaseLabel("선택 "));
		jpC.jpT.add(jtSelect = new JTextField(15));
		jpC.jpT.add(imgReset = new ImageLabel(null, "초기화", 30, 30));
		jpC.jpT.add(jbSel = new JButton("선택"));
		
		ImgModelData allData = db.getImg("SELECT * FROM oldpopsong.music;",9);
		
		Vector<Vector<String>> datas = allData.datas;
		Vector<ImageIcon> icons = allData.icons;
		
		//이미지 텍스트 중첩
		Vector<String> list = new Vector<String>();
		
		jtSelect.setEditable(false);
		
		jp = new BasePanel();
		jp.setGridLayout(0, 5, 5, 5);
		for (int i = 0; i < datas.size(); i++) {
			//이미지 배열 로 넣어서 빨간줄 나오게 할수 있게 하기
			jp.setEmpty(10, 0, 0, 0).add(imgJs[i] = new ImageLabel(ss(datas.get(i).get(1)), icons.get(i), 110, 110).setBottom().setCenter());
			int ii = i;
			imgJs[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					imgJs[ii].setRedLines();
				
					//이미지 리스트에 넣어서 중첩 될수 있게 하기
					String num = datas.get(ii).get(0).trim();
					if(list.contains(num)) {
						list.remove(num);
					}else {
						list.add(num);
					}
				
					list.sort(Comparator.naturalOrder());
					jtSelect.setText(String.join(",", list));
				}
			});
		}
		
		jsp = new JScrollPane(jp);
		
		jpC.jpC.setEmpty(10, 0, 10, 0).add(jsp);
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
		imgReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				
				//이미지 리셋
				jpC.jpC.removeAll();
				jtSelect.setText("");
				ImgModelData allData = db.getImg("SELECT * FROM oldpopsong.music;",9);
				
				Vector<Vector<String>> datas = allData.datas;
				Vector<ImageIcon> icons = allData.icons;
				
				Vector<String> list = new Vector<String>();
				jtSelect.setEditable(false);
				
				jp = new BasePanel();
				jp.setGridLayout(0, 5, 5, 5);
				for (int i = 0; i < datas.size(); i++) {
					jp.setEmpty(10, 0, 0, 0).add(imgJs[i] = new ImageLabel(ss(datas.get(i).get(1)), icons.get(i), 110, 110).setBottom().setCenter());
					int ii = i;
					imgJs[i].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub
							super.mouseClicked(e);
							imgJs[ii].setRedLines();
							
							String num = datas.get(ii).get(0).trim();
							
							if(list.contains(num)) {
								list.remove(num);
							}else {
								list.add(num);
							}
						
							list.sort(Comparator.naturalOrder());
							jtSelect.setText(String.join(",", list));
						}
					});
				}
				
				jsp = new JScrollPane(jp);
				
				jpC.jpC.setEmpty(10, 0, 10, 0).add(jsp);
				repaint();
				validate();
			}
		
		});
		
		jbSel.addActionListener(e -> {
			String numData = jtSelect.getText().trim();
			
			if(numData.split(",").length != 4) {
				Msg.error("선호 음원 4개를 선택하세요.");
				return;
			}
			
			Resmodel.numData = numData;
			Close();
			//만약 받은 값이 1아니면 0 아니면 2면
			if(Resmodel.move == 1) {
				Close();
				new SginFrom(preFrame);
			}else if(Resmodel.move == 0) {
				Close();
				new LoginFrame(preFrame);
			}else if(Resmodel.move == 2) {
				Close();
				new GuessInfo(preFrame);
			}
		});
		
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				Close();
				if(Resmodel.move == 1) {
					Close();
					new SginFrom(preFrame);
				}else if(Resmodel.move == 0) {
					Close();
					new LoginFrame(preFrame);
				}else if(Resmodel.move == 2) {
					Close();
					new GuessInfo(preFrame);
				}
			}
		});
	}
	
	public String ss(String s) {
		if(s.length() >= 9) {
			return s.substring(0, 9) + "...";
		}
		return s;
	}

}
