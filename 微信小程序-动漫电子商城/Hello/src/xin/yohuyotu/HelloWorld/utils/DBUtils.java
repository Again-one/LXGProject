package xin.yohuyotu.HelloWorld.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * ���ݿ��������Ҫ����ִ�����ݿ������װ��bo����
 * �Ĳ����Լ��ͷ����ӻ����ӳأ����ѽ����װ��bo����
 * @author d
 *
 */
public class DBUtils {
	//�ͷ���Դ
	public static void realseSource(Connection conn,PreparedStatement pst,ResultSet rs){
		C3p0Utils.close(conn, pst, rs);
	}
	//ͨ��bo�ͷ���Դ
	public static void realseSource(DBUtils_BO bo){
		if(bo!=null){
			realseSource(bo.conn,bo.pst,bo.rs);
		}
	}
	//��ѯ��ɺ󣬻���Ҫ��ȡ���������Ϣ
	public static void executeQuery(DBUtils_BO bo){
		try{
			bo.rs=bo.pst.executeQuery();
		}catch(SQLException e){
			realseSource(bo);
			System.out.println("SQL����д�");
			e.printStackTrace();
		}
	}
	public static int executeUpdate(DBUtils_BO bo){
		Connection conn=bo.conn;
		PreparedStatement pst=bo.pst;
		int a=0;
		try{
			a=pst.executeUpdate();
		}catch(SQLException e){
			realseSource(conn, pst, null);       
			System.out.println("SQL����д�");
			e.printStackTrace();
		}
		 realseSource(conn, pst,null );       
		 return a;
	}
	
}