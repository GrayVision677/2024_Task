package window;

import javax.swing.JButton;
import javax.swing.JLabel;

import base.Comp.BaseFrame;

public class MainFrame extends BaseFrame{

	private JButton jbLogin;
	private JButton jbLogOut;
	private JButton jbSignin;
	private JButton jbSearch;
	private JButton jbMyPage;
	private JButton jban;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		setFrame("test", 500, 500, preFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jbLogin = new JButton("로그인");
		jbLogOut = new JButton("로그아웃");
		jbSignin = new JButton("회원가입");
		jbSearch = new JButton("통합검색");
		jbMyPage = new JButton("마이페이지");
		jban = new JButton("분석");
		
		
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		
		jbSearch.setEnabled(false);
		jbMyPage.setEnabled(false);
		
		jpTop.addChild();
		jpTop.jpCenter.setFlowCenter().add(new JLabel("SKILL MUSIC(OLD POP SONG)"));
		jpTop.jpBottom.setFlowCenter().add(jbLogin);
		jpTop.jpBottom.add(jbSignin);
		jpTop.jpBottom.add(jbSearch);
		jpTop.jpBottom.add(jbMyPage);
		jpTop.jpBottom.add(jban);
		
		
	}

	@Override
	public void setEvent() {
		// TODO Auto-generated method stub
		jbLogin.addActionListener(e -> {
			jpTop.removeAll();
			jbSearch.setEnabled(true);
			jbMyPage.setEnabled(true);
			jpTop.addChild();
			jpTop.jpCenter.setFlowCenter().add(new JLabel("SKILL MUSIC(OLD POP SONG)"));
			jpTop.jpBottom.setFlowCenter().add(jbLogOut);
			jpTop.jpBottom.add(jbSignin);
			jpTop.jpBottom.add(jbSearch);
			jpTop.jpBottom.add(jbMyPage);
			jpTop.jpBottom.add(jban);
			refresh();
		});
		
	}

}
