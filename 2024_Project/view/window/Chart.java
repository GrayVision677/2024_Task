package window;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;

import base.Comp.BaseFrame;
import base.Comp.BaseLabel;

public class Chart extends BaseFrame {

	private JComboBox jcYear;

	public Chart(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.setFrame("통계", 600, 500, preFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDsign() {
		// TODO Auto-generated method stub
		jpT.setFlowCenter().add(new BaseLabel("연령별 TOP 5").setFont("맑은고딕", 35));
		jpC.setFlowRight().add(jcYear = new JComboBox<>());
		jcYear.addItem("10대");
		jcYear.addItem("20대");
		jcYear.addItem("30대");
		jcYear.addItem("40대");
		jcYear.addItem("50대");
		jcYear.addItem("60대");
		jcYear.addItem("70대 이상");
	}

	@Override
	public void setEvent() {
		// TODO Auto-generated method stub
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
