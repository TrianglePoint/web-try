package net.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.db.DAO;

public class BoardDAO implements DAO{
	Connection con;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	public BoardDAO() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			con = ds.getConnection();
		}catch(Exception e) {
			System.err.println("Failed connect DB: " + e);
			return;
		}
	}
	
	// Get the post list.
	public List<BoardBean> getList() {
		List<BoardBean> beans = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM BOARD");
			
			beans = new ArrayList<BoardBean>();
			while(rs.next()) {
				BoardBean bean = new BoardBean();
				bean.setNum(rs.getInt("NUM"));
				bean.setRead_count(rs.getInt("READ_COUNT"));
				bean.setText(rs.getString("TEXT"));
				bean.setTitle(rs.getString("TITLE"));
				bean.setWrite_date(rs.getDate("WRITE_DATE"));
				bean.setWriter(rs.getString("WRITER"));
				beans.add(bean);
			}
			
			return beans;
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return null;
	}
	
	// Increase the number of post.
	public boolean readcountUp(BoardBean bean) {
		try {
			pstmt = con.prepareStatement("UPDATE BOARD SET READ_COUNT=READ_COUNT+1 WHERE num=?");
			pstmt.setInt(1, bean.getNum());
			
			if(pstmt.executeUpdate() != 0) {
				return true;
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
	}
	
	// Get the post as post number.
	public BoardBean getPost(BoardBean bean) {
		try {
			pstmt = con.prepareStatement("SELECT * FROM BOARD WHERE NUM=?");
			pstmt.setInt(1, bean.getNum());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setRead_count(rs.getInt("READ_COUNT"));
				bean.setText(rs.getString("TEXT"));
				bean.setTitle(rs.getString("TITLE"));
				bean.setWrite_date(rs.getDate("WRITE_DATE"));
				bean.setWriter(rs.getString("WRITER"));
			}
			
			return bean;
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return null;
	}
	
	// Insert the post to DB.
	public boolean insertPost(BoardBean bean) {
		try {
			pstmt = con.prepareStatement("INSERT INTO BOARD VALUES(BOARD_NUM_SEQ.NEXTVAL, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, bean.getRead_count());
			pstmt.setString(2, bean.getText());
			pstmt.setString(3, bean.getTitle());
			pstmt.setDate(4, bean.getWrite_date());
			pstmt.setString(5, bean.getWriter());
					
			if(pstmt.executeUpdate() != 0) {
				return true;
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void close() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
