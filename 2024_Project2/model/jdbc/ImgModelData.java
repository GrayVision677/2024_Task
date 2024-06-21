package jdbc;

import java.util.Vector;

import javax.swing.ImageIcon;

public class ImgModelData {
	public Vector<Vector<Object>> datas;
	public Vector<ImageIcon> icons;

	public ImgModelData(Vector<Vector<Object>> datas2, Vector<ImageIcon> icons) {
		this.datas = datas2;
		this.icons = icons;
	}
}
