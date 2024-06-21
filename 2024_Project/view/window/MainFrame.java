package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ResModel.ImgModelData;
import ResModel.Resmodel;
import base.Comp.BaseFrame;
import base.Comp.BaseLabel;
import base.Comp.BaseNullPanel;
import base.Comp.BasePanel;
import base.Comp.ImageLabel;
import jdbc.DbManager;

public class MainFrame extends BaseFrame {
	
	String[] str = "로그인,회원가입,음원검색,마이페이지,분석".split(",");
	JButton[] jb = new JButton[5];
	private JScrollPane jsp;
	private DbManager db;
	public BasePanel jp;
	private ImageLabel img;
	private ImageLabel imgPanel;
	public BaseFrame preFrame;
	private JLabel jlCir;
	private JPanel jpCir;
	private BaseLabel jlNum;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		setFrame("메인", 673,544, null);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		db = new DbManager();
		
	}

	@Override
	public void setDsign() {
		// TODO Auto-generated method stub
		System.out.println("0");
		
		//디자인
		jpC.addChild();
		jpC.jpC.removeAll();
		jpM.setEmpty(10, 10, 10, 10);
		jpT.setFlowCenter().setEmpty(0, 0, 20, 0).add(new BaseLabel("SKILL MUSIC(OLD POP SONG)").setFont("맑은고딕", 25));
		for (int i = 0; i < str.length; i++) {
			jpC.jpT.setGridLayout(1, 5, 10, 10).setEmpty(10, 0, 0, 10).add(jb[i] = new JButton(str[i]));
			jb[i].setPreferredSize(new Dimension(50,50));
		}
		System.out.println("1");
		
		
		//성인 이미지 데이터 가져오기
		ImgModelData allData = db.getImg("SELECT * FROM oldpopsong.music;",9);
		
		Vector<Vector<String>> datas = allData.datas;
		Vector<ImageIcon> icons = allData.icons;
		
		//미성년자 이미지 데이터 가져오기
		ImgModelData ageData = db.getImg("SELECT * FROM oldpopsong.music where agelimit = 0;",9);
		
		Vector<Vector<String>> agedatas = ageData.datas;
		Vector<ImageIcon> ageicons = ageData.icons;
		
		jp = new BasePanel();
		jp.setGridLayout(0, 5, 5, 5);
		
		System.out.println(Resmodel.age);
		
		//로그인 정보 없을때 제어
		if(Resmodel.login == null) {
			for (int i = 0; i < 98; i++) {
				jp.setEmpty(5, 5, 5, 5).add(img = new ImageLabel(ss(datas.get(i).get(1)), icons.get(i), 110, 110).setBottom());
				if(Integer.parseInt(datas.get(i).get(7)) == 1) {
					imgPanel = new ImageLabel(null, "19금", 30, 30);
					BaseNullPanel jpNull = new BaseNullPanel(0, 0, imgPanel, 70, -7, 40, 40, img, -5, -10, 130, 130);
					jp.add(jpNull);
				}
				int ii = i;
				img.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						super.mouseClicked(e);
						if(Resmodel.login != null){
							Resmodel.data = datas.get(ii);
							Resmodel.icon = icons.get(ii);
							Close();
							new MusicInfo(preFrame);
						}
					}
				});
			}
			
			jsp = new JScrollPane(jp);
			jpC.jpC.setEmpty(10, 10, 10, 10).add(jsp);
		}
		
		//미성년자 이미지 제어
		//로그인에서 받아온 나이 값이 19살 보다 작으면 
		if(Resmodel.age < 19) {
			for (int i = 0; i < agedatas.size(); i++) {
				jp.setEmpty(5, 5, 5, 5).add(img = new ImageLabel(ss(agedatas.get(i).get(1)), ageicons.get(i), 110, 110).setBottom());
				int ii = i;
				img.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						super.mouseClicked(e);
						if(Resmodel.login != null){
							Resmodel.data = datas.get(ii);
							Resmodel.icon = icons.get(ii);
							Close();
							new MusicInfo(preFrame);
						}
					}
				});
				
				jsp = new JScrollPane(jp);
				jpC.jpC.setEmpty(10, 10, 10, 10).add(jsp);
			}
			//성인 제어
		}else if(Resmodel.age > 19){
			for (int i = 0; i < 98; i++) {
				jp.setEmpty(5, 5, 5, 5).add(img = new ImageLabel(ss(datas.get(i).get(1)), icons.get(i), 110, 110).setBottom());
				if(Integer.parseInt(datas.get(i).get(7)) == 1) {
					imgPanel = new ImageLabel(null, "19금", 30, 30);
					BaseNullPanel jpNull = new BaseNullPanel(0, 0, imgPanel, 70, -7, 40, 40, img, -5, -10, 130, 130);
					jp.add(jpNull);
				}
				int ii = i;
				img.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						super.mouseClicked(e);
						if(Resmodel.login != null){
							Resmodel.data = datas.get(ii);
							Resmodel.icon = icons.get(ii);
							Close();
							new MusicInfo(preFrame);
						}
					}
				});
			}
			
			jsp = new JScrollPane(jp);
			jpC.jpC.setEmpty(10, 10, 10, 10).add(jsp);
		}
		
		
		
		//텍스트 필드 입력못하게 만들기
		jb[2].setEnabled(false);
		jb[3].setEnabled(false);
		
		//만약 로그인 정보가 있으면 버튼 변환
		if(Resmodel.login != null) {
			jb[0].setText("로그아웃");
			jb[1].setText("회원정보");
			jb[2].setEnabled(true);
			jb[3].setEnabled(true);
			refrush();
		}
		
		refrush();
	}

	@Override
	public void setEvent() {
		// TODO Auto-generated method stub
		jb[0].addActionListener(e -> {
			//로그인 선택 && 로그아웃 
			if(Resmodel.login != null){
				//로그인 Null값으로 만들기
				Resmodel.login = null;
				jb[0].setText("로그인");
				jb[1].setText("회원가입");
				
				jb[2].setEnabled(false);
				jb[3].setEnabled(false);
			}else {
				//닫고
				Close();
				//move움직이는 값을 보내기 
				//로그인 프레임 값 0
				//회원가입 1
				//회원수정 2
				Resmodel.move = 0;
				new LoginFrame(this);
				refrush();
			}
		});
		
		jb[1].addActionListener(e -> {
			//로그인 값이 있으면  move 값을 2로 설정 후 이동
			if(Resmodel.login != null) {
				Close();
				Resmodel.move  = 2;
				new GuessInfo(this);
			}else {
				//없으면 회원가입 폼으로 이동
				Close();
				Resmodel.move = 1;
				new SginFrom(this);
			}
		});
		
		
		jb[2].addActionListener(e -> {
			Close();
			new MusicSel(this); 
		});
		
		jb[3].addActionListener(e -> {
			Close();
			new Mypage(this);
		});
		
		jb[4].addActionListener(e -> {
			Close();
			new Chart(this);
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(0);
			}
		});
	}
	
	//9글가 제어 
	public String ss(String s) {
		if(s.length() >= 9) {
			return s.substring(0, 9) + "...";
		}
		return s;
	}
	
	//int변환
	public Integer rei(String s) {
		return Integer.parseInt(s);
	}

	
}
