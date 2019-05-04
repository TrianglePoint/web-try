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
	
	public List<BoardBean> getList() {
		List<BoardBean> beans = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from board");
			
			beans = new ArrayList<BoardBean>();
			while(rs.next()) {
				BoardBean bean = new BoardBean();
				bean.setBoard_num(rs.getInt("board_num"));
				bean.setRead_count(rs.getInt("read_count"));
				bean.setText(rs.getString("text"));
				bean.setTitle(rs.getString("title"));
				bean.setWrite_date(rs.getDate("write_date"));
				bean.setWriter(rs.getString("writer"));
				beans.add(bean);
			}
			
			return beans;
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return null;
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
