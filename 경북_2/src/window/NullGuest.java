package window;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JTextField;

import base.Comp.BaseFrame;
import base.Comp.BaseLabel;
import jdbc.Msg;

public class NullGuest extends BaseFrame{

	private JTextField jtpw;
	private JButton jbCom;
private JTextField jtphone;

	public NullGuest() {
		// TODO Auto-generated constructor stub
		super.setFrame("비회원 정보입력", 386,225);
	}

	@Override
	public void Comp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DS() {
		// TODO Auto-generated method stub
		jpM.setEmpty(10, 10, 10, 10);
		jpT.addChild();
		jpT.jpL.add(new BaseLabel(null).setimg("로고", 50, 50));
		jpT.jpC.setFlowLayout(FlowLayout.CENTER).add(new BaseLabel("비회원 정보입력").setFont("HY헤드라인M",25));
		
		jpC.addChild();
		jpC.jpL.setEmpty(0, 0, 0, 20).setGridLayout(2, 1, 5, 5).add(new BaseLabel("전화번호"));
		jpC.jpL.setEmpty(0, 0, 0, 20).add(new BaseLabel("비밀번호"));
		
		jpC.jpC.setGridLayout(2, 1, 5, 5).add(jtphone = new JTextField(15));
		jpC.jpC.setGridLayout(2, 1, 5, 5).add(jtpw = new JTextField(15));
		
		jpB.setGridLayout(1, 1, 10, 10).setEmpty(10, 0, 0, 0).add(jbCom = new JButton("확인"));
	}

	@Override
	public void Event() {
		// TODO Auto-generated method stub
		jbCom.addActionListener(e -> {
			String[] phone = jtphone.getText().split("-");
			String pw = jtpw.getText().trim();
			
			if(jtphone.getText().trim().isBlank() || pw.isBlank()) {
				Msg.error("빈칸이 있습니다.");
				return;
			}
			
			if(!phone[0].equals("010") || phone[1].length() != 4 || phone[2].length() != 4) {
				Msg.error("전화번호 형식을 확인해주세여.");
				return;
			}
		});
	}

}
