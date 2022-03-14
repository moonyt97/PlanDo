package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PlanDAO {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void getCon(){
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource)envctx.lookup("jdbc/pool");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Vector<PlanBean> getPlan(String userName, int startRow, int endRow){
		Vector<PlanBean> v = new Vector<>();
		getCon();
		String sql ="SELECT * FROM (SELECT A.* ,ROWNUM RNUM FROM(SELECT * FROM TBL_LIST WHERE MEMBER_NAME=?)A) WHERE RNUM >= ? AND RNUM<=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				PlanBean bean = new PlanBean();
				bean.setNo(rs.getInt(1));
				bean.setContent(rs.getString(2));
				bean.setDate(rs.getDate(3));
				bean.setUserName(rs.getString(4));
				v.add(bean);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return v;
	}
	public void insertPlan(String userName, String content){
		getCon();
		String sql = "INSERT INTO TBL_LIST VALUES(SEQ_TBL_PLAN.Nextval,?,SYSDATE,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setString(2, userName);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void deletePlan(int no){
		getCon();
		String sql ="DELETE FROM TBL_LIST WHERE LIST_NO=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getAllCount(String userName) {
		int count = 0;
		getCon();
		try {
			String sql = "SELECT COUNT(*) FROM TBL_LIST WHERE MEMBER_NAME =?";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs=pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
}
