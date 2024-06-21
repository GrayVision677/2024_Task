package jdbc;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;

import ResModel.ImgModelData;

public class DbManager {
	private static String url = "jdbc:mysql://localhost/?"
	         + "CharacterEncoding=UTF-8&"
	            + "serverTimezone=UTC&"
	            + "allowPublicKeyRetrieval=true&"
	            + "allowLoadLocalInfile=true&"
	            + "allowMultiQueries=true";

	private static String id = "root";
	private static String pw = "1234";
	private static Connection con;
	private PreparedStatement pstmt;
	
	public static Connection getConnetion() throws Exception{
		try {
			con = DriverManager.getConnection(url,id,pw);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}
	

	public DbManager() {
		try {
			con = DriverManager.getConnection(url,id,pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int dbSet(String sql, Object...val) {
		try {
			pstmt = con.prepareStatement(sql);
			
			for (int i = 0; i < val.length; i++) {
				pstmt.setObject(i+1, val[i]);
			}
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public Vector<Vector<Object>> getDb(String sql, Object...val){
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		try {
			pstmt = con.prepareStatement(sql);
			
			for (int i = 0; i < val.length; i++) {
				pstmt.setObject(i+1, val[i]);
			}
			
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
				Vector<Object> row = new Vector<Object>();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					row.add(rs.getObject(i + 1)+ "");
				}
				data.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
		
	}
	
	public ImgModelData getImg(String sql,int ImgIndex ,Object...val){
		Vector<Vector<String>> datas = new Vector<Vector<String>>();
		Vector<ImageIcon> icons = new Vector<ImageIcon>();
		try {
			pstmt = con.prepareStatement(sql);
			
			for (int i = 0; i < val.length; i++) {
				pstmt.setObject(i+1, val[i]);
			}
			
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
				Vector<String> row = new Vector<String>();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					row.add(rs.getObject(i + 1)+ "");
				}
				datas.add(row);
				Blob blob = rs.getBlob(ImgIndex + 1);
					icons.add(new ImageIcon(blob.getBinaryStream().readAllBytes()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ImgModelData(datas, icons);
	}
	
	
	
	

}
