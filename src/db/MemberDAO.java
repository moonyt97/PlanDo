package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void getCon(){
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int checkMember(String userName){
		getCon();
		int result = 0;
		try {
			String sql = "SELECT * FROM TBL_MEMBER WHERE MEMBER_NAME=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result=1;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void insertMember(String userName) {
		getCon();
		try {
			String sql ="INSERT INTO TBL_MEMBER VALUES(?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
