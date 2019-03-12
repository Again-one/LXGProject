package xin.yohuyotu.HelloWorld.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 数据库操作类主要负责执行数据库操作封装类bo对象
 * 的操作以及释放连接回连接池，并把结果封装回bo对象。
 * @author d
 *
 */
public class DBUtils {
	//释放资源
	public static void realseSource(Connection conn,PreparedStatement pst,ResultSet rs){
		C3p0Utils.close(conn, pst, rs);
	}
	//通过bo释放资源
	public static void realseSource(DBUtils_BO bo){
		if(bo!=null){
			realseSource(bo.conn,bo.pst,bo.rs);
		}
	}
	//查询完成后，还需要提取结果集的信息
	public static void executeQuery(DBUtils_BO bo){
		try{
			bo.rs=bo.pst.executeQuery();
		}catch(SQLException e){
			realseSource(bo);
			System.out.println("SQL语句有错");
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
			System.out.println("SQL语句有错");
			e.printStackTrace();
		}
		 realseSource(conn, pst,null );       
		 return a;
	}
	
}