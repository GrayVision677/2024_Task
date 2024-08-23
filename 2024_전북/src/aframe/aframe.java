package aframe;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

//import form.a로그인;
import jdbc.db;
import jdbc.vq;


public class aframe extends JFrame implements WindowListener, ActionListener, MouseListener, KeyListener {

	public static Thread th;
	public static JPanel pc, mp, ep, wp, np, sp, cp, p1, p2, p3, p4, p5,p6,p7,p8,p9, p10, stp,p0, pp;
	public static JLabel jl, sw; //sw : 위치보기
	public static JButton jb; 
	public static JTextField jt, x, y, jpw;
	public static JComboBox com;
	public static JTable jta;
	public static JCheckBox chk;
	public static JScrollPane jsp;
	public static DefaultTableModel dtm;
	public static DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
	public static String n = BorderLayout.NORTH;
	public static String w = BorderLayout.WEST;
	public static String c = BorderLayout.CENTER;
	public static String e = BorderLayout.EAST;
	public static String s = BorderLayout.SOUTH;
	public static String sql;
	public static Color sky = new Color(20, 135, 250);//하늘색
	public static ResultSet rs;
	public static SimpleDateFormat daf = new SimpleDateFormat("yyyy-MM-dd");
	public static DecimalFormat def = new DecimalFormat("#,##0");

	public static JTextField hint() {//회원가입
		return new JTextField() {
			public void paint(Graphics g) {
				super.paint(g);

				if (isFocusable()) {
					g.setColor(Color.blue);
					g.drawLine(0, getHeight(), getWidth(), getHeight());
				}
			}
		};
	}
	
	public void cl(JComponent c, int sz) {
	      c.setBackground(Color.blue);
	      c.setForeground(Color.white);
	      ft(c, 1, sz);
	}
	
	public boolean cdate(String s) {// 물어보고 문자를 datef형식으로 바꾸기
		try {
			if (daf.format(daf.parse(s)).equals(s))
			return true;
		} catch (Exception e) {	
		}
		return false;
	}
	
	public void fs(String f) {
		setTitle(f);

		Image img = java.awt.Toolkit.getDefaultToolkit().createImage("datafiles/logo.png");
		setIconImage(img);

		add(pc = new JPanel(new BorderLayout()));
		pc.add(np = new JPanel(new BorderLayout()), n);
		pc.add(wp = new JPanel(new BorderLayout()), w);
		pc.add(cp = new JPanel(new BorderLayout()), c);
		pc.add(ep = new JPanel(new BorderLayout()), e);
		pc.add(sp = new JPanel(new BorderLayout()), s);

		this.addWindowListener(this);
		cell.setHorizontalAlignment(0);
	}

	public void sz(JComponent c, int x, int y) {
		c.setPreferredSize(new Dimension(x, y));
	}

	public void ft(JComponent c, int x, int y) {
		c.setFont(new Font("맑은 고딕", x, y));
	}

	public void line(JComponent c, Color col) {
		c.setBorder(new LineBorder(col));
	}

	public void emp(JComponent c, int x, int y, int a, int b) {
		c.setBorder(new EmptyBorder(x, y, a, b));
	}

	public void imsg(String c) {
		JOptionPane.showMessageDialog(null, c, "정보", 1);
	}

	public void wmsg(String c) {
		JOptionPane.showMessageDialog(null, c, "경고", 0);
	}
	
	public void bk(JComponent c , Color col) {
	      c.setBackground(col);
	}
	
	 public void fk(JComponent c , Color col) {
	     c.setForeground(col);
	 }
	 
	public void sh() {
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public void shp() {
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public static void jpgup(JLabel c, String data, int x, int y) {
		c.setIcon(new ImageIcon(new ImageIcon("datafiles/" + data + ".jpg").getImage().getScaledInstance(x, y, 4)));
	}

	public static void pngup(JLabel c, String data, int x, int y) {
		c.setIcon(new ImageIcon(new ImageIcon("datafiles/" + data + ".png").getImage().getScaledInstance(x, y, 4)));
	}

	public ImageIcon blobup(InputStream is, int x, int y) {
		try {
			return new ImageIcon(ImageIO.read(is).getScaledInstance(x, y, 4));
		} catch (Exception e) {
		}
		return null;
	}

	public Integer rei(String s) {
		return Integer.parseInt(s);
	}

	public boolean cnum(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
